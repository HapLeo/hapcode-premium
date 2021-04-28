package top.hapleow.hapcodepremium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Java统一的表结构对象模型，所有数据库表模型都转换成该模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaTable implements Serializable {


    /**
     * 表名(mysql)
     */
    private String name;


    /**
     * 表说明(mysql)
     */
    private String comment;

    /**
     * 字段列表
     */
    private transient List<JavaField> fields;

    public JavaTable(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
}
