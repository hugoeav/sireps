/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.usuarios;

import beans.usuarios.Grado;
import beans.usuarios.Registro;
import daos.conexion.ConexionDBP;
import java.sql.Connection;
import beans.usuarios.Usuario;
import daos.BCrypting.PasswordUtils;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.joda.time.LocalDate;

/**
 *
 * @author hugoeav
 */
public class UsuariosDAO {

    
    public boolean validaNuevoUsuario(Usuario usuarioInput){
        String sql = "SELECT  curp FROM padron_profesionales.usuarios where curp=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {
           pst.setString(1, usuarioInput.getCurp());
           ResultSet rs = pst.executeQuery();
           if(rs.next()){
               return true;
           }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean buscaCedula(Grado gradoInput){

        String sql = "SELECT * FROM catalogos.registros_previos where cedula_profesional=? and id_usuario_reclama is null;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {
           pst.setString(1, gradoInput.getCedula());
           ResultSet rs = pst.executeQuery();
            System.out.println("LA CEDULA INGRESADA EN DAO ES"+ gradoInput.getCedula());
           if(rs.next()){
               return cedulaReclamada(gradoInput);
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean registraNuevoUsuario(Usuario usuario) throws SQLException, IOException{
        String sql="INSERT INTO padron_profesionales.usuarios(curp, rfc, nombre, primer_apellido, segundo_apellido, contrasena, correo)VALUES (?, ?, ?, ?, ?, ?, ?);";
        try(Connection con= ConexionDBP.getConnection();PreparedStatement pst=con.prepareCall(sql)){
            pst.setString(1, usuario.getCurp());
            pst.setString(2, usuario.getRfc());
            pst.setString(3, usuario.getNombre());
            pst.setString(4, usuario.getPrimerApellido());
            if(usuario.getSegundoApellido() != null){
                pst.setString(5, usuario.getSegundoApellido());
            }
            else{
                pst.setString(5, null);
            }
            
            pst.setString(6, PasswordUtils.hashPassword(usuario.getContrasena()));
            pst.setString(7, usuario.getCorreo()); 
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        }catch (Exception e) {
        e.printStackTrace();
        return false;
        }
    }
    
    public Usuario inicioSesion(Usuario usuario) throws SQLException, IOException{
        System.out.println("curp"+usuario.getCurp());
        Usuario usuarioLogged= null;
        String sql="SELECT id_usuario, curp, rfc, nombre, primer_apellido, segundo_apellido, contrasena, correo FROM padron_profesionales.usuarios where curp=?;";
         try (Connection con = ConexionDBP.getConnection();PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, usuario.getCurp());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String hashedPassword= rs.getString("contrasena");
                if(PasswordUtils.verifyPassword(usuario.getContrasena(), hashedPassword)){
                    usuarioLogged=new Usuario();
                    usuarioLogged.setId_usuario(rs.getInt("id_usuario"));
                    usuarioLogged.setCurp(rs.getString("curp"));
                    usuarioLogged.setRfc(rs.getString("rfc"));
                    usuarioLogged.setNombre(rs.getString("nombre"));
                    usuarioLogged.setPrimerApellido(rs.getString("primer_apellido"));
                    usuarioLogged.setSegundoApellido(rs.getString("segundo_apellido"));
                    usuarioLogged.setCorreo(rs.getString("correo"));   
                }
            }
         }catch (Exception e) {
            e.printStackTrace();
          }
        return usuarioLogged;
    }
    
    public Usuario infoUsuario() throws SQLException, IOException{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        Usuario usuario=new Usuario();
        String sql="SELECT * FROM padron_profesionales.usuarios where id_usuario=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, userLog.getId_usuario());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setCurp(rs.getString("curp"));
                usuario.setRfc(rs.getString("rfc"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPrimerApellido(rs.getString("primer_apellido"));
                usuario.setSegundoApellido(rs.getString("segundo_apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setTelefonoLab(rs.getString("telefonolab"));
                usuario.setFecha_agregado(rs.getDate("fecha_agregado"));
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
    public boolean actualizaTelefono(Usuario usuario) throws SQLException, IOException{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        String sql="UPDATE padron_profesionales.usuarios SET  telefono=?, telefonolab=? WHERE id_usuario=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, usuario.getTelefono());
            pst.setString(2, usuario.getTelefonoLab());
            pst.setInt(3, userLog.getId_usuario());
            int filasAfectadas=pst.executeUpdate();
            return filasAfectadas>0;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean cedulaReclamada(Grado grado) throws SQLException, IOException{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        LocalDate fechaMod = LocalDate.now();
        Date sqlFechaMod = new Date(fechaMod.toDate().getTime());
        String sql="UPDATE catalogos.registros_previos SET  id_usuario_reclama=?, fecha_modificado=? WHERE cedula_profesional=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, userLog.getId_usuario());
            pst.setDate(2, sqlFechaMod);
            pst.setString(3, grado.getCedula());
            int filasAfectadas=pst.executeUpdate();
            return filasAfectadas>0;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
