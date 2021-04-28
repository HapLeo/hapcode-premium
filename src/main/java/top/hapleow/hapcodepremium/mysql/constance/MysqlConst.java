package top.hapleow.hapcodepremium.mysql.constance;

public class MysqlConst {

    public static final String LIST_TABLES_SQL = "show table status WHERE 1=1";
    public static final String LIST_FIELDS_SQL = "show full fields from ";
    public static final String TABLE_DETAIL_SQL = "select table_name as name,TABLE_COMMENT as comment from information_schema.`TABLES` where table_name = '%s' ";
}
