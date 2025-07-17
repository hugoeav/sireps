/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.domicilios;

import beans.domicilios.CodigoPostal;
import beans.domicilios.Colonia;
import beans.domicilios.Estado;
import beans.domicilios.Localidad;
import beans.domicilios.Municipio;
import beans.usuarios.Domicilio;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import daos.conexion.ConexionDBP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hugoeav
 */
public class DomicilioDao {
    
    
    public boolean registraDomicilio(Domicilio domicilio) throws SQLException, IOException{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        StringBuilder sql = new StringBuilder("INSERT INTO padron_profesionales.domicilios(id_solicitud, nombre, calle, numero_ext, numero_int, id_localidad, id_municipio, id_estado, codigo_postal, id_usuario, tipo_domicilio, predeterminado,");
        if(domicilio.getId_colonia() > 0){
                sql.append("id_colonia)VALUES ( ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            }
            else{
                sql.append("colonia_manual)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            }
        try(Connection con= ConexionDBP.getConnection(); PreparedStatement pst=con.prepareCall(sql.toString())){
            pst.setInt(1, domicilio.getId_solicitud());
            pst.setString(2, domicilio.getNombre());
            pst.setString(3, domicilio.getCalle());
            pst.setString(4, domicilio.getNumero_ext());
            if(domicilio.getNumero_int() != null && domicilio.getNumero_int() != ""){
               pst.setString(5, domicilio.getNumero_int());
            }
            else{
               pst.setString(5, null);
            }
            pst.setInt(6, domicilio.getId_localidad());
            pst.setInt(7, domicilio.getId_municipio());
            pst.setInt(8, domicilio.getId_estado());
            pst.setString(9, domicilio.getCodigo_postal());
            pst.setInt(10,userLog.getId_usuario());
            pst.setString(11, domicilio.getTipo_domicilio());
            pst.setBoolean(12, domicilio.isPredeterminado());
            if(domicilio.getId_colonia() > 0){
                 pst.setInt(13,domicilio.getId_colonia());
            }
            else{
                 pst.setString(13, domicilio.getColonia_manual());
            }
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    
    public List<Colonia> obtener_colonias(CodigoPostal cp) {
        List<Colonia> colonias = new ArrayList<>();
        String sql = "SELECT c.id_colonia,c.colonia,c.tipo_asentamiento,c.id_cp FROM catalogos.colonias c JOIN catalogos.codigos_postales cp ON c.id_cp = cp.id_cp WHERE cp.codigo_postal = ?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cp.getCodigoPostal());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Colonia colonia = new Colonia();
                colonia.setColonia(rs.getString("colonia"));
                colonia.setId_colonia(rs.getInt("id_colonia"));
                colonia.setAsentamiento(rs.getString("tipo_asentamiento"));
                colonia.setId_cp(rs.getInt("id_cp"));
                colonias.add(colonia);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colonias;
    }
    
     public List<Localidad> obtener_localidades(CodigoPostal cp) {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT l.id_localidad,l.clave_localidad,l.localidad,l.id_municipio FROM catalogos.localidades l JOIN catalogos.codigos_postales cp ON l.id_municipio = cp.id_municipio WHERE cp.codigo_postal = ?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cp.getCodigoPostal());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("id_localidad"));
                localidad.setClave_localidad(rs.getString("clave_localidad"));
                localidad.setLocalidad(rs.getString("localidad"));
                localidades.add(localidad);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidades;
    }
     
     public List<Localidad> obtener_localidades(int municipio) {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT id_localidad, clave_localidad, localidad FROM catalogos.localidades where id_municipio=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {            
            ps.setInt(1, municipio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("id_localidad"));
                localidad.setClave_localidad(rs.getString("clave_localidad"));
                localidad.setLocalidad(rs.getString("localidad"));
                localidades.add(localidad);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidades;
    }
    
    
    public CodigoPostal obtener_edo_mun(CodigoPostal cp){
         CodigoPostal cod_post=new CodigoPostal();
          String sql="SELECT cp.id_cp,cp.codigo_postal,cp.id_municipio,m.municipio,m.id_estado,e.nombre_estado FROM catalogos.codigos_postales cp JOIN catalogos.municipios m ON cp.id_municipio = m.id_municipio JOIN catalogos.estados e ON m.id_estado = e.id_estado WHERE cp.codigo_postal = ?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps= con.prepareStatement(sql)) {
            ps.setString(1, cp.getCodigoPostal());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cod_post.setId_cp(rs.getInt("id_cp"));
                cod_post.setCodigoPostal(rs.getString("codigo_postal"));
                cod_post.setId_estado(rs.getInt("id_estado"));
                cod_post.setId_municipio(rs.getInt("id_municipio"));
                cod_post.setEstado(rs.getString("nombre_estado"));
                cod_post.setMunicipio(rs.getString("municipio"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return cod_post;
    }
    
    
    public List<Estado> obtener_estados() {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT * FROM catalogos.estados;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId_estado(rs.getInt("id_estado"));
                estado.setNombre_estado(rs.getString("nombre_estado"));
                System.out.println("estado"+estado.getNombre_estado());
                estados.add(estado);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estados;
    }
    
    public List<Municipio> obtener_municipios(int id_estado) {
        List<Municipio> municipios = new ArrayList<>();
        String sql = "SELECT id_municipio, num_municipio, municipio FROM catalogos.municipios where id_estado=?;";
        try (Connection con = ConexionDBP.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            
            ps.setInt(1, id_estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Municipio municipio = new Municipio();
                municipio.setId_municipio(rs.getInt("id_municipio"));
                municipio.setClave_mun(rs.getString("num_municipio"));
                municipio.setMunicipio(rs.getString("municipio"));
                municipios.add(municipio);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return municipios;
    }
    
    public Domicilio encontrarDomicilio(Solicitud solicitud,String tipo) throws SQLException, IOException{
        Domicilio domicilio= new Domicilio();
        String sql="SELECT d.*,c.colonia,c.tipo_asentamiento,l.localidad,m.municipio,e.nombre_estado,j.numero,j.nombre as juris FROM padron_profesionales.domicilios d LEFT JOIN catalogos.colonias c ON d.id_colonia = c.id_colonia LEFT JOIN catalogos.localidades l ON d.id_localidad = l.id_localidad LEFT JOIN catalogos.municipios m ON d.id_municipio = m.id_municipio LEFT JOIN catalogos.estados e ON d.id_estado = e.id_estado LEFT JOIN catalogos.jurisdicciones j ON m.id_jurisdiccion = j.id_jurisdiccion WHERE d.id_solicitud = ?  AND d.tipo_domicilio = ?;";
        try(Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)){
            pst.setInt(1, solicitud.getId_solicitud());
            pst.setString(2, tipo);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                domicilio.setId_domicilio(rs.getInt("id_domicilio"));
                domicilio.setNombre(rs.getString("nombre"));
                domicilio.setCalle(rs.getString("calle"));
                domicilio.setNumero_ext(rs.getString("numero_ext"));
                domicilio.setNumero_int(rs.getString("numero_int"));
                domicilio.setId_colonia(rs.getInt("id_colonia"));
                domicilio.setColonia_manual(rs.getString("colonia_manual"));
                domicilio.setId_localidad(rs.getInt("id_localidad"));
                domicilio.setId_municipio(rs.getInt("id_municipio"));
                domicilio.setId_estado(rs.getInt("id_estado"));
                domicilio.setCodigo_postal(rs.getString("codigo_postal"));
                domicilio.setTipo_domicilio(rs.getString("tipo_domicilio"));
                domicilio.setId_usuario(rs.getInt("id_usuario"));
                domicilio.setId_solicitud(rs.getInt("id_solicitud"));
                domicilio.setPredeterminado(rs.getBoolean("predeterminado"));
                domicilio.setFecha_agregado(rs.getDate("fecha_agregado"));
                domicilio.setColonia(rs.getString("colonia"));
                domicilio.setTipo_asentamiento(rs.getString("tipo_asentamiento"));
                domicilio.setLocalidad(rs.getString("localidad"));
                domicilio.setMunicipio(rs.getString("municipio"));
                domicilio.setEstado(rs.getString("nombre_estado"));
                domicilio.setNum_juris(rs.getString("numero"));
                domicilio.setJuris(rs.getString("juris"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return domicilio;
    }
    
    
    
    
}
