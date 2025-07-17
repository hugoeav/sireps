/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.pruebas;

import beans.usuarios.Documento;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.SUCCESS;
import daos.documentos.DocumentosDao;
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
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Informatica
 */
public class DocumentosTest {
    
    //variables auxiliares
    List<Documento> documentos;
    String rutaDescarga;
    boolean existe;
    
    List<Solicitud> solicitudes;
    //Datos del NAS
    private final String nasUrl="http://10.24.0.9:7000/webapi/entry.cgi";
    private final String NASDirectory="/tdesarrollo/padronProfesionales";
    private final String usuarioNas="desarrollo";
    private final String pswdNas="Des25**//slp";
    List<Documento> archivosSubidos = new ArrayList<>();
    List<String> archivosFallidos = new ArrayList<>();
    
    public String uploadFile(){
        System.out.println("SI ENTRO A LA FUNCION");
        return SUCCESS;
    }
    
    
     public int subirArchivo(File doc,String docName,String tipo, Usuario User) throws Exception{
        HttpSession session = ServletActionContext.getRequest().getSession();
        HttpServletRequest request = ServletActionContext.getRequest();
        
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        DocumentosDao metodos=new DocumentosDao();
        String fileName=quitarAcentos(docName);
        String rutaDinamica = construirRutaDinamica(User, NASDirectory);
        String ruta = rutaDinamica + "/" + fileName;
        crearCarpetasSiNoExisten(NASDirectory,rutaDinamica);
        try {
            Documento documento= new Documento(); 
            String response = uploadFileToNAS(rutaDinamica, doc, quitarAcentos(fileName));
            String link=createLink(ruta);
             // Guardar en la base de datos
            documento.setNombre(fileName);
            documento.setRuta_archivo(ruta);
            documento.setTipo_archivo(tipo);
            documento.setId_usuario(User.getId_usuario());
            documento.setLink_acceso(link);
            int idGenerado = metodos.registraDocumento(documento, User);
            if (idGenerado <= 0) {
                archivosFallidos.add(fileName + " - Error al registrar en la base de datos.");
                if(!existe){ //Si ya existia un archivo con el mismo nombre en el servidor no lo borra
                  deleteFile(ruta);
                }
                
                return -1;
            }
            archivosSubidos.add(documento);
            return idGenerado;
            
        }catch (Exception e) {
            if(!existe){ //Si ya existia un archivo con el mismo nombre en el servidor no lo borra
                deleteFile(ruta);
            }
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
    
    public boolean listarArchivos(String path) throws Exception{
        String sid=authenticate();
        String utl=nasUrl+"?api=SYNO.FileStation.List&version=2&method=list&folder_path="+path+"&_sid="+sid;
        
        return true;
    }
    
    
    private String createLink(String filePath) throws Exception {
        String pathNoBlanks= URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());
        String sid = authenticate();
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
    
    private String uploadFileToNAS(String filePath, File fileToUpload, String name) throws Exception {
    System.out.println("Verificando si el archivo ya existe: " + name);
    
    // Si el archivo ya existe, mostrar mensaje de error
    if (archivoExisteEnNAS(filePath, name)) {
        archivosFallidos.add(name + " - Error el archivo ya existe en la carpeta de destino");
        throw new Exception("El archivo '" + name + "' ya existe en la carpeta destino. Cambia el nombre antes de subirlo.");
    }

    System.out.println("El archivo no existe, procediendo con la subida.");
    
    String encodedPath = URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());

    // Autenticación: obtener el SID
    String sid = authenticate();

    // Configuración del endpoint para subir archivos
    String uploadUrl = nasUrl + "?api=SYNO.FileStation.Upload&version=2&method=upload&_sid=" + sid;

    // Configuración de la conexión
    URL url = new URL(uploadUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----Boundary");

    // Escribir datos del archivo en la solicitud
    OutputStream outputStream = conn.getOutputStream();
    try {
        outputStream.write(("------Boundary\r\n" +
                            "Content-Disposition: form-data; name=\"path\"\r\n\r\n" +
                            filePath + "\r\n").getBytes());
        outputStream.write(("------Boundary\r\n" +
                            "Content-Disposition: form-data; name=\"file\"; filename=\"" + name + "\"\r\n" +
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

    return response.toString();
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
    
    private boolean archivoExisteEnNAS(String filePath, String fileName) throws Exception {
        existe=false;
        String encodedPath = URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString());
        String sid = authenticate();

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
                existe=true;
                return true; // El archivo ya existe
            }
        }

        return false;
    }
    
    private void crearCarpetasSiNoExisten(String base,String ruta) throws IOException, Exception {
        String pathNoBlanks=ruta.replaceFirst("^"+(base+"/"), "");
         //pathNoBlanks=pathNoBlanks.replace(" ", "%20");
        pathNoBlanks= URLEncoder.encode(pathNoBlanks, StandardCharsets.UTF_8.toString());
         System.out.println("prueba");
         System.out.println("pathno blanks "+pathNoBlanks);
         System.out.println("base: "+base);
        if(!verificaFolder(ruta)){
            System.out.println("NO EXISTE CARPETA, se crea la ruta");
            String sid=authenticate();
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
    
    public boolean verificaFolder(String ruta) throws Exception{
        boolean existe=false;
    //verificamos que existan
        String sid=authenticate();

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
    
    private boolean deleteFile(String path) throws Exception{
        String encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString());
        String sid=authenticate();
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
        if ("pdf".equals(extension)) {
            contentType = "application/pdf";
        } else if ("jpg".equals(extension) || "jpeg".equals(extension)) {
            contentType = "image/jpeg";
        } else if ("png".equals(extension)) {
            contentType = "image/png";
        } else if ("gif".equals(extension)) {
            contentType = "image/gif";
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
    String sid = authenticate(); // Usa tu método existente
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
    
    public boolean ligarDocumentos(int solicitud,int documento){
        DocumentosDao metodos=new DocumentosDao();
        return metodos.ligarDocumentos(documento, solicitud);
    }
    
}
