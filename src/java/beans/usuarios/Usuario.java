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
public class Usuario {
    private int id_usuario;
    private String curp;
    private String rfc;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String contrasena;
    private String correo;
    private String telefono;
    private String telefonoLab;
    private Date fecha_agregado;
    private Date fecha_modificado;
    
    
    //setters

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTelefonoLab(String telefonoLab) {
        this.telefonoLab = telefonoLab;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecha_agregado(Date fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }

    public void setFecha_modificado(Date fecha_modificado) {
        this.fecha_modificado = fecha_modificado;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
    
    
    
    
    //getters

    public int getId_usuario() {
        return id_usuario;
    }

    public String getCurp() {
        return curp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTelefonoLab() {
        return telefonoLab;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFecha_agregado() {
        return fecha_agregado;
    }

    public Date getFecha_modificado() {
        return fecha_modificado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRfc() {
        return rfc;
    }
    
    
    
}
