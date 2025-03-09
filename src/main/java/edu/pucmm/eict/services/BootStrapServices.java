package edu.pucmm.eict.services;
//import edu.pucmm.eict.services.DataBaseServices;
import org.h2.tools.Server;

//import edu.pucmm.eict.services.DataBaseServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BootStrapServices {

    private static BootStrapServices instance;
    private static Server server;
    
        private BootStrapServices(){
    
        }
    
        public static BootStrapServices getInstance(){
            if(instance == null){
                instance = new BootStrapServices();
            }
            return instance;
        }
    
        public static void startDB() throws SQLException {
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start();
        String status = Server.createWebServer("-webPort", "8082", "-webAllowOthers").start().getStatus();
        System.out.println("Status Web: "+status);
    }

    public static void stopDB() throws SQLException {
        if(server != null) {
            server.stop();
        }
    }

    /**
     * Método para crear las tablas necesarias
     * @throws SQLException
     */
    public static void init() throws SQLException {
        Connection con = DataBaseServices.getInstance().getConnection();
        Statement statement = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS ESTUDIANTE\n" +
                "(\n" +
                "  MATRICULA INTEGER PRIMARY KEY NOT NULL,\n" +
                "  NOMBRE VARCHAR(100) NOT NULL,\n" +
                "  APELLIDO VARCHAR(100) NOT NULL,\n" +
                "  TELEFONO VARCHAR(25) NOT NULL,\n" +
                "  CARRERA VARCHAR(50) NOT NULL\n" +
                ");";
        statement.execute(sql);
        statement.close();
        con.close();
    }}