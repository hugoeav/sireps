<%@include file="../includes/cabecera.jspf" %>

<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>
<main class="flex-grow-1 d-flex flex-column container-fluid">
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center principal" style="margin-top: 40px;">
        <div class="row justify-content-center w-100">
            <div class="col-lg-8">
                <div class="card shadow-lg border-0 rounded-lg">
                    <div class="card-header text-center" style="background-color: #dc3545; color: white;">
                        <h5 class="font-weight-bold my-2">Solicitud de Primera Vez Detectada</h5>
                    </div>
                    <div class="card-body text-center">
                        <p class="fs-5 text-danger">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            No puedes iniciar una nueva solicitud.
                        </p>
                        <p class="fs-6">
                            Actualmente tienes una solicitud de Primera Vez con el <strong>folio <span class="text-primary" id="folio-actual">${folio}</span></strong><s:iterator value="solicitudes"><s:if test="tipo == 'primeraVez'"><strong><s:property value="id_solicitud" /></strong></s:if></s:iterator>.
                        </p>
                        <p class="fs-6">
                            Por favor, finaliza esa solicitud si aún no concluye o solicita una actualización.
                        </p>

                        <div class="mt-4">
                            <a href="/RegistroP/solicitudes.action" class="btn btn-outline-success">
                                <i class="bi bi-arrow-left me-1 red-hover"></i> Volver a mis Solicitudes
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>                         
</main>
<%@include file="../includes/pie.jspf" %>