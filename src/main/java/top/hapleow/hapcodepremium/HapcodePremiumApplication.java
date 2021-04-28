package top.hapleow.hapcodepremium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import top.hapleow.hapcodepremium.model.JavaField;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.service.IFieldService;
import top.hapleow.hapcodepremium.service.ITableService;

import java.util.List;

@SpringBootApplication
@Component
public class HapcodePremiumApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HapcodePremiumApplication.class, args);

        ITableService tableService = context.getBean(ITableService.class);
        IFieldService fieldService = context.getBean(IFieldService.class);

        List<JavaTable> javaTables = tableService.listJavaTables();
        List<JavaField> schedule = fieldService.listJavaFields("item_coupon");
        System.out.println();

        // 已获取数据库表、字段信息

        // 模板引擎


    }

}
