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
public class Colonia {
    
    int id_colonia;
    String colonia;
    String asentamiento;
    int id_cp;

    public void setId_colonia(int id_colonia) {
        this.id_colonia = id_colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }

    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }
    

    public int getId_colonia() {
        return id_colonia;
    }

    public String getColonia() {
        return colonia;
    }

    public String getAsentamiento() {
        return asentamiento;
    }

    public int getId_cp() {
        return id_cp;
    }
    
    
    
    
}
