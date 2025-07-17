/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.domicilios;

/**
 *
 * @author Informatica
 */
public class Localidad {
    int id_localidad;
    String clave_localidad;
    String localidad;

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public void setClave_localidad(String clave_localidad) {
        this.clave_localidad = clave_localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public String getClave_localidad() {
        return clave_localidad;
    }

    public String getLocalidad() {
        return localidad;
    }
            
    
}
