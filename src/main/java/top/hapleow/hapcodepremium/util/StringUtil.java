package top.hapleow.hapcodepremium.util;

import lombok.Data;

import java.util.Objects;

@Data
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.replace(" ", "").length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String valueOf(Object obj) {

        if (obj == null) {
            return "";
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        return obj.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param field
     * @return
     */
    public static String toCamelCaseWithFirstUp(String field) {

        String camelCase = toCamelCase(field);

        if (camelCase != null && Objects.equals(camelCase, "")) {
            return camelCase;
        }

        char c = field.charAt(0);
        if (c >= 97 && c <= 122) {
            char c1 = (char) (c - 32);
            camelCase = camelCase.replaceFirst(StringUtil.valueOf(c), StringUtil.valueOf(c1));
        }
        return camelCase;
    }

    /**
     * 下划线转驼峰
     *
     * @param field
     * @return
     */
    public static String toCamelCase(String field) {

        if (isEmpty(field)) {
            return field;
        }

        field = field.toLowerCase();

        String[] fieldLatters = field.split("_");
        if (fieldLatters.length == 0) {
            return field;
        }

        String firstLatter = fieldLatters[0];
        StringBuilder result = new StringBuilder(firstLatter);
        for (int i = 1; i < fieldLatters.length; i++) {
            String fieldLatter = fieldLatters[i];
            if (isEmpty(fieldLatter)) {
                continue;
            }
            String latter = fieldLatter.replace(fieldLatter.charAt(0), (char) (fieldLatter.charAt(0) - 32));
            result.append(latter);
        }
        return result.toString();
    }
}
