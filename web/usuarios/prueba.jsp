<%@include file="../includes/cabecera.jspf" %>
<main class="flex-grow-1 container my-4">
<!-- Formulario principal -->
<form id="formPrincipal" action="solicitudPrimeraVez.do" method="post">
  <!-- Campos: nombre, domicilio, estado, etc. -->
  <input type="text" name="nombre" placeholder="Nombre completo" required />
  <!-- ...otros campos -->

  <button type="submit" onclick="abrirModal(event)">Siguiente</button>
</form>
<!-- Modal oculto al inicio -->
<div id="modalArchivos" class="modal">
  <div class="modal-content">
    <span class="cerrar" onclick="cerrarModal()">&times;</span>
    <h3>Subir Documentos Requeridos</h3>
    <form id="formArchivos" enctype="multipart/form-data">
      <label>Acta de nacimiento: <input type="file" name="actaNacimiento" required></label><br>
      <label>Comprobante domicilio: <input type="file" name="comprobanteDomicilio" required></label><br><br>
      <button type="button" onclick="enviarAmbosForms()">Enviar Solicitud Completa</button>
    </form>
  </div>
</div>

</main>
<%@include file="../includes/pie.jspf" %>

<script>
function abrirModal(event) {
  event.preventDefault(); // Evita el submit del primer form
  document.getElementById('modalArchivos').style.display = 'block';
}

function cerrarModal() {
  document.getElementById('modalArchivos').style.display = 'none';
}

function enviarAmbosForms() {
  const form1 = document.getElementById('formPrincipal');
  const form2 = document.getElementById('formArchivos');

  const formData = new FormData(form1);
  const formArchivos = new FormData(form2);

  // Combinar archivos en el FormData principal
  for (let [key, value] of formArchivos.entries()) {
    formData.append(key, value);
  }

  fetch('procesarSolicitudCompleta.do', {
    method: 'POST',
    body: formData
  }).then(resp => {
    if (resp.ok) {
      alert('Solicitud enviada correctamente.');
      window.location.href = 'paginaConfirmacion.jsp';
    } else {
      alert('Error al enviar solicitud.');
    }
  });
}
    </script>
    
    
<!-- Estilos del modal -->
<style>
.modal {
  display: none;
  position: fixed;
  z-index: 1000;
  padding-top: 80px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0,0,0,0.4);
}
.modal-content {
  margin: auto;
  background-color: white;
  padding: 20px;
  border-radius: 6px;
  width: 50%;
}
.cerrar {
  float: right;
  font-size: 28px;
  cursor: pointer;
}
</style>