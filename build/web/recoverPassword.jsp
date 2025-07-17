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
                        <h3 class="font-weight-light my-4">Recuperar Contraseña</h3>
                    </div>
                    <div class="card-body">
                        <form action="getToken" method="post">
                            <label>Curp:</label>
                            <input type="email" name="curp" required />
                            <input class="btn btn-success" type="submit" value="Enviar link de recuperación" />
                          </form>
                   
                     
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