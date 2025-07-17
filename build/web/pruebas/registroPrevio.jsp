<%@include file="../includes/cabecera.jspf" %>
<main class="flex-grow-1 container my-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="card shadow-lg border-0 rounded-lg mt-4">
                    <div class="card-header text-white text-center" style="background-color: #d9534f;">
                        <h4 class="my-2">Cédula ya registrada - Solicitud de Reclamo</h4>
                    </div>
                    <div class="card-body">
                        <div class="alert alert-warning text-center">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            Hemos detectado que la <strong>cédula profesional ingresada ya está registrada</strong> en nuestro sistema.
                            <br>Se ha creado automáticamente una <strong>solicitud de reclamo</strong> para verificar tu identidad.
                        </div>

                        <p class="mb-4 text-center">Para continuar, por favor actualiza la siguiente información para validar tu identidad y continuar con tu proceso:</p>

                        <form id="formActualizaDatos" method="post" action="actualizaDatosReclamo.action">
                            <div class="row">
                                <!-- Grados Académicos -->
                                <div class="col-md-12 mb-4">
                                    <label for="grados" class="form-label fw-bold">Grados Académicos</label>
                                    <select class="form-select select2" id="grados" name="usuarioInput.grados[]" multiple required>
                                        <option value="Tecnico">Técnico</option>
                                        <option value="Licenciatura">Licenciatura</option>
                                        <option value="Especialidad">Especialidad</option>
                                        <option value="Maestría">Maestría</option>
                                        <option value="Doctorado">Doctorado</option>
                                    </select>
                                    <div class="form-text">Puedes seleccionar más de uno si aplica.</div>
                                </div>

                                <!-- Dirección Particular -->
                                <div class="col-md-12 mb-4">
                                    <label class="form-label fw-bold" for="direccionParticular">Dirección Particular</label>
                                    <textarea class="form-control" id="direccionParticular" name="usuarioInput.direccionParticular" rows="3" required></textarea>
                                </div>

                                <!-- Dirección Laboral -->
                                <div class="col-md-12 mb-4">
                                    <label class="form-label fw-bold" for="direccionLaboral">Dirección Laboral</label>
                                    <textarea class="form-control" id="direccionLaboral" name="usuarioInput.direccionLaboral" rows="3" required></textarea>
                                </div>
                            </div>

                            <div class="d-grid mt-4">
                                <button type="submit" class="btn btn-success btn-lg">Actualizar Información</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="../includes/pie.jspf" %>