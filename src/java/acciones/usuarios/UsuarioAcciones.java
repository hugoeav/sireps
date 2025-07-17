/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.usuarios;

import beans.usuarios.Grado;
import beans.usuarios.Registro;
import beans.usuarios.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import daos.usuarios.UsuariosDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author hugoeav
 */
public class UsuarioAcciones extends ActionSupport implements SessionAware {
    private Map<String, Object> sessionn;
    private String resultMessage;
    private Usuario usuarioInput;
    private Grado gradoInput;
    
    
    public String redirige(){
        String curp = (String) sessionn.get("curp");
        String mensaje = (String) sessionn.get("resultMessage");
        if(mensaje != null && mensaje != ""){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        request.setAttribute("resultMessage", mensaje);
        }
       
        return SUCCESS;
    }
    
    public Usuario usuarioAdmin(){
        Usuario usuario=new Usuario();
        usuario.setCurp("SUJEY.LOPEZ");
        usuario.setNombre("SUJEY");
        usuario.setPrimerApellido("LOPEZ");
        usuario.setContrasena("calidad**");
        return usuario;
    }
    
    public Usuario usuarioAdminTI(){
        Usuario usuario=new Usuario();
        usuario.setCurp("ADMIN");
        usuario.setNombre("Diseño");
        usuario.setPrimerApellido("de Sistemas Institucionales");
        usuario.setContrasena("tecnologias**");
        return usuario;
    }
     
     public String validaNuevoUsuario() throws SQLException, IOException{
        UsuariosDAO metodos=new UsuariosDAO();
        boolean curpInBD=metodos.validaNuevoUsuario(usuarioInput);
        //sessionn.put("curp", usuarioInput.getCurp());
        if(!curpInBD){
            sessionn.put("curpValidada", usuarioInput.getCurp());
            return "redirect";
        }
        
        resultMessage = "Hubo un error al registrar. CURP ya registrada";
        sessionn.put("resultMessage", resultMessage);
        return "redirectError";
    }
     
     public String registraUsuario() throws SQLException, IOException{
         UsuariosDAO metodos=new UsuariosDAO();
         boolean registrado=metodos.registraNuevoUsuario(usuarioInput);
         if(registrado){
            Usuario usuario=metodos.inicioSesion(usuarioInput);
            sessionn.put("usuario", usuario); 
             System.out.println("LA CEDULA INGRESADA EN ACCIONES ES: "+gradoInput.getCedula());
            if(metodos.buscaCedula(gradoInput)){
                
            }
            return "redirect";
         }
         else{
             return "redirectError";
         }
     }
     
     public String iniciarSesion() throws SQLException, IOException{
         UsuariosDAO metodos=new UsuariosDAO();
         Usuario admin=usuarioAdmin();
         Usuario adminTI=usuarioAdminTI();
         sessionn.put("curp", usuarioInput.getCurp());
         if((usuarioInput.getCurp().equals(admin.getCurp()) && usuarioInput.getContrasena().equals(admin.getContrasena())) || (usuarioInput.getCurp().equals(adminTI.getCurp()) && usuarioInput.getContrasena().equals(adminTI.getContrasena()))){
             
                sessionn.put("role", "admin");
                
                sessionn.put("usuario", admin);
                return "redirect";
         }
         else{
             Usuario usuarioLoggedIn=metodos.inicioSesion(usuarioInput);
            if(usuarioLoggedIn !=null){
                sessionn.put("usuario", usuarioLoggedIn);
                return "redirect";
            }
         }
         
        resultMessage = "Hubo un error al iniciar sesión. CURP o contraseña incorrectos";
        sessionn.put("resultMessage", resultMessage);
         return "redirectError";
     }
     
     public String ejecutarLogout() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        session.invalidate();  // Eliminar la sesión
        return "redirect";
    }

    public void setUsuarioInput(Usuario usuarioInput) {
        this.usuarioInput = usuarioInput;
    }

    public Usuario getUsuarioInput() {
        return usuarioInput;
    }
    
    @Override
     public void setSession(Map<String, Object> session) {
        this.sessionn = session;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setGradoInput(Grado gradoInput) {
        this.gradoInput = gradoInput;
    }

    public Grado getGradoInput() {
        return gradoInput;
    }
    
}
