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
public class Domicilio {
    private int id_domicilio;
    private String nombre;
    private String calle;
    private String numero_ext;
    private String numero_int;
    private int id_colonia;
    private String colonia;
    private String tipo_asentamiento;
    private String colonia_manual;
    private int id_localidad;
    private String localidad;
    private int id_municipio;
    private String municipio;
    private int id_estado;
    private String estado;
    private String codigo_postal;
    private int id_usuario;
    private String tipo_domicilio;
    private int id_solicitud;
    private boolean predeterminado;
    private String num_juris;
    private String juris;
    private Date fecha_agregado;
    private Date fecha_modificado;

    public void setId_domicilio(int id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public void setPredeterminado(boolean predeterminado) {
        this.predeterminado = predeterminado;
    }
    

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero_ext(String numero_ext) {
        this.numero_ext = numero_ext;
    }

    public void setNumero_int(String numero_int) {
        this.numero_int = numero_int;
    }

    public void setId_colonia(int id_colonia) {
        this.id_colonia = id_colonia;
    }

    public void setColonia_manual(String colonia_manual) {
        this.colonia_manual = colonia_manual;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setTipo_domicilio(String tipo_domicilio) {
        this.tipo_domicilio = tipo_domicilio;
    }

    public void setFecha_agregado(Date fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }

    public void setFecha_modificado(Date fecha_modificado) {
        this.fecha_modificado = fecha_modificado;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipo_asentamiento(String tipo_asentamiento) {
        this.tipo_asentamiento = tipo_asentamiento;
    }

    public void setNum_juris(String num_juris) {
        this.num_juris = num_juris;
    }

    public void setJuris(String juris) {
        this.juris = juris;
    }
    

    public int getId_domicilio() {
        return id_domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public boolean isPredeterminado() {
        return predeterminado;
    }

    public String getColonia() {
        return colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEstado() {
        return estado;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero_ext() {
        return numero_ext;
    }

    public String getNumero_int() {
        return numero_int;
    }

    public int getId_colonia() {
        return id_colonia;
    }

    public String getColonia_manual() {
        return colonia_manual;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public int getId_estado() {
        return id_estado;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getTipo_domicilio() {
        return tipo_domicilio;
    }

    public Date getFecha_agregado() {
        return fecha_agregado;
    }

    public Date getFecha_modificado() {
        return fecha_modificado;
    }

    public String getTipo_asentamiento() {
        return tipo_asentamiento;
    }

    public String getNum_juris() {
        return num_juris;
    }

    public String getJuris() {
        return juris;
    }
    
    
}
