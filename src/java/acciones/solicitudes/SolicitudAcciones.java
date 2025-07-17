package acciones.solicitudes;

import acciones.documentos.DocumentosAcciones;
import acciones.domicilios.DomicilioAcciones;
import beans.usuarios.Documento;
import beans.usuarios.Solicitud;
import beans.usuarios.Domicilio;
import beans.usuarios.Grado;
import beans.usuarios.Usuario;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import daos.documentos.DocumentosDao;
import daos.domicilios.DomicilioDao;
import daos.grados.GradoDao;
import daos.usuarios.UsuariosDAO;
import daos.usuarios.SolicitudDao;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author hugoeav
 */
public class SolicitudAcciones extends ActionSupport implements SessionAware {
    //variables de datos
    private Usuario usuarioInput;
    private Solicitud solicitudInput;
    private Domicilio domicilioInput;
    private Domicilio domicilioLabInput;
    private Grado tecnicoInput;
    private Grado licenciaturaInput;
    private Grado maestriaInput;
    private Grado maestria2Input;
    private Grado especialidadInput;
    private Grado especialidad2Input;
    private Grado subEspecialidad;
    private Grado doctoradoInput;
    private List<Solicitud> solicitudes;
    private List<Solicitud> primeraVez;private List<Solicitud> esp; private List<Solicitud> repo; private List<Solicitud> domicilio; private List<Solicitud> aprob;private List<Solicitud> rechazadas;
    private List<Grado> grados;
    private List<Documento> documentos;
    private Map<Integer, String> documentosRev;
    //variables de archivos
    private  File tecTitulo; private String tecTituloFileName;
    private File licTitulo; private String licTituloFileName;
    private File maestTitulo; private String maestTituloFileName;
    private File maest2Titulo; private String maest2TituloFileName;
    private File espTitulo; private String espTituloFileName;
    private File esp2Titulo; private String esp2TituloFileName;
    private File subespTitulo; private String subespTituloFileName;
    private File docTitulo; private String docTituloFileName;
    private File tecCed; private String tecCedFileName;
    private File licCed; private String licCedFileName;
    private File maestCed; private String maestCedFileName;
    private File maest2Ced; private String maest2CedFileName;
    private File espCed; private String espCedFileName;
    private File esp2Ced; private String esp2CedFileName;
    private File subespCed; private String subespCedFileName;
    private File docCed; private String docCedFileName;
    private File fotosF; private String fotosFFileName;
    private File reciboPagoF; private String reciboPagoFFileName;
    private File domicilioF; private String domicilioFFileName;
    private Map<String, Integer> documentosPrecargados; private String direccionPredeterminada;
    //variables generales
    private String resultMessage;
    private Map<String, Object> session;
    
    public String redirige(){
        String mensaje = (String) session.get("resultMessage");
        if(mensaje != null && !"".equals(mensaje)){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            request.setAttribute("resultMessage", mensaje);
        }
        return SUCCESS;
    }
    
