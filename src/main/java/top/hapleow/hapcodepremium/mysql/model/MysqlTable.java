package top.hapleow.hapcodepremium.mysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlTable {

    /**
     * 表名(mysql)
     */
    private String name;


    /**
     * 表说明(mysql)
     */
    private String comment;

}
