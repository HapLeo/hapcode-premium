package top.hapleow.hapcodepremium.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hapleow.hapcodepremium.common.JavaTypeConverter;
import top.hapleow.hapcodepremium.util.StringUtil;

/**
 * Java的统一字段对象模型，所有类型的数据库字段模型都转换成该统一模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaField {

    /**
     * 字段名（数据库字段）
     */
    private String field;

    /**
     * 属性名（Java属性）
     */
    private String propertyName;


    /**
     * 键类型
     */
    private String key;

    /**
     * 数据类型（数据库）
     */
    private String type;

    /**
     * 类型
     */
    private String javaType;

    /**
     * 字段说明
     */
    private String comment;


    /**
     * 是否可空
     */
    private Boolean nullable;

    public JavaField(String field, String key, String type, String comment, Boolean nullable) {
        this.field = field;
        this.key = key;
        this.type = type;
        this.comment = comment;
        this.nullable = nullable;
        this.propertyName = StringUtil.toCamelCase(field);
        this.javaType = JavaTypeConverter.convert(type);
    }
}
