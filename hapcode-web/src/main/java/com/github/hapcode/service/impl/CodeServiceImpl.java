package com.github.hapcode.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.hapcode.cmd.CodeGenCmd;
import com.github.hapcode.cmd.TemplateCmd;
import com.github.hapcode.core.CodeGenerator;
import com.github.hapcode.core.OneTemplateParam;
import com.github.hapcode.core.Templates.ListTemplates;
import com.github.hapcode.db.model.TableInfo;
import com.github.hapcode.db.service.ITableInfoService;
import com.github.hapcode.service.ICodeGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
            Set<OneTemplateParam> modelTemplates = new HashSet<>();

            for (TemplateCmd template : codeGenCmd.getTemplates()) {

                OneTemplateParam oneTemplateParam = new OneTemplateParam();
                modelTemplates.add(oneTemplateParam);
                oneTemplateParam.setModelName(StrUtil.upperFirst(StrUtil.toCamelCase(tableName)));
                oneTemplateParam.setTemplateName(template.getTemplateName());
                oneTemplateParam.setTags(template.getTags());
                oneTemplateParam.setContent(content);
            }
            codeGenerator.executeOneModelTemplates(codeGenCmd.getRootPath(), modelTemplates);
        }

    }

    @Override
    public List<String> listTemplates() {
        return ListTemplates.listTemplates();
    }
}
