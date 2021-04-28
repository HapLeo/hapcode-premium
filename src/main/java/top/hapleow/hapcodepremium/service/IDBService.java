package top.hapleow.hapcodepremium.service;

import org.springframework.stereotype.Service;
import top.hapleow.hapcodepremium.enums.DbType;
import top.hapleow.hapcodepremium.model.JavaTable;

import java.util.List;
import java.util.Map;

@Service
public interface IDBService {

    DbType getDbType();

    JavaTable convertMapToJavaTable(Map<String, Object> map);

    String getTableListSql();

    String getTableDetailSql(String tableName);
}