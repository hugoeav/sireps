     document.addEventListener("DOMContentLoaded", function() {
    const curp = document.getElementById("curp");     
    const rfcInput = document.getElementById("addUserrfc");
    const nombreInput = document.getElementById("addUserName");
    const pApellidoInput = document.getElementById("addUserLName");
    const sApellidoInput = document.getElementById("addUserSecName");
    if(rfcInput !== null && rfcInput.value){
        rfcInput.value = rfcInput.value.substring(0, 10);
    }
    function convertirAMayusculas(event) {
        event.target.value = event.target.value.toUpperCase();
    }
    
    if(curp !== null){
        curp.addEventListener("input", convertirAMayusculas);
    }
    
    if(nombreInput !== null){
        nombreInput.addEventListener("input", convertirAMayusculas);
    }
    if(pApellidoInput !== null){
        pApellidoInput.addEventListener("input", convertirAMayusculas);
    }
    if(sApellidoInput !== null){
        sApellidoInput.addEventListener("input", convertirAMayusculas);
    }
    
    
  });
  
    document.addEventListener('DOMContentLoaded', function () {
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    });
    
    function togglePasswordVisibility(inputId, eyeIcon) {
        const input = document.getElementById(inputId);
        const icon = eyeIcon.querySelector("i");

        if (input.type === "password") {
            input.type = "text";
            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");
        } else {
            input.type = "password";
            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");
        }   
    }

    function validaFormatoCURP() {
        const curpInput = document.getElementById("curp");
        const curp = curpInput.value.toUpperCase().trim();
        const regexCurp = /^[A-Z]{4}\d{6}[HM][A-Z]{5}[A-Z\d]\d$/;

        if (!regexCurp.test(curp)) {
            alert("Por favor, ingresa una CURP válida.");
            return false;
        }

        return true;
    }
    
    function validarRegistro() {
        const curpInput = document.getElementById("addUserCurp");
        const nombreInput = document.getElementById("addUserName");
        const primerApellidoInput = document.getElementById("addUserLName");
        const segundoApellidoInput = document.getElementById("addUserSecName");
        const genero = document.getElementById('sexo').value;
        const fechaNac = document.getElementById('fechaNacimiento').value;
        const entidadNacimiento = document.getElementById('entidadNacimiento').value;
        const correoInput = document.getElementById("correo");
        const correo = document.getElementById("correo").value.trim();
        const passwordInput = document.getElementById("password");
        const password = document.getElementById("password").value;
        const passwordValInput = document.getElementById("passwordValidation");
        const passwordVal = document.getElementById("passwordValidation").value;
        const terminosInput = document.getElementById("terminos");
        const terminos = document.getElementById("terminos").checked;

        const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
        let valido = true;
        //reestablece errores
        document.getElementById("passwordError")?.remove();
        passwordInput.style.borderColor = "green";
        document.getElementById("correoError")?.remove();
        correoInput.style.borderColor = "green";
        document.getElementById("passwordValidationError")?.remove();
        passwordValInput.style.borderColor = "green";
        
    
        if (curpInput.value.trim() !== "") {
            
            const curpEsValida = validarCURP(curpInput, nombreInput, primerApellidoInput, segundoApellidoInput,genero,fechaNac,entidadNacimiento);
            if (!curpEsValida) {
                valido = false;
            }
            
        }
        

        if (!regexCorreo.test(correo)) {
            alert("Por favor, ingresa un correo electrónico válido.");
            valido = false;
        }
        
        if (!regexPassword.test(password)) {
            mostrarError(passwordInput, "Formato no válido contraseña");
            valido = false;
        }

        if (password != passwordVal) {
            mostrarError(passwordValInput, "Las contraseñas no coinciden.");
            valido = false;
        }

        if (!terminos) {
            alert("Debes aceptar los términos de uso y condiciones.");
            valido = false;
        }
        
        

        return valido;
    }
    
    function limpiarApellido(apellido) {
        const palabrasIgnoradas = ["DE", "DEL", "LA", "LAS", "LOS", "SAN", "SANTA", "Y"];
        // Reemplazamos la Ñ por X:
        apellido = apellido.replace(/Ñ/g, "X");
        // Normalizamos y removemos los acentos:
        apellido = apellido.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
        
        // Separamos y filtramos las palabras ignoradas:
        let partes = apellido.split(" ").filter(palabra => !palabrasIgnoradas.includes(palabra));
        return partes.join(" ");
    }
    
    function limpiarNombre(nombre) {
    const nombresIgnorar = ["MARIA", "JOSE", "MA", "J", "MA.", "J."];
    const prefijosExcluir = ["DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y"];
    // Reemplazamos la Ñ por X:
    nombre = nombre.replace(/Ñ/g, "X");
    let partes = quitarAcentos(nombre).toUpperCase().split(" ").filter(p => p !== "");
    
    // Elimina primeros nombres comunes
    if (partes.length > 0 && nombresIgnorar.includes(partes[0])) {
        partes.shift();
    }

    // Elimina prefijos adicionales
    while (partes.length > 0 && prefijosExcluir.includes(partes[0])) {
        partes.shift();
    }

    // Retorna el primer nombre "real"
    return partes.length > 0 ? partes[0] : "";
    }
    
    function mostrarError(input, mensaje) {
        input.style.borderColor = "red";
        let errorMsg = document.createElement("div");
        errorMsg.className = "text-danger mt-1";
        errorMsg.id = input.id + "Error";
        errorMsg.textContent = mensaje;
        input.parentNode.appendChild(errorMsg);
    }
    
    function convertirAMayusculas(event) {
        event.target.value = event.target.value.toUpperCase();
    }
    function quitarAcentos(texto) {
    return texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    }
    
    function validarFecha(fecha) {
    const anio = parseInt(fecha.substring(0, 2), 10);
    const mes = parseInt(fecha.substring(2, 4), 10);
    const dia = parseInt(fecha.substring(4, 6), 10);


    const fullYear = anio < 30 ? 2000 + anio : 1900 + anio;

    const date = new Date(fullYear, mes - 1, dia);
    return date.getFullYear() === fullYear && date.getMonth() === mes - 1 && date.getDate() === dia;
    }
    
    function generarRFC(curp) {
        const regexCURP = /^[A-Z]{4}\d{6}[HM][A-Z]{5}[A-Z0-9]\d$/;
        if (!regexCURP.test(curp)) {
            return "";
        }
        
        const rfcBase = curp.substring(0, 10);
        return rfcBase;
    }
    
    function validarCURP(curpInput, nombreInput, primerApellidoInput, segundoApellidoInput,genero,fechaNac,entidadNacimiento) {
        
        
        const altisonantes = ["BACA", "BAKA", "BUEI", "BUEY",
                                "CACA", "CACO", "CAGA", "CAGO", "CAKA", "CAKO", "COGE", "COGI", "COJA", "COJE", "COJI", "COJO", "COLA", "CULO",
                                "FALO", "FETO",
                                "GETA", "GUEI", "GUEY",
                                "JETA", "JOTO",
                                "KACA", "KACO", "KAGA", "KAGO", "KAKA", "KAKO", "KOGE", "KOGI", "KOJA", "KOJE", "KOJI", "KOJO", "KOLA", "KULO",
                                "LILO", "LOCA", "LOCO", "LOKA", "LOKO",
                                "MAME", "MAMO", "MEAR", "MEAS", "MEON", "MIAR", "MION", "MOCO", "MOKO", "MULA", "MULO",
                                "NACA", "NACO",
                                "PEDA", "PEDO", "PENE", "PIPI", "PITO", "POPO", "PUTA", "PUTO",
                                "QULO",
                                "RATA", "ROBA", "ROBE", "ROBO", "RUIN",
                                "SENO",
                                "TETA",
                                "VACA", "VAGA", "VAGO", "VAKA", "VUEI", "VUEY",
                                "WUEI", "WUEY"];
        const tablaVerificador = {
            '0':0,'1':1,'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9,
            'A':10,'B':11,'C':12,'D':13,'E':14,'F':15,'G':16,'H':17,'I':18,'J':19,
            'K':20,'L':21,'M':22,'N':23,'Ñ':24,'O':25,'P':26,'Q':27,'R':28,'S':29,
            'T':30,'U':31,'V':32,'W':33,'X':34,'Y':35,'Z':36
        };
        const fechaParts = fechaNac.split("-");
        const anio = fechaParts[0].substr(2,2);
        const mes = fechaParts[1];
        const dia = fechaParts[2];
        const fechaInput=anio+mes+dia;
        
        curpInput.style.borderColor = "green";
        document.getElementById("addUserCurpError")?.remove();
        const curp = curpInput.value.toUpperCase();
        const nombre = nombreInput.value.toUpperCase().trim();
        const primerApellido = primerApellidoInput.value.toUpperCase().trim();
        const segundoApellido = segundoApellidoInput.value.toUpperCase().trim();
        const primerApellidoLimpio = limpiarApellido(primerApellido);
        const segundoApellidoLimpio = limpiarApellido(segundoApellido);
        const nombreLimpio= limpiarNombre(nombre);
        
        // Extraer cada parte de la CURP
        const inicialApellido1 = primerApellidoLimpio.charAt(0);
        let primeraVocalApellido1 = primerApellidoLimpio.slice(1).match(/[AEIOU]/)?.[0] || "";
        const inicialApellido2 = segundoApellidoLimpio.charAt(0) || "X"; // Si no tiene segundo apellido, poner "X"
        const inicialNombre = nombreLimpio.charAt(0);
        const fechaNacimiento = curp.substring(4, 10); // AAMMDD
        const sexo = curp.charAt(10);
        const estado = curp.substring(11, 13);
        const primeraConsonanteApellido1 = primerApellidoLimpio.slice(1).match(/[^AEIOU]/)?.[0] || "X";
        const primeraConsonanteApellido2 = segundoApellidoLimpio.slice(1).match(/[^AEIOU]/)?.[0] || "X";
        const primeraConsonanteNombre = nombreLimpio.slice(1).match(/[^AEIOU]/)?.[0] || "X";
        const homoclave = curp.charAt(16); // Puede ser letra o número
        const digitoVerificador = curp.charAt(17); // Debe ser un número
        const porcion1=inicialApellido1+primeraVocalApellido1+inicialApellido2+inicialNombre;
        bCurpValida = true;
        
        if(altisonantes.includes(porcion1)){
            primeraVocalApellido1="X";
        }
        if (curp.length !== 18 || primerApellido.length === 0 || nombre.length === 0) {
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "La longitud de la CURP no es correcta");
            bCurpValida = false;
           
        }
        if (curp.charAt(0) !== inicialApellido1){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con primer apellido");
            bCurpValida = false;
        }
        if (curp.charAt(1) !== primeraVocalApellido1){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con primer apellido");
            bCurpValida = false;
        }
        if (curp.charAt(2) !== inicialApellido2){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con segundo apellido");
            bCurpValida = false;
        }
        
        if (curp.charAt(3) !== inicialNombre){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con nombre");
            bCurpValida = false;
        }
        if (!fechaInput.includes(fechaNacimiento)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. La fecha de nacimiento no es válida");
            bCurpValida = false;
        }
        
        if (!genero.includes(sexo)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. El caracter que indica el sexo no es válidp");
            bCurpValida = false;
        }
        
        if (!entidadNacimiento.includes(estado)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. Los caracteres que indican el estado de nacimiento no son validos");
            bCurpValida = false;
        }
        
        if (curp.charAt(13) !== primeraConsonanteApellido1){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con primer apellido");
            bCurpValida = false;
        }
        
        if (curp.charAt(14) !== primeraConsonanteApellido2){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con segundo apellido");
            bCurpValida = false;
        }
        
        if (curp.charAt(15) !== primeraConsonanteNombre){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. No coincide con nombre");
            bCurpValida = false;
        }
        
        if (!/^[A-Z0-9]$/.test(homoclave)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido. Hay un error en homoclave");
            bCurpValida = false;
        }
        
        let suma=0;
        for (let i = 0; i < 17; i++) {
            suma += tablaVerificador[curp.charAt(i)] * (18-i);
        }
        let digito = (10 - (suma % 10)) % 10;
        let digitoString=digito.toString();
        
        if (!digitoString.includes(digitoVerificador)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "CURP no válida.");
            bCurpValida = false;
        }
        
        
        return bCurpValida;
        
    }
    

