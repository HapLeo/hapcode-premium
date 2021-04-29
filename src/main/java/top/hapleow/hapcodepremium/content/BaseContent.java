package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基础内容
 */
@Data
@Component
@ConfigurationProperties(prefix = "base-content")
public class BaseContent {

    /**
     * 文件路径
     */
    private String rootPath;

    /**
     * 作者
     */
    private String author;

    /**
     * 包名前缀
     */
    private String namespacePrefix;

}
