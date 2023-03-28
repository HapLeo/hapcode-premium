package com.github.hapcode.core;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.github.hapcode.core.config.GroupTemplateConfig;
import com.github.hapcode.core.detective.FilePathAutoDetective;
import com.github.hapcode.core.detective.ImportListAutoDetective;
import com.github.hapcode.core.util.ClassNameAnalyzer;
import org.beetl.core.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/1
 */
public class CodeGenerator {

    public static void executeOneModelTemplates(String rootPath, Set<OneTemplateParam> oneTemplateParams) {

        // 执行两次，为了解决类中对本次执行的类的依赖
        // 通过第二次执行时自动探测依赖类，避免复杂的类路径提前组装或与模版耦合的顺序的配置方式，开发简单，
        // 此种操作是以此项目对性能要求极低的前提下做的选择，为了简化逻辑
        for (int i = 0; i < 2; i++) {

            for (OneTemplateParam oneTemplateParam : oneTemplateParams) {
                executeOneTemplate(rootPath, oneTemplateParam);
            }
        }
    }


    /**
     * 执行生成方法
     */
    public static void executeOneTemplate(String rootPath, OneTemplateParam oneTemplateParam) {

        Map<String, Object> content = new HashMap<>(oneTemplateParam.getContent());
        String templateName = oneTemplateParam.getTemplateName();
        String modelName = oneTemplateParam.getModelName();
        String[] tags = oneTemplateParam.getTags();

        String templatePath = "/templates/" + templateName;

        // 从模板拼接文件名
        String fileName = getFileName(modelName, templatePath);

        // 设置模板变量
        content.put("modelName", modelName);
        content.put("modelNameLowerFirst", StrUtil.lowerFirst(modelName));

        // 执行模板引擎，获取生成内容
        String str = render(content, templatePath);
        Set<String> clazzSet = ClassNameAnalyzer.findClassNames(str);

        // 获取需要导入的类全限定名并绑定到模板引擎的变量中
        Set<String> imports = ImportListAutoDetective.getImportClasses(clazzSet, rootPath, fileName);
        content.put("imports", imports);

        // 如果没有指定tags，则分析模板默认的tags
        if (tags == null || tags.length == 0) {
            tags = getTemplateTags(templateName);
        }
        // 自动探测需要生成到的目录
        File file = FilePathAutoDetective.detectPath(rootPath, tags);
        if (file == null)
            throw new RuntimeException(templateName + "未找到合适的文件夹，请指定更多tags或更精确的rootPath以便更精确的匹配目标文件夹！");
        File serviceFile = new File(file.getAbsolutePath() + File.separator + fileName);

        String pkg = FilePathAutoDetective.calClassPackage(serviceFile);
        content.put("packageName", pkg);

        // 重新生成模板内容
        String newContent = render(content, templatePath);

        // 将内容写入类文件中
        writeAndFlush(newContent, serviceFile);
    }

    /**
     * 获取模板默认的tags
     *
     * @param templateName
     * @return
     */
    private static String[] getTemplateTags(String templateName) {

        if (templateName == null) return null;

        int i = templateName.lastIndexOf(File.separator);
        if (i > 0) {
            templateName = templateName.substring(i + 1);
        }
        // model模板单独处理
        if (templateName.startsWith("model.")) {
            return new String[]{"model", "java"};
        }
        return StrUtil.toUnderlineCase(templateName).replace(".btl", "").replace("model", "").replace("imodel", "").replace("_", ".").split("\\.");
    }

    /**
     * 从模板名称获取文件名称
     *
     * @param modelName
     * @param templatePath
     * @return
     */
    private static String getFileName(String modelName, String templatePath) {

        File file = new ClassPathResource(templatePath).getFile();
        if (file == null) {
            throw new RuntimeException("模板文件" + templatePath + "不存在！");
        }
        return file.getName().split(".btl")[0].replace("model", modelName);
    }


    /**
     * 写文件
     *
     * @param str
     * @param serviceFile
     */
    private static void writeAndFlush(String str, File serviceFile) {
        try {
            serviceFile.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(serviceFile)) {
                fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            } finally {
                // todo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行渲染
     *
     * @return
     * @throws IOException
     */
    private static String render(Map<String, Object> content, String fileClassPath) {

        //初始化代码
        Template t = GroupTemplateConfig.groupTemplate().getTemplate(fileClassPath);
        t.binding("content", content);
        return t.render();
    }


}
