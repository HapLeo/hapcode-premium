package top.hapleow.hapcodepremium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.hapcodepremium.dto.CodingDTO.CodingDto;
import top.hapleow.hapcodepremium.service.IBeetlGeneratorService;

/**
 * 生成器控制类
 */
@RestController
@RequestMapping("/hapcode")
public class HapCodeController {

    @Autowired
    private IBeetlGeneratorService generatorService;

    @RequestMapping("/coding")
    public String coding(CodingDto dto) {

        return generatorService.coding(dto.getTemplateName(), dto.getTableName());
    }

    @RequestMapping("/codingAll")
    public String codingAll(CodingDto dto) {

        generatorService.codingAll(dto.getTableName());
        return "SUCCESS";
    }
}
