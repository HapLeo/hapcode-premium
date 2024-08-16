package com.github.hapcode.core.detective;

import com.github.hapcode.core.util.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wuyulin
 * @description 文件路径自动侦测工具
 * <p>根据标签自动侦测生成文件的位置</p>
 * @date 2022/12/1
 */
public class FilePathAutoDetective {

    /**
     * 黑名单目录
     */
    public static Set<String> execludePaths = new HashSet<>();

    static {
        execludePaths.add(File.separator + "src" + File.separator + "test");
        execludePaths.add(File.separator + "target");
        execludePaths.add(File.separator + ".git");
        execludePaths.add(File.separator + ".idea");
    }

    /**
     * 侦测rootPath路径下包含所有tags的一个目录（src/main下的java目录）
     *
     * @param fileList
     * @param tags
     * @return
     */
    public static File detectPath(List<File> fileList, String... tags) {

        return fileList.stream().filter(item -> match(item, tags)).findFirst().orElse(null);
    }

    /**
     * 侦测rootPath路径下包含所有tags的一个文件（src/main下的java目录）
     *
     * @param rootPath
     * @param tags
     * @return
     */
    public static File detectRealFile(String rootPath, String... tags) {

        List<File> fileList = FileUtil.listRealFile(rootPath);
        return fileList.stream().filter(item -> match(item, tags)).findFirst().orElse(null);
    }

    /**
     * 侦测所有符合要求的文件
     *
     * @param rootPath
     * @param tags
     * @return
     */
    public static List<File> detectRealFileList(String rootPath, String... tags) {

        List<File> fileList = FileUtil.listRealFile(rootPath);
        return fileList.stream().filter(item -> match(item, tags)).collect(Collectors.toList());
    }


    /**
     * 匹配指定文件路径是否包含指定tags的所有字符标签
     *
     * @param file
     * @param tags
     * @return
     */
    public static boolean match(File file, String... tags) {

        for (int i = 0; i < tags.length; i++) {
            if ("xml".equals(tags[i])) {
                tags[i] = "resource";
            }
        }

        String absolutePath = file.getAbsolutePath();
        for (String tag : tags) {
            if (!file.getAbsolutePath().contains(tag)) {
                return false;
            }
        }
        List<String> list = Arrays.asList(tags);
        if (list.contains("i") && list.contains("service") && absolutePath.contains(File.separator + "impl")) {
            return false;
        }
        return true;
    }


    /**
     * 从类路径中截取类的package属性
     *
     * @param file
     * @return
     */
    public static String calClassPackage(File file) {

        String absolutePath = file.getAbsolutePath();
        if (!absolutePath.endsWith(".java")) {
            return null;
        }
        String[] split = file.getAbsolutePath().replace(File.separator, ".").split(".java");
        if (split.length < 2){
            return null;
        }
        String clazzName = split[1];
        if (clazzName.startsWith(".")) {
            clazzName = clazzName.substring(1);
        }

        try {
            return clazzName.substring(0, clazzName.lastIndexOf("."));
        } catch (Exception e) {
            System.out.println(absolutePath);
            e.printStackTrace();
        }
        return null;
    }

}