    public String registroPrimeraVez() throws SQLException, IOException, Exception{
        //Declaraciones
        UsuariosDAO usuario_metodos=new UsuariosDAO();
        DomicilioAcciones domicilio_metodos=new DomicilioAcciones();
        GradoDao grados_metodos=new GradoDao();
        SolicitudDao metodos=new SolicitudDao();
        DocumentosAcciones doc_metodos=new DocumentosAcciones();
        Object[][] documentos = {
        {tecTitulo, tecTituloFileName, "tecTitulo"},
        {licTitulo, licTituloFileName, "licTitulo"},
        {maestTitulo, maestTituloFileName, "maestTitulo"},
        {maest2Titulo, maest2TituloFileName, "maest2Titulo"},
        {espTitulo, espTituloFileName, "espTitulo"},
        {esp2Titulo, esp2TituloFileName, "esp2Titulo"},
        {subespTitulo, subespTituloFileName, "subespTitulo"},
        {docTitulo, docTituloFileName, "docTitulo"},
        {tecCed, tecCedFileName, "tecCed"},
        {licCed, licCedFileName, "licCed"},
        {maestCed, maestCedFileName, "maestCed"},
        {maest2Ced, maest2CedFileName, "maest2Ced"},
        {espCed, espCedFileName, "espCed"},
        {esp2Ced, esp2CedFileName, "esp2Ced"},
        {subespCed, subespCedFileName, "subespCed"},
        {docCed, docCedFileName, "docCed"},
        {fotosF, fotosFFileName, "fotosF"},
        {reciboPagoF, reciboPagoFFileName, "compPagoF"},
        {domicilioF, domicilioFFileName, "domicilioF"}
    };
        // Arreglo de pares: objeto de grado y su tipo correspondiente
        Object[][] grados = {
            {tecnicoInput, "tecnico"},
            {licenciaturaInput, "licenciatura"},
            {maestriaInput, "maestria"},
            {maestria2Input, "maestria2"},
            {especialidadInput, "especialidad"},
            {especialidad2Input, "especialidad2"},
            {subEspecialidad, "subespecialidad"},
            {doctoradoInput, "doctorado"}
        };
        
        //subir documentos
        for (Object[] doc : documentos) {
            File archivo = (File) doc[0];
            String nombreArchivo = (String) doc[1];
            String identificador = (String) doc[2];
                System.out.println("nombre identi"+identificador+" nombre: "+nombreArchivo);
            if (archivo != null) {
                 HttpSession session = ServletActionContext.getRequest().getSession();
                Usuario userLog = (Usuario) session.getAttribute("usuario");
                int idDocumento=doc_metodos.subirArchivo(archivo, nombreArchivo, identificador,userLog);
                if(idDocumento >0){
                    documentosPrecargados.put(identificador, idDocumento);
                }
            }
        }
        if(doc_metodos.docsCompletos()){
            solicitudInput.setStatus("nueva");
            solicitudInput.setObservacion("La solicitud se ha enviado y será revisada de 1 a 3 días hábiles.");
        }
        else{
            solicitudInput.setStatus("pendienteDocs");
            solicitudInput.setObservacion("La solicitud NO se ha enviado está pendiente de anexar documentos. Subirlos en la sección de Mis Documentos");
        }
        int idSolicitud=metodos.nuevaSolicitud(solicitudInput);
        
        if(idSolicitud >0){
        //Ligar documentos con la solicitud
        for (Map.Entry<String, Integer> entry : documentosPrecargados.entrySet()) {
            String tipoDocumento = entry.getKey();
            Integer idDocumento = entry.getValue();

            if (idDocumento != null && idDocumento != -1) {
                // Ejecuta la acción para ese documento
                System.out.println("Documento válido: " + tipoDocumento + " con ID: " + idDocumento);
                doc_metodos.ligarDocumentos(idSolicitud, idDocumento);

            }
        }
        //Se actualiza el telefono del usuario
        usuario_metodos.actualizaTelefono(usuarioInput);
        //asigna solicitud
        domicilioInput.setId_solicitud(idSolicitud);
        domicilioLabInput.setId_solicitud(idSolicitud);
        
        System.out.println("La direccion predeterminada es "+direccionPredeterminada);
        //asigna direccion predeterminada
        if(direccionPredeterminada.equals("particular")){
            domicilioInput.setPredeterminado(true);
            domicilioLabInput.setPredeterminado(false);
        }
        else{
            domicilioInput.setPredeterminado(false);
            domicilioLabInput.setPredeterminado(true);
        }
        
        //guardar informacion en tablas de domicilio
        domicilio_metodos.registrarDireccionParticular(domicilioInput);
        domicilio_metodos.registrarDireccionLaboral(domicilioLabInput);
        
        

        // Solo se asigna tipo_grado y se registra si el objeto no es null
        for (Object[] grado : grados) {
            Grado gradoInput = (Grado) grado[0];
            String tipo = (String) grado[1];
            System.out.println("El id en for es: "+idSolicitud);
            if (gradoInput != null && gradoInput.getNombre() != null && !gradoInput.getNombre().isEmpty()) {
                gradoInput.setTipo_grado(tipo);
                gradoInput.setId_solicitud(idSolicitud);
                grados_metodos.registraGrado(gradoInput);
            }
        }
        resultMessage = "La solicitud se ha creado con éxito";
        session.put("resultMessage", resultMessage);
        return SUCCESS;
        }
        resultMessage = "Hubo un error al registrar la solicitud, intentalo más tarde.";
        session.put("resultMessage", resultMessage);
        return ERROR;
        
        
    }
    
