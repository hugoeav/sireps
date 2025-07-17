<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<main class="flex-grow-1 d-flex flex-column" style="min-height: 80vh;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <!-- Encabezado con color personalizado -->
                    <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                        <h3 class="font-weight-light my-4">Crear Usuario</h3>
                    </div>
                    <div class="card-body">
                        <s:form action="registro" method="post">
                            <!-- Campo de CURP -->
                            <div class="form-floating mb-4">
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    id="curp" 
                                    name="usuarioInput.curp" 
                                    placeholder="CURP" value="${curp}" disabled="true" required>
                                <label for="curp">CURP</label>
                            </div>
                                
                            <div class="form-floating mb-4">
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    id="curp" 
                                    name="usuarioInput.curp" 
                                    placeholder="CURP"  required>
                                <label for="nombre">Nombre</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    id="curp" 
                                    name="usuarioInput.curp" 
                                    placeholder="CURP"  required>
                                <label for="primerApellido">Primer Apellido</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    id="curp" 
                                    name="usuarioInput.curp" 
                                    placeholder="CURP"  >
                                <label for="segundoApellido">Segundo Apellido</label>
                            </div>
                                
                            
                            <div class="form-floating mb-4 required">
                                <input 
                                    type="password" 
                                    class="form-control" 
                                    id="password" 
                                    name="password" 
                                    placeholder="Contraseña" required>
                                <label for="password">Contraseña</label>
                            </div>
                             <div class="form-floating mb-4 required">
                                <input 
                                    type="password" 
                                    class="form-control" 
                                    id="passwordValidation" 
                                    placeholder="Contraseña" required>
                                <label for="password">Repite Contraseña</label>
                            </div>
                           
                      
                            <div class="d-grid">
                                <button type="submit" class="btn custom-btn btn-lg btn-success">Crear Usuario</button>
                            </div>
                        </s:form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="../includes/pie.jspf" %>

<%
    session.removeAttribute("curp");
    
%>