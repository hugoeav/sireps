
   function verPdfModal(url) {
    document.getElementById('iframePdf').src = url;
    var modal = new bootstrap.Modal(document.getElementById('pdfModal'));
    modal.show();
  }
  
  function obtenerNombreArchivo(tipo) {
    const nombres = {
        // Títulos
        tecTitulo: "Título de Técnico",
        licTitulo: "Título de Licenciatura",
        maestTitulo: "Título de Maestría",
        maest2Titulo: "Segundo Título de Maestría",
        espTitulo: "Título de Especialidad",
        esp2Titulo: "Segundo Título de Especialidad",
        subespTitulo: "Título de Subespecialidad",
        docTitulo: "Título de Doctorado",

        // Cédulas
        tecCed: "Cédula de Técnico",
        licCed: "Cédula de Licenciatura",
        maestCed: "Cédula de Maestría",
        maest2Ced: "Segunda Cédula de Maestría",
        espCed: "Cédula de Especialidad",
        esp2Ced: "Segunda Cédula de Especialidad",
        subespCed: "Cédula de Subespecialidad",
        docCed: "Cédula de Doctorado",

        // Otros
        fotosF: "Fotografía",
        compPagoF: "Comprobante de pago",
        domicilioF: "Comprobante de domicilio",
        registroF: "Registro profesional",
        registroFUsuario:"Acuse del Solicitante"
    };

    return nombres[tipo] || "Documento desconocido";
}


 function confirmarAccion(tipo) {
      if (!validarDocumentos()) {
        return;
      }
    const modal = new bootstrap.Modal(document.getElementById('confirmModal'));
    const form = document.getElementById('formAccion');
    const mensaje = document.getElementById('confirmMessage');

    // Limpia el formulario
    form.innerHTML = '';

    // Inserta mensaje y campos según el tipo de acción
    if (tipo === 'aprobar') {
      mensaje.textContent = '¿Estás seguro de que deseas APROBAR esta solicitud?';
      
      form.action = 'generarRegistro';
      form.method = 'post';
      form.innerHTML += "<input type=\"hidden\" name=\"solicitudInput.id_solicitud\" value=\"<s:property value='solicitudInput.id_solicitud' />\" required><input type=\"hidden\" name=\"solicitudInput.usuario.id_usuario\" value=\"<s:property value='solicitudInput.id_usuario' />\" required><input type=\"hidden\" name=\"solicitudInput.usuario.curp\" value=\"<s:property value='solicitudInput.usuario.curp' />\" required> <input type=\"hidden\" name=\"solicitudInput.status\" value=\"completa\" required> <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancelar</button> <button id=\"botonRegistro\" type=\"submit\" class=\"btn btn-success\">Aprobar</button>";
      const nuevoBoton = document.getElementById("botonRegistro");
    nuevoBoton.addEventListener("click", copiarDatosYEnviar);
    } else if (tipo === 'rechazar') {
      mensaje.textContent = '¿Estás seguro de que deseas RECHAZAR esta solicitud?';
      form.action = 'rechazarSoliciiud';
      form.method = 'post';
      form.innerHTML += "<input type=\"hidden\" name=\"solicitudInput.id_solicitud\" value=\"<s:property value=\'solicitudInput.id_solicitud\' /> \" required> <input type=\"hidden\" name=\"solicitudInput.status\" value=\"rechazada\" required> <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancelar</button> <button type=\"submit\" class=\"btn btn-danger\">Rechazar</button>";
    }

    modal.show();
  }
  
  
  function validarDocumentos() {
  // Selecciona todos los selects de estatus de documentos
  const selects = document.querySelectorAll('select[name^="documentosRev"]');
  let documentosInvalidos = 0;
  let documentosEnRevision = 0;

  selects.forEach(select => {
    const valor = select.value;
    if (valor === 'invalido') {
      documentosInvalidos++;
    } else if (valor === 'revision') {
      documentosEnRevision++;
    }
  });

  if (documentosInvalidos > 0) {
    alert("No puedes aprobar o rechazar la solicitud: Hay documentos marcados como INVÁLIDOS.");
    return false;
  }

  if (documentosEnRevision > 0) {
    alert("No puedes aprobar o rechazar la solicitud: Aún hay documentos EN REVISIÓN.");
    return false;
  }

  return true;
}

function mostrarSpinner() {
        const modal = bootstrap.Modal.getInstance(document.getElementById('confirmModal'));
        if (modal) {
           modal.hide();
        }
        document.getElementById("spinner-container").style.display = "flex"; 
    }
    
function copiarDatosYEnviar(event) {
  event.preventDefault(); 

    const formPrincipal = document.getElementById("principalForm");
  const formAccion = document.getElementById("formAccion");

   const selects = formPrincipal.querySelectorAll("select[name^='documentosRev']");
  console.log("Selects encontrados:", selects.length);

  selects.forEach(select => {
    const inputHidden = document.createElement("input");
    inputHidden.type = "hidden";
    inputHidden.name = select.name;
    inputHidden.value = select.value;
    formAccion.appendChild(inputHidden);
  });
  console.log("Si entra a funcion");
  formAccion.submit();
}


