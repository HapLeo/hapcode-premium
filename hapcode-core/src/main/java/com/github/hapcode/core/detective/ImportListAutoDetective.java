package com.github.hapcode.core.detective;

import cn.hutool.core.util.ObjectUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author wuyulin
 * @description Import列表自动侦测
 * <p>扫描一个类中的所有引用的类名并自动查找对应的全限定名列表</p>
 * @date 2022/12/1
 */
public class ImportListAutoDetective {


    private static Properties importsTemplate = new Properties();

    /**
     * 获取导入模板的项目表
     *
     * @return
     */
    static {
        InputStream resourceAsStream = ImportListAutoDetective.class.getResourceAsStream("/imports-template.properties");
        if (resourceAsStream != null) {
            try {
                importsTemplate.load(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取文件中的import列表
     *
     * @return
     */
    public static Map<String, String> readFileImportList(File file) {

        Map<String, String> result = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("import ")) {
                    String[] split = line.split(" ");
                    String clazzName = split[1].trim();
                    if (ObjectUtil.isEmpty(clazzName) || clazzName.contains("*")) {
                        continue;
                    }
                    int i = clazzName.lastIndexOf(".");
                    String key = clazzName.substring(i + 1, clazzName.length() - 1);
                    String value = clazzName.trim().substring(0, clazzName.length() - 1);
                    if (ObjectUtil.isNotEmpty(key) && ObjectUtil.isNotEmpty(value)) {
                        result.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 侦测指定路径下所有java文件中的import
     *
     * @param rootPath
     * @return
     */
    public static Map<String, String> readPathFilesImportList(String rootPath) {

        Map<String, String> result = new HashMap<>();

        List<File> fileList = FilePathAutoDetective.detectRealFileList(rootPath, ".java");
        for (File file : fileList) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.contains("target") || absolutePath.contains(".mvn")) {
                continue;
            }
            // 读取每个文件的全限定名
            String clazzName = file.getAbsolutePath().replaceAll(File.separator, ".").split(".java")[1];
            if (clazzName.startsWith(".")) {
                clazzName = clazzName.substring(1);
            }
            result.put(file.getName().split("\\.")[0], clazzName);

            // 读取每个文件中的import列表
            Map<String, String> oneFileImports = readFileImportList(file);
            result.putAll(oneFileImports);
        }
        return result;
    }


    /**
     * 导入类列表
     *
     * @param clazzSet
     * @param fileName
     * @return
     */
    public static Set<String> getImportClasses(Set<String> clazzSet, String rootPath, String fileName) {

        Set<String> result = new HashSet<>();

        if (clazzSet.isEmpty()) {
            return result;
        }

        // 探测根目录下所有类的import列表
        Map<String, String> importList = readPathFilesImportList(rootPath);

        // 去除当前类自身的引用
        if (fileName.endsWith(".java")) {
            String clazzName = fileName.replace(".java", "");
            clazzSet.remove(clazzName);
        }
        //匹配需要导入的类列表
        for (String next : clazzSet) {
            String clazzName = importList.get(next);
            // 如果目标项目中未找到对应依赖，则尝试从生成器的导入模板中寻找
            if (clazzName == null) {
                clazzName = (String) importsTemplate.get(next);
            }
            if (clazzName != null) {
                result.add(clazzName);
            }
        }

        return result;
    }


}