document.getElementById('registraButton').addEventListener('click', function(event) {
    event.preventDefault(); 

    if (!validarRegistro()) {
        return;
    }

    // Si pasa la validación, mostramos el modal
    document.getElementById('conf_curp').innerText = document.getElementById('addUserCurp').value;
    document.getElementById('conf_rfc').innerText = document.getElementById('addUserrfc').value;
    document.getElementById('conf_nombre').innerText = document.getElementById('addUserName').value;
    document.getElementById('conf_apellido1').innerText = document.getElementById('addUserLName').value;
    document.getElementById('conf_apellido2').innerText = document.getElementById('addUserSecName').value;
    document.getElementById('conf_sexo').innerText = document.getElementById('sexo').options[document.getElementById('sexo').selectedIndex].text;
    document.getElementById('conf_fecha').innerText = document.getElementById('fechaNacimiento').value;
    document.getElementById('conf_entidad').innerText = document.getElementById('entidadNacimiento').options[document.getElementById('entidadNacimiento').selectedIndex].text;
    document.getElementById('conf_correo').innerText = document.getElementById('correo').value;

    var myModal = new bootstrap.Modal(document.getElementById('confirmacionModal'));
    myModal.show();
});

document.getElementById("confirmarEnvio").addEventListener("click", function () {
    document.getElementById("formReg").submit();
});
