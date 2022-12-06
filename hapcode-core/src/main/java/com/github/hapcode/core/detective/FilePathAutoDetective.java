package com.github.hapcode.core.detective;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuyulin
 * @description 文件路径自动侦测工具
 * <p>根据标签自动侦测生成文件的位置</p>
 * @date 2022/12/1
 */
public class FilePathAutoDetective {

    /**
     * 侦测rootPath路径下包含所有tags的一个目录（src/main下的java目录）
     *
     * @param rootPath
     * @param tags
     * @return
     */
    public File detectPath(String rootPath, String... tags) {

        return FileUtil.loopFiles(rootPath, pathname -> match(pathname, tags)).stream().filter(File::isDirectory).findFirst().orElse(null);
    }

    /**
     * 侦测rootPath路径下包含所有tags的一个文件（src/main下的java目录）
     *
     * @param rootPath
     * @param tags
     * @return
     */
    public File detectRealFile(String rootPath, String... tags) {

        return FileUtil.loopFiles(rootPath, pathname -> match(pathname, tags)).stream().filter(File::isFile).findFirst().orElse(null);
    }

    /**
     * 侦测所有符合要求的文件
     *
     * @param rootPath
     * @param tags
     * @return
     */
    public List<File> detectRealFileList(String rootPath, String... tags) {

        return FileUtil.loopFiles(rootPath, pathname -> match(pathname, tags)).stream().filter(File::isFile).collect(Collectors.toList());
    }


    /**
     * 匹配指定文件路径是否包含指定tags的所有字符标签
     *
     * @param file
     * @param tags
     * @return
     */
    public boolean match(File file, String... tags) {
        for (String tag : tags) {
            if (!file.getAbsolutePath().contains(tag)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 从类路径中截取类的package属性
     *
     * @param file
     * @return
     */
    public String calClassPackage(File file) {

        String absolutePath = file.getAbsolutePath();
        if (!absolutePath.endsWith(".java")) {
            return null;
        }
        String clazzName = absolutePath.replaceAll("\\\\", "\\.").split("\\.java")[1];
        if (clazzName.startsWith(".")) {
            clazzName = clazzName.substring(1);
        }

        return clazzName.substring(0, clazzName.lastIndexOf("."));
    }
}