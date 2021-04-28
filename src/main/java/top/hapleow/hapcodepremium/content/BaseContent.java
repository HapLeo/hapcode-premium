package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.util.StringUtil;

import java.util.List;

/**
 * 基础内容
 */
@Data
@Component
@ConfigurationProperties(prefix = "content")
public class BaseContent implements IContent {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 导入类
     */
    private List<String> imports;

    /**
     * 业务中文名
     */
    private String bizChName;

    /**
     * 业务英文名（首字母大写）
     */
    private String bizEnBigName;

    /**
     * 业务英文名（首字母小写）
     */
    private String bizEnName;

    /**
     * 作者
     */
    private String author;

    /**
     * 表
     */
    private JavaTable table;


    public void setTable(JavaTable table) {
        this.table = table;

        String name = table.getName();
        String comment = table.getComment();

        bizEnBigName = StringUtil.toCamelCase(name);
        bizEnName = StringUtil.toCamelCaseWithoutFirst(name);

        this.bizChName = comment;

    }
}
