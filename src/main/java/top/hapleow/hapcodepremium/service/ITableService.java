package top.hapleow.hapcodepremium.service;

import org.springframework.stereotype.Service;
import top.hapleow.hapcodepremium.model.JavaTable;

import java.util.List;

@Service
public interface ITableService {

    List<JavaTable> listJavaTables();


    JavaTable getByTableName(String tableName);

}