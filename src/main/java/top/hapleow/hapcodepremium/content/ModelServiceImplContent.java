package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * API的内容对象
 */
@Data
@Component
@ConfigurationProperties(prefix = "model-service-impl")
public class ModelServiceImplContent extends AbstractContent {


}
