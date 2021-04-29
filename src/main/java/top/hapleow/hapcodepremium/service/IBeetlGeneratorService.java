package top.hapleow.hapcodepremium.service;

import top.hapleow.hapcodepremium.content.AbstractContent;

/**
 * Beetl生成器服务
 */
public interface IBeetlGeneratorService {

    /**
     * 将内容写入文件中
     *
     * @param templateName
     */
    void writeToFile(String templateName, String fileName, AbstractContent content);

    /**
     * 生成内容
     *
     * @param templateName
     * @param content
     * @return
     */
    String genContent(String templateName, AbstractContent content);


    /**
     * 编辑单个文件
     *
     * @param templateName
     * @param tableName
     * @return
     */
    String coding(String templateName, String tableName);

    /**
     * 生成所有文件
     *
     * @return
     */
    void codingAll(String tableName);

}
