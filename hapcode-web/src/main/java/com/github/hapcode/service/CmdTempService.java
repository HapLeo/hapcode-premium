package com.github.hapcode.service;

import com.github.hapcode.cmd.CodeGenCmd;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/6
 */
public interface CmdTempService {


    /**
     * 保存入参作为临时条件
     *
     * @param codeGenCmd
     */
    void storeCmdTemp(CodeGenCmd codeGenCmd);


    /**
     * 读取命令模板
     *
     * @param rootPath
     */
    CodeGenCmd readCmdTemp(String rootPath);
}
