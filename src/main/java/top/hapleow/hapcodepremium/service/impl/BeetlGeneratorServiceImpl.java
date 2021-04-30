package top.hapleow.hapcodepremium.service.impl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import top.hapleow.hapcodepremium.common.Const;
import top.hapleow.hapcodepremium.common.FileUtil;
import top.hapleow.hapcodepremium.content.AbstractContent;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.service.IBeetlGeneratorService;
import top.hapleow.hapcodepremium.service.IFieldService;
import top.hapleow.hapcodepremium.service.ITableService;
import top.hapleow.hapcodepremium.util.StringUtil;

import java.io.File;
import java.io.IOException;

@Service
public class BeetlGeneratorServiceImpl implements IBeetlGeneratorService {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private ITableService tableService;

    @Autowired
    private IFieldService fieldService;


    @Override
    public void writeToFile(String templateName, String fileName, AbstractContent content) {

        String filePath = content.getFilePath();
        FileUtil.createFile(genContent(templateName, content), fileName, filePath);
    }


    @Override
    public String genContent(String templateName, AbstractContent content) {

        //初始化代码
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();

        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            throw new RuntimeException("Beetl配置不存在");
        }
        cfg.setHtmlTagSupport(false);
        cfg.setPlaceholderStart("@{");
        cfg.setPlaceholderEnd("}");
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        //获取模板
        Template t = gt.getTemplate(Const.tempatePath + templateName);
        t.binding("content", content);

        //渲染结果
        String text = t.render();
        return text;
    }

    @Override
    public String coding(String templateName, String tableName) {

        String preName = templateName.split("\\.")[0];
        if (templateName.contains(".xml.")) {
            preName += "Xml";
        }
        if (templateName.startsWith("I")) {
            preName = preName.replaceFirst("I", "");
        }
        AbstractContent content = null;
        try {
            content = (AbstractContent) applicationContext.getBean(preName + "Content");

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("未找到配置内容：" + preName + "Content");
            return null;
        }

        JavaTable javaTable = tableService.getByTableName(tableName);
        if (javaTable != null) {
            content.setTable(javaTable);
        }

        String bizEnBigName = StringUtil.toCamelCase("_" + tableName);
        String fileName = templateName.replaceFirst("model", bizEnBigName).replace(".btl", "");

        writeToFile(templateName, fileName, content);
        return genContent(templateName, content);
    }

    @Override
    public void codingAll(String tableName) {

        File file = null;
        try {
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + Const.tempatePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File[] files = file.listFiles();
        for (File tempName : files) {
            coding(tempName.getName(), tableName);
        }
    }


}
