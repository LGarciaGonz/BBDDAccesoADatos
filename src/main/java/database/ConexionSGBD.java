package database;

import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSGBD {

    private static final String URL = "jdbc:mysql://localhost:6606";
    private static final String USUARIO = "root";
    private static final String CLAVE = "admin";

    public static Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("\nConexion OK al Sistema Gestor de BBDD");
        } catch (SQLException e) {
            System.err.println("Error en la conexión");
            e.printStackTrace();
        } catch (InvalidModuleDescriptorException e) {
            System.err.println("Error PAM");
        }

        return conexion;
    }
}
