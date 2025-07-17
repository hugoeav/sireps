<%@include file="../includes/cabecera.jspf" %>

<main class="flex-grow-1 d-flex flex-column">
    <div class="container-fluid d-flex justify-content-center align-items-center" style="min-height: calc(100vh - 180px);">
   <form id="uploadForm" method="post" enctype="multipart/form-data">
  <label for="fileInput">Seleccionar archivo PDF:</label>
   <input type="file" id="fileInput" name="archivo" accept="application/pdf" />
  <p id="fileName" style="color: blue; cursor: pointer; display: none; text-decoration: underline;"></p>

  <button type="submit">Enviar</button>
</form>
<!-- Modal para vista previa del PDF -->
<div class="modal fade" id="pdfModal" tabindex="-1" aria-labelledby="pdfModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered" style="max-width: 90%;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="pdfModalLabel">Vista previa del archivo PDF</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body" style="height: 80vh;">
        <iframe id="pdfViewer" src="" width="100%" height="100%" style="border: none;"></iframe>
      </div>
    </div>
  </div>
</div>
    </div>
</main>
 <script>
  document.addEventListener('DOMContentLoaded', function () {
    const fileInput = document.getElementById('fileInput');
    const fileName = document.getElementById('fileName');
    let selectedFile = null;

    fileInput.addEventListener('change', function () {
      const file = this.files[0];

      if (file && file.type === 'application/pdf') {
        selectedFile = file;
        fileName.textContent = file.name;
        fileName.style.display = 'block';
      } else {
        selectedFile = null;
        fileName.style.display = 'none';
      }
    });

    fileName.addEventListener('click', function () {
  if (!selectedFile) {
    alert("No hay archivo PDF seleccionado.");
    return;
  }

  const reader = new FileReader();
  reader.onload = function (e) {
    const blob = new Blob([e.target.result], { type: 'application/pdf' });
    const url = URL.createObjectURL(blob);

    const iframe = document.getElementById('pdfViewer');
    iframe.src = url;

    const pdfModal = new bootstrap.Modal(document.getElementById('pdfModal'));
    pdfModal.show();
  };
  reader.readAsArrayBuffer(selectedFile);
});
  });
</script>
<%@include file="../includes/pie.jspf" %>


