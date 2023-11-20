package com.github.hapcode.web;

import com.github.hapcode.cmd.CodeGenCmd;
import com.github.hapcode.db.service.ITableInfoService;
import com.github.hapcode.service.CmdTempService;
import com.github.hapcode.service.ICodeGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/6
 */
@RestController
public class CodeGenController {

    @Autowired
    private ITableInfoService tableInfoService;

    @Autowired
    private ICodeGenService codeGenService;

    @Autowired
    private CmdTempService cmdTempService;


    @PostMapping("/codeGen")
    public String codeGen(@RequestBody CodeGenCmd codeGenCmd) {

        codeGenService.codGen(codeGenCmd);
        // 保存参数模板，下一次自动回显
        cmdTempService.storeCmdTemp(codeGenCmd);
        return "SUCCESS";
    }


    @GetMapping("/listTables")
    public List<Map<String, Object>> listTables() {
        List<Map<String, Object>> maps = tableInfoService.listTables();
        return maps;
    }

    @GetMapping("/listTemplates")
    public List<String> listTemplates() {
        return codeGenService.listTemplates();
    }
}
