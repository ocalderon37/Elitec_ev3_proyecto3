package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDBConexion {
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/redsocial?useSSL=false&serverTimezone=UTC";
            String usuario = "root";     // Ajusta con tu usuario de MySQL si es distinto
            String clave = "mysql";       // Ajusta con tu contraseña de MySQL
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}