package top.hapleow.hapcodepremium.mysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlField {

    /**
     * 列名
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
}
