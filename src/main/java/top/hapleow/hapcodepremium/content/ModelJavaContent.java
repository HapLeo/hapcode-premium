package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.hapleow.hapcodepremium.model.JavaTable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * API的内容对象
 */
@Data
@Component
@ConfigurationProperties(prefix = "content.model")
public class ModelJavaContent extends AbstractContent {


    private String packageName;

    private List<String> imports;

}
