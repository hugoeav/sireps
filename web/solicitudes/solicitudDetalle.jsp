<%-- 
    Document   : solicitudDetalle
    Created on : 14/05/2025, 01:58:33 PM
    Author     : hugoeav
--%>

<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<main class="container-fluid mt-4">
  <script src="${pageContext.request.contextPath}/js/utileriasSolicitudDetalle.js"></script>  
  <div class="container">

    <div class="card shadow-lg border-0 mb-4">
      <div class="card-header text-white d-flex justify-content-between align-items-center" style="background-color: #3c8b41;">
        <h5 class="mb-0">Detalle de la Solicitud</h5>
        <span class="fw-bold">Folio: <s:property value="solicitudInput.id_solicitud" /></span>
      </div>
      <div class="card-body">

        <!-- Sección: Datos del Solicitante -->
        <h6 class="text-muted mb-2">Datos Personales</h6>
        <div class="row mb-2">
          <div class="col-md-3"><strong>Nombre:</strong> <s:property value="solicitudInput.usuario.nombre" /> <s:property value="solicitudInput.usuario.primerApellido" /> <s:property value="solicitudInput.usuario.segundoApellido" /></div>
          <div class="col-md-3"><strong>CURP:</strong> <s:property value="solicitudInput.usuario.curp" /></div>
          <div class="col-md-3"><strong>RFC:</strong> <s:property value="solicitudInput.usuario.rfc" /></div>
          
        </div>
        <div class="row mb-2">
            <div class="col-md-3"><strong>Teléfono Particular:</strong> <s:property value="solicitudInput.usuario.telefono" /></div>
            <div class="col-md-3"><strong>Correo:</strong> <s:property value="solicitudInput.usuario.correo" /></div>
             <div class="col-md-3"><strong>Teléfono Laboral:</strong> <s:property value="solicitudInput.usuario.telefonoLab" /></div>
        </div>
        
       <hr>
        <h6 class="text-muted mt-4 text-center"><s:if test="domicilioInput.predeterminado"><i class="bi bi-star-fill text-warning" title="Seleccionaste la dirección particular como predeterminada"></s:if></i>Dirección Particular</h6>
        <div class="row mb-2">
          <div class="col-md-4"><strong>Calle:</strong> <s:property value="domicilioInput.calle" /></div>
          <div class="col-md-2"><strong>No. Ext.:</strong> <s:property value="domicilioInput.numero_ext" /></div>
          <div class="col-md-2"><strong>No. Int.:</strong><s:if test="domicilioInput.numero_int != null"> <s:property value="domicilioInput.numero_int" /></s:if><s:else> S/N</s:else></div>
          <div class="col-md-4"><strong>Asentamiento y Nombre</strong> <s:property value="domicilioInput.tipo_asentamiento" /> <s:if test="domicilioInput.colonia != null"> <s:property value="domicilioInput.colonia" /></s:if><s:else><s:property value="domicilioInput.colonia_manual" /></s:else></div>
        </div>
        <div class="row mb-2">
          <div class="col-md-3"><strong>Localidad:</strong> <s:property value="domicilioInput.localidad" /></div>
          <div class="col-md-3"><strong>Municipio:</strong> <s:property value="domicilioInput.municipio" /></div>
          <div class="col-md-3"><strong>Estado:</strong> <s:property value="domicilioInput.estado" /></div>
          <div class="col-md-2">
            <strong>Jurisdicción:</strong>
            <s:property value="domicilioInput.num_juris" /> - <s:property value="domicilioInput.juris" />
          </div>
         
        </div>
          <hr>
            <!-- Dirección Laboral -->
            <h6 class="text-muted mt-4 text-center"><s:if test="domicilioLabInput.predeterminado"><i class="bi bi-star-fill text-warning" title="Seleccionaste la dirección laboral como predeterminada"></i></s:if>Dirección Laboral</h6>
            <div class="row mb-2">
                <div class="col-md-3"><strong>Institución:</strong> <s:property value="domicilioLabInput.nombre" /></div>

                <div class="col-md-5">
                  <div class="row">
                    <div class="col-md-6"><strong>Calle:</strong> <s:property value="domicilioLabInput.calle" /></div>
                    <div class="col-md-3"><strong>No. Ext.:</strong> <s:property value="domicilioLabInput.numero_ext" /></div>
                    <div class="col-md-3"><strong>No. Int.:</strong>
                      <s:if test="domicilioLabInput.numero_int != null">
                        <s:property value="domicilioLabInput.numero_int" />
                      </s:if>
                      <s:else>S/N</s:else>
                    </div>
                  </div>
                </div>

                <div class="col-md-4">
                  <strong>Asentamiento y Nombre:</strong>
                  <s:property value="domicilioLabInput.tipo_asentamiento" />
                  <s:if test="domicilioLabInput.colonia != null"><s:property value="domicilioLabInput.colonia" /></s:if><s:else><s:property value="domicilioLabInput.colonia_manual" /></s:else>
                </div>
              </div>
            <div class="row mb-2">
              <div class="col-md-3"><strong>Localidad:</strong> <s:property value="domicilioLabInput.localidad" /></div>
              <div class="col-md-3"><strong>Municipio:</strong> <s:property value="domicilioLabInput.municipio" /></div>
              <div class="col-md-3"><strong>Estado:</strong> <s:property value="domicilioLabInput.estado" /></div>
              <div class="col-md-2">
                <strong>Jurisdicción:</strong>
                <s:property value="domicilioLabInput.num_juris" /> - <s:property value="domicilioLabInput.juris" />
              </div>
            </div>

        <hr>

        <!-- Sección: Grados Académicos -->
        <h6 class="text-muted mb-3">Grados Académicos</h6>
        <div class="table-responsive">
          <table class="table table-bordered">
            <thead class="table-success">
              <tr>
                <th>Grado</th>
                <th>Institución</th>
                <th>Cédula</th>
              </tr>
            </thead>
            <tbody>
              <s:iterator value="grados">
              <tr>
                <td><s:property value="nombre" /></td>
                <td><s:property value="institucion" /></td>
                <td><s:property value="cedula" /></td>
              </tr>
              </s:iterator>
            </tbody>
          </table>
        </div>

        <hr>

        <!-- Sección: Documentos -->
        <h6 class="text-muted mb-3">Documentos Cargados</h6>
        <div class="table-responsive">
          <table class="table table-striped table-bordered">
            <thead class="table-success">
              <tr>
                <th>Tipo de Documento</th>
                <th>Documento</th>
                <th>Archivo</th>
                <th>Estado</th>
              </tr>
            </thead>
            <tbody>
                 <s:set var="conRegistro" value="%{false}" />
                 <s:set var="conRegistroFinal" value="%{false}" />
              <s:iterator value="documentos">
                  <s:if test="tipo_archivo != 'registroF' && tipo_archivo != 'registroFUsuario' && tipo_archivo != 'registroFinal'">
              <tr>
                 <td><span id="nombre_<s:property value='id_documento' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='tipo_archivo' />";
                        var idSpan = "nombre_<s:property value='id_documento' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script>
                 </td>
                <td><s:property value="nombre" /></td>
                <td> <button type="button" class="btn btn-sm btn-outline-primary" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')">
                  Ver PDF
                </button></td>
                <s:if test="valido == null">
                    <td><span class="badge bg-warning text-dark">En Revisión</span></td>
                
                </s:if>
                <s:elseif test="valido">
                    <td><span class="badge bg-success">Válido</span></td>
                </s:elseif>
                <s:else>
                    <td><span class="badge bg-danger">No válido</span></td>
                </s:else>
              </tr>
                  </s:if>
                  <s:else>
                      
                      <s:if test="tipo_archivo == 'registroFUsuario'">
                            <s:set var="conRegistro" value="%{true}" />
                            <s:set var="rutaRegistro" value="ruta_archivo" />
                      </s:if>
                      <s:else>
                          <s:if test="tipo_archivo == 'registroFinal'">
                              <s:set var="conRegistroFinal" value="%{true}" />
                            <s:set var="rutaRegistroFinal" value="ruta_archivo" />
                          </s:if>
                      </s:else>
                  </s:else>
              </s:iterator>
            </tbody>
          </table>
        </div>
