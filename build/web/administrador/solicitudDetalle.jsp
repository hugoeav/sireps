<%-- 
    Document   : soldicitudDetalle
    Created on : 14/05/2025, 02:14:02 PM
    Author     : hugoeav
--%>
<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<script>
     function verPdfModal(url) {
    document.getElementById('iframePdf').src = url;
    var modal = new bootstrap.Modal(document.getElementById('pdfModal'));
    modal.show();
  }
  
  function obtenerNombreArchivo(tipo) {
    const nombres = {
        //grados
        tecnico: "Técnico",
        licenciatura: "Licenciatura",
        maestria: "Maestría",
        maestria2: "Maestría2",
        especialidad: "Especialidad",
        especialdiad2: "Segunda Especialdiad",
        subespecialidad: "subespecialidad",
        doctorado: "Doctorado",
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
</script>

<main class="container-fluid mt-4">
  <div class="container">

    <div class="card shadow border-0 mb-4">
      <div class="card-header text-white d-flex justify-content-between align-items-center" style="background-color: #3c8b41;">
        <h5 class="mb-0">Detalle de Solicitud</h5>
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
        <h6 class="text-muted mt-4 text-center"><s:if test="domicilioInput.predeterminado"><i class="bi bi-star-fill text-warning" title="Se seleccionó la dirección particular como predeterminada"></s:if></i>Dirección Particular</h6>
        <div class="row mb-2">
          <div class="col-md-4"><strong>Calle:</strong> <s:property value="domicilioInput.calle" /></div>
          <div class="col-md-2"><strong>No. Ext.:</strong> <s:property value="domicilioInput.numero_ext" /></div>
          <div class="col-md-2"><strong>No. Int.:</strong><s:if test="domicilioInput.numero_int != null"> <s:property value="domicilioInput.numero_int" /></s:if><s:else> S/N</s:else></div>
          <div class="col-md-4"><strong>Asentamiento y Nombre</strong> <s:property value="domicilioInput.tipo_asentamiento" /><s:if test="domicilioInput.colonia != null"> <s:property value="domicilioInput.colonia" /></s:if><s:else><s:property value="domicilioInput.colonia_manual" /></s:else></div>
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
            <h6 class="text-muted mt-4 text-center"><s:if test="domicilioLabInput.predeterminado"><i class="bi bi-star-fill text-warning" title="Se seleccionó la dirección laboral como predeterminada"></i></s:if>Dirección Laboral</h6>
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
                  <s:if test="domicilioLabInput.colonia != null"> <s:property value="domicilioLabInput.colonia" /></s:if><s:else><s:property value="domicilioLabInput.colonia_manual" /></s:else>
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
        <!--
        <hr>

        <!-- Título Profesional Asignado 
        <h6 class="mb-3 text-success">Título Profesional Asignado</h6>
        <div class="mb-3">
          <input type="text" class="form-control" placeholder="Ej. Médico Cirujano, Cirujano Dentista, etc.">
        </div>-->

        <hr>
        <form id="principalForm" action="retroalimentacion" method="post" style="display:inline;">
       
        <!-- Archivos Cargados -->
        <h6 class="mb-3 text-success">Documentos Adjuntos</h6>
        <div class="table-responsive">
          <table class="table table-hover table-bordered">
            <thead class="table-success">
              <tr>
                <th>Tipo de Documento</th>
                <th>Documento</th>
                <th>Archivo</th>
                <th>Estatus</th>
                <th>Actualizar Estatus</th>
              </tr>
            </thead>
            
            <tbody>
              <s:set var="conRegistro" value="%{false}" />
              <s:set var="conRegistroFinal" value="%{false}" />
                <s:iterator value="documentos">
               <s:if test="tipo_archivo != 'registroF' and tipo_archivo != 'registroFinal'">
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
  </script></td>
                <td><s:property value="nombre" /></td>
               <td>
               <button type="button" class="btn btn-sm btn-outline-primary" onclick="verPdfModal('download?rutaDescarga=' + '<s:property value="ruta_archivo" />')">
                  Ver Documento
                </button>
              </td>
                <s:if test="valido == null">
                    <td><span class="badge bg-warning text-dark">En revisión</span></td>
                </s:if>
                <s:elseif test="valido">
                    <td><span class="badge bg-success">Válido</span></td>
                </s:elseif>
                <s:else>
                    <td><span class="badge bg-danger">No válido</span></td>
                </s:else>
                <td>
                    <select <s:if test="solicitudInput.status == 'completa' or solicitudInput.status == 'rechazada'">disabled="true"</s:if> class="form-select form-select-sm" name="documentosRev[<s:property value="id_documento" escapeHtml="false"/>]">
                      <option value="revision" <s:if test="valido == null">selected</s:if>>En revisión</option>
                      <option value="valido" <s:if test="valido == true">selected</s:if>>Válido</option>
                      <option value="invalido" <s:if test="valido == false">selected</s:if>>Inválido</option>
                    </select>
                  </td>
              </tr>
               </s:if>
               <s:else>
                   <s:if test="tipo_archivo == 'registroF'">
                      <s:set var="conRegistro" value="%{true}" />
                      <s:set var="rutaRegistro" value="ruta_archivo" />
                   </s:if>
                   <s:else>
                       <s:set var="conRegistroFinal" value="%{true}" />
                      <s:set var="rutaRegistroFinal" value="ruta_archivo" />
                   </s:else>
               </s:else>
                </s:iterator>
            </tbody>
          </table>
        </div>

        <hr>

        <!-- Observaciones -->
        <h6 class="mb-3 text-success">Observaciones al Solicitante</h6>
        <input type="hidden" name="solicitudInput.id_solicitud" value="<s:property value="solicitudInput.id_solicitud"/>" class="form-control" id="calle" required/>
        <div class="mb-3">
            <textarea<s:if test="solicitudInput.status == 'completa' or solicitudInput.status == 'rechazada'"> disabled="true" </s:if> name="solicitudInput.observacion" class="form-control" rows="4" placeholder="Ejemplo: El archivo de cédula no es legible, favor de cargar nuevamente..."><s:property value="solicitudInput.observacion"/></textarea>
        </div>
        <div class="mb-4">
            <button <s:if test="solicitudInput.status == 'completa' or solicitudInput.status == 'rechazada'"> hidden="true"</s:if> type="submit" class="btn btn-warning"><i class="bi bi-chat-left-text"></i> Enviar Observaciones</button>
        </div>
        
          <s:if test="#conRegistro">
         <hr>
         <!-- Acuse -->
        <h6 class="mb-3 text-success">Acuse de Registro</h6>
        <div class="mb-3">
          <p>Tu solicitud ha sido aprobada y registrada. Puedes descargar el acuse oficial a continuación:</p>
          <button type="button" class="btn btn-sm btn-outline-primary" onclick="verPdfModal('download?rutaDescarga=<s:property value='#rutaRegistro' />')">
                  Descargar Acuse PDF
                </button>
        </div>
        <hr>    
          </s:if>
    
  </form>
        
        
         <s:if test="!#conRegistroFinal && solicitudInput.status =='completa'">
              <h6 class="mb-3 text-success">Registro autorizado</h6>
              <form action="cargarRegistro" method="post" enctype="multipart/form-data" >
                   <input type="hidden" name="solicitudInput.id_solicitud" value="<s:property value='solicitudInput.id_solicitud' />" required>
                   <input type="hidden" name="solicitudInput.usuario.id_usuario" value="<s:property value='solicitudInput.usuario.id_usuario' />" required>
                    <input type="hidden" name="solicitudInput.usuario.curp" value="<s:property value='solicitudInput.usuario.curp' />" required>
                     <input type="hidden" name="solicitudInput.observacion" value="concluida" required>
                     <input type="hidden" name="solicitudInput.status" value="aprobada" required>
                   
                <div id="registro-subida" class="drop-zone mt-2">
                    Arrastra aquí o haz clic para subir tu archivo, no mayor a 3MB
                    <input class="archivo-carga" type="file" id="input-tecnico" name="registroFinal" accept=".pdf">
                    <div class="file-name" id="file-name-titulo"></div>
                </div>
                    <div class="text-center mt-4">
                     <button class="btn btn-success" type="submit"><i class="bi bi-cloud-upload"></i> Cargar</button>
                    </div>
              </form>
         </s:if>
            <s:elseif test="solicitudInput.status =='aprobada'">
            <div class="mb-3">
                 <h6 class="mb-3 text-success">Registro autorizado</h6>
                <p>El registro se ha cargado correctamente. Puedes decargar el registro oficial a continuación:</p>
                <button type="button" class="btn btn-sm btn-outline-primary" onclick="verPdfModal('download?rutaDescarga=<s:property value='#rutaRegistroFinal' />')">
                        Descargar Registro PDF
                      </button>
              </div>
        </s:elseif>
        <!-- Acciones finales -->
        
        <div <s:if test="solicitudInput.status == 'completa' or solicitudInput.status == 'rechazada' or solicitudInput.status == 'aprobada'"> hidden="true"</s:if> class="text-center">
           
                <!-- Botón Aprobar -->
            <button type="button" class="btn btn-success me-2" onclick="confirmarAccion('aprobar')">
              <i class="bi bi-check-circle-fill"></i> Aprobar Solicitud
            </button>

            <!-- Botón Rechazar -->
            <button type="button" class="btn btn-danger" onclick="confirmarAccion('rechazar')">
              <i class="bi bi-x-circle-fill"></i> Rechazar Solicitud
            </button>
        </div>

      </div>
        
     
    </div>

  </div>
<!-- Modal para ver PDF o Imagen -->
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
<!-- Modal de Confirmación -->
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content border-0 shadow">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="confirmModalLabel">Confirmar acción</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p id="confirmMessage">¿Estás seguro de que deseas realizar esta acción?</p>
      </div>
      <div class="modal-footer">
          <form id="formAccion" method="post" onsubmit="mostrarSpinner()">
         
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <!--<button type="submit" class="btn btn-primary" onclick="copiarDatosYEnviar()">Confirmar</button>-->
        </form>
      </div>
    </div>
  </div>
</div>
<div id="spinner-container" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(255, 255, 255, 0.8); z-index: 1000; justify-content: center; align-items: center; flex-direction: column;">
                <div class="spinner-grow text-success" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
                <p style="color: green; font-weight: bold; margin-top: 10px;">Generando Registro</p>
            </div>
<script>
    
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
      form.innerHTML += `
        <input type="hidden" name="solicitudInput.id_solicitud" value="<s:property value='solicitudInput.id_solicitud' />" required>
        <input type="hidden" name="solicitudInput.usuario.id_usuario" value="<s:property value='solicitudInput.id_usuario' />" required>
        <input type="hidden" name="solicitudInput.usuario.curp" value="<s:property value='solicitudInput.usuario.curp' />" required>
        <input type="hidden" name="solicitudInput.status" value="completa" required>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button id="botonRegistro" type="submit" class="btn btn-success">Aprobar</button>
      `;
      const nuevoBoton = document.getElementById("botonRegistro");
    nuevoBoton.addEventListener("click", copiarDatosYEnviar);
    } else if (tipo === 'rechazar') {
      mensaje.textContent = '¿Estás seguro de que deseas RECHAZAR esta solicitud?';
      form.action = 'rechazarSoliciiud';
      form.method = 'post';
      form.innerHTML += `
        <input type="hidden" name="solicitudInput.id_solicitud" value="<s:property value='solicitudInput.id_solicitud' />" required>
        <input type="hidden" name="solicitudInput.status" value="rechazada" required>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-danger">Rechazar</button>
      `;
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
  mostrarSpinner();
  formAccion.submit();
}

//drop zone
function limpiarNombreArchivo(nombre) {
    let nombreNormalizado = nombre.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    return nombreNormalizado.replace(/[^a-zA-Z0-9._-]/g, "_");
}

document.querySelectorAll('.drop-zone').forEach(zone => {
     const input = zone.querySelector('input');
  const fileNameDisplay = zone.querySelector('.file-name');

  zone.addEventListener('click', () => input.click());

  zone.addEventListener('dragover', e => {
    e.preventDefault();
    zone.classList.add('dragover');
  });

  zone.addEventListener('dragleave', () => {
    zone.classList.remove('dragover');
  });

  zone.addEventListener('drop', e => {
    e.preventDefault();
    zone.classList.remove('dragover');
    if (e.dataTransfer.files.length) {
      manejarArchivo(input, e.dataTransfer.files[0], fileNameDisplay);
    }
  });

  input.addEventListener('change', () => {
    if (input.files.length > 0) {
      manejarArchivo(input, input.files[0], fileNameDisplay);
    }
  });

  function manejarArchivo(input, archivoOriginal, display) {
    const nombreLimpio = limpiarNombreArchivo(archivoOriginal.name);
    const archivoLimpio = new File([archivoOriginal], nombreLimpio, { type: archivoOriginal.type });

    // Reemplazar archivo en el input con un nuevo DataTransfer
    const dataTransfer = new DataTransfer();
    dataTransfer.items.add(archivoLimpio);
    input.files = dataTransfer.files;

    display.textContent = "Archivo seleccionado: " + archivoLimpio.name;
    display.style.cursor = 'pointer';
    display.classList.add('text-primary', 'text-decoration-underline');

    // Mostrar modal al hacer clic en el nombre
    display.addEventListener('click', () => {
       event.stopPropagation(); // <- esto evita que se propague hacia el contenedor .drop-zone 
      if (archivoLimpio.type === "application/pdf") {
        const reader = new FileReader();
        reader.onload = function (e) {
          const blob = new Blob([e.target.result], { type: 'application/pdf' });
          const url = URL.createObjectURL(blob);
          document.getElementById('iframePdf').src = url;
          const modal = new bootstrap.Modal(document.getElementById('pdfModal'));
          modal.show();
        };
        reader.readAsArrayBuffer(archivoLimpio);
      }
    }, { once: false }); // evitar múltiples listeners
  }

  function limpiarNombreArchivo(nombre) {
    return nombre.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[^a-zA-Z0-9. _-]/g, "");
  }
});
</script>
</main>
<%@include file="../includes/pie.jspf" %>
