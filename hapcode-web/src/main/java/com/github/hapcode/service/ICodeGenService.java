package com.github.hapcode.service;

import com.github.hapcode.db.dto.cmd.CodeGenCmd;

import java.util.List;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/6
 */
public interface ICodeGenService {

    void codGen(CodeGenCmd codeGenCmd);

    List<String> listTemplates();
}
