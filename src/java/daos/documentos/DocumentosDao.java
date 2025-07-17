/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.documentos;

import acciones.documentos.DocumentosAcciones;
import beans.usuarios.Documento;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import daos.conexion.ConexionDBP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Informatica
 */
public class DocumentosDao {
    public int registraDocumento(Documento doc, Usuario usuario) throws SQLException, IOException {
        int idGenerado = -1;
        String sql = "INSERT INTO padron_profesionales.documentos(nombre, ruta_archivo, link_acceso, tipo_archivo, id_usuario) VALUES (?, ?, ?, ?, ?) RETURNING id_documento";
        try (Connection con = ConexionDBP.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, doc.getNombre());
            pst.setString(2, doc.getRuta_archivo());
            pst.setString(3, doc.getLink_acceso());
            pst.setString(4, doc.getTipo_archivo());
            pst.setInt(5, usuario.getId_usuario());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               idGenerado = rs.getInt("id_documento"); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idGenerado;
    }
    
    
    public List<Documento> encuentraDocumentos() {
        DocumentosAcciones doc_metodos=new DocumentosAcciones();
    HttpSession session = ServletActionContext.getRequest().getSession();
    Usuario userLog = (Usuario) session.getAttribute("usuario");

    // Mapa para guardar solo el documento más reciente por tipo
    Map<String, Documento> documentosMasRecientes = new HashMap<>();
    List<Documento> docsFiltrados = null;
    String sql = "SELECT * FROM padron_profesionales.documentos WHERE id_usuario = ?";
    try (Connection con = ConexionDBP.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, userLog.getId_usuario());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Documento documento = new Documento();
            documento.setId_documento(rs.getInt("id_documento"));
            documento.setNombre(rs.getString("nombre"));
            documento.setRuta_archivo(rs.getString("ruta_archivo"));
            documento.setLink_acceso(rs.getString("link_acceso"));
            documento.setTipo_archivo(rs.getString("tipo_archivo"));
            documento.setId_usuario(rs.getInt("id_usuario"));
            documento.setFecha_agregado(rs.getTimestamp("fecha_agregado")); 
            documento.setFecha_modificado(rs.getTimestamp("fecha_modificado"));
           //documento.setValido((Boolean)rs.getObject("valido"));

            String tipo = documento.getTipo_archivo();

            // Solo conservar el más reciente por tipo
            if (!documentosMasRecientes.containsKey(tipo) || 
                documento.getFecha_agregado().after(documentosMasRecientes.get(tipo).getFecha_agregado())) {
                documentosMasRecientes.put(tipo, documento);
            }
        }
        docsFiltrados=doc_metodos.isInNAS(new ArrayList<>(documentosMasRecientes.values()));
        } catch (Exception e) {
            e.printStackTrace(); // imprime el error para depuración
        }

        //return new ArrayList<>(documentosMasRecientes.values());
        return docsFiltrados;
    }
    
    public List<Documento> encuentraDocumentos(int solicitud) {
    DocumentosAcciones doc_metodos=new DocumentosAcciones();
    List<Documento> docsFiltrados = null;
    // Mapa para guardar solo el documento más reciente por tipo
    Map<String, Documento> documentosMasRecientes = new HashMap<>();
    String sql = "SELECT \n" +
        "    sd.id_solicitud,\n" +
        "    sd.id_documento,\n" +
        "    sd.valido,\n" +
        "    d.nombre,\n" +
        "    d.ruta_archivo,\n" +
        "    d.link_acceso,\n" +
        "    d.tipo_archivo,\n" +
        "    d.id_usuario,\n" +
        "    d.fecha_agregado,\n" +
        "    d.fecha_modificado\n" +
        "FROM padron_profesionales.solicitud_documentos sd\n" +
        "LEFT JOIN padron_profesionales.documentos d \n" +
        "    ON sd.id_documento = d.id_documento\n" +
        "WHERE sd.id_solicitud = ?;";

    try (Connection con = ConexionDBP.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, solicitud);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Documento documento = new Documento();
            documento.setId_documento(rs.getInt("id_documento"));
            documento.setNombre(rs.getString("nombre"));
            documento.setRuta_archivo(rs.getString("ruta_archivo"));
            documento.setLink_acceso(rs.getString("link_acceso"));
            documento.setTipo_archivo(rs.getString("tipo_archivo"));
            documento.setId_usuario(rs.getInt("id_usuario"));
            documento.setFecha_agregado(rs.getTimestamp("fecha_agregado")); 
            documento.setFecha_modificado(rs.getTimestamp("fecha_modificado"));
           documento.setValido((Boolean)rs.getObject("valido"));

            String tipo = documento.getTipo_archivo();

            // Solo conservar el más reciente por tipo
            if (!documentosMasRecientes.containsKey(tipo) || 
                documento.getFecha_agregado().after(documentosMasRecientes.get(tipo).getFecha_agregado())) {
                documentosMasRecientes.put(tipo, documento);
            }
        }
        docsFiltrados=doc_metodos.isInNAS(new ArrayList<>(documentosMasRecientes.values()));
    } catch (Exception e) {
        e.printStackTrace(); // imprime el error para depuración
    }

        //return new ArrayList<>(documentosMasRecientes.values());
        return docsFiltrados;
    }
    
    public boolean ligarDocumentos(int documento, int solicitud){
         String sql = "INSERT INTO padron_profesionales.solicitud_documentos( id_solicitud, id_documento)VALUES (?, ?);";
        try (Connection con = ConexionDBP.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, solicitud);
            pst.setInt(2, documento);
            int filasAfectadas=pst.executeUpdate();
            return filasAfectadas >0;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Documento> encuentraDocumentos(Solicitud solicitud) {
    // Mapa para guardar solo el documento más reciente por tipo
    Map<String, Documento> documentosMasRecientes = new HashMap<>();
    String sql = "SELECT sd.id_solicitud,sd.id_documento,sd.valido,d.nombre,d.ruta_archivo,d.link_acceso,d.tipo_archivo,d.fecha_agregado FROM padron_profesionales.solicitud_documentos sd JOIN padron_profesionales.documentos d ON sd.id_documento = d.id_documento WHERE sd.id_solicitud = ?;";
    try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, solicitud.getId_solicitud());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Documento documento = new Documento();
            documento.setId_documento(rs.getInt("id_documento"));
            documento.setNombre(rs.getString("nombre"));
            documento.setRuta_archivo(rs.getString("ruta_archivo"));
            documento.setLink_acceso(rs.getString("link_acceso"));
            documento.setTipo_archivo(rs.getString("tipo_archivo"));
            //documento.setId_usuario(rs.getInt("id_usuario"));
            documento.setValido((Boolean)rs.getObject("valido"));
            documento.setFecha_agregado(rs.getTimestamp("fecha_agregado")); 
            //documento.setFecha_modificado(rs.getTimestamp("fecha_modificado"));

            String tipo = documento.getTipo_archivo();

            // Solo conservar el más reciente por tipo
            if (!documentosMasRecientes.containsKey(tipo) || 
                documento.getFecha_agregado().after(documentosMasRecientes.get(tipo).getFecha_agregado())) {
                documentosMasRecientes.put(tipo, documento);
            }
        }

    } catch (Exception e) {
        e.printStackTrace(); // imprime el error para depuración
    }

    return new ArrayList<>(documentosMasRecientes.values());
    }
    
    
    public Documento encuentraFotos(Solicitud solicitud) {
    HttpSession session = ServletActionContext.getRequest().getSession();
    Usuario userLog = (Usuario) session.getAttribute("usuario");
     Documento documento = new Documento();
    // Mapa para guardar solo el documento más reciente por tipo
    Map<String, Documento> documentosMasRecientes = new HashMap<>();
    String sql = "SELECT \n" +
    "    sd.id_solicitud,\n" +
    "    d.id_documento,\n" +
    "    d.tipo_archivo,\n" +
    "    d.ruta_archivo,\n" +
    "	d.fecha_agregado\n" +
    "FROM \n" +
    "    padron_profesionales.solicitud_documentos sd\n" +
    "JOIN \n" +
    "    padron_profesionales.documentos d \n" +
    "    ON sd.id_documento = d.id_documento \n" +
    "WHERE \n" +
    "    sd.id_solicitud = ? and d.tipo_archivo='fotosF';";
    try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, solicitud.getId_solicitud());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String tipo = documento.getTipo_archivo();

            // Solo conservar el más reciente por tipo
            if (!documentosMasRecientes.containsKey(tipo) || 
                documento.getFecha_agregado().after(documentosMasRecientes.get(tipo).getFecha_agregado())) {
                documentosMasRecientes.put(tipo, documento);
                Usuario user=new Usuario();
                documento.setId_documento(rs.getInt("id_documento"));
                documento.setTipo_archivo(rs.getString("tipo_archivo"));
                documento.setRuta_archivo(rs.getString("ruta_archivo"));
                documento.setFecha_agregado(rs.getDate("fecha_agregado"));
            }
        }

    } catch (Exception e) {
        e.printStackTrace(); // imprime el error para depuración
    }

    return  documento;
   }
}
