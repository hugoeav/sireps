/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.variables;

import beans.variables.HojaPago;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import daos.variables.DAOVariables;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author hugoeav
 */
public class VariablesAcciones extends ActionSupport implements SessionAware {
    private Map<String, Object> sessionn;
    private String resultMessage;
    HojaPago hojaPago;
    
    public String execute(){
        DAOVariables metodos=new DAOVariables();
         String mensaje = (String) sessionn.get("resultMessage");
        if(mensaje != null && mensaje != ""){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        request.setAttribute("resultMessage", mensaje);
        }

        hojaPago=metodos.detalleHojaPago();
        return SUCCESS;
    }
    
    public String actualizaDatosPago(){
        DAOVariables metodos=new DAOVariables();
        if(metodos.actualizaHojaPago(hojaPago)){
            resultMessage = "Exito al guardar los cambios";
            sessionn.put("resultMessage", resultMessage);
            return SUCCESS;
        }
        else{
            resultMessage = "Hubo un error al guardos los cambios";
            sessionn.put("resultMessage", resultMessage);
            return ERROR;
        }
    }

    public void setHojaPago(HojaPago hojaPago) {
        this.hojaPago = hojaPago;
    }


    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    

    public HojaPago getHojaPago() {
        return hojaPago;
    }

    public Map<String, Object> getSessionn() {
        return sessionn;
    }

    public String getResultMessage() {
        return resultMessage;
    }
    
    @Override
     public void setSession(Map<String, Object> session) {
        this.sessionn = session;
    }
     
}
