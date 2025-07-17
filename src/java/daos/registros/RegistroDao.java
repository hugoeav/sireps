/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.registros;

import beans.usuarios.Registro;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import daos.conexion.ConexionDBP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import org.joda.time.LocalDate;


/**
 *
 * @author Informatica
 */
public class RegistroDao {
    public Registro obtenerInfoReg(Solicitud solicitud) throws SQLException, IOException{
         Registro registro=new Registro();
         String sql="SELECT\n" +
"  r.id_solicitud,\n" +
"  r.numero_registro,\n" +
"  u.id_usuario,\n" +
"  u.nombre,\n" +
"  u.primer_apellido,\n" +
"  u.segundo_apellido,\n" +
"  u.curp,\n" +
"  d.calle,\n" +
"  d.numero_ext,\n" +
"  d.numero_int,\n" +
"  COALESCE(c.colonia, d.colonia_manual) AS colonia,\n" +
"  l.localidad AS localidad,\n" +
"  m.municipio AS municipio,\n" +
"  e.nombre_estado AS estado,\n" +
"  d.codigo_postal,\n" +
"\n" +
"  CONCAT(u.nombre, ' ', u.primer_apellido, ' ', u.segundo_apellido) AS nombre_completo,\n" +
"  CONCAT(d.calle, ' No.', d.numero_ext,\n" +
"         CASE WHEN d.numero_int IS NOT NULL AND d.numero_int <> ''\n" +
"              THEN ' Int. ' || d.numero_int ELSE '' END) AS calle_completa,\n" +
"  CONCAT(\n" +
"    COALESCE(c.tipo_asentamiento || ' ', ''),\n" +
"    COALESCE(c.colonia, d.colonia_manual),\n" +
"    ', C.P ', d.codigo_postal\n" +
"  ) AS colonia_completa,\n" +
"  CONCAT(m.municipio, ', ', e.nombre_estado) AS ciudad_completa,\n" +
"\n" +
"  -- Agregamos los grados y cédulas usando subconsultas correlacionadas\n" +
"  (\n" +
"    SELECT STRING_AGG(g.nombre, E'\\n' ORDER BY g.id_grado)\n" +
"    FROM padron_profesionales.grados g\n" +
"    WHERE g.id_usuario = u.id_usuario\n" +
"  ) AS grados,\n" +
"\n" +
"  (\n" +
"    SELECT STRING_AGG(g.cedula, E'\\n' ORDER BY g.id_grado)\n" +
"    FROM padron_profesionales.grados g\n" +
"    WHERE g.id_usuario = u.id_usuario\n" +
"  ) AS cedulas\n" +
"\n" +
"FROM padron_profesionales.registros r\n" +
"JOIN padron_profesionales.usuarios u ON u.id_usuario = r.id_usuario\n" +
"JOIN padron_profesionales.domicilios d ON d.id_usuario = u.id_usuario AND r.id_solicitud = d.id_solicitud AND d.predeterminado = true\n" +
"LEFT JOIN catalogos.colonias c ON c.id_colonia = d.id_colonia\n" +
"LEFT JOIN catalogos.localidades l ON l.id_localidad = d.id_localidad\n" +
"LEFT JOIN catalogos.municipios m ON m.id_municipio = d.id_municipio\n" +
"LEFT JOIN catalogos.estados e ON e.id_estado = d.id_estado\n" +
"WHERE r.id_solicitud = ?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {
            pst.setInt(1, solicitud.getId_solicitud());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
               
                Usuario usuario=new Usuario();
                registro.setNumero_registro(rs.getString("numero_registro"));
                usuario.setCurp(rs.getString("curp"));
                registro.setUsuario(usuario);
                registro.setNombre_completo(rs.getString("nombre_completo"));
                registro.setCalle_completa(rs.getString("calle_completa"));
                registro.setColonia_completa(rs.getString("colonia_completa"));
                registro.setCiudad_completa(rs.getString("ciudad_completa"));
                registro.setGrados(rs.getString("grados"));
                registro.setCedulas(rs.getString("cedulas"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return registro;
    }
    
    public String obtenerInfoReg(Usuario usuario) throws SQLException, IOException{
        String sql="SELECT\n" +
            "  r.id_solicitud,\n" +
            "  r.numero_registro,\n" +
            "  u.id_usuario,\n" +
            "  u.nombre,\n" +
            "  u.primer_apellido,\n" +
            "  u.segundo_apellido,\n" +
            "  u.curp,\n" +
            "  d.calle,\n" +
            "  d.numero_ext,\n" +
            "  d.numero_int,\n" +
            "  COALESCE(c.colonia, d.colonia_manual) AS colonia,\n" +
            "  l.localidad AS localidad,\n" +
            "  m.municipio AS municipio,\n" +
            "  e.nombre_estado AS estado,\n" +
            "  d.codigo_postal,\n" +
            "\n" +
            "  -- Concatenaciones\n" +
            "  CONCAT(u.nombre, ' ', u.primer_apellido, ' ', u.segundo_apellido) AS nombre_completo,\n" +
            "  CONCAT(d.calle, ' No.', d.numero_ext,\n" +
            "         CASE WHEN d.numero_int IS NOT NULL AND d.numero_int <> ''\n" +
            "              THEN ' Int. ' || d.numero_int ELSE '' END) AS calle_completa,\n" +
            "  CONCAT(\n" +
            "    COALESCE(c.tipo_asentamiento || ' ', ''),\n" +
            "    COALESCE(c.colonia, d.colonia_manual),\n" +
            "    ', C.P ', d.codigo_postal\n" +
            "  ) AS colonia_completa,\n" +
            "  CONCAT(m.municipio, ', ', e.nombre_estado) AS ciudad_completa\n" +
            "\n" +
            "FROM padron_profesionales.registros r\n" +
            "JOIN padron_profesionales.usuarios u ON u.id_usuario = r.id_usuario\n" +
            "JOIN padron_profesionales.domicilios d ON d.id_usuario = u.id_usuario AND r.id_solicitud=d.id_solicitud AND d.predeterminado = true\n" +
            "LEFT JOIN catalogos.colonias c ON c.id_colonia = d.id_colonia\n" +
            "LEFT JOIN catalogos.localidades l ON l.id_localidad = d.id_localidad\n" +
            "LEFT JOIN catalogos.municipios m ON m.id_municipio = d.id_municipio\n" +
            "LEFT JOIN catalogos.estados e ON e.id_estado = d.id_estado\n" +
            "WHERE r.id_usuario = ?;";
        
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {
            pst.setInt(1, usuario.getId_usuario());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Registro registro=new Registro();
                Usuario user=new Usuario();
                registro.setNumero_registro(rs.getString("numero_registro"));
                user.setCurp(rs.getString("curp"));
                registro.setUsuario(user);
                registro.setNombre_completo(rs.getString("nombre_completo"));
                registro.setCalle_completa(rs.getString("calle_completa"));
                registro.setColonia_completa(rs.getString("colonia_completa"));
                registro.setCiudad_completa(rs.getString("ciudad_completa"));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public boolean crearRegistroBD(Solicitud solicitud){
        LocalDate fechaExpedicion = LocalDate.now();
        LocalDate vigencia = fechaExpedicion.plusYears(1);
        Date sqlFechaExpedicion = new Date(fechaExpedicion.toDate().getTime());
        Date sqlVigencia = new Date(vigencia.toDate().getTime());
        int anioActual = LocalDate.now().getYear();
        String sql="INSERT INTO padron_profesionales.registros(numero_registro, fecha_expedicion, vigencia, vigente, id_usuario, id_solicitud)VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {

                 int consecutivo = obtenerConsecutivo();
                 if(consecutivo >0){
                   String numeroReg="SSSLP-"+ consecutivo+"/"+anioActual;
                   pst.setString(1, numeroReg);
                   pst.setDate(2, sqlFechaExpedicion);
                   pst.setDate(3, sqlVigencia);
                   pst.setBoolean(4, true);
                   pst.setInt(5,solicitud.getUsuario().getId_usuario());
                   pst.setInt(6,solicitud.getId_solicitud());
                   int filasAfectadas=pst.executeUpdate();
                   return filasAfectadas>0;  
                 }
                 return false;
             }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    public int obtenerConsecutivo(){
        String getConsecutivoSQL = "SELECT MAX(CAST(SPLIT_PART(SPLIT_PART(numero_registro, '-', 2), '/', 1) AS INTEGER))\n" +
            "FROM padron_profesionales.registros\n" +
            "WHERE EXTRACT(YEAR FROM fecha_expedicion) = EXTRACT(YEAR FROM CURRENT_DATE);";
          try (Connection con = ConexionDBP.getConnection();PreparedStatement pstCons=con.prepareStatement(getConsecutivoSQL)) {
             ResultSet rsCons=pstCons.executeQuery();
               if(rsCons.next()){
                    int consecutivo = rsCons.getInt(1) + 1;
                    return consecutivo;
               }
             
          }catch(Exception e){
            e.printStackTrace();
        }
          return -1;
    }
}