    public String misSolicitudes(){
        String mensaje = (String) session.get("resultMessage");
        if(mensaje != null && !"".equals(mensaje)){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            request.setAttribute("resultMessage", mensaje);
        }
        SolicitudDao metodos= new SolicitudDao();
        solicitudes=metodos.solicitudesXUser();
        return SUCCESS;
    }
    
    public String solicitudes(){
        SolicitudDao metodos= new SolicitudDao();
        solicitudes=metodos.solicitudes();
        aprob=new ArrayList<>();
        rechazadas=new ArrayList<>();
        primeraVez=new ArrayList<>();
        esp=new ArrayList<>();
        repo=new ArrayList<>();
        domicilio=new ArrayList<>();
        for(Solicitud sol : solicitudes){
            switch(sol.getTipo()){
                case "primeraVez":
                    if(sol.getStatus().equals("completa") || sol.getStatus().equals("aprobada")){
                        aprob.add(sol);
                    }
                    else{
                        if(sol.getStatus().equals("rechazada")){
                            if(sol != null){
                                rechazadas.add(sol);
                            }
                             
                        }
                        else{
                            primeraVez.add(sol);
                        }
                    }
                   
                    break;
                case "especialidad":
                    if(sol.getStatus().equals("completa")){
                        aprob.add(sol);
                    }
                    else if(sol.getStatus().equals("rechazada")){
                        rechazadas.add(sol);
                    }
                    else{
                        esp.add(sol);
                    }
                    break;
                case "reposicion":
                    if(sol.getStatus().equals("completa")){
                        aprob.add(sol);
                    }
                    else if(sol.getStatus().equals("rechazada")){
                        rechazadas.add(sol);
                    }
                    else{
                        repo.add(sol);
                    }
                    break;
                case "domicilio":
                    if(sol.getStatus().equals("completa")){
                        aprob.add(sol);
                    }
                    else if(sol.getStatus().equals("rechazada")){
                        rechazadas.add(sol);
                    }
                    else{
                        domicilio.add(sol);
                    }
                    break;
            }
        }
        return SUCCESS;
    }
    
    public String detalleSolicitud() throws SQLException, IOException, Exception{
        DomicilioDao domicilio_metodos= new DomicilioDao();
        SolicitudDao solicitud_metodos=new SolicitudDao();
        GradoDao grados_metodos=new GradoDao();
        DocumentosDao documentos_metodos=new DocumentosDao();
        //la solicitud llega solo con el id y obtenemos la demas informacion
        solicitudInput=solicitud_metodos.detalleSolicitud(solicitudInput);
        domicilioInput=domicilio_metodos.encontrarDomicilio(solicitudInput, "particular");
        domicilioLabInput=domicilio_metodos.encontrarDomicilio(solicitudInput, "laboral");
        grados=grados_metodos.obtenerGrados(solicitudInput);
        documentos=documentos_metodos.encuentraDocumentos(solicitudInput);
        String mensaje = (String) session.get("resultMessage");
        if(mensaje != null && !"".equals(mensaje)){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            request.setAttribute("resultMessage", mensaje);
        }
        return SUCCESS;
    }
    

    
    public String enviaComentarios() throws SQLException, IOException{
        System.out.println("la solciitud con folio: "+solicitudInput.getId_solicitud()+"la obervacion es "+solicitudInput.getObservacion());
        SolicitudDao solicitud_metodos= new SolicitudDao();
        solicitudInput.setStatus("revision");
        solicitud_metodos.registraComentarios(solicitudInput);
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
        }
            solicitud_metodos.actualizarStatusSolicitud(solicitudInput);

