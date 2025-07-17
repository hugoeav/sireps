<%-- 
    Document   : busqueda
    Created on : 10/04/2025, 01:11:05 PM
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
                    <p>Datos de Consulta</p>
                    <s:form action="nuevaSolicitudPV" method="post" onsubmit="return validateForm()" acceptcharset="UTF-8">
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label for="nombre" class="form-label required">Nombre(s)</label>
                                <input type="text" name="nombre" class="form-control" id="addUserName" />
                            </div>
                            <div class="col-md-4">
                                <label for="ap" class="form-label required">Primer Apellido</label>
                                <input type="text" name="apellidoPaterno" class="form-control" id="addUserLName" />
                            </div>
                            <div class="col-md-4">
                                <label for="sap" class="form-label">Segundo Apellido</label>
                                <input type="text" name="apellidoMaterno" class="form-control" id="addUserSecName" />
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-success" id="btnAddSolicitud">Buscar</button>
                        </div>
                    </s:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="../includes/pie.jspf" %>
