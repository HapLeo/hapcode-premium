package com.github.hapcode.core;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/2
 */
@Data
public class GenParams {

    /**
     * 项目根目录
     */
    private String rootPath;


    /**
     * 模型名称
     */
    private String modelName;


    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模版中需要填充的内容
     */
    private Map<String, Object> content;


    /**
     * 标签数组
     */
    private String[] tags;


}
