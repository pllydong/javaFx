package mtyx.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import mtyx.constant.FilePathConstant;

import java.util.Date;
import java.util.Map;

/**
 * @author guokun
 * @date 2024/4/23 1:29
 */
public class UseThisUtils {
    private static final Date DEFAULT_DATE = DateUtil.parseDate("2024-04-25");

    public static Date getCanUseDate() {
        Integer addDays = getTxtMap().values().stream().map(Integer::parseInt).filter(n -> n > 0).reduce(Integer::sum).orElse(0);
        return DateUtil.offsetDay(DEFAULT_DATE, addDays);
    }

    private static Map<String, String> getTxtMap() {
        return KeyValueFileUtils.getAllKeyValuePairs(FilePathConstant.TXT_FILE_PATH);
    }

    private static void saveTxtMap(Map<String, String> txtMap) {
        KeyValueFileUtils.storeAllKeyValuePairs(FilePathConstant.TXT_FILE_PATH, txtMap);
    }
}