<s:if test="!#conRegistro">
        <hr>
        
        <!-- Sección: Comentarios -->
        <h6 class="text-muted mb-3">Observaciones o Comentarios</h6>
        <div class="alert alert-warning">
          <strong>Revisión:</strong> <s:property value="solicitudInput.observacion" />
        </div>
</s:if>
          <s:if test="#conRegistro">
         <hr>
         <!-- Acuse -->
        <h6 class="mb-3 text-success">Acuse de Registro</h6>
        <div class="mb-3">
          <p>Tu solicitud ha sido aprobada y registrada. Puedes descargar el acuse a continuación:</p>
          <button type="button" class="btn btn-sm btn-outline-primary" onclick="verPdfModal('download?rutaDescarga=<s:property value='#rutaRegistro' />')">
                  Descargar Acuse PDF
                </button>
        </div>
          </s:if>
        <s:if test="#conRegistroFinal">
         <hr>
         <!-- Acuse -->
        <h6 class="mb-3 text-success">Registro Oficial</h6>
        <div class="mb-3">
          <p>Tu registro ha sido cargado y registrado. Puedes descargar el registro oficial a continuación:</p>
          <button type="button" class="btn btn-sm btn-outline-primary" onclick="verPdfModal('download?rutaDescarga=<s:property value='#rutaRegistroFinal' />')">
                  Descargar Registro Oficial PDF
                </button>
        </div>
          </s:if>
      </div>
      <div class="text-center">
      <a href="solicitudes.action" class="btn btn-secondary">
        <i class="bi bi-arrow-left"></i> Regresar
      </a>
      </div>
    </div>

    

  </div>
        <div class="modal fade" id="pdfModal" tabindex="-1" aria-labelledby="pdfModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl" style="max-width: 90%;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Visualizar Documento</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body" style="height: 80vh;">
        <iframe id="iframePdf" src="" width="100%" height="100%" style="border: none;"></iframe>
      </div>
    </div>
  </div>
</div>
</main>
<%@include file="../includes/pie.jspf" %>
