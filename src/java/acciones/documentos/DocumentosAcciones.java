/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.documentos;

import beans.usuarios.Documento;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import daos.documentos.DocumentosDao;
import daos.usuarios.SolicitudDao;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hugoeav
 */
public class DocumentosAcciones implements SessionAware{
    //titulos
    File tecTitulo; String tecTituloFileName;
    File licTitulo; String licTituloFileName;
    File maestTitulo; String maestTituloFileName;
    File maest2Titulo; String maest2TituloFileName;
    File espTitulo; String espTituloFileName;
    File esp2Titulo; String esp2TituloFileName;
    File subespTitulo; String subespTituloFileName;
    File docTitulo; String docTituloFileName;
    //cedulas
    File tecCed; String tecCedFileName;
    File licCed; String licCedFileName;
    File maestCed; String maestCedFileName;
    File maest2Ced; String maest2CedFileName;
    File espCed; String espCedFileName;
    File esp2Ced; String esp2CedFileName;
    File subespCed; String subespCedFileName;
    File docCed; String docCedFileName;
    //otros documentos
    File fotosF; String fotosFFileName;
    File reciboPagoF; String reciboPagoFFileName;
    File domicilioF; String domicilioFFileName;
    File registro; String registroFileName;
    File registroFinal; String registroFinalFileName;
    //variables auxiliares
    List<Documento> documentos;
    String rutaDescarga;
    Solicitud solicitudInput;
    private String resultMessage;
    private Map<String, Object> session;
    List<Solicitud> solicitudes;
    //Datos del NAS
    private final String nasUrl="http://10.24.0.9:7000/webapi/entry.cgi";
    private final String NASDirectory="/tdesarrollo/padronProfesionales";
    private final String usuarioNas="desarrollo";
    private final String pswdNas="Des25**//slp";
    List<Documento> archivosSubidos = new ArrayList<>();
    List<String> archivosFallidos = new ArrayList<>();
    
    public String execute() throws Exception{
        DocumentosDao metodos=new DocumentosDao();
        SolicitudDao metodos_solicitud=new SolicitudDao();
        solicitudes=metodos_solicitud.solicitudesXUser();
        int solicitudLoc=solicitudActiva();
       
       
        if(solicitudLoc > 0){
            System.out.println("Con solicitud activa");
            documentos=metodos.encuentraDocumentos(solicitudLoc);
        }
        else{
           documentos=metodos.encuentraDocumentos(); 
        }
        
        return SUCCESS;
    }
    
    
    public List<Documento> isInNAS(List<Documento> docs) throws Exception{
        List<Documento>  docsFiltrados=docs;
         Map<String, Set<String>> cacheCarpetas = new HashMap<>();
         Iterator<Documento> it = docs.iterator();

            while (it.hasNext()) {
                Documento doc = it.next();
                String carpeta = quitarNombreArchivo(doc.getRuta_archivo());
                if (!cacheCarpetas.containsKey(carpeta)) {
                    cacheCarpetas.put(carpeta, obtenerArchivosEnCarpeta(carpeta));
                }
                Set<String> archivos = cacheCarpetas.get(carpeta);
                if (!archivos.contains(doc.getNombre())) {
                    System.out.println("removido: "+doc.getNombre());
                    it.remove();
                    
                }
            }
            return docsFiltrados;
    }
    
