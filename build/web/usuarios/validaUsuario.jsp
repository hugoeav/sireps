<%@include file="../includes/cabecera.jspf" %>
<%
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String curpValidada = (String) session.getAttribute("curpValidada");
    Boolean curpYaRegistrada = (Boolean) session.getAttribute("curpYaRegistrada");
%>
<main class="flex-grow-1 container my-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                        <h3 class="font-weight-light my-4">Crear Usuario</h3>
                    </div>
                    <div class="card-body">
                        
                       <s:if test="#session.curpValidada == null">
                            <!-- Mostrar primer formulario si aún no se ha validado CURP -->
                       
                                <s:form action="validaUser" method="post" >
                                    <div class="form-floating mb-4">
                                        <input 
                                            type="text" 
                                            class="form-control" 
                                            id="curp" 
                                            name="usuarioInput.curp" 
                                            placeholder="CURP" required>
                                        <label for="curp">CURP</label>
                                    </div>
                                    <div class="text-center mt-3">
                                        <a href="https://www.gob.mx/curp/" target="_blank">¿No conoces tu CURP?</a>
                                    </div>
                                    <div class="d-grid mt-4">
                                        <button type="submit" class="btn custom-btn btn-lg btn-success">Validar</button>
                                    </div>
                                </s:form>
                                <s:if test="#session.resultMessage != null">
                                    <div class="alert alert-danger text-center mt-4" role="alert">
                                        <ul style="list-style-type: disc; text-align: left; display: inline-block;">
                                            <li>Esta CURP ya se encuentra registrada. Si crees que es un error, comunícate al xxxxxxx. O <a href="iniciarSesion" >inicia Sesión</a></li>
                                        </ul>
                                    </div>
                                </s:if>
                       </s:if>
                <s:else>       
                    <s:form id="formReg" action="registro" method="post" onsubmit="return validarRegistro()">
                            <div class="row">
                                <!-- CURP -->
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="addUserCurp" name="usuarioInput.curp" value="${curpValidada}" readonly required>
                                        <label for="addUserCurp">CURP</label>
                                    </div>
                                </div>
                                <!-- RFC -->
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="addUserrfc" name="usuarioInput.rfc" value="${curpValidada}" readonly required>
                                        <label for="addUserrfc">RFC sin homoclave</label>
                                    </div>
                                </div>

                                <!-- Nombre -->
                                <div class="col-md-6 mb-4 position-relative">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="usuarioInput.nombre" id="addUserName" placeholder="Nombre" required>
                                        <label for="addUserName">Nombre</label>
                                        <span class="position-absolute top-50 end-0 translate-middle-y me-2"
                                              data-bs-toggle="tooltip"
                                              data-bs-placement="left"
                                              title="Debes de capturar tu nombre de acuerdo a tu acta de nacimiento, si capturas acentos, aparecerán en tu registro oficial."
                                              style="cursor: pointer;">
                                            <i class="fas fa-info-circle text-secondary"></i>
                                        </span>
                                    </div>
                                </div>

                                <!-- Primer Apellido -->
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="usuarioInput.primerApellido" id="addUserLName" placeholder="Primer Apellido" required>
                                        <label for="addUserLName">Primer Apellido</label>
                                    </div>
                                </div>

                                <!-- Segundo Apellido -->
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="usuarioInput.segundoApellido" id="addUserSecName" placeholder="Segundo Apellido">
                                        <label for="addUserSecName">Segundo Apellido</label>
                                    </div>
                                </div>
                                
                                <!-- Sexo -->
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <select class="form-select" id="sexo" name="usuarioInput.sexo" required>
                                            <option value="">Selecciona el sexo</option>
                                            <option value="H">Hombre</option>
                                            <option value="M">Mujer</option>
                                        </select>
                                    <label for="sexo">Sexo</label>
                                    </div>
                                </div>
                               
                                        <!-- Fecha de nacimiento -->
                                        <div class="col-md-6 mb-4">
                                            <div class="form-floating">
                                                <input type="date" class="form-control" id="fechaNacimiento" name="usuarioInput.fechaNacimiento" required>
                                                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                                            </div>
                                        </div>

                                        <!-- Estado de nacimiento -->
                                        <div class="col-md-6 mb-4">
                                            <div class="form-floating">
                                                <select class="form-select" id="entidadNacimiento" name="usuarioInput.entidadNacimiento" required>
                                                    <option value="">Selecciona el estado de nacimiento</option>
                                                    <option value="AS">Aguascalientes</option>
                                                    <option value="BC">Baja California</option>
                                                    <option value="BS">Baja California Sur</option>
                                                    <option value="CC">Campeche</option>
                                                    <option value="CL">Coahuila</option>
                                                    <option value="CM">Colima</option>
                                                    <option value="CS">Chiapas</option>
                                                    <option value="CH">Chihuahua</option>
                                                    <option value="DF">Ciudad de México</option>
                                                    <option value="DG">Durango</option>
                                                    <option value="GT">Guanajuato</option>
                                                    <option value="GR">Guerrero</option>
                                                    <option value="HG">Hidalgo</option>
                                                    <option value="JC">Jalisco</option>
                                                    <option value="MC">México</option>
                                                    <option value="MN">Michoacán</option>
                                                    <option value="MS">Morelos</option>
                                                    <option value="NT">Nayarit</option>
                                                    <option value="NL">Nuevo León</option>
                                                    <option value="OC">Oaxaca</option>
                                                    <option value="PL">Puebla</option>
                                                    <option value="QT">Querétaro</option>
                                                    <option value="QR">Quintana Roo</option>
                                                    <option value="SP">San Luis Potosí</option>
                                                    <option value="SL">Sinaloa</option>
                                                    <option value="SR">Sonora</option>
                                                    <option value="TC">Tabasco</option>
                                                    <option value="TS">Tamaulipas</option>
                                                    <option value="TL">Tlaxcala</option>
                                                    <option value="VZ">Veracruz</option>
                                                    <option value="YN">Yucatán</option>
                                                    <option value="ZS">Zacatecas</option>
                                                    <option value="NE">Nacido en el extranjero</option>
                                                </select>
                                                <label for="entidadNacimiento">Estado de Nacimiento</label>
                                            </div>
                                        </div>

                                <!-- Correo -->
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <input type="email" class="form-control" id="correo" name="usuarioInput.correo" placeholder="Correo electrónico" required>
                                        <label for="correo">Correo electrónico</label>
                                    </div>
                                </div>
                                
                                <div class="col-md-6 mb-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="cedulaProfesional" name="gradoInput.cedula" placeholder="Cédula Profesional" required>
                                        <label for="cedulaProfesional">Cédula Profesional Técnico/Licenciatura</label>
                                    </div>
                                </div>
                                
                                <!-- Contraseña -->
                                <div class="col-md-6 mb-4 position-relative">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="password" name="usuarioInput.contrasena" placeholder="Contraseña" required>
                                        <label for="password">Contraseña</label>
                                        <span class="position-absolute top-50 end-0 translate-middle-y me-5"
                                              data-bs-toggle="tooltip"
                                              data-bs-placement="left"
                                              title="La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número."
                                              style="cursor: pointer;">
                                            <i class="fas fa-info-circle text-secondary"></i>
                                        </span>
                                        <span class="position-absolute top-50 end-0 translate-middle-y me-2"
                                              style="cursor: pointer;"
                                              onclick="togglePasswordVisibility('password', this)">
                                            <i class="fas fa-eye text-secondary"></i>
                                        </span>
                                    </div>
                                </div>

                                <!-- Repite Contraseña -->
                                <div class="col-md-6 mb-4 position-relative">
                                    <div class="form-floating">
                                        <input type="password" id="passwordValidation" class="form-control" placeholder="Repite Contraseña" required>
                                        <label for="passwordValidation">Repite Contraseña</label>
                                        <span class="position-absolute top-50 end-0 translate-middle-y me-2"
                                              style="cursor: pointer;"
                                              onclick="togglePasswordVisibility('passwordValidation', this)">
                                            <i class="fas fa-eye text-secondary"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <!-- Términos -->
                            <div class="form-check mb-4">
                                <input class="custom-control-indicator" type="checkbox" value="1" id="terminos">
                                <label class="form-check-label" for="terminos">
                                    Acepto los <a href="#">términos de uso y condiciones</a>.
                                </label>
                            </div>

                            <!-- Botón -->
                            <div class="d-grid">
                                <button type="submit" id="registraButton" class="btn custom-btn btn-lg btn-success">Crear Usuario</button>
                            </div>

                            <!-- Editar CURP -->
                            <div class="text-center mt-4">
                                <s:a action="crearUsuarioVal">
                                    <span class="btn btn-outline-secondary">Editar CURP</span>
                                </s:a>
                            </div>
                        </s:form>
                                    
                </s:else>
                  

                    </div>
                </div>
            </div>
        </div>
    </div>
   <!-- Modal -->
