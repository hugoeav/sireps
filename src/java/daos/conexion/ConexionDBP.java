package daos.conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDBP {



    public static Connection getConnection() throws IOException, SQLException {     
        // Cargar el archivo de propiedades
        Properties propiedades = new Properties();
         try (InputStream input = ConexionDBP.class.getClassLoader().getResourceAsStream("resources/constantes.properties")) {
            if (input == null) {
                throw new IOException("No se encontró el archivo constantes.properties");
            }
            propiedades.load(input);
        }


        // Obtener los valores de las propiedades
        String servidor = propiedades.getProperty("servidor");
        String baseDatos = propiedades.getProperty("baseDatos");
        String usuario = propiedades.getProperty("username");//"postgres"; 
        String contrasena = propiedades.getProperty("password");// "12345";
        String puerto=propiedades.getProperty("puerto");
        
        String urlConexion = "jdbc:postgresql://" + servidor + ":"+puerto+"/" + baseDatos;
        System.out.println("base: "+ urlConexion);
        System.out.println("serv: "+ servidor);
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado.", e);
        }

        // Establecer la conexión con la base de datos
        return DriverManager.getConnection(urlConexion, usuario, contrasena);
    }
}