    public String precargarArch() throws Exception{
        SolicitudDao metodos_solicitud=new SolicitudDao();
         solicitudes=metodos_solicitud.solicitudesXUser();
        int idSolicitud=solicitudPendienteDocs();
        System.out.println("ID YA ASIGNADO "+idSolicitud);
        
        //titulos
        subirNoNull(tecTitulo, tecTituloFileName, "tecTitulo",idSolicitud);
        subirNoNull(licTitulo, licTituloFileName, "licTitulo",idSolicitud);
        subirNoNull(maestTitulo, maestTituloFileName, "maestTitulo",idSolicitud);
        subirNoNull(maest2Titulo, maest2TituloFileName, "maest2Titulo",idSolicitud);
        subirNoNull(espTitulo, espTituloFileName, "espTitulo",idSolicitud);
        subirNoNull(esp2Titulo, esp2TituloFileName, "esp2Titulo",idSolicitud);
        subirNoNull(subespTitulo, subespTituloFileName, "subespTitulo",idSolicitud);
        subirNoNull(docTitulo, docTituloFileName, "docTitulo",idSolicitud);
            //cedulas
        subirNoNull(tecCed, tecCedFileName, "tecCed",idSolicitud);
        subirNoNull(licCed, licCedFileName, "licCed",idSolicitud);
        subirNoNull(maestCed, maestCedFileName, "maestCed",idSolicitud);
        subirNoNull(maest2Ced, maest2CedFileName, "maest2Ced",idSolicitud);
        subirNoNull(espCed, espCedFileName, "espCed",idSolicitud);
        subirNoNull(esp2Ced, esp2CedFileName, "esp2Ced",idSolicitud);
        subirNoNull(subespCed, subespCedFileName, "subespCed",idSolicitud);
        subirNoNull(docCed, docCedFileName, "docCed",idSolicitud);
        //otros documentos
        subirNoNull(fotosF, fotosFFileName, "fotosF",idSolicitud);
        subirNoNull(reciboPagoF, reciboPagoFFileName, "compPagoF",idSolicitud);
        subirNoNull(domicilioF, domicilioFFileName, "domicilioF",idSolicitud);
        //subirNoNull(registro, registroFileName, "registro",idSolicitud);
        
        return "redirect";
    }
    
    private void subirNoNull(File archivo, String fileName, String clave, int idSolicitud) throws Exception {
        
        int idGeneradoDoc=-1;
        if (archivo != null) {
             HttpSession session = ServletActionContext.getRequest().getSession();
              Usuario userLog = (Usuario) session.getAttribute("usuario");
            idGeneradoDoc=subirArchivo(archivo, fileName, clave,userLog);
        }
        if(idSolicitud > 0 && idGeneradoDoc > 0){
            ligarDocumentos(idSolicitud,idGeneradoDoc);
            if(docsCompletos()){
                SolicitudDao metodos=new SolicitudDao();
                Solicitud s=new Solicitud();
                s.setId_solicitud(idSolicitud);
                s.setStatus("nueva");
                s.setObservacion("La solicitud se ha creado y se revisará proximamente");
                s.setStatus("nueva");
                s.setObservacion("La solicitud se ha creado y se revisará proximamente");
                
                metodos.actualizarStatusSolicitud(s);
                metodos.actualizarObservacionSolicitud(s);
            }
        }
    }
    
    
    
    private int solicitudPendienteDocs(){ //detecta si a alguna solicitud necesita subir algun documento
        int id=-1;
        if(solicitudes != null){
          for (Solicitud solicitudd : solicitudes){
              if("pendienteDocs".equals(solicitudd.getStatus()) || "resubirDocs".equals(solicitudd.getStatus())){
                  id=solicitudd.getId_solicitud();
              }
          }  
        }
        System.out.println("EL ID DE LA SOLICITUD PENDIENTE ES: "+id);
        return id;
    }
    
    private int solicitudActiva(){ //detecta si la solicitud ya se encuentra concluida
        int id=-1;
        if(solicitudes != null){
          for (Solicitud solicitudd : solicitudes){
              if(!"rechazada".equals(solicitudd.getStatus()) && !"completa".equals(solicitudd.getStatus())){
                  id=solicitudd.getId_solicitud();
              }
          }  
        }
        System.out.println("EL ID DE LA SOLICITUD ACTIVA ES: "+id);
        return id;
    }
    
