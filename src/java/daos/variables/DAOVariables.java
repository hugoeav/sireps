/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.variables;

import beans.variables.HojaPago;
import daos.conexion.ConexionDBP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hugoeav
 */
public class DAOVariables {
    public HojaPago detalleHojaPago(){
         HojaPago hojaPago=new HojaPago();
         String sql="SELECT\n" +
            "  MAX(CASE WHEN nombre_variable = 'anio' THEN valor END) AS anio,\n" +
            "  MAX(CASE WHEN nombre_variable = 'clave' THEN valor END) AS clave,\n" +
            "  MAX(CASE WHEN nombre_variable = 'monto' THEN valor END) AS monto\n" +
            "FROM catalogos.variables\n" +
            "WHERE id_reporte = 'pago';";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               hojaPago.setClavePago(rs.getString("clave"));
               hojaPago.setTotalPago(rs.getString("monto"));
            }
        }catch (Exception e) {
        e.printStackTrace(); // imprime el error para depuración
        }
        return hojaPago;
    }
    
    public boolean actualizaHojaPago(HojaPago hojaPago){
        String sql="UPDATE catalogos.variables AS t SET valor = v.valor,fecha_modificado = now() FROM ( VALUES ('clave', ?),('monto', ?)) AS v(nombre_variable, valor) WHERE t.nombre_variable = v.nombre_variable;";
         try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, hojaPago.getClavePago());
             ps.setString(2, hojaPago.getTotalPago());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
         }catch (Exception e) {
            e.printStackTrace(); 
        }
         return false;
    }
    
}
