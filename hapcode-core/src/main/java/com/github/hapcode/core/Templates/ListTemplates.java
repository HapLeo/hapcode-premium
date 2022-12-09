package com.github.hapcode.core.Templates;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/3
 */
public class ListTemplates {


    public static List<String> listTemplates() {

        String templatePath = "templates";
        URL url = ListTemplates.class.getResource("/" + templatePath);
        if (url == null) return Collections.EMPTY_LIST;
        File file = new File(url.getFile());
        List<String> list = new ArrayList<>();
        for (File file1 : FileUtil.loopFiles(file)) {
            if (file1.isFile()) {
                String path = file1.getPath();
                String s = path.split(templatePath)[1];
                list.add(s);
            }
        }
        return list;
    }
}
