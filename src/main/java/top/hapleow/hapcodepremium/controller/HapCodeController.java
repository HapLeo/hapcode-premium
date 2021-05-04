package top.hapleow.hapcodepremium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String coding(@RequestBody CodingDto dto) {

        generatorService.coding(dto);
        return "SUCCESS";
    }

    @RequestMapping("/codingAll")
    public String codingAll(@RequestBody CodingDto dto) {

        generatorService.codingAll(dto);
        return "SUCCESS";
    }
}
