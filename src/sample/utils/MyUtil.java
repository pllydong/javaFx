package sample.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import sample.model.TouristSpot;

import java.util.*;

/**
 * @author guokun
 * @date 2024/6/20 22:34
 */
public class MyUtil {
    public static final String CHECKED = "☑";
    public static final String UNCHECKED = "☐";

    public static String setCheck(String txt, int pos) {
        txt = txt.replaceAll(CHECKED, UNCHECKED);
        int index = txt.indexOf(UNCHECKED);
        for (int i = 0; i < pos; i++) {
            index = txt.indexOf(UNCHECKED, index + UNCHECKED.length());
        }
        return txt.substring(0, index) + CHECKED + txt.substring(index + UNCHECKED.length());
    }

    public static void main(String[] args) {
        System.out.println(setCheck(" ☑  미혼     ☐ 기혼     ☐ 사별     ☐ 이혼", 2));
    }

    /**
     * 生成随机景点安排
     *  1. 第一天安排两个景点
     *  2. 中间每天安排三个景点
     *  3. 最后一天不安排景点
     * @param startDt yyyyMMdd
     * @param endDt yyyyMMdd
     * @return
     */
    public static Map<String, List<TouristSpot>> getRandomTouristMap(String startDt, String endDt) {
        if (startDt.compareTo(endDt) >= 0) {
            throw new RuntimeException("出发日期 小于等于 返航日期！");
        }
        int startDtNum = Integer.parseInt(endDt);
        int endDtNum = Integer.parseInt(startDt);
        int size = startDtNum - endDtNum + 1;
        HashMap<String, List<TouristSpot>> map = new HashMap<>(size);

        List<TouristSpot> touristSpotTmpList = new ArrayList<>(TouristSpot.TOURIST_SPOT_MAP.values());
        // 1. 第一天安排两个景点
        map.put(startDt, createRandomListFromTmpListAndRemove(touristSpotTmpList, 2));
        // 2. 中间每天安排三个景点
        for (int i = startDtNum + 1; i < endDtNum; i++) {
            map.put(StrUtil.fillBefore(String.valueOf(i), '0', 8),
                    createRandomListFromTmpListAndRemove(touristSpotTmpList, 3));
        }
        // 3. 最后一天不安排景点
        map.put(endDt, new ArrayList<>());

        return map;
    }

    /**
     * 选取指定个数个数据且从模板列表剔除
     * @param tmpList
     * @param size
     * @return
     */
    public static <T> List<T> createRandomListFromTmpListAndRemove(List<T> tmpList, int size) {
        if (CollUtil.isEmpty(tmpList) || size > tmpList.size()) {
            throw new IllegalArgumentException("剩余的景点不足够创建！");
        }
        List<T> res = RandomUtil.randomEleList(tmpList, size);
        tmpList.clear();
        tmpList.addAll(CollUtil.disjunction(tmpList, res));
        return res;
    }
}
