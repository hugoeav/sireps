/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.registros;

import acciones.reportes.ReportePDFAction;
import beans.usuarios.Documento;
import beans.usuarios.Domicilio;
import beans.usuarios.Grado;
import beans.usuarios.Solicitud;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import daos.registros.RegistroDao;
import daos.usuarios.SolicitudDao;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author hugoeav
 */
public class RegistrosAcciones  implements SessionAware  {
    Solicitud solicitudInput;
    Domicilio domicilioInput;
    Domicilio domicilioInputLab;
    List<Grado> grados;
    List<Documento> documentos;
    private Map<Integer, String> documentosRev;
    private String resultMessage;
    private Map<String, Object> session;
    
    public String nuevoRegistro() throws Exception{
        System.out.println("Creando Nuevo Registro Profesional");
        RegistroDao registro_metodos=new RegistroDao();
        ReportePDFAction pdf_metodos=new ReportePDFAction();
        SolicitudDao solicitud_metodos=new SolicitudDao();
        
        System.out.println("solicitud id: "+solicitudInput.getId_solicitud());
        boolean registrado=registro_metodos.crearRegistroBD(solicitudInput);
        if(registrado){
            if(!solicitud_metodos.actualizarStatusSolicitud(solicitudInput) && !solicitud_metodos.actualizarObservacionSolicitud(solicitudInput)){
                throw new Exception("Error al actualizar el Estatus u observación de la solicitud");
            }
            for (Map.Entry<Integer, String> entry : documentosRev.entrySet()) {
            Integer idDocumento = entry.getKey();
            String estatus = entry.getValue();
            Documento doc=new Documento();
            doc.setId_documento(idDocumento);
            if(estatus.equals("valido")){
                doc.setValido(Boolean.TRUE);
            }
            else if(estatus.equals("invalido")){
                doc.setValido(Boolean.FALSE);
                solicitudInput.setStatus("resubirDocs");
            }else{
                doc.setValido(null);
            }
            solicitud_metodos.cambiarStatusDoc(doc, solicitudInput);
           
            
            System.out.println("Documento ID: " + idDocumento + " | Estatus: " + estatus);

            }
           
        pdf_metodos.crearPDFs(solicitudInput);
           
            
           return SUCCESS; 
        }else{
            return ERROR;
        }
        
    }

    public void setSolicitudInput(Solicitud solicitudInput) {
        this.solicitudInput = solicitudInput;
    }

    public void setDomicilioInput(Domicilio domicilioInput) {
        this.domicilioInput = domicilioInput;
    }

    public void setDomicilioInputLab(Domicilio domicilioInputLab) {
        this.domicilioInputLab = domicilioInputLab;
    }
    

    public void setGrados(List<Grado> grados) {
        this.grados = grados;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setDocumentosRev(Map<Integer, String> documentosRev) {
        this.documentosRev = documentosRev;
    }
    
    public Solicitud getSolicitudInput() {
        return solicitudInput;
    }

    public Domicilio getDomicilioInput() {
        return domicilioInput;
    }

    public Domicilio getDomicilioInputLab() {
        return domicilioInputLab;
    }


    public List<Grado> getGrados() {
        return grados;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public Map<Integer, String> getDocumentosRev() {
        return documentosRev;
    }
    

    @Override
    public void setSession(Map<String, Object> session) {
    this.session = session;
    }
    
    
    
}