<div class="modal fade" id="confirmacionModal" tabindex="-1" aria-labelledby="confirmacionModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmacionModalLabel">Confirmación de Datos</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p>Por favor, revise cuidadosamente que los siguientes datos sean correctos:</p>
        <ul class="list-group mb-3">
          <li class="list-group-item"><strong>CURP:</strong> <span id="conf_curp"></span></li>
          <li class="list-group-item"><strong>RFC (Sin Homoclave):</strong> <span id="conf_rfc"></span></li>
          <li class="list-group-item"><strong>Nombre:</strong> <span id="conf_nombre"></span></li>
          <li class="list-group-item"><strong>Primer Apellido:</strong> <span id="conf_apellido1"></span></li>
          <li class="list-group-item"><strong>Segundo Apellido:</strong> <span id="conf_apellido2"></span></li>
          <li class="list-group-item"><strong>Sexo:</strong> <span id="conf_sexo"></span></li>
          <li class="list-group-item"><strong>Fecha de Nacimiento:</strong> <span id="conf_fecha"></span></li>
          <li class="list-group-item"><strong>Entidad de Nacimiento:</strong> <span id="conf_entidad"></span></li>
          <li class="list-group-item"><strong>Correo:</strong> <span id="conf_correo"></span></li>
        </ul>

        <div class="alert alert-warning" role="alert">
          <strong>Nota:</strong> Es indispensable que la información capturada sea correcta y coincida fielmente con los documentos oficiales, ya que así será registrada en sus solicitudes.
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Revisar nuevamente</button>
        <button type="button" class="btn btn-success" id="confirmarEnvio">Confirmar y Enviar</button>
      </div>
    </div>
  </div>
</div>
 <script src="${pageContext.request.contextPath}/js/utileriasValidaUsuario.js"></script>
</main>
<%@include file="../includes/pie.jspf" %>



<%
    session.removeAttribute("curpValidada");
%>