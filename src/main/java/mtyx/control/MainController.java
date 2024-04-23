package mtyx.control;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.SneakyThrows;
import mtyx.constant.FilePathConstant;
import mtyx.constant.NumberConstant;
import mtyx.constant.UiConstant;
import mtyx.model.*;
import mtyx.utils.ExecutorUtils;
import mtyx.utils.HttpUtils;
import mtyx.utils.KeyValueFileUtils;
import mtyx.utils.UseThisUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;
import static mtyx.constant.ConfigKeyConstant.*;

public class MainController implements Initializable {

    public DatePicker scheduleDatePicker;
    public TextField runStartTimePicker;
    public TextField runEndTimePicker;
    public Button startButton;
    public TextArea runtimeLogTextArea;
    public TextField productPriceRangeFileField;
    public TextField loginAccountsField;
    public TextField otherNoRunPeriodField;
    public TextField firstNoRunPeriodField;
    public TextField txtField;
    public Button txtButton;
    public TextArea bsidTextArea;
    public TextArea userAgentTextArea;

    private Date scheduleDate;
    private Date runStartTime;
    private Date runEndTime;
    private int otherNoRunPeriod;
    private int firstNoRunPeriod;

    private final Map<String, Region> regionMap = new HashMap<>();

    /**
     * 产品价格map
     * <skuId, Product>
     */
    private final Map<Long, Product> productPriceMap = new HashMap<>();
    /**
     * 账户map
     * <account, LoginAccount>
     */
    private final Map<String, LoginAccount> loginAccountMap = new HashMap<>();

    private final Map<String, ProductV2> needScheduleProductV2Map = new HashMap<>();

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 开始按钮
        startButton.setText(UiConstant.STR_START);
        startButton.setOnAction(event -> {
            switch (startButton.getText()) {
                case UiConstant.STR_START:
                    startButton.setText(UiConstant.STR_END);
                    start();
                    break;
                case UiConstant.STR_END:
                    startButton.setText(UiConstant.STR_START);
                    end();
                    break;
                default:
                    loge("错误的字符串[%s]", startButton.getText());
            }
        });

        // 排期日期默认设置为T+3
        scheduleDatePicker.setValue(LocalDate.now().plusDays(3));

        txtButton.setOnAction(e -> {
            txtButton.setDisable(true);

            Map<String, String> txtMap = getTxtMap();
            txtMap.computeIfPresent(txtField.getText(),
                    (key, value) -> {
                        int days = Integer.parseInt(value);
                        if (days < 0) {
                            days = Math.abs(days);
                            log("成功激活" + days + "天");
                        }
                        return String.valueOf(days);
                    });
            saveTxtMap(txtMap);
            log("当前可以使用程序至 " + DateUtil.format(UseThisUtils.getCanUseDate(), NORM_DATETIME_PATTERN));

            txtButton.setDisable(false);
        });

