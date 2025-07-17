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
public class Registro {
    private int id_registro;
    private String numero_registro;
    private Date fecha_expedicion;
    private Date vigencia;
    private boolean vigente;
    private int id_usuario;
    private Usuario usuario;
    private String nombre_completo;
    private String calle_completa;
    private String colonia_completa;
    private String ciudad_completa;
    private String grados;
    private String cedulas;
    private Date fecha_agregado;
    private Date fecha_modificado;

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public void setNumero_registro(String numero_registro) {
        this.numero_registro = numero_registro;
    }

    public void setFecha_expedicion(Date fecha_expedicion) {
        this.fecha_expedicion = fecha_expedicion;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
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

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public void setCalle_completa(String calle_completa) {
        this.calle_completa = calle_completa;
    }

    public void setColonia_completa(String colonia_completa) {
        this.colonia_completa = colonia_completa;
    }

    public void setCiudad_completa(String ciudad_completa) {
        this.ciudad_completa = ciudad_completa;
    }

    public void setGrados(String grados) {
        this.grados = grados;
    }

    public void setCedulas(String cedulas) {
        this.cedulas = cedulas;
    }
    
    

    public int getId_registro() {
        return id_registro;
    }

    public String getNumero_registro() {
        return numero_registro;
    }

    public Date getFecha_expedicion() {
        return fecha_expedicion;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public boolean isVigente() {
        return vigente;
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

    public String getNombre_completo() {
        return nombre_completo;
    }

    public String getCalle_completa() {
        return calle_completa;
    }

    public String getColonia_completa() {
        return colonia_completa;
    }

    public String getCiudad_completa() {
        return ciudad_completa;
    }

    public String getGrados() {
        return grados;
    }

    public String getCedulas() {
        return cedulas;
    }
    
    
}
