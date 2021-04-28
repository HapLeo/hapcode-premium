package top.hapleow.hapcodepremium.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JavaTypeConverter {

    private static Map<String, String> types = new HashMap<>();

    static {
        types.put("char", "String");
        types.put("varchar", "String");
        types.put("nvarchar", "String");

        types.put("decimal", "Double");
        types.put("double", "Double");

        types.put("tinyint", "Integer");
        types.put("int", "Integer");
        types.put("long", "Long");

        types.put("date", "LocalDate");
        types.put("datetime", "LocalDateTime");

    }


    /**
     * 将数据库的类型转换成Java类型
     *
     * @param type
     * @return
     */
    public static String convert(String type) {

        for (String key : types.keySet()) {
            if (key.contains(type.toLowerCase())) {
                return types.get(key);
            }
        }
        return "String";
    }
}
