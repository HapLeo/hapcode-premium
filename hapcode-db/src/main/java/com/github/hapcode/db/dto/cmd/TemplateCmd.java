package com.github.hapcode.db.dto.cmd;

import lombok.Data;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/3
 */
@Data
public class TemplateCmd {

    private String templateName;

    private String[] tags;
}
