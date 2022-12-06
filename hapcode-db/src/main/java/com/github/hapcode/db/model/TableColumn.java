package com.github.hapcode.db.model;

import cn.hutool.core.util.StrUtil;
import com.github.hapcode.db.JavaTypeConverter;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/2
 */
@Data
public class TableColumn implements Serializable {

    /**
     * 表列名
     */
    private String field;


    /**
     * 键类型
     */
    private String key;

    /**
     * 数据类型
     */
    private String type;

    /**
     * 字段说明
     */
    private String comment;


    /**
     * Java类属性名
     */
    private String propertyName;

    /**
     * 数据库字段对应Java类型
     */
    private String javaType;


    /**
     * 修复空属性
     */
    public void repair() {

        // 通过field转换并赋值到propertyName
        if (field != null) this.propertyName = StrUtil.lowerFirst(StrUtil.toCamelCase(field.toLowerCase()));
        if (type != null) javaType = JavaTypeConverter.convert(type);
    }
}