        resultMessage = "Se enviaron los comentarios con éxito";
        session.put("resultMessage", resultMessage);
        return SUCCESS;
    }
    
    public String rechazaSolicitud() throws SQLException, IOException, Exception{
        SolicitudDao solicitud_metodos= new SolicitudDao();
         DomicilioDao domicilio_metodos= new DomicilioDao();
        GradoDao grados_metodos=new GradoDao();
        DocumentosDao documentos_metodos=new DocumentosDao();
        System.out.println("el id de solicitud es "+solicitudInput.getId_solicitud());
        System.out.println("el status es "+solicitudInput.getStatus());
        solicitud_metodos.actualizarStatusSolicitud(solicitudInput);
         //recarga datos para volver a mostrar la misma pantalla
         solicitudInput=solicitud_metodos.detalleSolicitud(solicitudInput);
        domicilioInput=domicilio_metodos.encontrarDomicilio(solicitudInput, "particular");
        domicilioLabInput=domicilio_metodos.encontrarDomicilio(solicitudInput, "laboral");
        grados=grados_metodos.obtenerGrados(solicitudInput);
        documentos=documentos_metodos.encuentraDocumentos(solicitudInput);
        resultMessage = "La Solicitud se Rechazó";
        session.put("resultMessage", resultMessage);
        return SUCCESS;
    }
    
    

    public void setUsuarioInput(Usuario usuarioInput) {
        this.usuarioInput = usuarioInput;
    }
    
    public void setSolicitudInput(Solicitud solicitudInput) {
        this.solicitudInput = solicitudInput;
    }

    public void setDomicilioInput(Domicilio domicilioInput) {
        this.domicilioInput = domicilioInput;
    }

    public void setDomicilioLabInput(Domicilio domicilioLabInput) {
        this.domicilioLabInput = domicilioLabInput;
    }

    public void setTecnicoInput(Grado tecnicoInput) {
        this.tecnicoInput = tecnicoInput;
    }

    public void setLicenciaturaInput(Grado licenciaturaInput) {
        this.licenciaturaInput = licenciaturaInput;
    }

    public void setMaestriaInput(Grado maestriaInput) {
        this.maestriaInput = maestriaInput;
    }

    public void setMaestria2Input(Grado maestria2Input) {
        this.maestria2Input = maestria2Input;
    }

    public void setEspecialidadInput(Grado especialidadInput) {
        this.especialidadInput = especialidadInput;
    }

    public void setEspecialidad2Input(Grado especialidad2Input) {
        this.especialidad2Input = especialidad2Input;
    }

    public void setSubEspecialidad(Grado subEspecialidad) {
        this.subEspecialidad = subEspecialidad;
    }

    public void setDoctoradoInput(Grado doctoradoInput) {
        this.doctoradoInput = doctoradoInput;
    }

    public void setTecTitulo(File tecTitulo) {
        this.tecTitulo = tecTitulo;
    }

    public void setTecTituloFileName(String tecTituloFileName) {
        this.tecTituloFileName = tecTituloFileName;
    }

    public void setLicTitulo(File licTitulo) {
        this.licTitulo = licTitulo;
    }

    public void setLicTituloFileName(String licTituloFileName) {
        this.licTituloFileName = licTituloFileName;
    }

    public void setMaestTitulo(File maestTitulo) {
        this.maestTitulo = maestTitulo;
    }

    public void setMaestTituloFileName(String maestTituloFileName) {
        this.maestTituloFileName = maestTituloFileName;
    }

    public void setMaest2Titulo(File maest2Titulo) {
        this.maest2Titulo = maest2Titulo;
    }

    public void setMaest2TituloFileName(String maest2TituloFileName) {
        this.maest2TituloFileName = maest2TituloFileName;
    }

    public void setEspTitulo(File espTitulo) {
        this.espTitulo = espTitulo;
    }

    public void setEspTituloFileName(String espTituloFileName) {
        this.espTituloFileName = espTituloFileName;
    }

    public void setEsp2Titulo(File esp2Titulo) {
        this.esp2Titulo = esp2Titulo;
    }

    public void setEsp2TituloFileName(String esp2TituloFileName) {
        this.esp2TituloFileName = esp2TituloFileName;
    }

    public void setSubespTitulo(File subespTitulo) {
        this.subespTitulo = subespTitulo;
    }

    public void setSubespTituloFileName(String subespTituloFileName) {
        this.subespTituloFileName = subespTituloFileName;
    }

    public void setDocTitulo(File docTitulo) {
        this.docTitulo = docTitulo;
    }

    public void setDocTituloFileName(String docTituloFileName) {
        this.docTituloFileName = docTituloFileName;
    }

    public void setTecCed(File tecCed) {
        this.tecCed = tecCed;
    }

    public void setTecCedFileName(String tecCedFileName) {
        this.tecCedFileName = tecCedFileName;
    }

    public void setLicCed(File licCed) {
        this.licCed = licCed;
    }

    public void setLicCedFileName(String licCedFileName) {
        this.licCedFileName = licCedFileName;
    }

    public void setMaestCed(File maestCed) {
        this.maestCed = maestCed;
    }

    public void setMaestCedFileName(String maestCedFileName) {
        this.maestCedFileName = maestCedFileName;
    }

    public void setMaest2Ced(File maest2Ced) {
        this.maest2Ced = maest2Ced;
    }

    public void setMaest2CedFileName(String maest2CedFileName) {
        this.maest2CedFileName = maest2CedFileName;
    }

    public void setEspCed(File espCed) {
        this.espCed = espCed;
    }

    public void setEspCedFileName(String espCedFileName) {
        this.espCedFileName = espCedFileName;
    }

    public void setEsp2Ced(File esp2Ced) {
        this.esp2Ced = esp2Ced;
    }

    public void setEsp2CedFileName(String esp2CedFileName) {
        this.esp2CedFileName = esp2CedFileName;
    }

    public void setSubespCed(File subespCed) {
        this.subespCed = subespCed;
    }

    public void setSubespCedFileName(String subespCedFileName) {
        this.subespCedFileName = subespCedFileName;
    }

    public void setDocCed(File docCed) {
        this.docCed = docCed;
    }

    public void setDocCedFileName(String docCedFileName) {
        this.docCedFileName = docCedFileName;
    }

    public void setFotosF(File fotosF) {
        this.fotosF = fotosF;
    }

    public void setFotosFFileName(String fotosFFileName) {
        this.fotosFFileName = fotosFFileName;
    }

    public void setReciboPagoF(File reciboPagoF) {
        this.reciboPagoF = reciboPagoF;
    }

    public void setReciboPagoFFileName(String reciboPagoFFileName) {
        this.reciboPagoFFileName = reciboPagoFFileName;
    }

    public void setDomicilioF(File domicilioF) {
        this.domicilioF = domicilioF;
    }

    public void setDomicilioFFileName(String domicilioFFileName) {
        this.domicilioFFileName = domicilioFFileName;
    }

    public void setDocumentosPrecargados(Map<String, Integer> documentosPrecargados) {
        this.documentosPrecargados = documentosPrecargados;
    }

    public void setDireccionPredeterminada(String direccionPredeterminada) {
        this.direccionPredeterminada = direccionPredeterminada;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
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

    public void setPrimeraVez(List<Solicitud> primeraVez) {
        this.primeraVez = primeraVez;
    }

    public void setEsp(List<Solicitud> esp) {
        this.esp = esp;
    }

    public void setRepo(List<Solicitud> repo) {
        this.repo = repo;
    }

    public void setDomicilio(List<Solicitud> domicilio) {
        this.domicilio = domicilio;
    }

    public void setAprob(List<Solicitud> aprob) {
        this.aprob = aprob;
    }

    public void setRechazadas(List<Solicitud> rechazadas) {
        this.rechazadas = rechazadas;
    }
    
    
 

    public Usuario getUsuarioInput() {
        return usuarioInput;
    }

    public Solicitud getSolicitudInput() {
        return solicitudInput;
    }

    public Domicilio getDomicilioInput() {
        return domicilioInput;
    }

    public Domicilio getDomicilioLabInput() {
        return domicilioLabInput;
    }

    public Grado getTecnicoInput() {
        return tecnicoInput;
    }

    public Grado getLicenciaturaInput() {
        return licenciaturaInput;
    }

    public Grado getMaestriaInput() {
        return maestriaInput;
    }

    public Grado getMaestria2Input() {
        return maestria2Input;
    }

    public Grado getEspecialidadInput() {
        return especialidadInput;
    }

    public Grado getEspecialidad2Input() {
        return especialidad2Input;
    }

    public Grado getSubEspecialidad() {
        return subEspecialidad;
    }

    public Grado getDoctoradoInput() {
        return doctoradoInput;
    }  

    public File getTecTitulo() {
        return tecTitulo;
    }

    public String getTecTituloFileName() {
        return tecTituloFileName;
    }

    public File getLicTitulo() {
        return licTitulo;
    }

    public String getLicTituloFileName() {
        return licTituloFileName;
    }

    public File getMaestTitulo() {
        return maestTitulo;
    }

    public String getMaestTituloFileName() {
        return maestTituloFileName;
    }

    public File getMaest2Titulo() {
        return maest2Titulo;
    }

    public String getMaest2TituloFileName() {
        return maest2TituloFileName;
    }

    public File getEspTitulo() {
        return espTitulo;
    }

    public String getEspTituloFileName() {
        return espTituloFileName;
    }

    public File getEsp2Titulo() {
        return esp2Titulo;
    }

    public String getEsp2TituloFileName() {
        return esp2TituloFileName;
    }

    public File getSubespTitulo() {
        return subespTitulo;
    }

    public String getSubespTituloFileName() {
        return subespTituloFileName;
    }

    public File getDocTitulo() {
        return docTitulo;
    }

    public String getDocTituloFileName() {
        return docTituloFileName;
    }

    public File getTecCed() {
        return tecCed;
    }

    public String getTecCedFileName() {
        return tecCedFileName;
    }

    public File getLicCed() {
        return licCed;
    }

    public String getLicCedFileName() {
        return licCedFileName;
    }

    public File getMaestCed() {
        return maestCed;
    }

    public String getMaestCedFileName() {
        return maestCedFileName;
    }

    public File getMaest2Ced() {
        return maest2Ced;
    }

    public String getMaest2CedFileName() {
        return maest2CedFileName;
    }

    public File getEspCed() {
        return espCed;
    }

    public String getEspCedFileName() {
        return espCedFileName;
    }

    public File getEsp2Ced() {
        return esp2Ced;
    }

    public String getEsp2CedFileName() {
        return esp2CedFileName;
    }

    public File getSubespCed() {
        return subespCed;
    }

    public String getSubespCedFileName() {
        return subespCedFileName;
    }

    public File getDocCed() {
        return docCed;
    }

    public String getDocCedFileName() {
        return docCedFileName;
    }

    public File getFotosF() {
        return fotosF;
    }

    public String getFotosFFileName() {
        return fotosFFileName;
    }

    public File getReciboPagoF() {
        return reciboPagoF;
    }

    public String getReciboPagoFFileName() {
        return reciboPagoFFileName;
    }

    public File getDomicilioF() {
        return domicilioF;
    }

    public String getDomicilioFFileName() {
        return domicilioFFileName;
    }

    public Map<String, Integer> getDocumentosPrecargados() {
        return documentosPrecargados;
    }

    public String getDireccionPredeterminada() {
        return direccionPredeterminada;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
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

    public Map<Integer, String> getDocumentosRev() {
        return documentosRev;
    }

    public List<Solicitud> getPrimeraVez() {
        return primeraVez;
    }

    public List<Solicitud> getEsp() {
        return esp;
    }

    public List<Solicitud> getRepo() {
        return repo;
    }

    public List<Solicitud> getDomicilio() {
        return domicilio;
    }

    public List<Solicitud> getAprob() {
        return aprob;
    }

    public List<Solicitud> getRechazadas() {
        return rechazadas;
    }

    public Map<String, Object> getSession() {
        return session;
    }
    

   @Override
public void setSession(Map<String, Object> session) {
    this.session = session;
}

   
    
    
}
