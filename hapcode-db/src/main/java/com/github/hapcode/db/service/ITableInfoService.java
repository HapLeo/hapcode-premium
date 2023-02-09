package com.github.hapcode.db.service;

import com.github.hapcode.db.model.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/2
 */
public interface ITableInfoService {

    List<Map<String, Object>> listTables();


    TableInfo listColumns(String tableName);


    String getDatabaseName();
}
