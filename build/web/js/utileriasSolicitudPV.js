
    let contadorMaestria = 0;
    let contadorEspecialidad = 0;

    function mostrarGrado() {
        const gradoSeleccionado = document.getElementById("gradoSelect").value;
        
        if (gradoSeleccionado === "") {
            alert("No existen más grados por agregar");
            return;
        }

        var select = document.getElementById("gradoSelect");
        switch (gradoSeleccionado) {
             
            case "maestria":
                if (contadorMaestria === 0) {
                    document.getElementById("maestria").style.display = "flex";
                    contadorMaestria++;
                } else if (contadorMaestria === 1) {
                    document.getElementById("maestria2").style.display = "flex";
                    contadorMaestria++;
                    var optionMaest = select.querySelector('option[value="maestria"]');
                    if(optionMaest) {
                        optionMaest.remove();
                    }
                    
                } else {
                    alert("Ya no puedes agregar más maestrías.");
                }
                break;

            case "especialidad":
                if (contadorEspecialidad === 0) {
                    document.getElementById("especialidad").style.display = "flex";
                    contadorEspecialidad++;
                } else if (contadorEspecialidad === 1) {
                    document.getElementById("especialidad2").style.display = "flex";
                    contadorEspecialidad++;
                     var optionEsp = select.querySelector('option[value="especialidad"]');
                if(optionEsp) {
                    optionEsp.remove();
                }
                } else {
                    alert("Ya no puedes agregar más especialidades.");
                }
                break;
            case "subespecialidad":
                if (contadorEspecialidad === 0) {
                    document.getElementById("especialidad").style.display = "flex";
                    contadorEspecialidad++;
                }
                document.getElementById("subespecialidad").style.display = "flex";
                
                var optionSub = select.querySelector('option[value="subespecialidad"]');
                if(optionSub) {
                    optionSub.remove();
                }
                
                break;

            case "doctorado":
                if (contadorMaestria === 0) {
                    document.getElementById("maestria").style.display = "flex";
                    contadorMaestria++;
                } 
                document.getElementById("doctorado").style.display = "flex";
                
               
                var optionDoctorado = select.querySelector('option[value="doctorado"]');
                if(optionDoctorado) {
                    optionDoctorado.remove();
                }
                break;

            default:
                alert("Selección no válida.");
        }
    }
    
    function ocultarGrado(grado) {
    const select = document.getElementById("gradoSelect");

    switch (grado) {
        case "maestria":
            if (contadorMaestria === 2) {
                document.getElementById("maestria2").style.display = "none";
                limpiarCampos("maestria2");
                contadorMaestria--;
                // Restaurar opción
                if (!select.querySelector('option[value="maestria"]')) {
                    const option = new Option("Maestría", "maestria");
                    select.appendChild(option);
                }
            } else if (contadorMaestria === 1) {
                const doctoradoVisible = document.getElementById("doctorado").style.display !== "none";
                if (doctoradoVisible) {
                    alert("No puedes eliminar Maestría porque Doctorado está activo.");
                    return;
                }
                document.getElementById("maestria").style.display = "none";
                limpiarCampos("maestria");
                contadorMaestria--;
            }
            break;

        case "especialidad":
            if (contadorEspecialidad === 2) {
                document.getElementById("especialidad2").style.display = "none";
                limpiarCampos("especialidad2");
                contadorEspecialidad--;
                if (!select.querySelector('option[value="especialidad"]')) {
                    const option = new Option("Especialidad", "especialidad");
                    select.appendChild(option);
                }
            } else if (contadorEspecialidad === 1) {
                const subespVisible = document.getElementById("subespecialidad").style.display !== "none";
                if (subespVisible) {
                    alert("No puedes eliminar Especialidad porque Subespecialidad está activo.");
                    return;
                }
                document.getElementById("especialidad").style.display = "none";
                limpiarCampos("especialidad");
                contadorEspecialidad--;
            }
            break;

        case "subespecialidad":
            document.getElementById("subespecialidad").style.display = "none";
            limpiarCampos("subespecialidad");
            if (!select.querySelector('option[value="subespecialidad"]')) {
                const option = new Option("Subespecialidad", "subespecialidad");
                select.appendChild(option);
            }
            break;

        case "doctorado":
            document.getElementById("doctorado").style.display = "none";
            limpiarCampos("doctorado");
            if (!select.querySelector('option[value="doctorado"]')) {
                const option = new Option("Doctorado", "doctorado");
                select.appendChild(option);
            }
            break;

        default:
            alert("No se puede ocultar ese grado.");
        }
    }
    
    function limpiarCampos(idContenedor) {
    const contenedor = document.getElementById(idContenedor);
    if (contenedor) {
        const inputs = contenedor.querySelectorAll("input");
        inputs.forEach(input => {
            input.value = "";
        });
    }
    }
    
    
    
    
    
     function updateCPData(cp,est,mun,loc,col) {
    // Verifica que el CP tenga 5 dígitos
    if (cp.length !== 5) {
        // Limpiar el campo del municipio y el combo de colonias si no es válido
        document.getElementById(mun).value = '';
        document.getElementById(col).innerHTML = '<option value="-1">Seleccione una colonia</option>';
        return;
    }

    var baseUrl = "obtenerDatosCP?cp.codigoPostal=" + cp;

    fetch(baseUrl)
        .then(response => response.json())
        .then(data => {

            if (data.cp && data.cp.id_municipio && data.cp.id_estado) {
                  document.getElementById(est).value = data.cp.id_estado;
                
                // Actualiza los municipios y espera a que carguen
                updateMunicipiosData(data.cp.id_estado,mun,loc).then(() => {
                    // Asigna el valor del municipio
                    const municipioSelect = document.getElementById(mun);
                    municipioSelect.value = data.cp.id_municipio;
                    
                    // **Fuerza el evento change para reflejar visualmente**
                    municipioSelect.dispatchEvent(new Event('change'));

                    // Actualiza las localidades después de seleccionar el municipio
                    updateLocalidadesData(data.cp.id_municipio,est,loc,mun);
                }).catch(error => {
                    console.error('Error al cargar municipios:', error);
                });
            } else {
                document.getElementById(mun).value = '';
            }

            // Actualizar el combo de colonias.
            let coloniaSelect = document.getElementById(col);
            // Limpiar el select y agregar la opción por defecto.
            coloniaSelect.innerHTML = '<option value="-1">Seleccione una colonia</option>';
            
            let localidadesSelect=document.getElementById(loc);
            localidadesSelect.innerHTML = '<option value="-1">Seleccione una Localidad</option>';

            if (Array.isArray(data.colonias)) {
                data.colonias.forEach(item => {
                    let option = document.createElement('option');
                    option.value = item.id_colonia; // Valor: id de la colonia
                    option.textContent = item.colonia; // Texto: nombre de la colonia
                    coloniaSelect.appendChild(option);
                });
            }
            
            if (Array.isArray(data.localidades)) {
                data.localidades.forEach(item => {
                    let option = document.createElement('option');
                    option.value = item.id_localidad; // Valor: id de la colonia
                    option.textContent = item.localidad; // Texto: nombre de la colonia
                    localidadesSelect.appendChild(option);
                });
            }
             $('#colonia').trigger('change');
             $('#localidades').trigger('change');
        })
        .catch(error => {
            console.error('Error al obtener datos:', error);
        });
}




