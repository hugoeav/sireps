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
            <p><strong>Nombre:</strong> Hugo Amaro P�rez</p>
            <p><strong>CURP:</strong> AAVH960228HSPMZG08</p>
            <p><strong>RFC:</strong> AAVH960228</p>
          </div>
          <div class="col-md-6">
            <p><strong>Direcci�n Particular:</strong> Calle Falsa 123, Col. Centro, SLP</p>
            <p><strong>Direcci�n Laboral:</strong> Hospital General, Av. Salud 456</p>
          </div>
        </div>

        <hr>

        <!-- Grados Acad�micos -->
        <h6 class="mb-3 text-success">Grados Acad�micos</h6>
        <div class="table-responsive">
          <table class="table table-bordered table-striped">
            <thead class="table-success">
              <tr>
                <th>Grado</th>
                <th>Instituci�n</th>
                <th>C�dula</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Licenciatura en Medicina</td>
                <td>UASLP</td>
                <td>1234567</td>
              </tr>
              <tr>
                <td>Especialidad en Pediatr�a</td>
                <td>UNAM</td>
                <td>7654321</td>
              </tr>
            </tbody>
          </table>
        </div>

        <hr>

        <!-- T�tulo Profesional Asignado -->
        <h6 class="mb-3 text-success">T�tulo Profesional Asignado</h6>
        <p><strong>M�dico Cirujano</strong></p>

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
                <td>T�tulo Profesional</td>
                <td><a href="/docs/titulo.pdf" target="_blank">titulo.pdf</a></td>
                <td><span class="badge bg-success">V�lido</span></td>
              </tr>
              <tr>
                <td>C�dula Profesional</td>
                <td><a href="/docs/cedula.pdf" target="_blank">cedula.pdf</a></td>
                <td><span class="badge bg-success">V�lido</span></td>
              </tr>
              <tr>
                <td>Comprobante de Pago</td>
                <td><a href="/docs/pago.pdf" target="_blank">pago.pdf</a></td>
                <td><span class="badge bg-success">V�lido</span></td>
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
          <p>Tu solicitud ha sido aprobada y registrada. Puedes descargar el acuse oficial a continuaci�n:</p>
          <a href="/acuse/acuse_hugo_amaro.pdf" class="btn btn-outline-primary" target="_blank">
            <i class="bi bi-download"></i> Descargar Acuse (PDF)
          </a>
        </div>

      </div>
    </div>

  </div>
</main>

<%@include file="../includes/pie.jspf" %>