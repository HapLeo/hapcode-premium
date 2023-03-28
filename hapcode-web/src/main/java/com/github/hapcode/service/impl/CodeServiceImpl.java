package com.github.hapcode.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.hapcode.cmd.CodeGenCmd;
import com.github.hapcode.cmd.TemplateCmd;
import com.github.hapcode.core.CodeGenerator;
import com.github.hapcode.core.GenParams;
import com.github.hapcode.core.Templates.ListTemplates;
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

        for (String tableName : codeGenCmd.getTableNames()) {
            TableInfo table = tableInfoService.listColumns(tableName);
            Map<String, Object> content = new HashMap<>();
            content.put("table", table);
            for (TemplateCmd template : codeGenCmd.getTemplates()) {

                GenParams genParams = new GenParams();
                genParams.setRootPath(codeGenCmd.getRootPath());
                genParams.setModelName(StrUtil.upperFirst(StrUtil.toCamelCase(tableName)));
                genParams.setTemplateName(template.getTemplateName());
                genParams.setTags(template.getTags());
                genParams.setContent(content);
                codeGenerator.execute(genParams);
            }
        }

    }

    @Override
    public List<String> listTemplates() {
        return ListTemplates.listTemplates();
    }
}
