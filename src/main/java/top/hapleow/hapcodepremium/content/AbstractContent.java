package top.hapleow.hapcodepremium.content;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public abstract class AbstractContent {

    @Autowired
    private IContent baseContent;

}
