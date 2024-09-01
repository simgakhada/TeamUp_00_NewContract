package simgakhada.teamup00.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate
{
    private static Connection con;
    public static Connection getConnection()
    {
        Properties props = new Properties();
        try {
            if (con == null) {
                props.load(new FileReader("src/main/resources/config/connection-info.properties"));
                String url = props.getProperty("url");
                String user = props.getProperty("user");
                String password = props.getProperty("password");
                con = DriverManager.getConnection(url, user, password);
            }
        }
        catch (IOException | SQLException e)
        {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static Connection getConnection2()
    {
        Connection con = null;
        Properties prop = new Properties();
        try {

            prop.load(new FileReader("src/main/resources/config/connection-info.properties"));
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            con = DriverManager.getConnection(url,user,password);


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con)
    {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt)
    {
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs)
    {
        try {
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}