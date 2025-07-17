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
public class Municipio {
    int id_municipio;
    String municipio;
    String clave_mun;

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    

    public void setClave_mun(String clave_mun) {
        this.clave_mun = clave_mun;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    

    public String getClave_mun() {
        return clave_mun;
    }
    
    
    
}
