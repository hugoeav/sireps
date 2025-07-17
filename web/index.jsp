<%
    session.removeAttribute("curp");
    
%>

<%@include file="includes/cabecera.jspf" %>

<main class="flex-grow-1 d-flex flex-column container-fluid">
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center principal" >
        <div class="row justify-content-center w-100">
            <div class="col-lg-8">
                <div class="card shadow-lg border-0 rounded-lg">
                    <div class="card-header text-center" style="background-color: #3c8b41; color: white;">
                        <h5 class="font-weight-bold my-3">DOCUMENTOS PARA REGISTRO DE TITULO POR PRIMERA VEZ DE LICENCIATURA/TÉCNICOS Y ESPECIALIDAD Y/O MAESTRÍA</h5>
                    </div>
                    <!-- Contenido principal -->
                    <div class="card-body text-center">
                        <p class="fs-7">
                            <b>EL ART.69 DE LA LEY DE SALUD DEL ESTADO DE SAN LUIS POTOSÍ</b> establece que:<br>
                            Para el ejercicio de actividades en el campo de la salud, se requiere que los titulos profesionales o certificados de especialización, hayan sido
                            legalmente expedidos y registrados por las autoridades competentes.
                            Todos los profesionales y técnicos del ramo de la salud que deseen ejercer su profesión en la Entidad, deberán obtener su registro respectivo ante la Secretería de
                            Salud del Estado.
                        </p>
                        
                        <hr class="hr hr-blurry my-1" />
                        <p class="fs-7">
                            Los documentos abajo descritos es para obtener el registro por <b>"primera vez"</b>. Favor de leerlos con detenimiento cada uno de ellos.<br>
                        </p>
                        
                        <div>
                        <div class="text-start"> 
                            <ol>
                                <li><b>Ficha de pago:</b> Realizar el pago en cualquier oficina de recaudación de la <b><u>Secretería de Finanzas del Estado</u></b> y presentar el comprobante <b><u>original y copia</u></b></li>
                                <li>Solicitud de registro: Proporcionar datos correctos</li>
                                <li><b>Requisitos:</b>
                                    <ul class="ps-4"> 
                                        <li>Fotocopia simple <b><u>título profesional</u></b> tamaño carta por ambos lados</li>
                                        <li>Fotocopia simple de <b><u>cédula profesional</u></b> tamaño postal por ambos lados y/o imprimir en tamaño carta cédula electrónica.</li>
                                        <li>2 fotografías blanco y negro o color, tamaño credencial ovaladas autoadheribles recientes, de frente, papel mate, vestido formal, personal enfermería con uniforme oficial.<br>
                                            <b>IDENTIFICAR LAS FOTOS CON SU NOMBRE AL REVERSO DE CADA UNA</b> 
                                        </li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                        </div>
                        
                        <hr class="hr hr-blurry" />
                        
                        <p class="fs-7">
                            La recepción de documentos será los dias <b>martes y jueves</b> de 8:30 a 14:00 hrs. con la Lic. Sujey López, en la Subdirección de Calidad y Educación en Salud, ubicado en Calzada
                            de Guadalupe No. 5850, tercer piso, Col.Lomas de la Virgen C.P. 78380, tel, 8 34 11 00 Ext. 21263
                        </p>
                        <hr class="hr hr-blurry" />
                        
                        <p class="fs-7">
                            Es importante que en la fecha que se le indique que debe recoger su Registro de Titulo Profesional, presente su comprobante de recepción de documentos. Solo cuenta con 60 días
                            naturales para recogerlo, de lo contrario deberá realizar nuevamente el trámite completo.
                        </p>
                        <s:if test="#session.usuario == null">
                    <s:a action="crearUsuarioVal">

                    <span class="btn btn-outline-secondary">Registrarse</span>
                </s:a>
                        </s:if>
                    </div>

                </div>
            </div>
        </div>
       
    </div>
</main>
<%@include file="includes/pie.jspf" %>


