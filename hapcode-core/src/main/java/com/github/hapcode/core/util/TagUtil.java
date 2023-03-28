package com.github.hapcode.core.util;

import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.Objects;

/**
 * tag标签工具
 */
public class TagUtil {


    /**
     * 获取模板默认的tags
     *
     * @param templateClassPath
     * @return
     */
    public static String[] getTemplateTags(String templateClassPath) {

        if (templateClassPath == null) return null;

        int i = templateClassPath.lastIndexOf(File.separator);
        if (i > 0) {
            templateClassPath = templateClassPath.substring(i + 1);
        }
        // model模板单独处理
        if (templateClassPath.startsWith("model.")) {
            return new String[]{"model", "java"};
        }
        return StrUtil.toUnderlineCase(templateClassPath).replace(".btl", "").replace("model", "").replace("imodel", "").replace("_", ".").split("\\.");
    }


    /**
     * 从模版中获取最后一级包名
     * eg. model,controller,service,impl,dao,mapper
     *
     * @param templateClassPath
     * @return
     */
    public static String getTemplatePkgTag(String templateClassPath) {

        if (templateClassPath == null) return null;
        int i = templateClassPath.lastIndexOf(File.separator);
        String pkgTag = "";
        if (i > 0) {
            pkgTag = templateClassPath.substring(i + 1);
        }
        pkgTag = pkgTag.split("\\.")[0];

        if (Objects.equals(pkgTag, "model")) {
            return pkgTag;
        }

        String[] split = StrUtil.toUnderlineCase(pkgTag).split("_");
        return split[split.length - 1];
    }


}
