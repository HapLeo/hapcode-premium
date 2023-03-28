package com.github.hapcode.core;

import lombok.Data;

import java.util.Map;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/2
 */
@Data
public class OneTemplateParam {

    /**
     * 模型名称
     */
    private String modelName;


    /**
     * 模板classpath
     */
    private String templateClassPath;

    /**
     * 模版中需要填充的内容
     */
    private Map<String, Object> content;


    /**
     * 标签数组
     */
    private String[] tags;


}
