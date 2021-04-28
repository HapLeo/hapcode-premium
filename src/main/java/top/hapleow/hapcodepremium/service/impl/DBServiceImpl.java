package top.hapleow.hapcodepremium.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hapleow.hapcodepremium.config.SpringProperties;
import top.hapleow.hapcodepremium.enums.DbType;
import top.hapleow.hapcodepremium.exception.BizException;
import top.hapleow.hapcodepremium.model.JavaTable;
import top.hapleow.hapcodepremium.mysql.constance.MysqlConst;
import top.hapleow.hapcodepremium.service.IDBService;

import java.util.Map;

@Service
public class DBServiceImpl implements IDBService {


    @Autowired
    private SpringProperties springProperties;


    @Override
    public DbType getDbType() {

        if (springProperties.getUrl() == null) {
            throw new BizException("请配置数据库URL");
        }
        if (springProperties.getUrl().contains("mysql")) {
            return DbType.MYSQL;
        }
        return null;
    }

    @Override
    public JavaTable convertMapToJavaTable(Map<String, Object> tableMap) {

        Object name = tableMap.get("Name");
        Object comment = tableMap.get("Comment");
        String nameStr = name == null ? "" : (String) name;
        String commentStr = comment == null ? "" : (String) comment;
        return new JavaTable(nameStr, commentStr);
    }

    @Override
    public String getTableListSql() {

        DbType dbType = getDbType();
        switch (dbType) {
            case MYSQL:
                return MysqlConst.LIST_TABLES_SQL;
            case MSSQL: // todo
            case ORACLE: // todo
        }
        return MysqlConst.LIST_TABLES_SQL;
    }

    @Override
    public String getTableDetailSql(String tableName) {

        DbType dbType = getDbType();
        switch (dbType) {
            case MYSQL:
                return String.format(MysqlConst.TABLE_DETAIL_SQL, tableName);
            case MSSQL: // todo
            case ORACLE: // todo
        }
        return MysqlConst.LIST_TABLES_SQL;
    }

}
