package com.github.hapcode.cmd;

import lombok.Data;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/2
 */
@Data
public class CodeGenCmd {

    private String rootPath;

    private String moduleName;

    private String[] tableNames;

    private TemplateCmd[] templates;
}
