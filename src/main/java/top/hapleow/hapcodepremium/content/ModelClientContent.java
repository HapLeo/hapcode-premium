package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * API的内容对象
 */
@Data
@Component
@ConfigurationProperties(prefix = "model-client")
public class ModelClientContent extends AbstractContent {

    /**
     * feign客户端指定的服务端
     */
    private String feignClientServer;

}