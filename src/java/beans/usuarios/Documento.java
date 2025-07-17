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
public class Documento {
    private int id_documento;
    private String nombre;
    private String ruta_archivo;
    private String link_acceso;
    private String tipo_archivo;
    private int id_usuario;
    private Boolean valido;
    private Date fecha_agregado;
    private Date fecha_modificado;

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRuta_archivo(String ruta_archivo) {
        this.ruta_archivo = ruta_archivo;
    }

    public void setLink_acceso(String link_acceso) {
        this.link_acceso = link_acceso;
    }

    public void setTipo_archivo(String tipo_archivo) {
        this.tipo_archivo = tipo_archivo;
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

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    
    
    public int getId_documento() {
        return id_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta_archivo() {
        return ruta_archivo;
    }

    public String getLink_acceso() {
        return link_acceso;
    }

    public String getTipo_archivo() {
        return tipo_archivo;
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

    public Boolean getValido() {
        return valido;
    }

    
    
    
}
