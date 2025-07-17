<%-- 
    Document   : documentos
    Created on : 2/05/2025, 12:32:45 PM
    Author     : hugoeav
--%>

<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<main class="flex-grow-1 container my-4">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="card shadow border-0">
                <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                    <h5 class="font-weight-bold my-2">Carga de Documentos</h5>
                </div>
                <div class="card-body">
                    <s:form action="cargarDocs" method="post" enctype="multipart/form-data"  onsubmit="return validateForm()">
                        <input type="hidden" name="solicitudes" value="<s:property value='solicitudes'/>">
                        <ul class="nav nav-tabs mb-3" id="docTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="titulo-tab" data-bs-toggle="tab" data-bs-target="#titulo" type="button" role="tab">Título Profesional</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="cedula-tab" data-bs-toggle="tab" data-bs-target="#cedula" type="button" role="tab">Cédula Profesional</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="comprobante-tab" data-bs-toggle="tab" data-bs-target="#comprobante" type="button" role="tab">Comprobante de Domicilio</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="foto-tab" data-bs-toggle="tab" data-bs-target="#foto" type="button" role="tab">Fotografías</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="foto-tab" data-bs-toggle="tab" data-bs-target="#pago" type="button" role="tab">Comprobante de Pago</button>
                            </li>
                        </ul>

                        <div class="tab-content" id="docTabsContent">
                           
                            <div class="tab-pane fade show active" id="titulo" role="tabpanel">
                                            <label class="form-label">Titulo de Técnico</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'tecTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="tecnico-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                            <div class="mt-2">
                                                                <a href="javascript:void(0);" onclick="mostrarCarga('tecnico')" class="text-primary text-decoration-underline">
                                                                    Reemplazar documento
                                                                </a>
                                                            </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="tecnico-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo, no mayor a 3MB
                                                <input class="archivo-carga" type="file" id="input-tecnico" name="tecTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                             <label class="form-label">Título Licenciatura</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'licTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="licenciatura-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('licenciatura')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="licenciatura-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-licenciatura" name="licTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            
                                           <label class="form-label">Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maestTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="maestria-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestria')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="maestria-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-maestria" name="maestTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            <label class="form-label">Segunda Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maest2Titulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="maestria2-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                          <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestria2')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="maestria2-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-maestria2" name="maest2Titulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                                
                                            <label class="form-label">Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'espTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="especialidad-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('especialidad')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="especialidad-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-especialdad" name="espTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                                
                                            <label class="form-label">Segunda Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'esp2Titulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="especialidad2-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                      <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('esp2Titulo')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="especialidad2-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-esp2Titulo" name="tecnicoF" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            
                                           <label class="form-label">Subespecialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'subespTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="subespecialidad-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                         <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('subespecialidad')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="subespecialidad-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-subespecialidad" name="subespTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            <label class="form-label">Doctorado</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'docTitulo'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="doctorado-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                         <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('doctorado')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="doctorado-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-doctorado" name="docTitulo" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                           
                                     
                                     </div>
                                            <div class="tab-pane fade" id="cedula" role="tabpanel">
                                                <label class="form-label">Cédula de Técnico</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'tecCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="tecnicoCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('tecnicoCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="tecnicoCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-tecnicoCed" name="tecCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                             <label class="form-label">Licenciatura</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'licCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="licenciaturaCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('licenciaturaCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="licenciaturaCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-licenciaturaCed" name="licCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            
                                           <label class="form-label">Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maestCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="maestriaCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestriaCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="maestriaCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-maestriaCed" name="maestCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            <label class="form-label">Segunda Maestria</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'maest2Ced'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="maestria2Ced-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                          <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('maestria2Ced')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="maestria2Ced-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-maestria2Ced" name="maest2Ced" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                                
                                            <label class="form-label">Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'espCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="especialidadCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                         <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('especialidadCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="especialidadCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-especialdiadCed" name="espCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                                
                                            <label class="form-label">Segunda Especialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'esp2Ced'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="especialidad2Ced-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                         <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('especialidad2Ced')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="especialidad2Ced-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-especialidad2Ced" name="esp2Ced" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                            
                                           <label class="form-label">Subespecialidad</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'subespCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="subespecialidadCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                        <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('subespecialidadCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="subespecialidadCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-subespecialidadCed" name="subespCed" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            <label class="form-label">Doctorado</label>

                                            <s:set var="docEncontrado" value="%{false}" />

                                            <s:iterator value="documentos">
                                                <s:if test="tipo_archivo == 'docCed'">
                                                    <s:set var="docEncontrado" value="%{true}" />
                                                    <div id="doctoradoCed-existente" class="border p-3 rounded bg-light text-center">
                                                        <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                       <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                            <s:property value="nombre" />
                                                        </a>
                                                        <s:if test="!valido && valido != null">
                                                        <div class="mt-2">
                                                            <a href="javascript:void(0);" onclick="mostrarCarga('doctoradoCed')" class="text-primary text-decoration-underline">
                                                                Reemplazar documento
                                                            </a>
                                                        </div>
                                                        </s:if>
                                                    </div>
                                                </s:if>
                                            </s:iterator>
                                        
                                            <div id="doctoradoCed-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input class="archivo-carga" type="file" id="input-doctoradoCed" name="docCed" accept=".pdf">
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
                                                             <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                                <s:property value="nombre" />
                                                            </a>
                                                            <s:if test="!valido && valido != null">
                                                            <div class="mt-2">
                                                                <a href="javascript:void(0);"  onclick="mostrarCarga('domicilio')" class="text-primary text-decoration-underline">
                                                                    Reemplazar documento
                                                                </a>
                                                            </div>
                                                            </s:if>
                                                        </div>
                                                    </s:if>
                                                </s:iterator>
                                        
                                            <div id="domicilio-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input id="input-domicilio" class="archivo-carga" type="file"  name="domicilioF" accept=".pdf">
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
                                                           <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                                <s:property value="nombre" />
                                                            </a>
                                                            <s:if test="!valido && valido != null">
                                                            <div class="mt-2">
                                                                <a href="javascript:void(0);" onclick="mostrarCarga('fotos')" class="text-primary text-decoration-underline">
                                                                    Reemplazar documento
                                                                </a>
                                                            </div>
                                                            </s:if>
                                                        </div>
                                                    </s:if>
                                                </s:iterator>
                                        
                                            <div id="fotos-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input id="input-fotos" class="archivo-carga" type="file"  name="fotosF" accept=".jpg,.jpeg,.png">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            </div>
                                                
                                            <div class="tab-pane fade" id="pago" role="tabpanel">
                                                <label for="fotosArchivo" class="form-label">Sube tu comprobante de pago</label>
                                                <s:set var="docEncontrado" value="%{false}" />

                                                <s:iterator value="documentos">
                                                    <s:if test="tipo_archivo == 'compPagoF'">
                                                        <s:set var="docEncontrado" value="%{true}" />
                                                        <div id="pago-existente" class="border p-3 rounded bg-light text-center">
                                                            <i class="bi bi-file-earmark-pdf-fill text-danger" style="font-size: 2rem;"></i><br/>
                                                           <a href="#" onclick="verPdfModal('download?rutaDescarga=<s:property value='ruta_archivo' />')" >
                                                                <s:property value="nombre" />
                                                            </a>
                                                            <s:if test="!valido && valido != null">
                                                            <div class="mt-2">
                                                                <a href="javascript:void(0);" onclick="mostrarCarga('pago')" class="text-primary text-decoration-underline">
                                                                    Reemplazar documento
                                                                </a>
                                                            </div>
                                                            </s:if>
                                                        </div>
                                                    </s:if>
                                                </s:iterator>
                                        
                                            <div id="pago-subida" class="drop-zone mt-2" style="display: <s:property value='!#docEncontrado ? \"block\" : \"none\"' />;">
                                                Arrastra aquí o haz clic para subir tu archivo
                                                <input id="input-pago" class="archivo-carga" type="file"  name="reciboPagoF" accept=".pdf">
                                                <div class="file-name" id="file-name-titulo"></div>
                                            </div>
                                                
                                            </div>
                                       
                                    </div>
                        </div>

                        <div class="text-center mt-4">
                            <button class="btn btn-success" type="submit"><i class="bi bi-cloud-upload"></i> Guardar Documentos</button>
                        </div>
                    </s:form>
                </div>
            </div>
                <div id="spinner-container" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(255, 255, 255, 0.8); z-index: 1000; justify-content: center; align-items: center; flex-direction: column;">
                <div class="spinner-grow text-success" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
                <p style="color: green; font-weight: bold; margin-top: 10px;">Guardando...</p>
            </div>
        </div>
  <!-- Modal para ver PDF -->
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
   <script src="${pageContext.request.contextPath}/js/utileriasDocumentos.js"></script>
</main>

<%@include file="../includes/pie.jspf" %>



