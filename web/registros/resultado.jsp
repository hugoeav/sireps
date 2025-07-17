<%-- 
    Document   : resultado
    Created on : 10/04/2025, 01:40:57 PM
    Author     : hugoeav
--%>

<%@include file="../includes/cabecera.jspf" %>
<main class="flex-grow-1 d-flex flex-column">
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center principal" >
        <div class="row justify-content-center w-100">
            <div class="col-lg-8">
                <div class="card shadow-lg border-0 rounded-lg">
                    <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                        <h5 class="font-weight-bold my-3">Consulta de Registro de Título</h5>
                    </div>
                    <div class="card-body text-center">
                        <div class="card-body text-start">
                            <ul class="nav nav-tabs" id="resultadoTabs" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="resultados-tab" data-bs-toggle="tab" data-bs-target="#resultados" type="button" role="tab" aria-controls="resultados" aria-selected="true">Resultados</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="detalle-tab" data-bs-toggle="tab" data-bs-target="#detalle" type="button" role="tab" aria-controls="detalle" aria-selected="false">Detalle</button>
                                </li>
                            </ul>
                            <div class="tab-content mt-3" id="resultadoTabsContent">
                               
                                <div class="tab-pane fade show active" id="resultados" role="tabpanel" aria-labelledby="resultados-tab">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-striped">
                                            <thead class="table-success">
                                                <tr>
                                                    <th>Número de Registro</th>
                                                    <th>Nombre</th>
                                                    <th>Primer Apellido</th>
                                                    <th>Segundo Apellido</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>123456</td>
                                                    <td>Ana</td>
                                                    <td>Pérez</td>
                                                    <td>Gomez</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                               
                                <div class="tab-pane fade" id="detalle" role="tabpanel" aria-labelledby="detalle-tab">
                                    <div class="mt-3">
                                        <p><strong>Número de Registro:</strong> 123456</p>
                                        <p><strong>Nombre del Profesional:</strong> Ana Pérez Gómez</p>
                                        <p><strong>Año de Expedición:</strong> 2020</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="../includes/pie.jspf" %>