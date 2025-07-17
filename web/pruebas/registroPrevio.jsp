<%@include file="../includes/cabecera.jspf" %>
<main class="flex-grow-1 container my-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="card shadow-lg border-0 rounded-lg mt-4">
                    <div class="card-header text-white text-center" style="background-color: #d9534f;">
                        <h4 class="my-2">C�dula ya registrada - Solicitud de Reclamo</h4>
                    </div>
                    <div class="card-body">
                        <div class="alert alert-warning text-center">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            Hemos detectado que la <strong>c�dula profesional ingresada ya est� registrada</strong> en nuestro sistema.
                            <br>Se ha creado autom�ticamente una <strong>solicitud de reclamo</strong> para verificar tu identidad.
                        </div>

                        <p class="mb-4 text-center">Para continuar, por favor actualiza la siguiente informaci�n para validar tu identidad y continuar con tu proceso:</p>

                        <form id="formActualizaDatos" method="post" action="actualizaDatosReclamo.action">
                            <div class="row">
                                <!-- Grados Acad�micos -->
                                <div class="col-md-12 mb-4">
                                    <label for="grados" class="form-label fw-bold">Grados Acad�micos</label>
                                    <select class="form-select select2" id="grados" name="usuarioInput.grados[]" multiple required>
                                        <option value="Tecnico">T�cnico</option>
                                        <option value="Licenciatura">Licenciatura</option>
                                        <option value="Especialidad">Especialidad</option>
                                        <option value="Maestr�a">Maestr�a</option>
                                        <option value="Doctorado">Doctorado</option>
                                    </select>
                                    <div class="form-text">Puedes seleccionar m�s de uno si aplica.</div>
                                </div>

                                <!-- Direcci�n Particular -->
                                <div class="col-md-12 mb-4">
                                    <label class="form-label fw-bold" for="direccionParticular">Direcci�n Particular</label>
                                    <textarea class="form-control" id="direccionParticular" name="usuarioInput.direccionParticular" rows="3" required></textarea>
                                </div>

                                <!-- Direcci�n Laboral -->
                                <div class="col-md-12 mb-4">
                                    <label class="form-label fw-bold" for="direccionLaboral">Direcci�n Laboral</label>
                                    <textarea class="form-control" id="direccionLaboral" name="usuarioInput.direccionLaboral" rows="3" required></textarea>
                                </div>
                            </div>

                            <div class="d-grid mt-4">
                                <button type="submit" class="btn btn-success btn-lg">Actualizar Informaci�n</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="../includes/pie.jspf" %>