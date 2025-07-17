<%-- 
    Document   : bandeja
    Created on : 14/05/2025, 02:08:47 PM
    Author     : Informatica
--%>

<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/utileriasBandeja.js"></script>
<main class="flex-grow-1 d-flex flex-column">
  <div class="container">

    <div class="card shadow-lg border-0">
      <div class="card-header text-white d-flex justify-content-between align-items-center" style="background-color: #3c8b41;">
        <h5 class="mb-0">Bandeja de Nuevas Solicitudes</h5>
      </div>
      <div class="card-body">
       <ul class="nav nav-tabs mb-3" id="docTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="titulo-tab" data-bs-toggle="tab" data-bs-target="#primera" type="button" role="tab">Primera vez</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="cedula-tab" data-bs-toggle="tab" data-bs-target="#especialidad" type="button" role="tab">Actualización de Especialidad</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="comprobante-tab" data-bs-toggle="tab" data-bs-target="#reposicion" type="button" role="tab">Reposición</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="foto-tab" data-bs-toggle="tab" data-bs-target="#domicilio" type="button" role="tab">Cambio de Domicilio</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="foto-tab" data-bs-toggle="tab" data-bs-target="#aprobadas" type="button" role="tab">Aprobadas</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="foto-tab" data-bs-toggle="tab" data-bs-target="#rechazadas" type="button" role="tab">Rechazadas</button>
                            </li>
                        </ul>
           <div class="tab-content" id="docTabsContent">
             <div class="tab-pane fade show active" id="primera" role="tabpanel">  
                <div class="table-responsive">
                  <table id="nuevasTable" class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead class="table-success">
                      <tr>
                        <th>Folio</th>
                        <th>Nombre</th>
                        <th>CURP</th>
                        <th>Fecha de Solicitud</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="primeraVez">
                          <s:if test="tipo =='primeraVez' and status != 'pendienteDocs' and status != 'completa' and status != 'rechazada' ">
                      <tr>
                        <td><s:property value="id_solicitud" /></td>
                        <td><s:property value="usuario.nombre" /> <s:property value="usuario.primerApellido" /> <s:property value="usuario.segundoApellido" /></td>
                        <td><s:property value="usuario.curp" /></td>
                        <td><s:property value="fecha_agregado" /></td>
                        <td><span class="badge bg-warning text-dark" id="status_<s:property value='id_solicitud' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='status' />";
                        var idSpan = "status_<s:property value='id_solicitud' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script></td>
                        <td>
                            <form action="solicitudDetalleAdmin" method="post" style="display:inline;">
                                <input type="hidden" name="solicitudInput.id_solicitud" value="<s:property value='id_solicitud' />" />
                                <button type="submit" class="btn btn-sm btn-primary <s:if test="status == 'nueva'">pulse-animation</s:if>">
                                  <i class="bi bi-eye"></i> Ver Detalles
                                </button>
                              </form>
                        </td>
                      </tr>
                          </s:if>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
             </div>
             <!--Actualización de Especialidad -->
            <div class="tab-pane fade" id="especialidad" role="tabpanel">
            <div class="table-responsive">
                  <table id="especialidadTable" class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead class="table-success">
                      <tr>
                        <th>Folio</th>
                        <th>Nombre</th>
                        <th>CURP</th>
                        <th>Fecha de Solicitud</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="esp"> 
                      <tr>
                        <td><s:property value="id_solicitud" /></td>
                        <td><s:property value="usuario.nombre" /> <s:property value="usuario.primerApellido" /> <s:property value="usuario.segundoApellido" /></td>
                        <td><s:property value="usuario.curp" /></td>
                        <td><s:property value="fecha_agregado" /></td>
                        <td><span class="badge bg-warning text-dark" id="status_<s:property value='id_solicitud' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='status' />";
                        var idSpan = "status_<s:property value='id_solicitud' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script></td>
                        <td>
                          <!--  
                          <a href="detalleSolicitud.jsp?folio=2025-00123" class="btn btn-sm btn-primary">
                            <i class="bi bi-eye"></i> Ver Detalles
                          </a>-->
                          <a href="solicitudDetalleAdmin?solicitudInput.id_solicitud=<s:property value="id_solicitud" />" 
                                class="btn btn-sm btn-primary <s:if test="status == 'nueva'">pulse-animation</s:if>">
                               <i class="bi bi-eye"></i> Ver Detalles
                             </a>
                        </td>
                      </tr>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
            </div>
             <div class="tab-pane fade" id="reposicion" role="tabpanel">
            <div class="table-responsive">
                  <table id="reposicionTable" class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead class="table-success">
                      <tr>
                        <th>Folio</th>
                        <th>Nombre</th>
                        <th>CURP</th>
                        <th>Fecha de Solicitud</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="repo"> 
                      <tr>
                        <td><s:property value="id_solicitud" /></td>
                        <td><s:property value="usuario.nombre" /> <s:property value="usuario.primerApellido" /> <s:property value="usuario.segundoApellido" /></td>
                        <td><s:property value="usuario.curp" /></td>
                        <td><s:property value="fecha_agregado" /></td>
                        <td><span class="badge bg-warning text-dark" id="status_<s:property value='id_solicitud' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='status' />";
                        var idSpan = "status_<s:property value='id_solicitud' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script></span></td>
                        <td>
                          <!--  
                          <a href="detalleSolicitud.jsp?folio=2025-00123" class="btn btn-sm btn-primary">
                            <i class="bi bi-eye"></i> Ver Detalles
                          </a>-->
                          <a href="solicitudDetalleAdmin?solicitudInput.id_solicitud=<s:property value="id_solicitud" />" 
                                class="btn btn-sm btn-primary <s:if test="status == 'nueva'">pulse-animation</s:if>">
                               <i class="bi bi-eye"></i> Ver Detalles
                             </a>
                        </td>
                      </tr>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
            </div>
             <div class="tab-pane fade" id="domicilio" role="tabpanel">
            <div class="table-responsive">
                  <table id="domicilioTable" class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead class="table-success">
                      <tr>
                        <th>Folio</th>
                        <th>Nombre</th>
                        <th>CURP</th>
                        <th>Fecha de Solicitud</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="domicilio"> 
                      <tr>
                        <td><s:property value="id_solicitud" /></td>
                        <td><s:property value="usuario.nombre" /> <s:property value="usuario.primerApellido" /> <s:property value="usuario.segundoApellido" /></td>
                        <td><s:property value="usuario.curp" /></td>
                        <td><s:property value="fecha_agregado" /></td>
                        <td><span class="badge bg-warning text-dark" id="status_<s:property value='id_solicitud' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='status' />";
                        var idSpan = "status_<s:property value='id_solicitud' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script></span></td>
                        <td>
                          <!--  
                          <a href="detalleSolicitud.jsp?folio=2025-00123" class="btn btn-sm btn-primary">
                            <i class="bi bi-eye"></i> Ver Detalles
                          </a>-->
                          <a href="solicitudDetalleAdmin?solicitudInput.id_solicitud=<s:property value="id_solicitud" />" 
                                class="btn btn-sm btn-primary <s:if test="status == 'nueva'">pulse-animation</s:if>">
                               <i class="bi bi-eye"></i> Ver Detalles
                             </a>
                        </td>
                      </tr>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
            </div>
             <div class="tab-pane fade" id="aprobadas" role="tabpanel">
            <div class="table-responsive">
                  <table id="aprobadasTable" class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead class="table-success">
                      <tr>
                        <th>Folio</th>
                        <th>Nombre</th>
                        <th>CURP</th>
                        <th>Fecha de Solicitud</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="aprob"> 
                        <tr>
                          <td data-order="<s:property value="id_solicitud" />"><s:property value="id_solicitud" /></td>
                          <td><s:property value="usuario.nombre" /> <s:property value="usuario.primerApellido" /> <s:property value="usuario.segundoApellido" /></td>
                          <td><s:property value="usuario.curp" /></td>
                          <td><s:property value="fecha_agregado" /></td>
                          <td><span class="badge bg-warning text-dark" id="status_<s:property value='id_solicitud' />">Cargando...</span>
                      <script>
                        (function() {
                          var tipo = "<s:property value='status' />";
                          var idSpan = "status_<s:property value='id_solicitud' />";
                          var span = document.getElementById(idSpan);
                          if (span) {
                            span.innerText = obtenerNombreArchivo(tipo);
                          }
                        })();
                      </script></td>
                          <td>
                            <!--  
                            <a href="detalleSolicitud.jsp?folio=2025-00123" class="btn btn-sm btn-primary">
                              <i class="bi bi-eye"></i> Ver Detalles
                            </a>-->
                            <a href="solicitudDetalleAdmin?solicitudInput.id_solicitud=<s:property value="id_solicitud" />" 
                                  class="btn btn-sm btn-primary <s:if test="status == 'completa'">pulse-animation</s:if>">
                                 <i class="bi bi-eye"></i> Ver Detalles
                               </a>
                          </td>
                        </tr>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
            </div>
             <div class="tab-pane fade" id="rechazadas" role="tabpanel">
            <div class="table-responsive">
                  <table id="rechazadasTable" class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead class="table-success">
                      <tr>
                        <th>Folio</th>
                        <th>Nombre</th>
                        <th>CURP</th>
                        <th>Fecha de Solicitud</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="rechazadas"> 
                      <tr>
                        <td><s:property value="id_solicitud" /></td>
                        <td><s:property value="usuario.nombre" /> <s:property value="usuario.primerApellido" /> <s:property value="usuario.segundoApellido" /></td>
                        <td><s:property value="usuario.curp" /></td>
                        <td><s:property value="fecha_agregado" /></td>
                        <td><span class="badge bg-warning text-dark" id="status_<s:property value='id_solicitud' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='status' />";
                        var idSpan = "status_<s:property value='id_solicitud' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script></td>
                        <td>
                          <!--  
                          <a href="detalleSolicitud.jsp?folio=2025-00123" class="btn btn-sm btn-primary">
                            <i class="bi bi-eye"></i> Ver Detalles
                          </a>-->
                          <a href="solicitudDetalleAdmin?solicitudInput.id_solicitud=<s:property value="id_solicitud" />" 
                                class="btn btn-sm btn-primary <s:if test="status == 'nueva'">pulse-animation</s:if>">
                               <i class="bi bi-eye"></i> Ver Detalles
                             </a>
                        </td>
                      </tr>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
            </div>
           </div>

      </div>
    </div>

  </div>
</main>
<%@include file="../includes/pie.jspf" %>


