<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>

<%@include file="../includes/cabecera.jspf" %>


<main class="flex-grow-1 d-flex flex-column">
    <div class="container-fluid d-flex justify-content-center align-items-center" style="min-height: calc(100vh - 180px);">
        <div class="card shadow-lg w-100" style="max-width: 98%; border-radius: 15px; overflow: hidden;">
            <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                <h3>SOLICITUD PARA REGISTRO ESTATAL DE TÍTULO PROFESIONAL</h3>
            </div>
            <div class="card-body" style="background-color: #f9f9f9;">
                <s:form action="registroPV" method="post" onsubmit="return validarform()" enctype="multipart/form-data" acceptcharset="UTF-8">
                    <input type="hidden" name="solicitudInput.tipo" value="primeraVez" class="form-control" id="calle" required/>
                    
                    <button class="btn btn-success w-100 mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#datosgnrlCollapse">
                        <b>Datos Generales</b>
                    </button>
                    <div id="datosgnrlCollapse" class="collapse show border p-3 rounded">
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label for="nombre" class="form-label required">Nombre(s)</label>
                                <input type="text" name="usuarioInput.nombre" class="form-control" id="addUserName" value="${usuario.nombre}" readonly required/>
                            </div>
                            <div class="col-md-4">
                                <label for="ap" class="form-label required">Primer Apellido</label>
                                <input type="text" name="usuarioInput.primerApellido" class="form-control" id="addUserLName" value="${usuario.primerApellido}" readonly required/>
                            </div>
                            <div class="col-md-4">
                                <label for="sap" class="form-label">Segundo Apellido</label>
                                <input type="text" name="usuarioInput.segundoApellido" class="form-control" value="${usuario.segundoApellido}" readonly id="addUserSecName" />
                            </div>
                        </div>


                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="curp" class="form-label required">CURP</label>
                                <input type="text" name="usuarioInput.curp" class="form-control" id="addUserCurp" value="${usuario.curp}" readonly required/>
                            </div>
                            <div class="col-md-6">
                                <label for="rfc" class="form-label required">RFC sin homoclave</label>
                                <input type="text" name="usuarioInput.rfc" class="form-control" id="rfcInput" value="${usuario.rfc}" readonly required/>
                            </div>
                        </div>


                        <div class="form-group mb-3 text-center">
                            <h6>DOMICILIO PARTICULAR</h6>
                        </div>
                            <input type="hidden" name="domicilioInput.nombre" value="particular" class="form-control" id="calle" required/>
                            <input type="hidden" name="domicilioInput.tipo_domicilio" value="particular" class="form-control" id="calle" required/>

                        <div class="row mb-3">
                            <div class="col-md-3">
                                <label for="cp" class="form-label">Código Postal</label>
                                <input type="text" name="domicilioInput.codigo_postal" class="form-control" id="cp"  onchange="updateCPData(this.value,'estado','municipio','localidad','colonia')"/>
                            </div>
                            <div class="col-md-3">
                                <label for="cp" class="form-label required">Estado</label>
                                <select name="domicilioInput.id_estado" id="estado" class="form-select"  onchange="updateMunicipiosData(this.value,'municipio','localidad')">
                                    <option value="-1">Seleccione un Estado</option>
                                   <s:iterator value="estados">
                                        <option value="<s:property value='id_estado' />"><s:property value="nombre_estado" /></option>
                                    </s:iterator>
                                </select>
                                
                            </div>
                            <div class="col-md-3">
                                <label for="localidad" class="form-label required">Municipio</label>
                                <select name="domicilioInput.id_municipio" id="municipio" class="form-select" onchange="updateLocalidadesData(this.value,'estado','localidad','municipio')">
                                    <option value="-1">Seleccione un Municipio</option>
                                </select>
                               
                            </div>
                            <div class="col-md-3">
                                <label for="localidades" class="form-label required">Localidad</label>
                                <select name="domicilioInput.id_localidad" class="form-control" id="localidad">
                                    <option value="-1">Seleccione una Localidad</option>
                                   
                                </select>
                               
                            </div>
                            
                        </div>


                       <div class="row mb-2">
                            <div class="col-md-3">
                                <label for="calle" class="form-label required">Calle</label>
                                <input type="text" name="domicilioInput.calle" class="form-control" oninput="this.value = this.value.toUpperCase()" id="calle" required/>
                            </div>
                            <div class="col-md-2">
                                <label for="numExt" class="form-label">Número Exterior</label>
                                <input type="text" name="domicilioInput.numero_ext" class="form-control" id="numExt" required/>
                            </div>
                           <div class="col-md-2">
                                <label for="numExt" class="form-label">Número Interior</label>
                                <input type="text" name="domicilioInput.numero_int" class="form-control" id="numExt" />
                            </div>
                           <div class="col-md-3">
                                <label for="colonia" class="form-label required">Colonia</label>
                                <select name="domicilioInput.id_colonia" class="form-control" id="colonia">
                                    <option value="-1">Seleccione una colonia</option>
                                </select>
                                <div class="form-check mt-2">
                                    <input class="form-check-input" type="checkbox" id="nuevaColoniaChk">
                                    <label class="form-check-label" for="nuevaColoniaChk">
                                        No aparece mi colonia
                                    </label>
                                </div>
                                <div class="mt-2" id="nuevaColoniaDiv" style="display:none;">
                                    <label for="nuevaColonia" class="form-label">Agregar Colonia</label>
                                    <input type="text" name="domicilioInput.colonia_manual" oninput="this.value = this.value.toUpperCase()" class="form-control" id="nuevaColonia">
                                </div>
                            </div>
                            
                            <div class="col-md-2">
                                <label for="tel" class="form-label required">Teléfono</label>
                                <input type="number" name="usuarioInput.telefono" class="form-control" id="tel" required/>
                            </div>
                           <div class="col-md-12 mt-3">
                                <div class="form-check form-check-inline" style="white-space: nowrap;">
                                  <input class="form-check-input radio-verde" checked type="radio" name="direccionPredeterminada" id="direccionParticular" value="particular">
                                  <label class="form-check-label" for="direccionParticular" style="display: inline-block;">
                                    Dirección Particular Predeterminada
                                    <i class="fas fa-info-circle text-secondary ms-2"
                                       data-bs-toggle="tooltip"
                                       title="Selecciona esta opción si deseas que tu dirección particular sea la que aparezca en tu registro."
                                       style="cursor: pointer;">
                                    </i>
                                  </label>
                                </div>
                              </div>
                        </div>
                    </div>
                    
                    <button class="btn btn-success w-100 mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#escolaridadCollapse">
                        <b>Escolaridad</b>
                    </button>
                    <div id="escolaridadCollapse" class="collapse border p-3 rounded">
                        <div id="nivelTecnico" class="row mb-3">
                            <div class="col-md-4">
                                <label class="form-label">Nivel Técnico <i class="fas fa-info-circle text-secondary ms-2"
                                            data-bs-toggle="tooltip"
                                            title="Captura tu titulo tal cual aparece en tu documento"
                                            style="cursor: pointer;">
                                         </i></label>
                                <input type="text" name="tecnicoInput.nombre" oninput="this.value = this.value.toUpperCase()" id="nivTec" class="form-control " />
                            </div>
                            <div class="col-md-4">
                                <label class="form-label obligatorioTec">Institución Educativa que Expide el Documento</label>
                                <input type="text" id="instInput" name="tecnicoInput.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" />
                            </div>
                            <div class="col-md-4">
                                <label class="form-label obligatorioTec">Cédula Profesional <i 
    class="bi bi-info-circle-fill text-primary ms-1"
    data-bs-toggle="tooltip"
    data-bs-html="true"
    data-bs-placemente="bottom"
    data-bs-boundary="window"
    data-bs-title="<img src='../img/ejemploCed.png' style='max-width:100%; height:auto;' />">
  </i></label>
                                <input type="number" name="tecnicoInput.cedula" class="form-control" />
                            </div>
                        </div>
                         <hr class="hr hr-blurry my-1" />
                        <div id="licenciatura" class="row mb-3">
                            <div class="col-md-4">
                                <label class="form-label lic">Licenciatura</label>
                                <input type="text" name="licenciaturaInput.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="licLabel" />
                            </div>
                            <div class="col-md-4">
                                <label class="form-label obligatorioLic">Institución Educativa que Expide el Documento</label>
                                <input type="text" name="licenciaturaInput.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" />
                            </div>
                            <div class="col-md-4">
                                <label class="form-label obligatorioLic">Cédula Profesional</label>
                                <input type="number" name="licenciaturaInput.cedula" class="form-control" />
                            </div>
                        </div>

                       <div id="maestria" class="row mb-3 position-relative" style="display: none;">
                            <div class="col-12 position-relative">
                                <span class="btn-close-grado" onclick="ocultarGrado('maestria')">&minus;</span>

                                <div class="row">
                                    <div class="col-md-4 col-sm-12">
                                        <label class="form-label obligatorioMaest">Maestría</label>
                                        <input type="text" name="maestriaInput.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="maestLabel" />
                                    </div>
                                    <div class="col-md-4 col-sm-12">
                                        <label class="form-label obligatorioMaest">Institución Educativa que Expide el Documento</label>
                                        <input type="text" name="maestriaInput.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" />
                                    </div>
                                    <div class="col-md-4 col-sm-12">
                                        <label class="form-label obligatorioMaest">Cédula Profesional</label>
                                        <input type="number" name="maestriaInput.cedula" class="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="maestria2" class="row mb-3" style="display: none;">
                            <div class="col-md-4">
                                <label class="form-label ">Segunda Maestría</label>
                                <input type="text" name="maestria2Input.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="maestLabel2" />
                            </div>
                            <div class="col-md-4">
                                <label class="form-label obligatorioMaest2">Institución Educativa que Expide el Documento</label>
                                <input type="text" name="maestria2Input.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" />
                            </div>
                            <div class="col-md-4">
                                <label class="form-label obligatorioMaest2">Cédula Profesional</label>
                                <input type="number" name="maestria2Input.cedula" class="form-control" />
                            </div>
                        </div>
                                
                                <div id="especialidad" class="row mb-3 position-relative" style="display: none;">
                                    <div class="col-12 position-relative">
                                        <span class="btn-close-grado" onclick="ocultarGrado('especialidad')">&minus;</span>
                                         <div class="row">
                                            <div class="col-md-4 col-sm-12">
                                                <label for="nombre" class="form-label esp">Especialidad</label>
                                                <input type="text" name="especialidadInput.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control " id="espLabel" />
                                            </div>
                                            <div class="col-md-4 col-sm-12">
                                                <label for="ap" class="form-label obligatorioEsp">Institución Educativa que Expide el Documento</label>
                                                <input type="text" name="especialidadInput.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" id="instnt" />
                                            </div>
                                            <div class="col-md-4 col-sm-12">
                                                <label for="sap" class="form-label obligatorioEsp">Cédula Profesional</label>
                                                <input type="number" name="especialidadInput.cedula" class="form-control" id="cpnt" />
                                            </div>
                                         </div>
                                    </div>
                                </div>
                                <div id="especialidad2" class="row mb-3" style="display: none;">
                                    <div class="col-md-4">
                                        <label for="nombre" class="form-label">Segunda Especialidad</label>
                                        <input type="text" name="especialidad2Input.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="espLabel2" />
                                    </div>
                                    <div class="col-md-4">
                                        <label for="ap" class="form-label obligatorioEsp2">Institución Educativa que Expide el Documento</label>
                                        <input type="text" name="especialidad2Input.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" id="instnt" />
                                    </div>
                                    <div class="col-md-4">
                                        <label for="sap" class="form-label obligatorioEsp2">Cédula Profesional</label>
                                        <input type="number" name="especialidad2Input.cedula" class="form-control" id="cpnt" />
                                    </div>
                                </div>
                                <div id="subespecialidad" class="row mb-3 position-relative" style="display: none;">
                                    <div class="col-12 position-relative">
                                        <span class="btn-close-grado" onclick="ocultarGrado('subespecialidad')">&minus;</span>
                                        <div class="row">
                                            <div  class="col-md-4 col-sm-12">
                                                <label for="nombre" class="form-label">Subespecialidad</label>
                                                <input type="text" name="subEspecialidad.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="subLabel" />
                                            </div>
                                            <div class="col-md-4 col-sm-12">
                                                <label for="ap" class="form-label obligatorioSub">Institución Educativa que Expide el Documento</label>
                                                <input type="text" name="subEspecialidad.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" id="instnt" />
                                            </div>
                                            <div class="col-md-4 col-sm-12">
                                                <label for="sap" class="form-label obligatorioSub">Cédula Profesional</label>
                                                <input type="number" name="subEspecialidad.cedula" class="form-control" id="cpnt" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div id="doctorado" class="row mb-3 position-relative" style="display: none;">
                                    <div class="col-12 position-relative">
                                        <span class="btn-close-grado" onclick="ocultarGrado('doctorado')">&minus;</span>
                                        <div class="row">
                                            <div class="col-md-4 col-sm-12">
                                                <label class="form-label ">Doctorado</label>
                                                <input type="text" name="doctoradoInput.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="docLabel" />
                                            </div>
                                            <div class="col-md-4 col-sm-12">
                                                <label class="form-label obligatorioDoc">Institución Educativa que Expide el Documento</label>
                                                <input type="text" name="doctoradoInput.institucion" oninput="this.value = this.value.toUpperCase()" class="form-control" />
                                            </div>
                                            <div class="col-md-4 col-sm-12">
                                                <label class="form-label obligatorioDoc">Cédula Profesional</label>
                                                <input type="number" name="doctoradoInput.cedula" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                       
                        <div class="text-center mb-3">
                            <div style="display: inline-block; width: auto;">
                                <select id="gradoSelect" class="form-select mb-2">
                                    <option value="" selected disabled>Selecciona un grado académico</option>
                                    <option value="maestria">Maestría</option>
                                    <option value="especialidad">Especialidad</option>
                                    <option value="subespecialidad">Subespecialidad</option>
                                    <option value="doctorado">Doctorado</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-secondary" onclick="mostrarGrado()">Añadir Grado</button>
                        </div>        
                        </div>


   
                 
                    
                    <button class="btn btn-success w-100 mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#datosLabCollapse">
                        <b>Datos Laborales</b>
                    </button>
                            <input type="hidden" name="domicilioLabInput.tipo_domicilio" value="laboral" class="form-control" id="calle" required/>
                    <div id="datosLabCollapse" class="collapse border p-3 rounded">
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label for="nombre" class="form-label required">Nombre de la Institución</label>
                                <input type="text" name="domicilioLabInput.nombre" oninput="this.value = this.value.toUpperCase()" class="form-control" id="institucion" required/>
                            </div>
                        </div>
                            <div class="row mb-3">
                                <div class="col-md-3">
                                    <label for="codigoPostal" class="form-label ">Código Postal</label>
                                    <input type="number" name="domicilioLabInput.codigo_postal" class="form-control" id="cpLab" onchange="updateCPData(this.value,'estadoLab','municipioLab','localidadLab','coloniaLab')"/>
                                </div>
                               
                                <div class="col-md-3">
                                    <label for="cp" class="form-label required">Estado</label>
                                <select name="domicilioLabInput.id_estado" id="estadoLab" class="form-select"  onchange="updateMunicipiosData(this.value,'municipioLab','localidadLab')">
                                    <option value="-1">Seleccione un Estado</option>
                                   <s:iterator value="estados">
                                    <option value="<s:property value='id_estado' />"><s:property value="nombre_estado" /></option>
                                     </s:iterator>
                                </select>
                                </div>
                                 <div class="col-md-3">
                                    <label for="sap" class="form-label required">Municipio</label>
                                    <select name="domicilioLabInput.id_municipio" id="municipioLab" class="form-select" onchange="updateLocalidadesData(this.value,'estadoLab','localidadLab','municipioLab')">
                                        <option value="-1">Seleccione un Municipio</option>
                                    </select>
                                </div>
                                
                                 <div class="col-md-3">
                                    <label for="colonia" class="form-label required">Localidad</label>
                                    <select name="domicilioLabInput.id_localidad" class="form-control" id="localidadLab">
                                        <option value="-1">Seleccione una Localidad</option>
                                   
                                    </select>
                                </div>
                            </div>
                            <div class="row mb-3">
                                
                                <div class="col-md-3">
                                    <label for="nombre" class="form-label required">Calle</label>
                                    <input type="text" name="domicilioLabInput.calle" oninput="this.value = this.value.toUpperCase()" class="form-control" id="calleLab" required/>
                                </div>
                                <div class="col-md-2">
                                    <label for="ap" class="form-label required">Número Exterior</label>
                                    <input type="text" name="domicilioLabInput.numero_ext" class="form-control" id="numLab" required/>
                                </div>
                                <div class="col-md-2">
                                    <label for="ap" class="form-label">Número Interior</label>
                                    <input type="text" name="domicilioLabInput.numero_int" class="form-control" id="numLab" />
                                </div>
                                <div class="col-md-3">
                                    <label for="colonia" class="form-label required">Colonia</label>
                                 
                                    <select name="domicilioLabInput.id_colonia" class="form-control" id="coloniaLab">
                                        <option value="-1">Seleccione una colonia</option>
                                    </select>
                                    <div class="form-check mt-2">
                                        <input class="form-check-input" type="checkbox" id="nuevaColoniaLabChk">
                                        <label class="form-check-label" for="nuevaColoniaLabChk">
                                            No aparece mi colonia
                                        </label>
                                    </div>
                                    <div class="mt-2" id="nuevaColoniaLabDiv" style="display:none;">
                                        <label for="nuevaColonia" class="form-label">Agregar Colonia</label>
                                        <input type="text" name="domicilioLabInput.colonia_manual" oninput="this.value = this.value.toUpperCase()" class="form-control" id="nuevaColoniaLab">
                                    </div>
                                </div>
                                
                                <div class="col-md-2">
                                    <label for="ap" class="form-label required">Teléfono</label>
                                    <input type="number" name="usuarioInput.telefonoLab" class="form-control" id="telLab" required/>
                                </div>
                               
                                <div class="col-md-12 mt-3">
                                     <div class="form-check form-check-inline" style="white-space: nowrap;">
                                       <input class="form-check-input radio-verde" type="radio" name="direccionPredeterminada" id="direccionLaboral" value="laboral">

                                       <label class="form-check-label" for="direccionLaboral" style="display: inline-block;">
                                         Dirección Laboral Predeterminada
                                         <i class="fas fa-info-circle text-secondary ms-2"
                                            data-bs-toggle="tooltip"
                                            title="Selecciona esta opción si deseas que tu dirección laboral sea la que aparezca en tu registro."
                                            style="cursor: pointer;">
                                         </i>
                                       </label>
                                     </div>
                                   </div>
                                
                            </div>
                            
                        </div>
                   
                    <div class="text-center">
                        <button type="button" class="btn btn-success" id="btnAddSolicitud" onclick="abrirModal()">Continuar</button>
                    </div>
                            
                   <div class="modal fade" id="modalArchivos" tabindex="-1" aria-labelledby="modalArchivosLabel" aria-hidden="true" data-bs-backdrop="false" data-bs-keyboard="false">
                        <div class="modal-dialog modal-xl-custom modal-dialog-scrollable modal-dialog-centered">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="modalArchivosLabel">Subir documentos requeridos</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                          </div>
                          <div class="modal-body">

                            <!-- Nav Tabs -->
                            <ul class="nav nav-tabs" id="docTabs" role="tablist">
                               <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="tab-pago" data-bs-toggle="tab" data-bs-target="#contenido-pago" type="button" role="tab">Recibo de Pago</button>
                              </li>
                              <li class="nav-item" role="presentation">
                                <button class="nav-link" id="tab-titulo" data-bs-toggle="tab" data-bs-target="#contenido-titulo" type="button" role="tab">Título</button>
                              </li>
                              <li class="nav-item" role="presentation">
                                <button class="nav-link" id="tab-ced" data-bs-toggle="tab" data-bs-target="#contenido-ced" type="button" role="tab">Cédula</button>
                              </li>
                              <li class="nav-item" role="presentation">
                                <button class="nav-link" id="comprobante-tab" data-bs-toggle="tab" data-bs-target="#comprobante" type="button" role="tab">Domicilio</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="foto-tab" data-bs-toggle="tab" data-bs-target="#foto" type="button" role="tab">Fotos</button>
                            </li>
                            </ul>

                            <!-- Tab Content -->
                            <div class="tab-content mt-3" id="docTabsContent">
                                
                                <!-- TAB Pago -->
                                <div class="tab-pane fade show active" id="contenido-pago" role="tabpanel">
                                     <label class="form-label">Recibo de Pago</label>
                                    <div id="pago" class="drop-zone mt-2" style="display: block">
                                    Arrastra aquí o haz clic para subir tu archivo
                                    <input class="archivo-carga" type="file" name="reciboPagoF" accept=".pdf">
                                    <div class="file-name" id="file-name-titulo"></div>
                                  </div>
                                </div>
                                
                              <!-- TAB Titulo -->
                              <div class="tab-pane fade show " id="contenido-titulo" role="tabpanel">

                                <label class="form-label">Título de Técnico</label>
                                <s:set var="docEncontrado" value="%{false}" />
                                <s:set var="tecTitulo" value="%{-1}" />
                                <s:iterator value="documentos">
                                  <s:if test="tipo_archivo == 'tecTitulo'">
                                    <s:set var="docEncontrado" value="%{true}" />
                                    <s:set var="tecTitulo" value="%{id_documento}" />
                                    <div id="tecnico-existente" class="border p-3 rounded bg-light text-center">
                                      <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                      <a href="download?rutaDescarga=<s:property value='ruta_archivo' />">
                                        <s:property value="nombre" />
                                      </a>
                                      <div class="mt-2">
                                        <a href="javascript:void(0);" onclick="mostrarCarga('tecnico')" class="text-primary text-decoration-underline">Reemplazar documento</a>
                                      </div>
                                    </div>
                                  </s:if>
                                </s:iterator>
                                <input type="hidden" name="documentosPrecargados['tecTitulo']" value="<s:property value='%{#tecTitulo}'/>">
                                <div id="tecnico-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                    <label for="input-tecTitulo" class="form-label d-block">Título de Técnico</label>
                                  Arrastra aquí o haz clic para subir tu archivo
                                  <input class="archivo-carga" type="file" name="tecTitulo" accept=".pdf">
                                  <div class="file-name" id="file-name-titulo"></div>
                                </div>
                                  <br>
                                   <label class="form-label">Título Licenciatura</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="licTitulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'licTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="licTitulo" value="%{id_documento}" />
                                                    <div id="licenciatura-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('licenciatura')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                             <input type="hidden" name="documentosPrecargados['licTitulo']" value="<s:property value='%{#licTitulo}'/>">
                                            <div id="licenciatura-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="licTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            <br>
                                           <label class="form-label">Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="maestTitulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maestTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="maestTitulo" value="%{id_documento}" />
                                                    <div id="maestria-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestria')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                             <input type="hidden" name="documentosPrecargados['maestTitulo']" value="<s:property value='%{#maestTitulo}'/>">
                                            <div id="maestria-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="maestTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                <br>
                                            <label class="form-label">Segunda Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="maest2Titulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maest2Titulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="maest2Titulo" value="%{id_documento}" />
                                                    <div id="maestria2-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestria2')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['maest2Titulo']" value="<s:property value='%{#maest2Titulo}'/>">
                                            <div id="maestria2-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="maest2Titulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                              <br>  
                                            <label class="form-label">Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="espTitulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'espTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="espTitulo" value="%{id_documento}" />
                                                    <div id="especialidad-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('especialidad')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['espTitulo']" value="<s:property value='%{#espTitulo}'/>">
                                            <div id="especialidad-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="espTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                               <br> 
                                            <label class="form-label">Segunda Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="esp2Titulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'esp2Titulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="esp2Titulo" value="%{id_documento}" />
                                                    <div id="especialidad2-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                      <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('esp2Titulo')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                             <input type="hidden" name="documentosPrecargados['esp2itulo']" value="<s:property value='%{#esp2Titulo}'/>">
                                            <div id="especialidad2-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="esp2Titulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            <br>
                                           <label class="form-label">Subespecialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="subespTitulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'subespTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="subespTitulo" value="%{id_documento}" />
                                                    <div id="subespecialidad-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('subespecialidad')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['subespTitulo']" value="<s:property value='%{#subespTitulo}'/>">
                                            <div id="subespecialidad-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="subespTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                               <br> 
                                            <label class="form-label">Doctorado</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="docTitulo" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'docTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="docTitulo" value="%{id_documento}" />
                                                    <div id="doctorado-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('doctorado')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['docTitulo']" value="<s:property value='%{#docTitulo}'/>">
                                            <div id="doctorado-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="docTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                              </div>

                              <!-- TAB Cedula -->
                              <div class="tab-pane fade" id="contenido-ced" role="tabpanel">
                                  <label class="form-label">Cédula de Técnico</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="tecCed" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'tecCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="tecCed" value="%{id_documento}" />
                                                    <div id="tecnicoCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('tecnicoCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['tecCed']" value="<s:property value='%{#tecCed}'/>">
                                            <div id="tecnicoCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="tecCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                <br>
                                             <label class="form-label">Licenciatura</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="licCed" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'licCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="licCed" value="%{id_documento}" />
                                                    <div id="licenciaturaCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('licenciaturaCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['licCed']" value="<s:property value='%{#licCed}'/>">
                                            <div id="licenciaturaCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="licCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            <br>
                                           <label class="form-label">Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="maestCed" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maestCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="maestCed" value="%{id_documento}" />
                                                    <div id="maestriaCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestriaCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['maestCed']" value="<s:property value='%{#maestCed}'/>">
                                            <div id="maestriaCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="maestCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                               <br> 
                                            <label class="form-label">Segunda Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="maest2Ced" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maest2Ced'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="maest2Ced" value="%{id_documento}" />
                                                    <div id="maestria2Ced-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestria2Ced')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['maest2Ced']" value="<s:property value='%{#maest2Ced}'/>">
                                            <div id="maestria2Ced-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="maest2Ced" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                              <br>  
                                            <label class="form-label">Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="espCed" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'espCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="espCed" value="%{id_documento}" />
                                                    <div id="especialidadCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('especialidadCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['espCed']" value="<s:property value='%{#espCed}'/>">
                                            <div id="especialidadCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="espCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                              <br>  
                                                
                                            <label class="form-label">Segunda Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="esp2Ced" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'esp2Ced'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="esp2Ced" value="%{id_documento}" />
                                                    <div id="especialidad2Ced-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('especialidad2Ced')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['esp2Ced']" value="<s:property value='%{#esp2Ced}'/>">
                                            <div id="especialidad2Ced-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="esp2Ced" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            <br>
                                           <label class="form-label">Subespecialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="subespCed" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'subespCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="subespCed" value="%{id_documento}" />
                                                    <div id="subespecialidadCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('subespecialidadCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['subespCed']" value="<s:property value='%{#subespCed}'/>">
                                            <div id="subespecialidadCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="subespCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                              <br>  
                                            <label class="form-label">Doctorado</label>

                                            <s:set var="docEncontrado" value="%{false}" />
                                            <s:set var="docCed" value="%{-1}" />
                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'docCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <s:set var="docCed" value="%{id_documento}" />
                                                    <div id="doctoradoCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('doctoradoCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                            <input type="hidden" name="documentosPrecargados['docCed']" value="<s:property value='%{#docCed}'/>">
                                            <div id="doctoradoCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="tituloArchivo" name="docCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                              </div>
                                                
                                          <div class="tab-pane fade" id="comprobante" role="tabpanel">
                                                <label for="comprobanteArchivo" class="form-label">Sube el comprobante de domicilio:</label>
                                                 <s:set var="docEncontrado" value="%{false}" />

                                                <s:iterator value="documentos">
                                                    <s:if test="tipo_archivo == 'domicilioF'">
                                                        <s:set var="docEncontrado" value="%{true}" />
                                                        <div id="domicilio-existente" class="border p-3 rounded bg-light text-center">
                                                            <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                            <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                                <s:property value="nombre" />
                                                            </a>
                                                            <div class="mt-2">
                                                                <a href="javascript:void(0);" onclick="mostrarCarga('domicilio')" class="text-primary text-decoration-underline">
                                                                    Reemplazar documento
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </s:if>
                                                </s:iterator>
                                        
                                            <div id="domicilio-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-domicilio" name="domicilioF" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            </div>
                                            <div class="tab-pane fade" id="foto" role="tabpanel">
                                                <label for="fotosArchivo" class="form-label">Sube tus fotografías:</label>
                                                <s:set var="docEncontrado" value="%{false}" />

                                                <s:iterator value="documentos">
                                                    <s:if test="tipo_archivo == 'fotosF'">
                                                        <s:set var="docEncontrado" value="%{true}" />
                                                        <div id="fotos-existente" class="border p-3 rounded bg-light text-center">
                                                            <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                           <a href="download?rutaDescarga=<s:property value='ruta_archivo' />" >
                                                                <s:property value="nombre" />
                                                            </a>
                                                            <div class="mt-2">
                                                                <a href="javascript:void(0);" onclick="mostrarCarga('fotos')" class="text-primary text-decoration-underline">
                                                                    Reemplazar documento
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </s:if>
                                                </s:iterator>
                                        
                                            <div id="fotos-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="fotografias" name="fotosF" accept=".jpg,.jpeg,.png">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            </div>

                            </div> <!-- .tab-content -->

                          </div>
                          <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Enviar</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                          </div>
                        </div>
                      </div>
                    </div>
                </s:form>
            </div>
        </div>
        
    </div>
    <div id="spinner-container" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(255, 255, 255, 0.8); z-index: 1000; justify-content: center; align-items: center; flex-direction: column;">
                <div class="spinner-grow text-success" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
                <p style="color: green; font-weight: bold; margin-top: 10px;">Enviando Solicitud...</p>
    </div>
   <script src="${pageContext.request.contextPath}/js/utileriasSolicitudPV.js"></script> 
</main>
<%@include file="../includes/pie.jspf" %>



<style>
.modal {
  display: none;
  position: fixed;
  z-index: 1050;
  left: 0; top: 0; width: 100%; height: 100%;
  background-color: rgba(0,0,0,0.5);
}
.modal-content {
  margin: 10% auto; background: white; padding: 20px;
  border-radius: 10px; width: 40%; max-width: 600px;
}
.cerrar {
  float: right; font-size: 24px; cursor: pointer;
}

.modal-xl-custom {
  width: 98vw !important;      
  max-width: 98vw !important;
}

.modal-xl-custom .modal-content {
  width: 100% !important;
}

</style>
