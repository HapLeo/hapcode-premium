package top.hapleow.hapcodepremium.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.hapleow.hapcodepremium.model.JavaField;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.service.IDBService;
import top.hapleow.hapcodepremium.service.IFieldService;
import top.hapleow.hapcodepremium.service.ITableService;
import top.hapleow.hapcodepremium.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TableServiceImpl implements ITableService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDBService dbService;

    @Autowired
    private IFieldService fieldService;


    @Override
    public List<JavaTable> listJavaTables() {

        String tableListSql = dbService.getTableListSql();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(tableListSql);


        List<JavaTable> javaTables = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            javaTables.add(dbService.convertMapToJavaTable(map));
        }
        return javaTables;
    }



    @Override
    public JavaTable getByTableName(String tableName) {

        String tableDetailSql = dbService.getTableDetailSql(tableName);

        //List<JavaTable> javaTables = jdbcTemplate.queryForList(tableDetailSql, JavaTable.class);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(tableDetailSql);

        List<JavaTable> javaTables = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            String name = StringUtil.valueOf(map.get("name"));
            String comment = StringUtil.valueOf(map.get("comment"));

            JavaTable javaTable = new JavaTable(name,comment);
            javaTables.add(javaTable);
        }

        if (javaTables.size() == 0) {
            return null;
        }
        JavaTable javaTable = javaTables.get(0);
        List<JavaField> fieldList = fieldService.listJavaFields(javaTable.getName());
        javaTable.setFields(fieldList);
        return javaTable;
    }
}
