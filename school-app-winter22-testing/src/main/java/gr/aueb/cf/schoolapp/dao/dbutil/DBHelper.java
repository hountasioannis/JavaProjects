package gr.aueb.cf.schoolapp.dao.dbutil;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private DBHelper() {

    }

    public static void eraseData() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            conn = DBUtil.getConnection();
            conn.prepareStatement("SET @@foreign_key_checks = 0").executeUpdate();
            rs = conn.prepareStatement("SELECT TABLE_NAME FROM information_schema.tables WHERE TABLE_SCHEMA = 'tsdb'").executeQuery();

            List<String> tables = mapRSToList(rs);
            for (String table : tables) {
                conn.prepareStatement("DELETE FROM " + table).executeUpdate();
                conn.prepareStatement("ALTER TABLE " + table + " AUTO_INCREMENT = 1").executeUpdate();

            }

            conn.prepareStatement("SET @@foreign_key_checks = 1").executeUpdate();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();

            }

        }

    }
     public static List<String> mapRSToList(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(rs.getString("TABLE_NAME"));
    }
        return list;
     }
}