    public boolean docsCompletos() throws Exception{
        DocumentosDao metodos=new DocumentosDao();
        documentos=metodos.encuentraDocumentos();
        boolean tieneFotosF = false;
        boolean tieneCompPagoF = false;
        boolean tieneDomicilioF = false;
        
        if(documentos != null){
            for (Documento doc : documentos) {
                if ("fotosF".equalsIgnoreCase(doc.getTipo_archivo())) {
                    tieneFotosF = true;
                } else if ("compPagoF".equalsIgnoreCase(doc.getTipo_archivo())) {
                    tieneCompPagoF = true;
                } else if ("domicilioF".equalsIgnoreCase(doc.getTipo_archivo())) {
                    tieneDomicilioF = true;
                }
                if (tieneFotosF && tieneCompPagoF && tieneDomicilioF) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int subirArchivo(File doc,String docName,String tipo, Usuario User) throws Exception{
        DocumentosDao metodos=new DocumentosDao();
        String fileName=quitarAcentos(docName);
        String rutaDinamica = construirRutaDinamica(User, NASDirectory);
        String ruta = rutaDinamica + "/" + fileName;
        String sid=authenticate();
        crearCarpetasSiNoExisten(NASDirectory,rutaDinamica,sid);
        try {
            Documento documento= new Documento(); 
            String newName = uploadFileToNAS(rutaDinamica, doc, quitarAcentos(fileName),sid);
            String link=createLink(ruta,sid);
             // Guardar en la base de datos
            documento.setNombre(newName);
            documento.setRuta_archivo(ruta);
            documento.setTipo_archivo(tipo);
            documento.setId_usuario(User.getId_usuario());
            documento.setLink_acceso(link);
            int idGenerado = metodos.registraDocumento(documento, User);
            if (idGenerado <= 0) {
                archivosFallidos.add(fileName + " - Error al registrar en la base de datos.");
                return -1;
            }
            archivosSubidos.add(documento);
            return idGenerado;
            
        }catch (Exception e) {
            return -1;
        }
    }
    
    private String authenticate() throws Exception {
        
        String authUrl = nasUrl + "?api=SYNO.API.Auth&version=6&method=login&account=" + usuarioNas + "&passwd=" + pswdNas + "&session=FileStation&format=sid";
        URL url = new URL(authUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println("URL: "+url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Extraer el SID de la respuesta JSON
        String sid = response.toString().split("\"sid\":\"")[1].split("\"")[0];
        System.out.println("sid: "+sid);
        return sid;
    }
    
    
    private String createLink(String filePath,String sid) throws Exception {
        String pathNoBlanks= URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());
        // Generar enlace compartido
         String shareUrl = nasUrl + "?api=SYNO.FileStation.Sharing&version=1&method=create&path=" + pathNoBlanks + "&_sid=" + sid;
        URL url = new URL(shareUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        String sharedLink = response.toString().split("\"url\":\"")[1].split("\"")[0];
        return sharedLink;
    }
    
    /*
    private String tipoArchivo(String name){
        if (name.contains(".")) {
            int dotIndex = name.indexOf(".");
            return name.substring(dotIndex + 1).trim();
        }  
        return "Sin Formato";
    }*/
    private String getAvailableFileName(String filePath, String originalName,String sid) throws Exception{
        //separar nombre y extensión
        int dot=originalName.lastIndexOf(".");
        String base= (dot == -1) ? originalName                          
                              : originalName.substring(0, dot);
        String ext  = (dot == -1) ? "" : originalName.substring(dot);
        String candidate = originalName;
        int counter = 1;

     // Reintenta hasta encontrar un hueco
        while (archivoExisteEnNAS(filePath, candidate,sid)) {
        candidate = String.format("%s (%d)%s", base, counter++, ext);
        // (Opcional) evita bucle infinito
        if (counter > 10_000)
            throw new Exception("Demasiados archivos con el mismo nombre en la carpeta.");
        }
        return candidate;
    }
    
    private String uploadFileToNAS(String filePath, File fileToUpload, String name,String sid) throws Exception {
    System.out.println("Verificando si el archivo ya existe: " + name);
    String newName=getAvailableFileName(filePath,name,sid);
    if (!newName.equals(name)) {
        System.out.println("Nombre ocupado. Se usará: " + newName);
    }

    System.out.println("El archivo no existe, procediendo con la subida.");
    
    String encodedPath = URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());

    // Configuración del endpoint para subir archivos
    String uploadUrl = nasUrl + "?api=SYNO.FileStation.Upload&version=2&method=upload&_sid=" + sid;

    // Configuración de la conexión
    URL url = new URL(uploadUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----Boundary");

    // Escribir datos del archivo en la solicitudLoc
    OutputStream outputStream = conn.getOutputStream();
    try {
        outputStream.write(("------Boundary\r\n" +
                            "Content-Disposition: form-data; name=\"path\"\r\n\r\n" +
                            filePath + "\r\n").getBytes());
        outputStream.write(("------Boundary\r\n" +
                            "Content-Disposition: form-data; name=\"file\"; filename=\"" + newName + "\"\r\n" +
                            "Content-Type: application/octet-stream\r\n\r\n").getBytes());

        Files.copy(fileToUpload.toPath(), outputStream);
        outputStream.write("\r\n------Boundary--\r\n".getBytes());
    } finally {
        outputStream.close();
    }

    // Leer la respuesta
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        response.append(line);
    }
    reader.close();
    conn.disconnect();

    // Validar la respuesta
    if (!response.toString().contains("\"success\":true")) {
        throw new Exception("Error al subir archivo al NAS Contacta a Soporte TI response: " + response);
    }

    return newName;
    }
    
    private String construirRutaDinamica(Usuario user, String basePath) {
        StringBuilder ruta = new StringBuilder(basePath);
        ruta.append("/").append(user.getCurp());
        return ruta.toString();
    }
    
    public static String reemplazarEspacios(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.trim().replaceAll("\\s+", "_");
    }
    
    private static String quitarAcentos(String texto) {
        // Normalizar el texto a una forma en la que los acentos sean caracteres separados
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);

        textoNormalizado = textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return textoNormalizado;
    }
    
    private boolean archivoExisteEnNAS(String filePath, String fileName, String sid) throws Exception {
        String encodedPath = URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());

        // Obtener la lista de archivos en la carpeta
        String listUrl = nasUrl + "?api=SYNO.FileStation.List&version=2&method=list&folder_path=" + encodedPath + "&_sid=" + sid;
        URL url = new URL(listUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();

        // Parsear la respuesta JSON
        JSONObject jsonResponse = new JSONObject(response.toString());
        if (!jsonResponse.getBoolean("success")) {
            return false; // Si no se pudo obtener la lista, asumir que el archivo no existe
        }

        JSONArray files = jsonResponse.getJSONObject("data").getJSONArray("files");
        for (int i = 0; i < files.length(); i++) {
            JSONObject file = files.getJSONObject(i);
            String existingFileName = URLDecoder.decode(file.getString("name"), StandardCharsets.UTF_8.toString());
            if (existingFileName.equals(fileName)) {
                System.out.println("EL ARCHIVO YA EXISTE");
                return true; // El archivo ya existe
            }
        }

        return false;
    }
    
    private void crearCarpetasSiNoExisten(String base,String ruta,String sid) throws IOException, Exception {
        String pathNoBlanks=ruta.replaceFirst("^"+(base+"/"), "");
         //pathNoBlanks=pathNoBlanks.replace(" ", "%20");
        pathNoBlanks= URLEncoder.encode(pathNoBlanks, StandardCharsets.UTF_8.toString());
         System.out.println("prueba");
         System.out.println("pathno blanks "+pathNoBlanks);
         System.out.println("base: "+base);
        if(!verificaFolder(ruta,sid)){
            System.out.println("NO EXISTE CARPETA, se crea la ruta");
            //String sid=authenticate();
            String urlRequest = nasUrl + "?_sid=" + sid + "&api=SYNO.FileStation.CreateFolder&version=2&method=create&folder_path="+base+"&name="+ pathNoBlanks;
            System.out.println("url crear carpeta: "+urlRequest);
            URL url = new URL(urlRequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            System.out.println("URL: "+url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        }    
    }
    
    public boolean verificaFolder(String ruta, String sid) throws Exception{
        boolean existe=false;
    //verificamos que existan

        //separa carpetas
        String rutaLimpia = ruta.startsWith("/") ? ruta.substring(1) : ruta;
        String[] carpetas = rutaLimpia.split("/");
        String rutaAcumulativa = "";
        // Eliminar posibles espacios extra de cada carpeta
        for (int i = 0; i < carpetas.length-1; i++) {
             carpetas[i] = carpetas[i].trim();
             carpetas[i] = URLEncoder.encode(carpetas[i], StandardCharsets.UTF_8.toString());
            rutaAcumulativa += "/" + carpetas[i];
            System.out.println("ruta acumulativa  "+rutaAcumulativa);
            String getFolders=nasUrl+"?api=SYNO.FileStation.List&version=2&method=list&folder_path="+rutaAcumulativa+ "&_sid=" + sid;
            System.out.println("url consulta folder: "+getFolders);
            URL urlAPI = new URL(getFolders);
            HttpURLConnection conn = (HttpURLConnection) urlAPI.openConnection();
             conn.setRequestMethod("GET");
             conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
             BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("Response: "+response.toString());
            // Parsear la respuesta JSON
        JSONObject jsonResponse = new JSONObject(response.toString());
        if (!jsonResponse.getBoolean("success")) {
            return false; // La carpeta no existe si el resultado no es exitoso
        }

        // Verificar si la carpeta actual está en la lista de directorios
        JSONArray files = jsonResponse.getJSONObject("data").getJSONArray("files");
        boolean carpetaExiste = false;
        for ( int j = 0; j < files.length(); j++) {
            JSONObject file = files.getJSONObject(j);
            String decodedName = URLDecoder.decode(file.getString("name"), StandardCharsets.UTF_8.toString());
            System.out.println("carpeta buscando: "+carpetas[i+1]);
            
            
           // System.out.println("is dir "+j+" "+file.getBoolean("isdir")+" name: "+decodedName);
            if (file.getBoolean("isdir") && decodedName.equals(carpetas[i+1])) {
                carpetaExiste = true;
                
                existe=carpetaExiste;
                break;
            }
        }

        // Si no se encontró la carpeta, devolver false
        if (!carpetaExiste) {
            return false;
        }
            
        }
        return existe;
    }
    
    //método para eliminar archivo deshabilitado
    private boolean deleteFile(String path,String sid) throws Exception{
        String encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString());
        String deleteRequest= nasUrl+"?api=SYNO.FileStation.Delete&version=2&method=delete&path="+encodedPath+"&force_fold=true&_sid="+sid;
        try {
        URL url = new URL(deleteRequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";

            scanner.close();
            conn.disconnect();

           
            return response.contains("\"success\":true");
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String downloadFile() throws Exception{
  
        downloadFileFromNas(rutaDescarga);
        return Action.NONE;
    }
    
    public boolean downloadFileFromNas(String filePath) throws Exception{
        System.out.println("filePath "+filePath);
        String pathNoBlanks= URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());
        String sid = authenticate();
        String downloadReq=nasUrl+"?api=SYNO.FileStation.Download&version=2&method=download&path="+pathNoBlanks+"&_sid="+sid+"&mode=download";
        URL url = new URL(downloadReq);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream inputStream = conn.getInputStream();
        HttpServletResponse response = ServletActionContext.getResponse();
        //response.setContentType("application/octet-stream");
        //response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath.substring(filePath.lastIndexOf("/") + 1) + "\"");
        String extension = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
        String contentType = "application/octet-stream";
        if (null != extension) switch (extension) {
            case "pdf":
                contentType = "application/pdf";
                break;
            case "jpg":
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "gif":
                contentType = "image/gif";
                break;
            default:
                break;
        }
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + filePath.substring(filePath.lastIndexOf("/") + 1) + "\"");
        response.setContentLength(conn.getContentLength());
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            response.getOutputStream().write(buffer, 0, bytesRead);
        }
        response.getOutputStream().flush();
        inputStream.close();
        return true;
    }
    
    public BufferedImage getImageFromNas(String filePath) throws Exception {
    // Codificar la ruta del archivo
    String pathNoBlanks = URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());

    // Autenticarse con el NAS y construir la URL de descarga
    String sid = authenticate(); 
    String downloadReq = nasUrl + "?api=SYNO.FileStation.Download&version=2&method=download"
                        + "&path=" + pathNoBlanks
                        + "&_sid=" + sid
                        + "&mode=download";

    // Abrir conexión
    URL url = new URL(downloadReq);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    // Leer la imagen desde el NAS
    try (InputStream inputStream = conn.getInputStream()) {
        return ImageIO.read(inputStream);
    }
    }
    
    public String quitarNombreArchivo(String rutaCompleta) {
        if (rutaCompleta == null || !rutaCompleta.contains("/")) return rutaCompleta;
        int ultimaBarra = rutaCompleta.lastIndexOf("/");
        return rutaCompleta.substring(0, ultimaBarra);
    }
    
    public Set<String> obtenerArchivosEnCarpeta(String filePath) throws Exception {
    String encodedPath = URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());
    String sid=authenticate();
    String listUrl = nasUrl + "?api=SYNO.FileStation.List&version=2&method=list&folder_path=" + encodedPath + "&_sid=" + sid;
    URL url = new URL(listUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        response.append(line);
    }
    reader.close();
    conn.disconnect();

    JSONObject jsonResponse = new JSONObject(response.toString());
    if (!jsonResponse.getBoolean("success")) {
        return Collections.emptySet();
    }

    JSONArray files = jsonResponse.getJSONObject("data").getJSONArray("files");
    Set<String> nombres = new HashSet<>();
    for (int i = 0; i < files.length(); i++) {
        String nombre = URLDecoder.decode(files.getJSONObject(i).getString("name"), StandardCharsets.UTF_8.toString());
        nombres.add(nombre);
    }

    return nombres;
    }
    
    public boolean ligarDocumentos(int solicitud,int documento){
        DocumentosDao metodos=new DocumentosDao();
        return metodos.ligarDocumentos(documento, solicitud);
    }
    
    public String cargarRegistro() throws Exception{
        SolicitudDao solicitud_metodos=new SolicitudDao();
        System.out.println("solicitud ID: "+solicitudInput.getId_solicitud());
        int id_documento=subirArchivo(registroFinal,registroFinalFileName,"registroFinal",solicitudInput.getUsuario());
        ligarDocumentos(solicitudInput.getId_solicitud(), id_documento);
        if(!solicitud_metodos.actualizarStatusSolicitud(solicitudInput))
        {
           throw new Exception("Error al actualizar el Estatus u observación de la solicitud"); 
        }
        resultMessage = "El registro se ha cargado con éxito";
        session.put("resultMessage", resultMessage);
        return SUCCESS;
    }
    
    //SETTERS

    public void setRutaDescarga(String rutaDescarga) {
        this.rutaDescarga = rutaDescarga;
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

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void setArchivosSubidos(List<Documento> archivosSubidos) {
        this.archivosSubidos = archivosSubidos;
    }

    public void setArchivosFallidos(List<String> archivosFallidos) {
        this.archivosFallidos = archivosFallidos;
    }

    public void setRegistro(File registro) {
        this.registro = registro;
    }

    public void setRegistroFileName(String registroFileName) {
        this.registroFileName = registroFileName;
    }

    public void setRegistroFinal(File registroFinal) {
        this.registroFinal = registroFinal;
    }

    public void setRegistroFinalFileName(String registroFinalFileName) {
        this.registroFinalFileName = registroFinalFileName;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public void setSolicitudInput(Solicitud solicitudInput) {
        this.solicitudInput = solicitudInput;
    }

    
    
    
    

    public String getRutaDescarga() {
        return rutaDescarga;
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

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public String getNasUrl() {
        return nasUrl;
    }

    public String getNASDirectory() {
        return NASDirectory;
    }

    public String getUsuarioNas() {
        return usuarioNas;
    }

    public String getPswdNas() {
        return pswdNas;
    }

    public List<Documento> getArchivosSubidos() {
        return archivosSubidos;
    }

    public List<String> getArchivosFallidos() {
        return archivosFallidos;
    }

    public File getRegistro() {
        return registro;
    }

    public String getRegistroFileName() {
        return registroFileName;
    }

    public File getRegistroFinal() {
        return registroFinal;
    }

    public String getRegistroFinalFileName() {
        return registroFinalFileName;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public Solicitud getSolicitudInput() {
        return solicitudInput;
    }

   
      @Override
public void setSession(Map<String, Object> session) {
    this.session = session;
}
    
}
