package com.github.hapcode.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/1
 */
public class FileUtil {


    public static List<File> listFile(String rootPath) {

        try {
            return Files.walk(Paths.get(rootPath)).map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 获取根目录下的目录列表
     * 不包含文件
     *
     * @param rootPath
     * @return
     */
    public static List<File> listDirectory(String rootPath) {
        List<File> fileList = listFile(rootPath);
        return fileList.stream().filter(File::isDirectory).collect(Collectors.toList());
    }

    /**
     * 获取根目录下的文件列表
     * 不包含目录
     *
     * @param rootPath
     * @return
     */
    public static List<File> listRealFile(String rootPath) {
        List<File> fileList = listFile(rootPath);
        return fileList.stream().filter(File::isFile).collect(Collectors.toList());
    }

}
