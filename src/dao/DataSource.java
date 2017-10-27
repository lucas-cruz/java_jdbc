package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String hostname;
    private int port;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    public DataSource() {
        try{
            hostname = "localhost";
            port = 3306;
            database = "crudb";
            username = "root";
            password = "";
            
            String url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Deu certo");
        }            
    catch (SQLException ex){
        System.err.println("erro na conexÃ£o:"+ex.getMessage());
    }
    catch (Exception ex){
        System.err.println("erro geral: "+ex.getMessage());
    }    
   
}    
     public Connection getConnection(){
        return this.connection;
    }
    public void closeConnection(){
        try{
            connection.close();
        }catch (Exception ex){
            System.err.println("erro ao desconectar: "+ex.getMessage());
        }
     }
        
    
}
