package sample.utils;

import java.io.File;

/**
 * @author guokun
 * @date 2024/6/20 22:03
 */
public class MyFileUtil {
    public static void createAllDirectoriesIfNotExist(String filePath) {
        filePath = apendEndSeperator(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent!= null) {
                createAllDirectoriesRecursively(parent);
            }
        }
    }

    public static String apendEndSeperator(String filePath) {
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        return filePath;
    }

    private static void createAllDirectoriesRecursively(File file) {
        if (!file.exists()) {
            file.mkdirs();
        } else {
            File[] subFiles = file.listFiles();
            if (subFiles!= null) {
                for (File subFile : subFiles) {
                    System.out.println(subFile);
                    createAllDirectoriesRecursively(subFile);
                }
            }
        }
    }
}