function updateMunicipiosData(id_estado, mun , loc) {
   return new Promise((resolve, reject) => {
    var baseUrl="obtenerMunicipios?estadoInput="+id_estado;
    //alert("RESPONSE");
    if (id_estado === "-1") {
        document.getElementById(mun).innerHTML = '<option value="-1">Seleccione un Municipio</option>';
         document.getElementById(loc).innerHTML = '<option value="-1">Seleccione una Localidad</option>';
        return;
    }
    document.getElementById(loc).innerHTML = '<option value="-1">Seleccione una Localidad</option>';
    fetch(baseUrl)
        .then(response => response.json())
        .then(data => {
            console.log("Datos recibidos", data);
        console.log("Datos Municipios", data.municipios);
            let municipioSelect = document.getElementById(mun);
            municipioSelect.innerHTML = '<option value="-1">Seleccione un Municipio</option>';
            data.municipios.forEach(municipio => {
                let option = document.createElement('option');
                option.value = municipio.id_municipio;
                option.textContent = municipio.municipio;
                municipioSelect.appendChild(option);
            });
            
            resolve();
        })
        .catch(error => {
                console.error('Error al obtener Municipios', error);
                reject(error);
            });
    }); 
}


function updateLocalidadesData(id_municipio, estado, loc, mun) {
     const id_estado = document.getElementById(estado).value;
    var baseUrl="obtenerLocalidades?estadoInput="+id_estado+"&municipioInput="+id_municipio;
    //alert("RESPONSE");
    if (id_estado === "-1") {
        document.getElementById(mun).innerHTML = '<option value="-1">Seleccione un Municipio</option>';
         document.getElementById(loc).innerHTML = '<option value="-1">Seleccione una Localidad</option>';
        return;
    }
    
    fetch(baseUrl)
        .then(response => response.json())
        .then(data => {
            console.log("Datos recibidos", data);
        console.log("Datos Localidades", data.municipios);
            let localidadSelect = document.getElementById(loc);
            localidadSelect.innerHTML = '<option value="-1">Seleccione una Localidad</option>';
            data.localidades.forEach(localidad => {
                let option = document.createElement('option');
                option.value = localidad.id_localidad;
                option.textContent = localidad.localidad;
                localidadSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener Localidades', error));  
}

$(document).ready(function() {
    $('#colonia').select2({
        placeholder: 'Seleccione una colonia',
        allowClear: true,
        width: '100%'
    });
});

$(document).ready(function() {
    $('#municipio').select2({
        placeholder: 'Seleccione un Municipio',
        allowClear: true,
        width: '100%'
    });
    $('#municipio').val(data.cp.id_municipio).trigger('change');
});


$(document).ready(function() {
    $('#municipioLab').select2({
        placeholder: 'Seleccione un Municipio',
        allowClear: true,
        width: '100%'
    });
    $('#municipioLab').val(data.cp.id_municipio).trigger('change');
});

$(document).ready(function() {
    $('#localidad').select2({
        placeholder: 'Seleccione una Localidad',
        allowClear: true,
        width: '100%'
    });
});

$(document).ready(function() {
    $('#coloniaLab').select2({
        placeholder: 'Seleccione una colonia',
        allowClear: true,
        width: '100%'
    });
});

$(document).ready(function() {
    $('#localidadLab').select2({
        placeholder: 'Seleccione una Localidad',
        allowClear: true,
        width: '100%'
    });
});

document.getElementById('nuevaColoniaChk').addEventListener('change', function() {
    var nuevaColoniaDiv = document.getElementById('nuevaColoniaDiv');
    if (this.checked) {
        nuevaColoniaDiv.style.display = 'block';
    } else {
        nuevaColoniaDiv.style.display = 'none';
    }
});

document.getElementById('nuevaColoniaLabChk').addEventListener('change', function() {
    var nuevaColoniaDiv = document.getElementById('nuevaColoniaLabDiv');
    if (this.checked) {
        nuevaColoniaDiv.style.display = 'block';
    } else {
        nuevaColoniaDiv.style.display = 'none';
    }
});

    
   document.addEventListener("DOMContentLoaded", function () {
       const estadosValidos = [
        "AS", "BC", "BS", "CC", "CL", "CM", "CS", "CH", "DF", "DG", "GT", "GR", "HG", "JC", "MC", 
        "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TS", "TL", "VZ", 
        "YN", "ZS", "NE"
    ];
    const curpInput = document.getElementById("addUserCurp");
    const nombreInput = document.getElementById("addUserName");
    const primerApellidoInput = document.getElementById("addUserLName");
    const segundoApellidoInput = document.getElementById("addUserSecName");
    const submitButton = document.getElementById("btnAddSolicitud"); 
    const rfcInput = document.getElementById("rfcInput");
     let bCurpValida = false;
     
     function limpiarApellido(apellido) {
        const palabrasIgnoradas = ["DE", "DEL", "LA", "LAS", "LOS", "SAN", "SANTA", "Y"];
        // Normalizamos y removemos los acentos:
        apellido = apellido.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
        // Separamos y filtramos las palabras ignoradas:
        let partes = apellido.split(" ").filter(palabra => !palabrasIgnoradas.includes(palabra));
        return partes.join(" ");
    }
    
    function limpiarNombre(nombre) {
    const nombresCompuestos = ["MARIA", "JOSE", "MA", "J","MA.","J.","j"];
    let partes = quitarAcentos(nombre).split(" ");
    return (partes.length > 1 && nombresCompuestos.includes(partes[0]))
        ? partes[1]
        : partes[0];
    }
    
    function validarCURP() {
        
    const ALTISONANTES = ["BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA", "CAKO", "COGE", "COGI", "COJA", "COJE", "COJI", "COJO", "CULO", "FETO", "GUEY", "JOTO", "KACA", "KACO", "KAGA", "KAGO", "KOGE", "KOJO", "KULO", "MAME", "MAMO", "MEAR", "MEAS", "MEON", "MION", "MOCO", "MULA", "PEDA", "PEDO", "PENE", "PUTA", "PUTO", "QULO", "RATA", "RUIN"];
    const PREFIJOS_EXCLUIR = ["DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y"];
    const IGNORAR_NOMBRES = ["MARIA", "MA.", "MA", "JOSE", "J.", "J"];
      
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
    const primeraVocalApellido1 = primerApellidoLimpio.slice(1).match(/[AEIOU]/)?.[0] || "";
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
    
        bCurpValida = true;
        
        if (curp.length !== 18 || primerApellido.length === 0 || nombre.length === 0) {
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
            return;
        }
        if (curp.charAt(0) !== inicialApellido1){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        if (curp.charAt(1) !== primeraVocalApellido1){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        if (curp.charAt(2) !== inicialApellido2){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (curp.charAt(3) !== inicialNombre){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        if (!/^\d{6}$/.test(fechaNacimiento) || !validarFecha(fechaNacimiento)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (!["H", "M"].includes(sexo)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (!estadosValidos.includes(estado)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (curp.charAt(13) !== primeraConsonanteApellido1){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (curp.charAt(14) !== primeraConsonanteApellido2){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (curp.charAt(15) !== primeraConsonanteNombre){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (!/^[A-Z0-9]$/.test(homoclave)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (!/^\d$/.test(digitoVerificador)){
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "Formato no válido.");
            bCurpValida = false;
        }
        
        if (curps.includes(curp)) {
            document.getElementById("addUserCurpError")?.remove();
            mostrarError(curpInput, "CURP ya registrada.");
            bCurpValida = false;
        }
        
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
    
    submitButton.addEventListener("click", function (event) {
        validarCURP();


      
        if (!bCurpValida) {
            event.preventDefault();
            alert("Por favor, corrija los errores antes de continuar.");
        }
    });
    
    curpInput.addEventListener("input", validarCURP);
    nombreInput.addEventListener("input", validarCURP);
    primerApellidoInput.addEventListener("input", validarCURP);
    segundoApellidoInput.addEventListener("input", validarCURP);
    curpInput.addEventListener("input", convertirAMayusculas);
    nombreInput.addEventListener("input", convertirAMayusculas);
    primerApellidoInput.addEventListener("input", convertirAMayusculas);
    segundoApellidoInput.addEventListener("input", convertirAMayusculas);
    curpInput.addEventListener("input", function () {
        const curp = curpInput.value.toUpperCase().trim();
        const rfc = generarRFC(curp);
        
        
        if (rfc) {
            rfcInput.value = rfc;
        } else {
            rfcInput.value = "";
        }
    });
    
    })

    function validarform() {
    const getVal = (id) => document.getElementById(id)?.value.trim() || "";

    // Obtener valores de cada campo por grupo
    const tecnico = {
        nombre: getVal("nivTec"),
        institucion: getVal("instInput"),
        cedula: document.querySelector('[name="tecnicoInput.cedula"]')?.value.trim() || ""
    };

    const licenciatura = {
        nombre: getVal("licLabel"),
        institucion: document.querySelector('[name="licenciaturaInput.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="licenciaturaInput.cedula"]')?.value.trim() || ""
    };

    const maestria1 = {
        nombre: getVal("maestLabel"),
        institucion: document.querySelector('[name="maestriaInput.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="maestriaInput.cedula"]')?.value.trim() || ""
    };

    const maestria2 = {
        nombre: getVal("maestLabel2"),
        institucion: document.querySelector('[name="maestria2Input.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="maestria2Input.cedula"]')?.value.trim() || ""
    };

    const especialidad1 = {
        nombre: getVal("espLabel"),
        institucion: document.querySelector('[name="especialidadInput.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="especialidadInput.cedula"]')?.value.trim() || ""
    };

    const especialidad2 = {
        nombre: getVal("espLabel2"),
        institucion: document.querySelector('[name="especialidad2Input.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="especialidad2Input.cedula"]')?.value.trim() || ""
    };

    const subespecialidad = {
        nombre: getVal("subLabel"),
        institucion: document.querySelector('[name="subEspecialidad.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="subEspecialidad.cedula"]')?.value.trim() || ""
    };

    const doctorado = {
        nombre: getVal("docLabel"),
        institucion: document.querySelector('[name="doctoradoInput.institucion"]')?.value.trim() || "",
        cedula: document.querySelector('[name="doctoradoInput.cedula"]')?.value.trim() || ""
    };
    
    const estado = document.getElementById("estado")?.value || "-1";
    const municipio = document.getElementById("municipio")?.value || "-1";
    const localidad = document.getElementById("localidad")?.value || "-1";
    const estadoLab = document.getElementById("estadoLab")?.value || "-1";
    const municipioLab = document.getElementById("municipioLab")?.value || "-1";
    const localidadLab = document.getElementById("localidadLab")?.value || "-1";
    const coloniaSelect = document.getElementById("colonia")?.value || "-1";
    const coloniaManual = getVal("nuevaColonia");
    const coloniaSelectLab = document.getElementById("coloniaLab")?.value || "-1";
    const coloniaManualLab = getVal("nuevaColoniaLab");
    
    //archivos
   

    const errores = [];
    
    if (estado === "-1") {
        errores.push("\u2022 Debes seleccionar un Estado para domicilio particular.");
    }
    if (municipio === "-1") {
        errores.push("\u2022 Debes seleccionar un Municipio para domicilio particular.");
    }
    if (localidad === "-1") {
        errores.push("\u2022 Debes seleccionar una Localidad para domicilio particular.");
    }
    if (estadoLab === "-1") {
        errores.push("\u2022 Debes seleccionar un Estado para domicilio laboral .");
    }
    if (municipioLab === "-1") {
        errores.push("\u2022 Debes seleccionar un Municipio para domicilio laboral.");
    }
    if (localidadLab === "-1") {
        errores.push("\u2022 Debes seleccionar una Localidad para domicilio laboral.");
    }
    
    if (coloniaSelect !== "-1" && coloniaManual !== "") {
        errores.push("\u2022 Debes elegir solo una opción para la Colonia del domicilio particular: seleccionar del listado o ingresar una nueva, no ambas.");
    }
    if (coloniaSelect === "-1" && coloniaManual === "") {
        errores.push("\u2022 Debes seleccionar una Colonia del listado o ingresar una nueva manualmente para el domicilio particular.");
    }
    if (coloniaSelectLab !== "-1" && coloniaManualLab !== "") {
        errores.push("\u2022 Debes elegir solo una opción para la Colonia del domicilio laboral seleccionar del listado o ingresar una nueva, no ambas.");
    }
    if (coloniaSelectLab === "-1" && coloniaManualLab === "") {
        errores.push("\u2022 Debes seleccionar una Colonia del listado o ingresar una nueva manualmente para el domicilio laboral.");
    }

    // Reglas jerárquicas
    if (!tecnico.nombre && !licenciatura.nombre) {
        errores.push("\u2022 Debes captural al menos licenciatura o nivel técnico");
    }
    if (maestria1.nombre && !licenciatura.nombre) {
        errores.push("\u2022 Para capturar Maestría, primero debes capturar la Licenciatura.");
    }
    if (maestria2.nombre && !(maestria1.nombre && licenciatura.nombre)) {
        errores.push("\u2022 Para capturar Segunda Maestría, primero debes capturar la primera Maestría.");
    }
    if (especialidad1.nombre && !licenciatura.nombre) {
        errores.push("\u2022 Para capturar Especialidad, primero debes capturar la Licenciatura.");
    }
    if (especialidad2.nombre && !(especialidad1.nombre && licenciatura.nombre)) {
        errores.push("\u2022 Para capturar Segunda Especialidad, primero debes capturar la primera Especialidad.");
    }
    if (subespecialidad.nombre && !(especialidad1.nombre && licenciatura.nombre)) {
        errores.push("\u2022 Para capturar Subespecialidad, primero debes capturar al menos una Especialidad.");
    }
    if (doctorado.nombre && !(maestria1.nombre && licenciatura.nombre)) {
        errores.push("\u2022 Para capturar Doctorado, primero debes capturar al menos una Maestría.");
    }
    const validarGrupo = (grupo, nombre) => {
        const valores = Object.values(grupo).filter(v => v !== "");
  
        if (valores.length > 0 && valores.length < 3) {
            errores.push("\u2022 Todos los campos de "+nombre+" deben estar llenos si capturas alguno.");
        }
    };

    validarGrupo(tecnico, "Nivel Técnico");
    validarGrupo(licenciatura, "Licenciatura");
    validarGrupo(maestria1, "Maestría");
    validarGrupo(maestria2, "Segunda Maestría");
    validarGrupo(especialidad1, "Especialidad");
    validarGrupo(especialidad2, "Segunda Especialidad");
    validarGrupo(subespecialidad, "Subespecialidad");
    validarGrupo(doctorado, "Doctorado");
    
    const archivos = {
    tecnico: {
        requerido: tecnico.nombre !== "",
        titulo: document.getElementById("tecnico-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="tecTitulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("tecnicoCed-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="tecCed"]')?.files.length > 0
                  : true
    },
    licenciatura: {
        requerido: licenciatura.nombre !== "",
        titulo: document.getElementById("licenciatura-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="licTitulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("licenciaturaCed-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="licCed"]')?.files.length > 0
                  : true
    },
    maestria1: {
        requerido: maestria1.nombre !== "",
        titulo: document.getElementById("maestria-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="maestTitulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("maestriaCed-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="maestCed"]')?.files.length > 0
                  : true
    },
    maestria2: {
        requerido: maestria2.nombre !== "",
        titulo: document.getElementById("maestria2-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="maest2Titulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("maestria2Ced-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="maest2Ced"]')?.files.length > 0
                  : true
    },
    especialidad1: {
        requerido: especialidad1.nombre !== "",
        titulo: document.getElementById("especialidad-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="espTitulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("especialidadCed-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="espCed"]')?.files.length > 0
                  : true
    },
    especialidad2: {
        requerido: especialidad2.nombre !== "",
        titulo: document.getElementById("especialidad2-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="esp2Titulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("especialidad2Ced-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="esp2Ced"]')?.files.length > 0
                  : true
    },
    subespecialidad: {
        requerido: subespecialidad.nombre !== "",
        titulo: document.getElementById("subespecialidad-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="subespTitulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("subespecialidadCed-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="subespCed"]')?.files.length > 0
                  : true
    },
    doctorado: {
        requerido: doctorado.nombre !== "",
        titulo: document.getElementById("doctorado-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="docTitulo"]')?.files.length > 0
                  : true,
        cedula: document.getElementById("doctoradoCed-subida")?.style.display !== "none"
                  ? document.querySelector('input[name="docCed"]')?.files.length > 0
                  : true
    }
    };
    
    //validacion de peso y nombre
    const maxSizeMB = 150;
    const maxSizeBytes = maxSizeMB * 1024 * 1024;
    const maxSizeMBPerFile = 3;
    const maxSizeBytesPerFile = maxSizeMBPerFile * 1024 * 1024;
    const fileInputs = document.querySelectorAll('.archivo-carga');
    let totalSize = 0;
    let fileNames = new Set(); // Aquí guardaremos los nombres de los archivos

    for (let input of fileInputs) {
        for (let i = 0; i < input.files.length; i++) {
            const file = input.files[i];
            totalSize += file.size;
            if(file.size > maxSizeBytesPerFile){
                alert("El archivo "+file.name+" pesa más de 3 MB. Por favor, seleccione un archivo menos pesado.");
                return false;
            }
            if (fileNames.has(file.name)) {
                alert("El archivo "+file.name+" está repetido. Por favor, seleccione archivos con nombres diferentes.");
                return false;
            }
            
              const fileNameLower = file.name.toLowerCase();
            const isPDF = fileNameLower.endsWith('.pdf');
            const isImage = /\.(jpg|jpeg)$/i.test(fileNameLower);

            if (input.id === "fotografias") {
                // Para las fotos solo permitimos imágenes
                if (!isImage) {
                    alert("El archivo "+file.name+" debe ser una imagen (.jpg, .jpeg).");
                    return false;
                }
            } else {
                // Para los demás solo permitimos PDF
                if (!isPDF) {
                    alert("El archivo "+file.name+" debe ser un PDF.");
                    return false;
                }
            }

            fileNames.add(file.name);
        }
    }

    if (totalSize > maxSizeBytes) {
        alert("El tamaño total de los archivos no debe superar los 150 MB.");
        return false;
    }
    //termina validacion de pesos y nombre
    

    //valida que vayan los documentos correspondientes
        const nombresMostrar = {
        tecnico: "Nivel Técnico",
        licenciatura: "Licenciatura",
        maestria1: "Maestría",
        maestria2: "Segunda Maestría",
        especialidad1: "Primera Especialidad",
        especialidad2: "Segunda Especialidad",
        subespecialdiad: "Subespecialidad",
        doctorado: "Doctorado"
    };

    for (const [clave, datos] of Object.entries(archivos)) {
        if (datos.requerido) {
            if (!datos.titulo) {
                errores.push("\u2022 Debes adjuntar el archivo del TÍTULO correspondiente a "+nombresMostrar[clave]+".");
            }
            if (!datos.cedula) {
                errores.push("\u2022 Debes adjuntar el archivo de la CÉDULA correspondiente a "+nombresMostrar[clave]+".");
            }
        }
        

    }
    

    if (errores.length > 0) {
        alert(errores.join("\n"));
        return false;
    }
    const modal = bootstrap.Modal.getInstance(document.getElementById('modalArchivos'));
    if (modal) {
       modal.hide();
    }
    mostrarSpinner();

    return true;
}

//modal

function abrirModal() {
  var modal = new bootstrap.Modal(document.getElementById('modalArchivos'));
  modal.show();
}

//dropzone
function limpiarNombreArchivo(nombre) {
    let nombreNormalizado = nombre.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    return nombreNormalizado.replace(/[^a-zA-Z0-9._-]/g, "_");
}

document.querySelectorAll('.drop-zone').forEach(zone => {
    const input = zone.querySelector('input');
    const fileNameDisplay = zone.querySelector('.file-name');

    zone.addEventListener('click', () => input.click());

    zone.addEventListener('dragover', e => {
        e.preventDefault();
        zone.classList.add('dragover');
    });

    zone.addEventListener('dragleave', () => {
        zone.classList.remove('dragover');
    });

    zone.addEventListener('drop', e => {
        e.preventDefault();
        zone.classList.remove('dragover');
        if (e.dataTransfer.files.length) {
            reemplazarArchivoConNombreLimpio(input, e.dataTransfer.files[0], fileNameDisplay);
        }
    });

    input.addEventListener('change', () => {
        if (input.files.length > 0) {
            reemplazarArchivoConNombreLimpio(input, input.files[0], fileNameDisplay);
        }
    });

    function reemplazarArchivoConNombreLimpio(input, archivoOriginal, display) {
        const nombreLimpio = limpiarNombreArchivo(archivoOriginal.name);
        const archivoLimpio = new File([archivoOriginal], nombreLimpio, { type: archivoOriginal.type });

        // Reemplazar archivo en el input con un nuevo DataTransfer
        const dataTransfer = new DataTransfer();
        dataTransfer.items.add(archivoLimpio);
        input.files = dataTransfer.files;

        display.textContent = "Archivo seleccionado: " + archivoLimpio.name;
    }
});

function mostrarCarga(tipo) {
    console.log(tipo);
   document.getElementById(tipo + "-existente").style.display = "none";
    const divSubida = document.getElementById(tipo + "-subida");
    divSubida.style.display = "block";
    
      setTimeout(() => {
        const inputFile = document.getElementById("input-" + tipo);
        
        if (inputFile) {
            inputFile.click();
        } else {
            console.log("No se encontró input con id: input-" + tipo);
        }
    }, 100); 
}

function mostrarSpinner() {
        document.getElementById("spinner-container").style.display = "flex"; 
    }


document.addEventListener('DOMContentLoaded', function () {
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
  });
});

