function validateForm() {
    const maxSizeMB = 150;
    const maxSizeBytes = maxSizeMB * 1024 * 1024;
    const maxSizeMBPerFile = 3;
    const maxSizeBytesPerFile = maxSizeMBPerFile * 1024 * 1024;
    const fileInputs = document.querySelectorAll('.archivo-carga');
    let totalSize = 0;
    let fileNames = new Set();

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

    mostrarSpinner();
    return true;
}
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
      manejarArchivo(input, e.dataTransfer.files[0], fileNameDisplay);
    }
  });

  input.addEventListener('change', () => {
    if (input.files.length > 0) {
      manejarArchivo(input, input.files[0], fileNameDisplay);
    }
  });

  function manejarArchivo(input, archivoOriginal, display) {
    const nombreLimpio = limpiarNombreArchivo(archivoOriginal.name);
    const archivoLimpio = new File([archivoOriginal], nombreLimpio, { type: archivoOriginal.type });

    // Reemplazar archivo en el input con un nuevo DataTransfer
    const dataTransfer = new DataTransfer();
    dataTransfer.items.add(archivoLimpio);
    input.files = dataTransfer.files;

    display.textContent = "Archivo seleccionado: " + archivoLimpio.name;
    display.style.cursor = 'pointer';
    display.classList.add('text-primary', 'text-decoration-underline');

    // Mostrar modal al hacer clic en el nombre
    display.addEventListener('click', () => {
       event.stopPropagation(); // <- esto evita que se propague hacia el contenedor .drop-zone 
      if (archivoLimpio.type === "application/pdf") {
        const reader = new FileReader();
        reader.onload = function (e) {
          const blob = new Blob([e.target.result], { type: 'application/pdf' });
          const url = URL.createObjectURL(blob);
          document.getElementById('iframePdf').src = url;
          const modal = new bootstrap.Modal(document.getElementById('pdfModal'));
          modal.show();
        };
        reader.readAsArrayBuffer(archivoLimpio);
      }
    }, { once: false }); // evitar múltiples listeners
  }

  function limpiarNombreArchivo(nombre) {
    return nombre.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[^a-zA-Z0-9. _-]/g, "");
  }
});



function mostrarCarga(tipo) {

   document.getElementById(tipo + "-existente").style.display = "none";
    const divSubida = document.getElementById(tipo + "-subida");
    divSubida.style.display = "block";
    
    
      setTimeout(() => {
        const inputFile = document.getElementById("input-" + tipo);
        
        if (inputFile) {
            inputFile.click();
        }
    }, 100); 
}

function mostrarSpinner() {
        document.getElementById("spinner-container").style.display = "flex"; 
}
    
    
  function verPdfModal(url) {
    document.getElementById('iframePdf').src = url;
    var modal = new bootstrap.Modal(document.getElementById('pdfModal'));
    modal.show();
  }
