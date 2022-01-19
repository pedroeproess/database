package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if(conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
                return conn;
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return null;
    }

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (FileNotFoundException e) {
            throw new DbException(e.getMessage());
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
