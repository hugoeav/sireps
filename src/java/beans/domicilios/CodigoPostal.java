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
public class CodigoPostal {
    int id_cp;
    String codigoPostal;
    int id_estado;
    int id_municipio;
    String estado;
    String municipio;
    

    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

   

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    

    public int getId_cp() {
        return id_cp;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    

    public int getId_estado() {
        return id_estado;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public String getEstado() {
        return estado;
    }

    public String getMunicipio() {
        return municipio;
    }
    
    
    
    
}
