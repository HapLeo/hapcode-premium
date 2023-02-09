package com.github.hapcode.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.hapcode.db.model.TableColumn;
import com.github.hapcode.db.model.TableInfo;
import com.github.hapcode.db.service.ITableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wuyulin
 * @description
 * @date 2022/12/2
 */
@Service
public class TableInfoServiceImpl implements ITableInfoService {

    public static final String LIST_TABLES_SQL = "show table status WHERE 1=1";
    public static final String LIST_FIELDS_SQL = "show full fields from %s";
    public static final String TABLE_DETAIL_SQL = "select table_name as name,TABLE_COMMENT as comment from information_schema.`TABLES` where table_schema = ? and table_name = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> listTables() {

        return jdbcTemplate.queryForList(LIST_TABLES_SQL);
    }


    @Override
    public TableInfo listColumns(String tableName) {

        String databaseName = getDatabaseName();
        Map<String, Object> tableBaseInfo = jdbcTemplate.queryForMap(TABLE_DETAIL_SQL, databaseName, tableName);
        TableInfo tableInfo = new TableInfo();
        tableInfo.setName(String.valueOf(tableBaseInfo.get("name")));
        tableInfo.setComment(String.valueOf(tableBaseInfo.get("comment")));

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(String.format(LIST_FIELDS_SQL, tableName));
        CopyOptions copyOptions = CopyOptions.create();
        copyOptions.ignoreCase();
        List<TableColumn> tableColumns = BeanUtil.copyToList(maps, TableColumn.class, copyOptions);
        tableInfo.setFields(tableColumns);
        tableInfo.repair();
        return tableInfo;
    }


    @Override
    public String getDatabaseName() {
        Map<String, Object> dbnameMap = jdbcTemplate.queryForMap("select database() as databaseName");
        return (String) dbnameMap.get("databaseName");
    }
}
