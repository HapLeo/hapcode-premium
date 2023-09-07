package com.github.hapcode.core.util;

import com.github.hapcode.core.detective.FilePathAutoDetective;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
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
        fileList = fileList.stream().filter(File::isDirectory).collect(Collectors.toList());
        fileList.sort(Comparator.comparingInt(o -> o.getAbsolutePath().length()));
        fileList = fileList.stream().filter(file -> {
            String absolutePath = file.getAbsolutePath();
            for (String execludePath : FilePathAutoDetective.execludePaths) {
                if (absolutePath.contains(execludePath)) return false;
            }
            return true;
        }).collect(Collectors.toList());
        return fileList;
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
