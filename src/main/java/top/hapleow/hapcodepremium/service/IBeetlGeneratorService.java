package top.hapleow.hapcodepremium.service;

import top.hapleow.hapcodepremium.content.AbstractContent;
import top.hapleow.hapcodepremium.dto.CodingDTO.CodingDto;

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
     * 生成所有文件
     *
     * @return
     * @param dto
     */
    void codingAll(CodingDto dto);

    /**
     * 根据指定模板列表和数据库表列表生成
     *
     * @param dto
     */
    void coding(CodingDto dto);
}
