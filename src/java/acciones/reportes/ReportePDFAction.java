/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.reportes;

/**
 *
 * @author hugoeav
 */
import acciones.documentos.DocumentosAcciones;
import beans.usuarios.Documento;
import beans.usuarios.Registro;
import beans.usuarios.Solicitud;
import com.opensymphony.xwork2.ActionSupport;
import daos.conexion.ConexionDBP;
import daos.documentos.DocumentosDao;
import daos.registros.RegistroDao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;

public class ReportePDFAction extends ActionSupport {

    private InputStream inputStream;
    private Documento fotos;
    

    
    public String pago() throws Exception {
        // Cargar el archivo .jasper desde la carpeta "reportes"
        InputStream jasperStream =ServletActionContext.getServletContext().getResourceAsStream("/reportes/recibo_de_pago.jasper");      
        if (jasperStream == null) {
            throw new Exception("No se encontró el archivo del reporte.");
        }
        // Parámetros del reporte (si los hay)
        String ruta_logo = ServletActionContext.getServletContext().getRealPath("/img/logo Servicios de Salud.jpeg");
        BufferedImage originalImage = ImageIO.read(new File(ruta_logo));
        try (Connection conn = ConexionDBP.getConnection()){
            HashMap<String, Object> params = new HashMap<>();
            params.put("imagen_logo", originalImage);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, conn);
            // Exportar a PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            inputStream = new ByteArrayInputStream(pdfBytes);
        }
        return SUCCESS;
    }
    
    public boolean crearPDFs(Solicitud solicitud) throws Exception{
        String rutaPDFAdmin="/reportes/registroProfesional.jasper";
        String nombrePDFAdmin="registro_"+solicitud.getUsuario().getCurp()+".pdf";
        String nombrePDFUsuario="Acuse_"+solicitud.getUsuario().getCurp()+".pdf";
        String rutaPDFUsuario="/reportes/registroProfesionalUsuario.jasper";
        registro(solicitud,rutaPDFAdmin,nombrePDFAdmin,"registroF");
        registro(solicitud,rutaPDFUsuario,nombrePDFUsuario,"registroFUsuario");
        return true;
    }
    
    public String registro(Solicitud solicitud, String rutaPDFComp, String nombreArch,String tipo) throws Exception {
        DocumentosDao doc_metodos=new DocumentosDao();
        DocumentosAcciones metodos=new DocumentosAcciones();
        String ruta_logo = ServletActionContext.getServletContext().getRealPath("/img/logo Servicios de Salud.jpeg");
        fotos=doc_metodos.encuentraFotos(solicitud);
        String rutaImagenNAS = fotos.getRuta_archivo(); // Ruta NAS relativa
        System.out.println("LA SOLICITUD ES "+solicitud.getId_solicitud());
        BufferedImage logoImage = ImageIO.read(new File(ruta_logo));
        BufferedImage imagenDeNas = metodos.getImageFromNas(rutaImagenNAS);
        // Cargar el archivo .jasper desde la carpeta "reportes"
        InputStream jasperStream = ServletActionContext.getServletContext().getResourceAsStream(rutaPDFComp);

        if (jasperStream == null) {
            throw new Exception("No se encontró el archivo del reporte.");
        }
        // Parámetros del reporte 
        try (Connection conn = ConexionDBP.getConnection()) {
            RegistroDao registro_metodos=new RegistroDao();
            Registro registroData=registro_metodos.obtenerInfoReg(solicitud);
            HashMap<String, Object> params = new HashMap<>();
            params.put("imagen_logo", logoImage);
            params.put("imagen_foto", imagenDeNas);
            params.put("numero_registro", registroData.getNumero_registro());
            params.put("curp", registroData.getUsuario().getCurp());
            params.put("nombre_completo", registroData.getNombre_completo());
            params.put("calle_completa", registroData.getCalle_completa());
            params.put("colonia_completa", registroData.getColonia_completa());
            params.put("ciudad_completa", registroData.getCiudad_completa());
            params.put("grados", registroData.getGrados());
            params.put("cedulas", registroData.getCedulas());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, conn);
            // Exportar a PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            inputStream = new ByteArrayInputStream(pdfBytes);
            String registroFileName =nombreArch;
            File registro = File.createTempFile("registro_", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(registro)) {
                fos.write(pdfBytes);
                fos.flush();
            }
            int id_documento=metodos.subirArchivo(registro, registroFileName, tipo, solicitud.getUsuario());
            metodos.ligarDocumentos(solicitud.getId_solicitud(), id_documento);
        }

        return SUCCESS;
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
}