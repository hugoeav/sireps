<%-- 
    Document   : solicitudes
    Created on : 14/05/2025, 01:41:29 PM
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
<script src="${pageContext.request.contextPath}/js/utileriasSolicitudes.js"></script>
<main class="flex-grow-1 d-flex flex-column container-fluid mt-4">
  <div class="container">
    <div class="card shadow-lg border-0 rounded-lg">
      <div class="card-header d-flex justify-content-between align-items-center" style="background-color: #3c8b41;">
        <h5 class="text-white mb-0">MIS SOLICITUDES</h5>
        
      </div>
      <div class="card-body">
        
        <!-- Filtros -->
        <div class="row mb-4">
          <div class="col-md-4">
            <label for="estadoSolicitud" class="form-label">Estado de la solicitud:</label>
            <select id="estadoSolicitud" class="form-select">
              <option value="">-- Todas --</option>
              <option value="abierta">Abiertas</option>
              <option value="cerrada">Cerradas</option>
              <option value="pendiente">Pendientes</option>
              <option value="rechazada">Rechazadas</option>
            </select>
          </div>
          <div class="col-md-4">
            <label for="fechaInicio" class="form-label">Desde:</label>
            <input type="date" id="fechaInicio" class="form-control">
          </div>
          <div class="col-md-4">
            <label for="fechaFin" class="form-label">Hasta:</label>
            <input type="date" id="fechaFin" class="form-control">
          </div>
        </div>

        <!-- Tabla de Solicitudes -->
        <div class="table-responsive">
          <table id="tablaSolicitudes" class="table table-striped table-bordered w-100">
            <thead class="table-success">
              <tr>
                <th>Folio</th>
                <th>Tipo de Solicitud</th>
                <th>Fecha</th>
                <th>Estado</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
             <s:iterator value="solicitudes">
              <tr>
                <td><s:property value="id_solicitud" /></td>
                <td><span id="tipo_<s:property value='id_solicitud' />">Cargando...</span>
                    <script>
                      (function() {
                        var tipo = "<s:property value='tipo' />";
                        var idSpan = "tipo_<s:property value='id_solicitud' />";
                        var span = document.getElementById(idSpan);
                        if (span) {
                          span.innerText = obtenerNombreArchivo(tipo);
                        }
                      })();
                    </script></td>
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
                  <a href="solicitudDetalle?solicitudInput.id_solicitud=<s:property value="id_solicitud" />" class="btn btn-sm btn-primary">
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
</main>


<%@include file="../includes/pie.jspf" %>