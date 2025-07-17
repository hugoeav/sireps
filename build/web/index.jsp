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
                        <h5 class="font-weight-bold my-3">DOCUMENTOS PARA REGISTRO DE TITULO POR PRIMERA VEZ DE LICENCIATURA/T�CNICOS Y ESPECIALIDAD Y/O MAESTR�A</h5>
                    </div>
                    <!-- Contenido principal -->
                    <div class="card-body text-center">
                        <p class="fs-7">
                            <b>EL ART.69 DE LA LEY DE SALUD DEL ESTADO DE SAN LUIS POTOS�</b> establece que:<br>
                            Para el ejercicio de actividades en el campo de la salud, se requiere que los titulos profesionales o certificados de especializaci�n, hayan sido
                            legalmente expedidos y registrados por las autoridades competentes.
                            Todos los profesionales y t�cnicos del ramo de la salud que deseen ejercer su profesi�n en la Entidad, deber�n obtener su registro respectivo ante la Secreter�a de
                            Salud del Estado.
                        </p>
                        
                        <hr class="hr hr-blurry my-1" />
                        <p class="fs-7">
                            Los documentos abajo descritos es para obtener el registro por <b>"primera vez"</b>. Favor de leerlos con detenimiento cada uno de ellos.<br>
                        </p>
                        
                        <div>
                        <div class="text-start"> 
                            <ol>
                                <li><b>Ficha de pago:</b> Realizar el pago en cualquier oficina de recaudaci�n de la <b><u>Secreter�a de Finanzas del Estado</u></b> y presentar el comprobante <b><u>original y copia</u></b></li>
                                <li>Solicitud de registro: Proporcionar datos correctos</li>
                                <li><b>Requisitos:</b>
                                    <ul class="ps-4"> 
                                        <li>Fotocopia simple <b><u>t�tulo profesional</u></b> tama�o carta por ambos lados</li>
                                        <li>Fotocopia simple de <b><u>c�dula profesional</u></b> tama�o postal por ambos lados y/o imprimir en tama�o carta c�dula electr�nica.</li>
                                        <li>2 fotograf�as blanco y negro o color, tama�o credencial ovaladas autoadheribles recientes, de frente, papel mate, vestido formal, personal enfermer�a con uniforme oficial.<br>
                                            <b>IDENTIFICAR LAS FOTOS CON SU NOMBRE AL REVERSO DE CADA UNA</b> 
                                        </li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                        </div>
                        
                        <hr class="hr hr-blurry" />
                        
                        <p class="fs-7">
                            La recepci�n de documentos ser� los dias <b>martes y jueves</b> de 8:30 a 14:00 hrs. con la Lic. Sujey L�pez, en la Subdirecci�n de Calidad y Educaci�n en Salud, ubicado en Calzada
                            de Guadalupe No. 5850, tercer piso, Col.Lomas de la Virgen C.P. 78380, tel, 8 34 11 00 Ext. 21263
                        </p>
                        <hr class="hr hr-blurry" />
                        
                        <p class="fs-7">
                            Es importante que en la fecha que se le indique que debe recoger su Registro de Titulo Profesional, presente su comprobante de recepci�n de documentos. Solo cuenta con 60 d�as
                            naturales para recogerlo, de lo contrario deber� realizar nuevamente el tr�mite completo.
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


