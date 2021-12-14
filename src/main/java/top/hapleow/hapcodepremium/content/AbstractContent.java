package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.util.StringUtil;

import java.io.File;
import java.util.List;

@Data
public abstract class AbstractContent {

    @Autowired
    private BaseContent baseContent;

    /**
     * 文件路径
     */
    private String rootPath;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 文件类型
     */
    private String fileType;


    /**
     * 导入类
     */
    private List<String> imports;

    /**
     * 继承类或接口
     */
    private String extendsType;

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
     * 包名前缀（例如：com.jbs.item,用于组合出model的全限定名）
     */
    private String namespacePrefix;


    /**
     * 表
     */
    private JavaTable table;


    public void setTable(JavaTable table) {
        this.table = table;

        String name = table.getName();
        String comment = table.getComment();

        bizEnBigName = StringUtil.toCamelCaseWithFirstUp(name);
        bizEnName = StringUtil.toCamelCase(name);

        this.bizChName = comment;

    }


    public String getFilePath() {

        if (this.rootPath == null) {
            this.rootPath = baseContent.getRootPath();
        }

        String packagePath = packageName.replace(".", File.separator);

        return rootPath + File.separator + fileType + File.separator + packagePath + File.separator;
    }


    public String getAuthor() {

        return baseContent.getAuthor();
    }

    public void setAuthor(String author) {

        baseContent.setAuthor(author);
    }
}
