<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>


<main class="flex-grow-1 d-flex flex-column container-fluid">
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center principal">
        <div class="row justify-content-center w-100">
            <div class="col-lg-6">
                <div class="card shadow-lg border-0 rounded-lg">
                    <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                        <h5 class="font-weight-bold my-2">Registro de Pago</h5>
                    </div>
                    <div class="card-body">
                        <s:form action="actualizaHojaPago" method="post" class="px-4">
                            <div class="mb-3">
                                <label for="clavePago" class="form-label">Clave de Pago</label>
                                <input type="text" id="clavePago" name="hojaPago.clavePago" value="<s:property value="hojaPago.clavePago"/>" class="form-control" required maxlength="20" placeholder="Ej. 1234567890ABC">
                            </div>
                            <div class="mb-3">
                                <label for="totalPagar" class="form-label">Total a Pagar</label>
                                <div class="input-group">
                                    <span class="input-group-text">$</span>
                                    <input type="number" id="totalPagar" name="hojaPago.totalPago" class="form-control" value="<s:property value="hojaPago.totalPago"/>" step="0.01" min="0" required placeholder="Ej. 500.00">
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-success">Guardar Cambios</button>
                            </div>
                        </s:form>
                    </div>
                    <div class="card-footer text-muted text-center">
                        Asegúrate de ingresar los datos correctamente antes de guardar los cambios.
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="../includes/pie.jspf" %>