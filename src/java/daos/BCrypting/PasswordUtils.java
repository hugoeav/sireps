/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.BCrypting;

import beans.BCrypting.BCrypt;

/**
 *
 * @author Informatica
 */
public class PasswordUtils {
       // Método para encriptar la contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar si una contraseña coincide con el hash almacenado
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
}
