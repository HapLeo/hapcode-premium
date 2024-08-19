package com.github.hapcode.web;

import com.github.hapcode.cmd.CodeGenCmd;
import com.github.hapcode.service.CmdTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cmdTemp")
public class CmdTempController {

    @Autowired
    private CmdTempService cmdTempService;


    @GetMapping("/readCmdTemp")
    public CodeGenCmd readCmdTemp(@RequestParam String rootPath, @RequestParam String moduleName) {

        return cmdTempService.readCmdTemp(rootPath, moduleName);
    }
}
