package top.hapleow.hapcodepremium.util;

import lombok.Data;

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
    public static String toCamelCase(String field) {

        if (isEmpty(field)) {
            return field;
        }
        String[] fieldLatters = field.split("_");
        StringBuilder result = new StringBuilder();
        for (String fieldLatter : fieldLatters) {

            if (isEmpty(fieldLatter)) {
                continue;
            }
            String latter = fieldLatter.replace(fieldLatter.charAt(0), (char) (fieldLatter.charAt(0) - 32));
            result.append(latter);
        }
        return result.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param field
     * @return
     */
    public static String toCamelCaseWithoutFirst(String field) {

        if (isEmpty(field)) {
            return field;
        }
        String[] fieldLatters = field.split("_");
        if (fieldLatters.length == 0) {
            return field;
        }
        String firsttLatter = fieldLatters[0];
        char c = firsttLatter.charAt(0);
        if (c >= 65 && c < 97) {
            char c1 = (char) (c + 32);
            firsttLatter = firsttLatter.replaceFirst(StringUtil.valueOf(c), StringUtil.valueOf(c1));
        }
        StringBuilder result = new StringBuilder(firsttLatter);
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
