package lb.project.lb6_server.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.RowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

@SpringBootApplication
public class Lb6ServerApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication.run(Lb6ServerApplication.class, args);

        Class.forName("driver");
        Connection con = DriverManager.getConnection("", "", "");

        Statement stmt = con.createStatement();
        ResultSet set = stmt.executeQuery("Select * from user");
        while (set.next()) {
            String name = set.getString("name");
        }

        PreparedStatement pstmt = con.prepareStatement("select * from server where id = ?");
        pstmt.setInt(1, 1);
        ResultSet rs = pstmt.executeQuery();
        RowSetProvider.newFactory().createJdbcRowSet();






    }



}
