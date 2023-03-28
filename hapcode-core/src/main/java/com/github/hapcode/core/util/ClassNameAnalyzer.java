package com.github.hapcode.core.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassNameAnalyzer {


    /**
     * 获取指定字符串中大写字母开头的字符串集合
     * 用于从生成的类内容中搜索所有类名称，便于后续导入
     *
     * @param str
     * @return
     */
    public static Set<String> findClassNames(String str) {

        String regExp = "@|\\{|\\}|\\(|\\)|\\<|\\*|\r|\n|\\>|\\/|\\;|\\,|\\.";
        str = str.replaceAll(regExp, " ");
        String[] l1 = str.split(" ");
        Set<String> shortSet = new HashSet<>(Arrays.asList(l1));
        Set<String> collect = shortSet.stream().filter(ObjectUtil::isNotEmpty).filter(item -> {
            char c = item.charAt(0);
            return c >= 65 && c <= 90;
        }).collect(Collectors.toSet());
        return collect;
    }

}
