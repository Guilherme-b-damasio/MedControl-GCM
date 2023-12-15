package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Driver;

/**
 *
 * @author guilherme_b_damasio
 */
public class DB {
    
    private static Connection conn = null;

    public static Connection getConnection() {       
         try{
              Properties props = loadProperties();
              String url = props.getProperty("dburl");
              conn = DriverManager.getConnection(url, props);
              System.out.println("Deu certo");
              
            } catch (SQLException e) {
                conn = null;
            }        
           return conn;
           
    }


    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
    
}
