package top.hapleow.hapcodepremium.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.hapleow.hapcodepremium.enums.DbType;
import top.hapleow.hapcodepremium.model.JavaField;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.mysql.constance.MysqlConst;
import top.hapleow.hapcodepremium.service.IDBService;
import top.hapleow.hapcodepremium.service.IFieldService;
import top.hapleow.hapcodepremium.service.ITableService;
import top.hapleow.hapcodepremium.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FieldServiceImpl implements IFieldService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDBService dbService;


    @Override
    public List<JavaField> listJavaFields(String tableName) {

        List<JavaField> javaFields = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(getFieldListSql(tableName));
        for (Map<String, Object> map : maps) {

            String field = StringUtil.valueOf(map.get("Field"));
            String type = StringUtil.valueOf(map.get("Type"));
            String key = StringUtil.valueOf(map.get("Key"));
            String comment = StringUtil.valueOf(map.get("Comment"));
            Boolean nullable = "NO".equals(StringUtil.valueOf(map.get("Null"))) ? false : true;
            JavaField javaField = new JavaField(field, key, type, comment, nullable);
            javaFields.add(javaField);
        }
        return javaFields;
    }

    public String getFieldListSql(String tableName) {

        DbType dbType = dbService.getDbType();
        switch (dbType) {
            case MYSQL:
                return MysqlConst.LIST_FIELDS_SQL + tableName;
            case MSSQL: // todo
            case ORACLE: // todo
        }
        return MysqlConst.LIST_FIELDS_SQL + tableName;
    }
}
