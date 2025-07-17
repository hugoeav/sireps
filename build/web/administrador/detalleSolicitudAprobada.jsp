<%-- 
    Document   : detalleSolicitudAprobada
    Created on : 14/05/2025, 02:56:04 PM
    Author     : Informatica
--%>
<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<main class="container-fluid mt-4">
  <div class="container">

    <div class="card shadow border-0 mb-4">
      <div class="card-header text-white bg-success">
        <h5 class="mb-0">Solicitud Aprobada</h5>
      </div>
      <div class="card-body">

        <!-- Datos Personales -->
        <h6 class="mb-3 text-success">Datos del Solicitante</h6>
        <div class="row">
          <div class="col-md-6">
            <p><strong>Nombre:</strong> Hugo Amaro Pérez</p>
            <p><strong>CURP:</strong> AAVH960228HSPMZG08</p>
            <p><strong>RFC:</strong> AAVH960228</p>
          </div>
          <div class="col-md-6">
            <p><strong>Dirección Particular:</strong> Calle Falsa 123, Col. Centro, SLP</p>
            <p><strong>Dirección Laboral:</strong> Hospital General, Av. Salud 456</p>
          </div>
        </div>

        <hr>

        <!-- Grados Académicos -->
        <h6 class="mb-3 text-success">Grados Académicos</h6>
        <div class="table-responsive">
          <table class="table table-bordered table-striped">
            <thead class="table-success">
              <tr>
                <th>Grado</th>
                <th>Institución</th>
                <th>Cédula</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Licenciatura en Medicina</td>
                <td>UASLP</td>
                <td>1234567</td>
              </tr>
              <tr>
                <td>Especialidad en Pediatría</td>
                <td>UNAM</td>
                <td>7654321</td>
              </tr>
            </tbody>
          </table>
        </div>

        <hr>

        <!-- Título Profesional Asignado -->
        <h6 class="mb-3 text-success">Título Profesional Asignado</h6>
        <p><strong>Médico Cirujano</strong></p>

        <hr>

        <!-- Archivos Adjuntos -->
        <h6 class="mb-3 text-success">Documentos Adjuntos</h6>
        <div class="table-responsive">
          <table class="table table-bordered table-hover">
            <thead class="table-success">
              <tr>
                <th>Documento</th>
                <th>Archivo</th>
                <th>Estatus</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Título Profesional</td>
                <td><a href="/docs/titulo.pdf" target="_blank">titulo.pdf</a></td>
                <td><span class="badge bg-success">Válido</span></td>
              </tr>
              <tr>
                <td>Cédula Profesional</td>
                <td><a href="/docs/cedula.pdf" target="_blank">cedula.pdf</a></td>
                <td><span class="badge bg-success">Válido</span></td>
              </tr>
              <tr>
                <td>Comprobante de Pago</td>
                <td><a href="/docs/pago.pdf" target="_blank">pago.pdf</a></td>
                <td><span class="badge bg-success">Válido</span></td>
              </tr>
            </tbody>
          </table>
        </div>

        <hr>

        <!-- Observaciones -->
        <h6 class="mb-3 text-success">Observaciones del Revisor</h6>
        <p>No se encontraron inconsistencias en los documentos. Solicitud aprobada satisfactoriamente.</p>

        <hr>

        <!-- Acuse -->
        <h6 class="mb-3 text-success">Acuse de Registro</h6>
        <div class="mb-3">
          <p>Tu solicitud ha sido aprobada y registrada. Puedes descargar el acuse oficial a continuación:</p>
          <a href="/acuse/acuse_hugo_amaro.pdf" class="btn btn-outline-primary" target="_blank">
            <i class="bi bi-download"></i> Descargar Acuse (PDF)
          </a>
        </div>

      </div>
    </div>

  </div>
</main>

<%@include file="../includes/pie.jspf" %>