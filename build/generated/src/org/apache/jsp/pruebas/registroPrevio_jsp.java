package org.apache.jsp.pruebas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class registroPrevio_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/pruebas/../includes/cabecera.jspf");
    _jspx_dependants.add("/pruebas/../includes/pie.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_elseif_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_property_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_else;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_a_class_action;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_s_elseif_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_property_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_else = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_a_class_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_s_elseif_test.release();
    _jspx_tagPool_s_property_value_nobody.release();
    _jspx_tagPool_s_if_test.release();
    _jspx_tagPool_s_else.release();
    _jspx_tagPool_s_a_class_action.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <!-- Bootstrap CDN -->\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\">\r\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/@ckeditor/ckeditor5-build-classic@37.0.1/build/ckeditor.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print( request.getContextPath() );
      out.write("/css/myStyle.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css\">\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("    <!-- jQuery (requerido por Select2) -->\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- CSS de Select2 -->\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("    <!-- JS de Select2 -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js\"></script>\r\n");
      out.write("     <!-- Spinner -->\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!-- Cabecera -->\r\n");
      out.write("<nav class=\"navbar navbar-expand-lg custom-header navbar-dark custom-header\">\r\n");
      out.write("    <div class=\"container-fluid\">\r\n");
      out.write("        <!-- Logo -->\r\n");
      out.write("        <a class=\"navbar-brand\" href=\"");
      out.print( request.getContextPath() );
      out.write("/index.jsp\">\r\n");
      out.write("            <img class=\"logo-container\" src=\"");
      out.print( request.getContextPath() );
      out.write("/img/logo Servicios de Salud.png\" alt=\"Logo Salud\" width=\"300\" height=\"90\" />\r\n");
      out.write("        </a>\r\n");
      out.write("\r\n");
      out.write("        <!-- Botón para menú móvil -->\r\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\">\r\n");
      out.write("            <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("        </button>\r\n");
      out.write("        \r\n");
      out.write("       \r\n");
      out.write("        <!-- Menú -->\r\n");
      out.write("        ");
      //  s:if
      org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_0 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
      _jspx_th_s_if_0.setPageContext(_jspx_page_context);
      _jspx_th_s_if_0.setParent(null);
      _jspx_th_s_if_0.setTest("#session.usuario != null");
      int _jspx_eval_s_if_0 = _jspx_th_s_if_0.doStartTag();
      if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_if_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_if_0.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("        <div class=\"collapse navbar-collapse d-flex justify-content-between w-100\" id=\"navbarNav\">\r\n");
          out.write("            \r\n");
          out.write("            <ul class=\"navbar-nav\">\r\n");
          out.write("                ");
          if (_jspx_meth_s_if_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                ");
          //  s:else
          org.apache.struts2.views.jsp.ElseTag _jspx_th_s_else_0 = (org.apache.struts2.views.jsp.ElseTag) _jspx_tagPool_s_else.get(org.apache.struts2.views.jsp.ElseTag.class);
          _jspx_th_s_else_0.setPageContext(_jspx_page_context);
          _jspx_th_s_else_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
          int _jspx_eval_s_else_0 = _jspx_th_s_else_0.doStartTag();
          if (_jspx_eval_s_else_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_else_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_else_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_else_0.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("                    <li class=\"nav-item\">\r\n");
              out.write("                        <a class=\"nav-link\" href=\"");
              out.print( request.getContextPath() );
              out.write("/index.jsp\">Inicio</a>\r\n");
              out.write("                    </li>\r\n");
              out.write("\r\n");
              out.write("                    <li class=\"nav-item dropdown\">\r\n");
              out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"catalogosDropdown\" role=\"button\" data-bs-toggle=\"dropdown\">\r\n");
              out.write("                             Nueva Solicitud\r\n");
              out.write("                         </a>\r\n");
              out.write("                        <ul class=\"dropdown-menu\">\r\n");
              out.write("                            <li><a class=\"dropdown-item\" href=\"nuevaSolicitudPV\">Registro de Titulo Primera Vez</a></li>\r\n");
              out.write("                            <li><a class=\"dropdown-item\" href=\"");
              out.print( request.getContextPath() );
              out.write("/solicitudes/formcarr.jsp\">Actualización de Especialidad y/o Maestría</a></li>\r\n");
              out.write("                            <li><a class=\"dropdown-item\" href=\"listarSubdir\">Reposición de Registro de Titulo</a></li>\r\n");
              out.write("                            <li><a class=\"dropdown-item\" href=\"listaDpto\">Registrar Cambio de Domicilio</a></li>\r\n");
              out.write("                        </ul>\r\n");
              out.write("                    </li>\r\n");
              out.write("\r\n");
              out.write("                    <li class=\"nav-item\">\r\n");
              out.write("                        ");
              if (_jspx_meth_s_a_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_else_0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("                        <!--<a class=\"nav-link\" href=\"");
              out.print( request.getContextPath() );
              out.write("/informes/nuevoInf.jsp\">Mis Documentos</a>-->\r\n");
              out.write("                    </li>\r\n");
              out.write("\r\n");
              out.write("                    <li class=\"nav-item\">\r\n");
              out.write("                        ");
              if (_jspx_meth_s_a_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_else_0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("                    </li>\r\n");
              out.write("                    <li class=\"nav-item\">\r\n");
              out.write("                        ");
              if (_jspx_meth_s_a_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_else_0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("                    </li>\r\n");
              out.write("                ");
              int evalDoAfterBody = _jspx_th_s_else_0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_s_else_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_s_else_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_s_else.reuse(_jspx_th_s_else_0);
            return;
          }
          _jspx_tagPool_s_else.reuse(_jspx_th_s_else_0);
          out.write("  \r\n");
          out.write("               \r\n");
          out.write("            </ul>\r\n");
          out.write("           \r\n");
          out.write("            <div class=\"profile-container \">\r\n");
          out.write("                <div class=\"profile-info\">\r\n");
          out.write("                    ");
          if (_jspx_meth_s_property_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_0, _jspx_page_context))
            return;
          out.write(' ');
          if (_jspx_meth_s_property_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_0, _jspx_page_context))
            return;
          out.write("<br>\r\n");
          out.write("                    \r\n");
          out.write("                    ");
          if (_jspx_meth_s_if_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                    ");
          if (_jspx_meth_s_elseif_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                    ");
          if (_jspx_meth_s_else_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                    \r\n");
          out.write("                        \r\n");
          out.write("                </div>\r\n");
          out.write("                <div class=\"dropdown\">\r\n");
          out.write("                    <a href=\"#\" class=\"d-flex align-items-center text-decoration-none dropdown-toggle\" id=\"profileDropdown\" data-bs-toggle=\"dropdown\">\r\n");
          out.write("                        <div class=\"profile-icon\">\r\n");
          out.write("                            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"36\" height=\"36\" fill=\"#3c8b41\" class=\"bi bi-person-fill\" viewBox=\"0 0 16 16\">\r\n");
          out.write("                                <path d=\"M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6\"/>\r\n");
          out.write("                            </svg>\r\n");
          out.write("                        </div>\r\n");
          out.write("                    </a>\r\n");
          out.write("                    <ul class=\"dropdown-menu dropdown-menu-end\">\r\n");
          out.write("                        <li><a class=\"dropdown-item\" href=\"");
          out.print( request.getContextPath() );
          out.write("/usuarios/perfilUsuario.jsp\">Ver Perfil</a></li>\r\n");
          out.write("                        <li><hr class=\"dropdown-divider\"></li>\r\n");
          out.write("                        <li>\r\n");
          out.write("                            <form action=\"logout\" method=\"POST\" style=\"margin: 0;\">\r\n");
          out.write("                                <button type=\"submit\" class=\"dropdown-item\">Cerrar Sesión</button>\r\n");
          out.write("                            </form>\r\n");
          out.write("                        </li>\r\n");
          out.write("                    </ul>\r\n");
          out.write("                </div>\r\n");
          out.write("                    \r\n");
          out.write("            </div>\r\n");
          out.write("        </div>\r\n");
          out.write("     ");
          int evalDoAfterBody = _jspx_th_s_if_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_s_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_0);
        return;
      }
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_0);
      out.write("\r\n");
      out.write("        ");
      if (_jspx_meth_s_else_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("window.onload = function() {\r\n");
      out.write("    ");
 
        String resultMessage = (String) request.getAttribute("resultMessage");
        if (resultMessage != null) { 
            String type = resultMessage.contains("error") ? "error" : "success";
    
      out.write("\r\n");
      out.write("            console.log(\"Mostrando alerta con mensaje: ");
      out.print( resultMessage );
      out.write(" y tipo: ");
      out.print( type );
      out.write("\");\r\n");
      out.write("            showAlert(\"");
      out.print( resultMessage );
      out.write("\", \"");
      out.print( type );
      out.write("\");\r\n");
      out.write("            \r\n");
      out.write("    ");
 } 
        
    
      out.write("\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("function showAlert(message, type) {\r\n");
      out.write("    Swal.fire({\r\n");
      out.write("        icon: type,  \r\n");
      out.write("        title: type === \"success\" ? \"¡Éxito!\" : \"¡Error!\",\r\n");
      out.write("        text: message,\r\n");
      out.write("        confirmButtonColor: type === \"success\" ? \"#28a745\" : \"#dc3545\",\r\n");
      out.write("        timer: 4500,  \r\n");
      out.write("        showConfirmButton: false  \r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\n");
      out.write("<main class=\"flex-grow-1 container my-4\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row justify-content-center\">\n");
      out.write("            <div class=\"col-lg-10\">\n");
      out.write("                <div class=\"card shadow-lg border-0 rounded-lg mt-4\">\n");
      out.write("                    <div class=\"card-header text-white text-center\" style=\"background-color: #d9534f;\">\n");
      out.write("                        <h4 class=\"my-2\">Cédula ya registrada - Solicitud de Reclamo</h4>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <div class=\"alert alert-warning text-center\">\n");
      out.write("                            <i class=\"bi bi-exclamation-triangle-fill me-2\"></i>\n");
      out.write("                            Hemos detectado que la <strong>cédula profesional ingresada ya está registrada</strong> en nuestro sistema.\n");
      out.write("                            <br>Se ha creado automáticamente una <strong>solicitud de reclamo</strong> para verificar tu identidad.\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <p class=\"mb-4 text-center\">Para continuar, por favor actualiza la siguiente información para validar tu identidad y continuar con tu proceso:</p>\n");
      out.write("\n");
      out.write("                        <form id=\"formActualizaDatos\" method=\"post\" action=\"actualizaDatosReclamo.action\">\n");
      out.write("                            <div class=\"row\">\n");
      out.write("                                <!-- Grados Académicos -->\n");
      out.write("                                <div class=\"col-md-12 mb-4\">\n");
      out.write("                                    <label for=\"grados\" class=\"form-label fw-bold\">Grados Académicos</label>\n");
      out.write("                                    <select class=\"form-select select2\" id=\"grados\" name=\"usuarioInput.grados[]\" multiple required>\n");
      out.write("                                        <option value=\"Tecnico\">Técnico</option>\n");
      out.write("                                        <option value=\"Licenciatura\">Licenciatura</option>\n");
      out.write("                                        <option value=\"Especialidad\">Especialidad</option>\n");
      out.write("                                        <option value=\"Maestría\">Maestría</option>\n");
      out.write("                                        <option value=\"Doctorado\">Doctorado</option>\n");
      out.write("                                    </select>\n");
      out.write("                                    <div class=\"form-text\">Puedes seleccionar más de uno si aplica.</div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <!-- Dirección Particular -->\n");
      out.write("                                <div class=\"col-md-12 mb-4\">\n");
      out.write("                                    <label class=\"form-label fw-bold\" for=\"direccionParticular\">Dirección Particular</label>\n");
      out.write("                                    <textarea class=\"form-control\" id=\"direccionParticular\" name=\"usuarioInput.direccionParticular\" rows=\"3\" required></textarea>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <!-- Dirección Laboral -->\n");
      out.write("                                <div class=\"col-md-12 mb-4\">\n");
      out.write("                                    <label class=\"form-label fw-bold\" for=\"direccionLaboral\">Dirección Laboral</label>\n");
      out.write("                                    <textarea class=\"form-control\" id=\"direccionLaboral\" name=\"usuarioInput.direccionLaboral\" rows=\"3\" required></textarea>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <div class=\"d-grid mt-4\">\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-success btn-lg\">Actualizar Información</button>\n");
      out.write("                            </div>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"card-footer text-muted text-center\">\n");
      out.write("                        Servicios de Salud de San Luis Potosí\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</main>\n");
      out.write("\n");
      out.write('\r');
      out.write('\n');
  
        session.removeAttribute("resultMessage");
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("<footer class=\" text-center text-white py-4\" style=\"background-color: #3c8b41;\"> \r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <p>Servicios de Salud de San Luis Potosí, Prolongación Calzada de Guadalupe #5850 Lomas de la virgen CP 78380 | Tel: 8341100 Ext 21351</p>\r\n");
      out.write("        <p style=\"font-size: 0.8em;\">\r\n");
      out.write("            Consulta nuestro <a href=\"aviso_privacidad\" class=\"text-white\" style=\"text-decoration: underline;\">aviso de privacidad</a>\r\n");
      out.write("        </p>\r\n");
      out.write("        \r\n");
      out.write("    </div>\r\n");
      out.write("</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_if_1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_1 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_1.setPageContext(_jspx_page_context);
    _jspx_th_s_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
    _jspx_th_s_if_1.setTest("#session.role == 'admin'");
    int _jspx_eval_s_if_1 = _jspx_th_s_if_1.doStartTag();
    if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    <li class=\"nav-item\">\r\n");
        out.write("                        ");
        if (_jspx_meth_s_a_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    </li>\r\n");
        out.write("                    <li class=\"nav-item\">\r\n");
        out.write("                        ");
        if (_jspx_meth_s_a_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    </li>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_1);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_1);
    return false;
  }

  private boolean _jspx_meth_s_a_0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_a_0 = (org.apache.struts2.views.jsp.ui.AnchorTag) _jspx_tagPool_s_a_class_action.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_a_0.setPageContext(_jspx_page_context);
    _jspx_th_s_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_1);
    _jspx_th_s_a_0.setAction("solicitudesAdmin");
    _jspx_th_s_a_0.setCssClass("nav-link");
    int _jspx_eval_s_a_0 = _jspx_th_s_a_0.doStartTag();
    if (_jspx_eval_s_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_a_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_a_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_a_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("                        <span>Bandeja de Solicitudes</span>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_a_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_a_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_0);
      return true;
    }
    _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_0);
    return false;
  }

  private boolean _jspx_meth_s_a_1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_a_1 = (org.apache.struts2.views.jsp.ui.AnchorTag) _jspx_tagPool_s_a_class_action.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_a_1.setPageContext(_jspx_page_context);
    _jspx_th_s_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_1);
    _jspx_th_s_a_1.setAction("variables");
    _jspx_th_s_a_1.setCssClass("nav-link");
    int _jspx_eval_s_a_1 = _jspx_th_s_a_1.doStartTag();
    if (_jspx_eval_s_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_a_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_a_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_a_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("                        <span>Datos Ficha de Pago</span>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_a_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_a_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_1);
      return true;
    }
    _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_1);
    return false;
  }

  private boolean _jspx_meth_s_a_2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_else_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_a_2 = (org.apache.struts2.views.jsp.ui.AnchorTag) _jspx_tagPool_s_a_class_action.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_a_2.setPageContext(_jspx_page_context);
    _jspx_th_s_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_else_0);
    _jspx_th_s_a_2.setAction("documentos");
    _jspx_th_s_a_2.setCssClass("nav-link");
    int _jspx_eval_s_a_2 = _jspx_th_s_a_2.doStartTag();
    if (_jspx_eval_s_a_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_a_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_a_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_a_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("                        <span>Mis Documentos</span>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_a_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_a_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_2);
      return true;
    }
    _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_2);
    return false;
  }

  private boolean _jspx_meth_s_a_3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_else_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_a_3 = (org.apache.struts2.views.jsp.ui.AnchorTag) _jspx_tagPool_s_a_class_action.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_a_3.setPageContext(_jspx_page_context);
    _jspx_th_s_a_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_else_0);
    _jspx_th_s_a_3.setAction("solicitudes");
    _jspx_th_s_a_3.setCssClass("nav-link");
    int _jspx_eval_s_a_3 = _jspx_th_s_a_3.doStartTag();
    if (_jspx_eval_s_a_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_a_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_a_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_a_3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("                        <span>Mis Solicitudes</span>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_a_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_a_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_a_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_3);
      return true;
    }
    _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_3);
    return false;
  }

  private boolean _jspx_meth_s_a_4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_else_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_a_4 = (org.apache.struts2.views.jsp.ui.AnchorTag) _jspx_tagPool_s_a_class_action.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_a_4.setPageContext(_jspx_page_context);
    _jspx_th_s_a_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_else_0);
    _jspx_th_s_a_4.setAction("reportePdf");
    _jspx_th_s_a_4.setCssClass("nav-link");
    int _jspx_eval_s_a_4 = _jspx_th_s_a_4.doStartTag();
    if (_jspx_eval_s_a_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_a_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_a_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_a_4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("                        <span>Descargar Referencia de Pago</span>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_a_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_a_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_a_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_4);
      return true;
    }
    _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_4);
    return false;
  }

  private boolean _jspx_meth_s_property_0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_0 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_0.setPageContext(_jspx_page_context);
    _jspx_th_s_property_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
    _jspx_th_s_property_0.setValue("#session.usuario.nombre");
    int _jspx_eval_s_property_0 = _jspx_th_s_property_0.doStartTag();
    if (_jspx_th_s_property_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_0);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_0);
    return false;
  }

  private boolean _jspx_meth_s_property_1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_1 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_1.setPageContext(_jspx_page_context);
    _jspx_th_s_property_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
    _jspx_th_s_property_1.setValue("#session.usuario.primerApellido");
    int _jspx_eval_s_property_1 = _jspx_th_s_property_1.doStartTag();
    if (_jspx_th_s_property_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_1);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_1);
    return false;
  }

  private boolean _jspx_meth_s_if_2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_2 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_2.setPageContext(_jspx_page_context);
    _jspx_th_s_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
    _jspx_th_s_if_2.setTest("#session.usuario.nomDpto != null");
    int _jspx_eval_s_if_2 = _jspx_th_s_if_2.doStartTag();
    if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                        ");
        if (_jspx_meth_s_property_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_s_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_2);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_2);
    return false;
  }

  private boolean _jspx_meth_s_property_2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_2 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_2.setPageContext(_jspx_page_context);
    _jspx_th_s_property_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_2);
    _jspx_th_s_property_2.setValue("#session.usuario.nomDpto");
    int _jspx_eval_s_property_2 = _jspx_th_s_property_2.doStartTag();
    if (_jspx_th_s_property_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_2);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_2);
    return false;
  }

  private boolean _jspx_meth_s_elseif_0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_elseif_0 = (org.apache.struts2.views.jsp.ElseIfTag) _jspx_tagPool_s_elseif_test.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_elseif_0.setPageContext(_jspx_page_context);
    _jspx_th_s_elseif_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
    _jspx_th_s_elseif_0.setTest("#session.usuario.nomSubdir != null");
    int _jspx_eval_s_elseif_0 = _jspx_th_s_elseif_0.doStartTag();
    if (_jspx_eval_s_elseif_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_elseif_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_elseif_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_elseif_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                        ");
        if (_jspx_meth_s_property_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_elseif_0, _jspx_page_context))
          return true;
        out.write("calc\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_s_elseif_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_elseif_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_elseif_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_elseif_test.reuse(_jspx_th_s_elseif_0);
      return true;
    }
    _jspx_tagPool_s_elseif_test.reuse(_jspx_th_s_elseif_0);
    return false;
  }

  private boolean _jspx_meth_s_property_3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_elseif_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_3 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_3.setPageContext(_jspx_page_context);
    _jspx_th_s_property_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_elseif_0);
    _jspx_th_s_property_3.setValue("#session.usuario.nomSubdir");
    int _jspx_eval_s_property_3 = _jspx_th_s_property_3.doStartTag();
    if (_jspx_th_s_property_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_3);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_3);
    return false;
  }

  private boolean _jspx_meth_s_else_1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_else_1 = (org.apache.struts2.views.jsp.ElseTag) _jspx_tagPool_s_else.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_else_1.setPageContext(_jspx_page_context);
    _jspx_th_s_else_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_0);
    int _jspx_eval_s_else_1 = _jspx_th_s_else_1.doStartTag();
    if (_jspx_eval_s_else_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_else_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_else_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_else_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                        ");
        if (_jspx_meth_s_property_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_else_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_s_else_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_else_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_else_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_else.reuse(_jspx_th_s_else_1);
      return true;
    }
    _jspx_tagPool_s_else.reuse(_jspx_th_s_else_1);
    return false;
  }

  private boolean _jspx_meth_s_property_4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_else_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_4 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_4.setPageContext(_jspx_page_context);
    _jspx_th_s_property_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_else_1);
    _jspx_th_s_property_4.setValue("#session.usuario.nomDir");
    int _jspx_eval_s_property_4 = _jspx_th_s_property_4.doStartTag();
    if (_jspx_th_s_property_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_4);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_4);
    return false;
  }

  private boolean _jspx_meth_s_else_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_else_2 = (org.apache.struts2.views.jsp.ElseTag) _jspx_tagPool_s_else.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_else_2.setPageContext(_jspx_page_context);
    _jspx_th_s_else_2.setParent(null);
    int _jspx_eval_s_else_2 = _jspx_th_s_else_2.doStartTag();
    if (_jspx_eval_s_else_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_else_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_else_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_else_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            <div class=\"profile-container\">\r\n");
        out.write("                ");
        if (_jspx_meth_s_a_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_else_2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("            </div>\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_s_else_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_else_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_else_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_else.reuse(_jspx_th_s_else_2);
      return true;
    }
    _jspx_tagPool_s_else.reuse(_jspx_th_s_else_2);
    return false;
  }

  private boolean _jspx_meth_s_a_5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_else_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_a_5 = (org.apache.struts2.views.jsp.ui.AnchorTag) _jspx_tagPool_s_a_class_action.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_a_5.setPageContext(_jspx_page_context);
    _jspx_th_s_a_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_else_2);
    _jspx_th_s_a_5.setAction("iniciarSesion");
    _jspx_th_s_a_5.setCssClass("btn btn-outline-light btn-sm d-flex align-items-center shadow-sm");
    int _jspx_eval_s_a_5 = _jspx_th_s_a_5.doStartTag();
    if (_jspx_eval_s_a_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_a_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_a_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_a_5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    <i class=\"bi bi-person-circle me-2\"></i> Iniciar Sesión\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_a_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_a_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_a_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_5);
      return true;
    }
    _jspx_tagPool_s_a_class_action.reuse(_jspx_th_s_a_5);
    return false;
  }
}
