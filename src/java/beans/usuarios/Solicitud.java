/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.usuarios;

import java.util.Date;

/**
 *
 * @author hugoeav
 */
public class Solicitud {
    private int id_solicitud;
    private String tipo;
    private String observacion;
    private String status;
    private int id_usuario;
    private Date fecha_agregado;
    private Date fecha_modificado;
    private Usuario usuario;

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    public int getId_solicitud() {
        return id_solicitud;
    }

    public String getTipo() {
        return tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getStatus() {
        return status;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Date getFecha_agregado() {
        return fecha_agregado;
    }

    public Date getFecha_modificado() {
        return fecha_modificado;
    }   

    public Usuario getUsuario() {
        return usuario;
    }
    
}
