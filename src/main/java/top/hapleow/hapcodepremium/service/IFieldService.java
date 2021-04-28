package top.hapleow.hapcodepremium.service;

import org.springframework.stereotype.Service;
import top.hapleow.hapcodepremium.model.JavaField;
import top.hapleow.hapcodepremium.model.JavaTable;

import java.util.List;

@Service
public interface IFieldService {

    List<JavaField> listJavaFields(String tableName);

}