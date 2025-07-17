<%@include file="includes/cabecera.jspf" %>
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
                        <h3 class="font-weight-light my-4">Iniciar Sesión</h3>
                    </div>
                    <div class="card-body">
                        <s:form action="login" method="post">
                            <!-- Campo de Usuario -->
                            <div class="form-floating mb-4">
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    id="username" 
                                    name="usuarioInput.curp" 
                                    placeholder="CURP" value="<s:property value="#session.curp" />"  oninput="this.value = this.value.toUpperCase()" required>
                                <label for="username">CURP</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input 
                                    type="password" 
                                    class="form-control" 
                                    id="password" 
                                    name="usuarioInput.contrasena" 
                                    placeholder="Contraseña" required>
                                <label for="password">Contraseña</label>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn custom-btn btn-lg btn-success">Iniciar Sesión</button>
                            </div>
                        </s:form>
                   
                        <div class="text-center mt-3">
                            <a href="recoverPassword.jsp">¿Olvidaste tu contraseña?</a>
                        </div>
                        <div class="text-center mt-2">
                            <a href="crearUsuarioVal">Crear una cuenta</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="includes/pie.jspf" %>

<%
    session.removeAttribute("curp");
    
%>