        loadUiData();
    }

    private void loge(String message, Object... args) {
        loge(new RuntimeException(String.format(message, args)), message, args);
    }

    private void loge(Exception e) {
        loge(e, e.getMessage());
    }

    @SneakyThrows
    private void loge(Exception e, String message, Object... args) {
        log("[ERROR] " + message, args);
        end();
        throw e;
    }

    private synchronized String log(String message, Object... args) {
        String msg = String.format(message, args);
        runtimeLogTextArea.appendText(String.format("[%s] ", DateUtil.now()) + msg + "\n");
        return msg;
    }

    /**
     * 解析时间字符串为数组
     * - 分隔符支持英文冒号 :
     * - 解析为 时:分:秒 三个整数，缺则补充0
     * @param text
     * @return
     */
    private List<Integer> getTimeArr(String text) {
        List<Integer> res = new ArrayList<>();
        try {
            res.addAll(Arrays.stream(text.split(StrUtil.COLON)).map(Integer::parseInt).collect(Collectors.toList()));
        } catch (Exception e) {
            loge(e);
        }
        for (int i = res.size(); i <= 3; i++) {
            res.add(0);
        }
        return res;
    }

    /**
     * 将时间字符串解析为今日具体的时间
     * @param timeText
     * @return
     */
    private Date getNowDate(String timeText) {
        List<Integer> timeArr = getTimeArr(timeText);
        return DateUtil.date()
                .setField(DateField.HOUR_OF_DAY, timeArr.get(0))
                .setField(DateField.MINUTE, timeArr.get(1))
                .setField(DateField.SECOND, timeArr.get(2))
                .setField(DateField.MILLISECOND, 0);
    }

    private Date getNowDate(Date date) {
        DateTime time = DateUtil.date(date);
        return DateUtil.date()
                .setField(DateField.HOUR_OF_DAY, time.getField(DateField.HOUR_OF_DAY))
                .setField(DateField.MINUTE, time.getField(DateField.MINUTE))
                .setField(DateField.SECOND, time.getField(DateField.SECOND))
                .setField(DateField.MILLISECOND, 0);
    }

    /**
     * 初始化登录用户信息
     */
    private void initLoginAccounts() {
//        try (ExcelReader reader = ExcelUtil.getReader(loginAccountsField.getText())) {
//            List<LoginAccount> data = reader.readAll(LoginAccount.class);
//            loginAccountMap.clear();
//            for (LoginAccount account : data) {
//                loginAccountMap.put(account.getAccount(), account);
//            }
//            HttpUtils.resetBsidList(data.stream().map(LoginAccount::getBSID).collect(Collectors.toList()));
//            log("读取[%s]一共%s条用户数据。", loginAccountsField.getText(), loginAccountMap.size());
//        } catch (Exception e) {
//            loge(e);
//        }
        Map<String, String> configMap = getConfigMap();
        String bsid = bsidTextArea.getText().trim();
        configMap.put(BSID, bsid);
        String userAgent = userAgentTextArea.getText().trim();
        configMap.put(USER_AGENT, userAgent);
        saveConfigMap(configMap);

        HttpUtils.initBSID(bsid);
        HttpUtils.initUserAgent(userAgent);
    }

    private Map<String, String> getConfigMap() {
        return KeyValueFileUtils.getAllKeyValuePairs(FilePathConstant.CONFIG_FILE_PATH);
    }

    private void saveConfigMap(Map<String, String> configMap) {
        KeyValueFileUtils.storeAllKeyValuePairs(FilePathConstant.CONFIG_FILE_PATH, configMap);
    }

    /**
     * 初始化产品价格区间（底价）
     * - 不在底价表的产品将不会调价！
     */
    private void initProductPriceRange() {
        String path = productPriceRangeFileField.getText();
        Map<String, String> configMap = getConfigMap();
        configMap.put(PRODUCT_PRICE_EXCEL_PATH, path);
        saveConfigMap(configMap);
        try (ExcelReader reader = ExcelUtil.getReader(path)) {
            List<Product> data = reader.readAll(Product.class);
            productPriceMap.clear();
            for (Product product : data) {
                productPriceMap.put(product.getSkuId(), product);
            }
            log("读取[%s]一共%s条产品底价记录。", path, productPriceMap.size());
        } catch (Exception e) {
            loge(e);
        }
    }

    /**
     * 开始执行
     */
    private void start() {
        startButton.setDisable(true);
        ExecutorUtils.open();
        initData();
        startButton.setDisable(false);
    }

    private void loadUiData() {
        Map<String, String> configMap = getConfigMap();
        loadUiText(configMap, BSID, bsidTextArea);
        loadUiText(configMap, USER_AGENT, userAgentTextArea);
        loadUiText(configMap, RUN_START_TIME, runStartTimePicker);
        loadUiText(configMap, RUN_END_TIME, runEndTimePicker);
        loadUiText(configMap, FIRST_NO_RUN_PERIOD, firstNoRunPeriodField);
        loadUiText(configMap, OTHER_NO_RUN_PERIOD, otherNoRunPeriodField);
        loadUiText(configMap, PRODUCT_PRICE_EXCEL_PATH, productPriceRangeFileField);
    }

    private void loadUiText(Map<String, String> configMap, String key, TextInputControl textInputControl) {
        String value = configMap.get(key);
        if (null != value) {
            textInputControl.setText(value);
        }
    }

    private void initData() {
        doAsync(r -> {
            log(r);

            // 启动执行任务
            doAsync(rr -> {
                log("--执行结束--");
            }, () -> {
                execTask();
                return null;
            });
        }, () -> {
            initProductPriceRange();
            initLoginAccounts();
            initTime();
            initNetData();
            return "初始化数据完成。";
        });
    }

    private Map<String, String> getTxtMap() {
        return KeyValueFileUtils.getAllKeyValuePairs(FilePathConstant.TXT_FILE_PATH);
    }

    private void saveTxtMap(Map<String, String> txtMap) {
        KeyValueFileUtils.storeAllKeyValuePairs(FilePathConstant.TXT_FILE_PATH, txtMap);
    }

    private void execScanTask(List<ProductV2> collect, String msg) {
        CountDownLatch latch = new CountDownLatch(collect.size());
        if (DateUtil.date().after(canUseDate)) {
            Platform.runLater(() -> {
                log("\u7a0b\u5e8f\u5df2\u8fc7\u671f\uff0c\u8bf7\u4f7f\u7528\u6b63\u5f0f\u7248\u672c");
            });
            return;
        }
        collect.forEach(p -> doAsync(null , () -> {
            String activityId = p.getActivityId();
            JSONObject recommendPriceJson = doSelectRecommendPriceByActivityId(activityId);
            String costBaseLine = recommendPriceJson.getStr("costBaseLine");
            String desc = recommendPriceJson.getStr("desc");
            String biddingSort = recommendPriceJson.getStr("biddingSort");
            if (null != costBaseLine) {
                int line = 1;
                try {
                    line = Integer.parseInt(costBaseLine);
                } catch (NumberFormatException e) {
                    loge(e);
                }
                if (line > 1) {
                    ProductV2 productV2 = needScheduleProductV2Map.get(activityId);
                    Platform.runLater(() -> log(String.format("activityId[%s],skuId[%s],skuName:[%s] | %s | %s | %s |",
                            activityId, productV2.getScheduleSkuDTO().getSkuId(),
                            productV2.getScheduleSkuDTO().getSkuName(),
                            "当前售价:" + productV2.getSettlementPrice(),
                            biddingSort, desc)));

                    doUpdateProductPrice(activityId, null, findFirstNumberStr(desc), costBaseLine);
                }
            }
            latch.countDown();
            return null;
        }));
        try {
            latch.await();
            Platform.runLater(() -> log(msg));
        } catch (InterruptedException e) {
            Platform.runLater(() -> loge(e));
        }
    }

    /**
     * 执行任务
     */
    private void execTask() {
        int runTimeCnt = 0;
        long execTime = 0;
        canUseDate = UseThisUtils.getCanUseDate();
        while (UiConstant.STR_END.equals(startButton.getText())) {
            runTimeCnt++;
            if (ExecutorUtils.isClosed()) {
                return;
            }
            long sleepTime = 1000L - execTime;
            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

            long startTime = System.currentTimeMillis();
            execMainBody(runTimeCnt);
            // 超出范围则置空，可能会导致执行顺序有问题，但问题不大。
            if (runTimeCnt > 999999999) {
                runTimeCnt = 0;
            }
            execTime = System.currentTimeMillis() - startTime;
        }
    }

    private Date canUseDate;

    private void execMainBody(int runTimeCnt) {
        DateTime now = DateUtil.date();
        if (now.before(getNowDate(runStartTime)) || now.after(getNowDate(runEndTime))) {
            return;
        }

        boolean isRunFirstScanTask = runTimeCnt % firstNoRunPeriod == 0;
        boolean isRunOtherScanTask = runTimeCnt % otherNoRunPeriod == 0;
        if (isRunFirstScanTask || isRunOtherScanTask) {
            doSelectProducts();
        }
        if (isRunFirstScanTask) {
            List<ProductV2> collect = needScheduleProductV2Map.values().stream().filter(p -> p.getOperationType() == 2)
                    .collect(Collectors.toList());
            execScanTask(collect, String.format("--执行完成 \\上次扫描是第一名的产品 共[%s]个\\ 的扫描任务--", collect.size()));
        }
        if (isRunOtherScanTask) {
            List<ProductV2> collect = needScheduleProductV2Map.values().stream().filter(p -> p.getOperationType() == 1)
                    .collect(Collectors.toList());
            execScanTask(collect, String.format("--执行完成 /上次扫描为其他名次的产品  共[%s]个/ 的扫描任务--", collect.size()));
        }
    }

    /**
     * 初始化网络数据
     */
    private void initNetData() {
        doSelectRegions();
        doSelectProducts();
    }

    /**
     * 查询产品详情
     * @param activityId
     */
    private JSONObject doSelectDetail(String activityId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("activityId", activityId);
        return JSONUtil.parseObj(HttpUtils.sendPost(HttpUtils.getUrl("/api/vss/shepherd/pc/vendor/schedule/detail"),
                null, JSONUtil.toJsonStr(map), null)).getJSONObject("data");
    }

    /**
     * 根据活动id查询推荐的价格
     * /api/vss/shepherd/VendorScheduleSubmitGeneralService/queryRecommendPriceByActivityId
     * 无需降价时：
     * {
     *     "code": 0,
     *     "message": null,
     *     "data": {
     *         "recommendReportPrice": null,
     *         "costBaseLine": null,
     *         "desc": null,
     *         "biddingSort": null
     *     },
     *     "success": true
     * }
     *
     * 需要降价：
     * {
     *     "code": 0,
     *     "message": null,
     *     "data": {
     *         "recommendReportPrice": null,
     *         "costBaseLine": "2",
     *         "desc": "当前报价高于8.04元，很难获得排期！",
     *         "biddingSort": "竞价第2名"
     *     },
     *     "success": true
     * }
     * @param activityId
     * @return
     */
    private JSONObject doSelectRecommendPriceByActivityId(String activityId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("activityId", activityId);
        String jsonStr = HttpUtils.sendPost(
                HttpUtils.getUrl("/api/vss/shepherd/VendorScheduleSubmitGeneralService/queryRecommendPriceByActivityId"),
                null, JSONUtil.toJsonStr(map), null
        );
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        JSONObject data = jsonObject.getJSONObject("data");

        return data;
    }

    private String findFirstNumberStr(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }

        // 定义正则表达式，匹配数字（包含小数）
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");

        // 创建 Matcher 对象，并应用正则表达式
        Matcher matcher = pattern.matcher(str);

        // 查找第一个匹配项
        if (matcher.find()) {
            // 获取匹配到的数字
            String numberStr = matcher.group();

            // 输出结果
            return numberStr;
        } else {
            return null;
        }
    }

    /**
     * 查询网点列表
     */
    private void doSelectRegions() {
        String res = HttpUtils.sendPost(
                HttpUtils.getUrl("/api/vss/shepherd/schedule/VendorSkuScheduleQueryApiService/searchVendorHistoryScheduleRegion"),
                null,
                "{}",
                null
        );
        regionMap.clear();
        for (JSONObject data : JSONUtil.parseArray(JSONUtil.parseObj(res).get("data")).jsonIter()) {
            Region region = new Region();
            BeanUtil.copyProperties(data, region);
            regionMap.put(region.getRegionId(), region);
        }

        Platform.runLater(() -> log(String.format("查询网点列表,共查询到%s条网点数据。", regionMap.size())));
    }

    /**
     * 查询列表
     */
    private void doSelectProducts() {
        needScheduleProductV2Map.clear();
        int totalCount = 0;
        int total = 0;
        int limit = 100;
        int offset = 0;
        long startTime = DateUtil.beginOfDay(scheduleDate).getTime();
        long endTime = DateUtil.endOfDay(scheduleDate).getTime();
        JSONArray saleDate = new JSONArray();
        saleDate.add(startTime);
        saleDate.add(endTime);
        do {
            JSONObject reqJson = JSONUtil.parseObj("{\n" +
                    "  \"paging\": {\n" +
                    "    \"limit\": 100,\n" +
                    "    \"offset\": 0\n" +
                    "  },\n" +
                    "  \"saleDate\": [\n" +
                    "    1713761716402,\n" +
                    "    1714319999999\n" +
                    "  ],\n" +
                    "  \"scheduleStatus\": 10,\n" +
                    "  \"sellType\": 1,\n" +
                    "  \"saleStatus\": null,\n" +
                    "  \"regionIdList\": [],\n" +
                    "  \"skuIdList\": null,\n" +
                    "  \"saleStartTime\": 1713758116402,\n" +
                    "  \"saleEndTime\": 1714316399999,\n" +
                    "  \"skuSaleStatusList\": null,\n" +
                    "  \"sellPositionList\": null,\n" +
                    "  \"scheduleStatusList\": [\n" +
                    "    10\n" +
                    "  ],\n" +
                    "  \"categoryIdList\": [],\n" +
                    "  \"showUnRead\": true\n" +
                    "}");
            reqJson.getJSONArray("regionIdList").addAll(regionMap.keySet());
            JSONObject paging = reqJson.getJSONObject("paging");
            if (paging == null) {
                paging = new JSONObject();
                reqJson.putIfAbsent("paging", paging);
            }
            paging.set("limit", limit);
            paging.set("offset", offset);
            offset += limit;

            reqJson.set("saleDate", saleDate);
            reqJson.set("saleStartTime", startTime);
            reqJson.set("saleEndTime", endTime);


            JSONObject resJson = JSONUtil.parseObj(HttpUtils.sendPost(HttpUtils.getUrl("/api/vss/shepherd/schedule/VendorPCScheduleQueryApiService/searchPCScheduleList/v2"),
                    null, reqJson.toStringPretty(), null));
            JSONObject data = resJson.getJSONObject("data");
            total = data.getInt("total");
            JSONArray list = data.getJSONArray("list");
            /*
            {
            "scheduleDate": 1713888000000,
            "scheduleSkuDTO": {
                "productId": 100263838345137,
                "skuId": 100264114954355,
                "skuName": "齐孚透明防水创口贴 120片/盒",
                "barCodeList": [
                    "6975391170288"
                ],
                "productProductionDate": 1651420800000,
                "skuTagList": [
                    0
                ],
                "skuImg": "https://img.meituan.net/groceryimages/9888b9f404fccb2a2f5f70937520742456066.jpg"
            },
            "regionName": "网店-武汉-WH00001",
            "settlementPrice": "6.29",
            "reportSellPrice": "",
            "sellPrice": "",
            "cutlinePrice": null,
            "maxSellQuantity": 1000,
            "sellPosition": 2,
            "sellType": 1,
            "onlineType": 3,
            "scheduleStatusRejectReason": null,
            "activityId": "1781900110490824756",
            "deliveryStartTime": 1713974400000,
            "deliveryEndTime": 1714060799000,
            "sellStartTime": 1713884400000,
            "sellEndTime": 1713970799999,
            "showConfirm": false,
            "skuSaleStatus": 0,
            "sellPositions": null,
            "approvalFieldList": [
                "purchasePrice"
            ],
            "categoryIds": [
                100003,
                100113,
                230092,
                240543,
                252474
            ],
            "canDoOperationList": [],
            "scheduleStatusRejectRemark": null,
            "scheduleStatusRejectReasonDesc": null,
            "notifyMessageDTOList": null,
            "canEdit": true,
            "canEditFieldList": [
                "maxSellQuantity",
                "sellPosition",
                "productProductionDate",
                "purchasePrice",
                "seckillSettlementPrice",
                "seckillStock"
            ],
            "regionId": 10000823,
            "resourcePositionList": [
                {
                    "type": 3,
                    "stock": 1000,
                    "purchasePrice": "6.28",
                    "reportSellingPrice": null
                }
            ],
            "resourcePositionDisplay": null,
            "canSuspend": null,
            "suspendType": null,
            "suspendDesc": null,
            "canCancel": false,
            "canRevoke": false,
            "revokeDesc": null,
            "saleStatus": null,
            "scheduleStatus": 10,
            "canWithdraw": true,
            "withdrawDesc": null
        }
             */
            for (ProductV2 productV2 : BeanUtil.copyToList(list, ProductV2.class)) {
                Product product = productPriceMap.get(productV2.getScheduleSkuDTO().getSkuId());
                if (product != null && StrUtil.isNotBlank(productV2.getSettlementPrice())
                        && null != product.getLowestPrice()
                        && product.getLowestPrice().compareTo(new BigDecimal(productV2.getSettlementPrice())) < 0) {
                    needScheduleProductV2Map.put(productV2.getActivityId(), productV2);
                }
//                    Platform.runLater(() -> {
//                        log(productV2.getScheduleSkuDTO().getSkuId() + "\t" + productV2.getScheduleSkuDTO().getSkuName() + "\t" + productV2.getSettlementPrice());
//                    });
            }
            totalCount += list.size();
        } while (totalCount < total);

        int finalTotal = total;
        Platform.runLater(() -> log(String.format("查询未排期产品列表，一共有%s条数据，属于底价产品列表sku的产品共%s条。", finalTotal, needScheduleProductV2Map.size())));
    }

    /**
     * 初始化时间
     */
    private void initTime() {
        String runStartTimePickerText = runStartTimePicker.getText();
        String runEndTimePickerText = runEndTimePicker.getText();
        String otherNoRunPeriodFieldText = otherNoRunPeriodField.getText();
        String firstNoRunPeriodFieldText = firstNoRunPeriodField.getText();

        runStartTime = getNowDate(runStartTimePickerText);
        runEndTime = getNowDate(runEndTimePickerText);
        firstNoRunPeriod = Integer.parseInt(firstNoRunPeriodFieldText);
        otherNoRunPeriod = Integer.parseInt(otherNoRunPeriodFieldText);
        Map<String, String> configMap = getConfigMap();
        configMap.put(RUN_END_TIME, runEndTimePickerText);
        configMap.put(RUN_START_TIME, runStartTimePickerText);
        configMap.put(OTHER_NO_RUN_PERIOD, otherNoRunPeriodFieldText);
        configMap.put(FIRST_NO_RUN_PERIOD, firstNoRunPeriodFieldText);
        saveConfigMap(configMap);

        if (runEndTime.compareTo(runStartTime) <= 0) {
            loge("结束时间早于开始时间");
        }
        scheduleDate = DateUtil.date(scheduleDatePicker.getValue());
    }

    /**
     * 修改产品价格
     * @param activityId
     * @param newPrice
     */
    private void doUpdateProductPrice(String activityId, BigDecimal newPrice, String recommendReportPrice, String costBaseLine) {
        ProductV2 productV2 = needScheduleProductV2Map.get(activityId);
        JSONObject detail = doSelectDetail(activityId);

        if (null == productV2 || detail == null) {
            throw new RuntimeException("产品异常！activityId:" + activityId);
        }
        Product product = productPriceMap.get(productV2.getScheduleSkuDTO().getSkuId());

        String settlementPrice = detail.getStr("settlementPrice");
        productV2.setSettlementPrice(settlementPrice);

        BigDecimal price = newPrice;
        if (null == price) {
            BigDecimal oldPrice = new BigDecimal(settlementPrice).setScale(2, RoundingMode.DOWN);
            BigDecimal price001 = new BigDecimal(settlementPrice).subtract(NumberConstant.BIG_DECIMAL_0_01).setScale(2, RoundingMode.DOWN);
            BigDecimal bottomPrice = product.getLowestPrice().setScale(2, RoundingMode.DOWN);
            BigDecimal recommendPrice = null == recommendReportPrice ? null : new BigDecimal(recommendReportPrice).setScale(2, RoundingMode.DOWN);
            if (null == recommendPrice) {
                price = price001;
            } else if (oldPrice.compareTo(bottomPrice) == 0) {
                // 已经是底价了
                productV2.setOnlineType(3);
                return;
            } else if (recommendPrice.compareTo(bottomPrice) <= 0) {
                // 推荐价格低于底价，设置底价
                price = bottomPrice;
            } else {
                price = recommendPrice;
            }
        }
        BigDecimal seckillPrice = price.subtract(NumberConstant.BIG_DECIMAL_0_01).setScale(2, RoundingMode.DOWN);

        JSONArray resourcePositionListArr = detail.getJSONArray("resourcePositionList");
        ResourcePositionListDTO resourcePositionListDTO =
                resourcePositionListArr == null || resourcePositionListArr.isEmpty() ? null :
                        resourcePositionListArr.get(0, ResourcePositionListDTO.class);

        JSONObject reqJson = JSONUtil.parseObj("{\n" +
                "  \"abnormalReason\": {\n" +
                "    \"strategyCode\": \"\"\n" +
                "  },\n" +
                "  \"sellType\": 1,\n" +
                "  \"productProductionDate\": null,\n" +
                "  \"maxSellQuantity\": 1000,\n" +
                "  \"submitType\": 1,\n" +
                "  \"activityId\": \"1780109976741310489\",\n" +
                "  \"sellPosition\": 1,\n" +
                "  \"purchasePrice\": \"6.52\",\n" +
                "  \"promotionExtendField\": {},\n" +
                "  \"extendField\": {},\n" +
                "  \"changeVersion\": 1,\n" +
                "  \"originReportPrice\": \"6.53\",\n" +
                "  \"recommendReportPrice\": null,\n" +
                "  \"costBaseLine\": \"2\",\n" +
                "  \"seckillPriceDTO\": {\n" +
                "    \"originReportSeckPrice\": \"6.52\",\n" +
                "    \"seckPrice\": \"6.51\",\n" +
                "    \"recommendSeckPrice\": \"\"\n" +
                "  },\n" +
                "  \"autoPriceDTO\": {\n" +
                "    \"bottomPrice\": \"\",\n" +
                "    \"autoPrice\": 1\n" +
                "  },\n" +
                "  \"resourcePositionList\": [\n" +
                "    {\n" +
                "      \"type\": 3,\n" +
                "      \"stock\": \"1000\",\n" +
                "      \"purchasePrice\": \"6.51\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        reqJson.set("sellType", detail.get("sellType"));
        reqJson.set("maxSellQuantity", detail.get("maxSellQuantity"));
        reqJson.set("activityId", activityId);
        reqJson.set("sellPosition", detail.get("sellPosition"));
        reqJson.set("purchasePrice", price);
        reqJson.set("changeVersion", detail.get("changeVersion"));
        reqJson.set("originReportPrice", settlementPrice);
        reqJson.set("recommendReportPrice", recommendReportPrice);
        reqJson.set("costBaseLine", costBaseLine);
        if (null == resourcePositionListDTO) {
            reqJson.set("seckillPriceDTO", null);
        } else {
            JSONObject seckillPriceDTO = reqJson.getJSONObject("seckillPriceDTO");
            seckillPriceDTO.set("originReportSeckPrice", resourcePositionListDTO.getPurchasePrice());
            seckillPriceDTO.set("seckPrice", seckillPrice);
            //TODO 秒杀推荐价格


            resourcePositionListArr.getJSONObject(0).set("purchasePrice", seckillPrice);
        }
        reqJson.set("resourcePositionList", resourcePositionListArr);
        // 生产日期
        if (detail.containsKey("productProductionDate")) {
            reqJson.set("productProductionDate", detail.get("productProductionDate"));
        }
        // autoPriceDTO -> autoPrice: 自动价格类型 bottomPrice: 底价 ？ 可以往这个方向走？




        String message = JSONUtil.parseObj(HttpUtils.sendPost(
                HttpUtils.getUrl("/api/vss/shepherd/pc/vendor/modifySchedule"),
                null,
                JSONUtil.toJsonStr(reqJson),
                null
        )).getStr("message");

        String logMsg;

        if (StrUtil.isNotEmpty(message)) {
            // 修改成功
            logMsg = String.format("[修改价格] activityId[%s],skuId[%s],skuName:[%s] | %s | %s | %s |",
                    activityId, productV2.getScheduleSkuDTO().getSkuId(),
                    productV2.getScheduleSkuDTO().getSkuName(),
                    "原售价:" + productV2.getSettlementPrice(),
                    "修改价:" + price.setScale(2, RoundingMode.FLOOR),
                    message);
        } else {
            logMsg = String.format("尝试修改了 activityId[%s],skuId[%s],skuName:[%s] | %s | %s |",
                    activityId, productV2.getScheduleSkuDTO().getSkuId(),
                    productV2.getScheduleSkuDTO().getSkuName(),
                    "原售价:" + productV2.getSettlementPrice(),
                    "尝试修改价:" + price.setScale(2, RoundingMode.FLOOR));
        }
        if (null != logMsg) {
            Platform.runLater(() -> log(logMsg));
        }
    }

    /**
     * 结束执行
     */
    private void end() {
        ExecutorUtils.close();
        startButton.setDisable(false);
    }



    private <T> void doAsync(Consumer<T> uiConsumer, Supplier<T> supplier) {
        ExecutorUtils.getExecutor().execute(() -> {
            try {
                T s = null == supplier ? null : supplier.get();
                if (null != uiConsumer) {
                    Platform.runLater(() -> uiConsumer.accept(s));
                }
            } catch (Exception e) {
                loge(e);
            }
        });
    }

}
