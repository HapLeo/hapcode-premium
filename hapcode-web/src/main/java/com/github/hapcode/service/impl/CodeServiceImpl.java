package com.github.hapcode.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.hapcode.core.CodeGenerator;
import com.github.hapcode.core.Templates.ListTemplates;
import com.github.hapcode.db.dto.cmd.CodeGenCmd;
import com.github.hapcode.db.dto.cmd.TemplateCmd;
import com.github.hapcode.db.model.TableInfo;
import com.github.hapcode.db.service.ITableInfoService;
import com.github.hapcode.service.ICodeGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/6
 */
@Service
public class CodeServiceImpl implements ICodeGenService {

    @Autowired
    private ITableInfoService tableInfoService;

    private CodeGenerator codeGenerator = new CodeGenerator();

    @Override
    public void codGen(CodeGenCmd codeGenCmd) {

        TableInfo table = tableInfoService.listColumns(codeGenCmd.getTableName());
        Map<String, Object> tableContent = new HashMap<>();
        tableContent.put("table", table);
        for (TemplateCmd template : codeGenCmd.getTemplates()) {
            codeGenerator.execute(codeGenCmd.getRootPath(), StrUtil.upperFirst(StrUtil.toCamelCase(codeGenCmd.getTableName())), template.getTemplateName(), tableContent, template.getTags());
        }
    }

    @Override
    public List<String> listTemplates() {
        return ListTemplates.listTemplates();
    }
}
