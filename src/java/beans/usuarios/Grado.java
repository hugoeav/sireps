/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.usuarios;

import java.util.Date;

/**
 *
 * @author Informatica
 */
public class Grado {
    private int id_grado;
    private String nombre;
    private String tipo_grado;
    private String institucion;
    private String cedula;
    private int id_usuario;
    private int id_solicitud;
    private Date fecha_agregado;
    private Date fecha_modificado;

    public void setId_grado(int id_grado) {
        this.id_grado = id_grado;
    }

    public void setTipo_grado(String tipo_grado) {
        this.tipo_grado = tipo_grado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    
    

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

     public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setFecha_agregado(Date fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }

    public void setFecha_modificado(Date fecha_modificado) {
        this.fecha_modificado = fecha_modificado;
    }

    public int getId_grado() {
        return id_grado;
    }

    public String getTipo_grado() {
        return tipo_grado;
    }

    public String getNombre() {
        return nombre;
    } 
    
    public String getInstitucion() {
        return institucion;
    }

    public String getCedula() {
        return cedula;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }
    

    public Date getFecha_agregado() {
        return fecha_agregado;
    }

    public Date getFecha_modificado() {
        return fecha_modificado;
    }
    
    
    
}
