/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.domicilios;

import acciones.documentos.DocumentosAcciones;
import beans.domicilios.CodigoPostal;
import beans.domicilios.Colonia;
import beans.domicilios.Estado;
import beans.domicilios.Localidad;
import beans.domicilios.Municipio;
import beans.usuarios.Documento;
import beans.usuarios.Domicilio;
import beans.usuarios.Solicitud;
import static com.opensymphony.xwork2.Action.SUCCESS;
import daos.documentos.DocumentosDao;
import daos.domicilios.DomicilioDao;
import daos.usuarios.SolicitudDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hugoeav
 */
public class DomicilioAcciones {
    CodigoPostal cp;
    List<Colonia> colonias;
    List<Estado> estados;
    List<Municipio> municipios;
    Colonia colonia;
    List<Localidad> localidades;
    int estadoInput;
    int municipioInput;
    List<Documento> documentos;
    List <Solicitud> solicitudes;

    
    
    
    public String execute() throws Exception{
       DomicilioDao metodos=new DomicilioDao();
       DocumentosDao doc=new DocumentosDao();
       SolicitudDao solicitud_metodos=new SolicitudDao();
       solicitudes=solicitud_metodos.solicitudesXUser();
       for (Solicitud solicitud : solicitudes) {
        // Aquí puedes acceder a cada objeto solicitud
        if(solicitud.getTipo().equals("primeraVez") && !solicitud.getStatus().equals("rechazada")  ){
           return "BLOQUEO";
        }
       }
       estados=metodos.obtener_estados();
       documentos=doc.encuentraDocumentos();
       return SUCCESS;
    }

    
    public boolean registrarDireccionParticular(Domicilio domicilioPart) throws SQLException, IOException{
        DomicilioDao metodos=new DomicilioDao();
        return metodos.registraDomicilio(domicilioPart);
    }
    
    public boolean registrarDireccionLaboral(Domicilio domicilioLab) throws SQLException, IOException{
        DomicilioDao metodos=new DomicilioDao();
        return metodos.registraDomicilio(domicilioLab);
    }
    
    public String obtener_datos_cp(){
        DomicilioDao metodos=new DomicilioDao();
        cp=metodos.obtener_edo_mun(cp);
        colonias= metodos.obtener_colonias(cp);
        localidades=metodos.obtener_localidades(cp);
        municipios=metodos.obtener_municipios(estadoInput);
        return SUCCESS;
    }
    
    public String obtener_municipios(){
       DomicilioDao metodos=new DomicilioDao();
       municipios=metodos.obtener_municipios(estadoInput);
       return SUCCESS;
    }
    
    public String obtener_localidades(){
       DomicilioDao metodos=new DomicilioDao();
       localidades=metodos.obtener_localidades(municipioInput);
       return SUCCESS;
    }  
    
    public String prueba(){
        System.out.println("prueba");
        return SUCCESS;
    }

    public void setCp(CodigoPostal cp) {
        this.cp = cp;
    }

    public void setColonias(List<Colonia> colonias) {
        this.colonias = colonias;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public void setEstadoInput(int estadoInput) {
        this.estadoInput = estadoInput;
    }

    public void setMunicipioInput(int municipioInput) {
        this.municipioInput = municipioInput;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
    
    
    
    
    
    
    

    public CodigoPostal getCp() {
        return cp;
    }

    public List<Colonia> getColonias() {
        return colonias;
    }

    public Colonia getColonia() {
        return colonia;
    }

    

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public int getEstadoInput() {
        return estadoInput;
    }

    public int getMunicipioInput() {
        return municipioInput;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }
    
    
    
    
    
    
    
    
}
