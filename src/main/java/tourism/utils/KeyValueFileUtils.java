package tourism.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guokun
 * @date 2024/4/23 0:57
 */
public class KeyValueFileUtils {
    private static final String SEPARATOR = ": ";

//    /**
//     * 1. 获取文件中指定key值的内容（相同key值取第一个）
//     * @param filePath
//     * @param key
//     * @return
//     */
//    public static String getValueByKey(String filePath, String key) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(FileUtil.file(
//                KeyValueFileUtils.class.getClassLoader().getResource(filePath)
//        )))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(SEPARATOR);
//                if (parts.length == 2 && parts[0].trim().equals(key)) {
//                    return parts[1].trim();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 如果找不到指定的key，则返回null
//        return null;
//    }

    /**
     * 2. 获取文件所有键值对变成Map<String, String>返回
     * @param filePath
     * @return
     */
    public static Map<String, String> getAllKeyValuePairs(String filePath) {
        String originFilePath = filePath;;
        filePath = getFileAbsolutePath(filePath);
        Map<String, String> keyValueMap = new HashMap<>();
        InputStream inputStream = null;
        if (!FileUtil.isFile(filePath)) {
            if (createFile(FileUtil.file(filePath))) {
                inputStream = KeyValueFileUtils.class.getClassLoader().getResourceAsStream(originFilePath);
                FileWriter writer = null;
                try {
                    writer = new FileWriter(FileUtil.file(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line + StrUtil.LF);
                        String[] parts = line.split(SEPARATOR);
                        if (parts.length == 2) {
                            keyValueMap.putIfAbsent(parts[0].trim(), parts[1].trim());
                        }
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return keyValueMap;
            } else {
                throw new RuntimeException("文件创建失败");
            }
        } else {
            inputStream = FileUtil.getInputStream(filePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length == 2) {
                    keyValueMap.putIfAbsent(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return keyValueMap;
    }

    /**
     * 判断文件是否存在，不存在就创建
     * @param file
     */
    public static boolean createFile(File file) {
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File not exists, create it ...");
            //getParentFile() 获取上级目录（包含文件名时无法直接创建目录的）
            if (!file.getParentFile().exists()) {
                System.out.println("not exists");
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static String prefixPath() {
        //获得当前的URL
        URL location = KeyValueFileUtils.class.getProtectionDomain().getCodeSource().getLocation();
        //构建指向当前URL的文件描述符
        return new File(location.getPath()).getParentFile().getAbsolutePath();
    }

    private static String getFileAbsolutePath(String filePath) {
        filePath = filePath.replaceAll("/", "\\\\");
        if (!filePath.startsWith("\\")) {
            filePath = "\\" + filePath;
        }
        return prefixPath() + filePath;
    }

//    /**
//     * 3. 存入一个键值对（存在相同key值则覆盖，若文件中又多个只需要覆盖第一个即可，无视后面的）
//     * @param filePath
//     * @param key
//     * @param value
//     */
//    public static void storeKeyValuePair(String filePath, String key, String value) {
//        Map<String, String> keyValueMap = getAllKeyValuePairs(filePath);
//        keyValueMap.put(key, value);
//
//        write(filePath, keyValueMap);
//    }

    private static void write(String filePath, Map<String, String> keyValueMap) {
        filePath = getFileAbsolutePath(filePath);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
            sb.append(entry.getKey() + SEPARATOR + entry.getValue() + StrUtil.LF);
        }
        FileUtil.writeUtf8String(sb.toString(), FileUtil.file(filePath));
    }

    // 4. 存入一个Map<String,String>直接清空全部key值并存入map中全部的keyvalue
    public static void storeAllKeyValuePairs(String filePath, Map<String, String> newKeyValueMap) {
        write(filePath, newKeyValueMap);
    }

}
