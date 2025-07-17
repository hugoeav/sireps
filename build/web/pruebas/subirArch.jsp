<%-- 
    Document   : subirArch
    Created on : 24/06/2025, 09:28:21 AM
    Author     : Informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="uploadFileTest" method="post" enctype="multipart/form-data" onsubmit="return validateForm()" >
                <!-- Campo para seleccionar archivo -->
                <div class="form-group mb-4">
                    <label for="file" class="form-label">Selecciona uno o más archivos:</label>
                    <input type="file" name="files" cssClass="form-control" id="file" multiple="multiple"/>
                </div>
                <!-- Botón de envío -->
                <div class="d-grid">
                    <button type="submit" cssClass="btn custom-btn btn-block" >Subir</button>
                </div>
            </form>
    </body>
</html>
