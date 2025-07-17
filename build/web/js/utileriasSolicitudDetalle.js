
    function obtenerNombreArchivo(tipo) {
    const nombres = {
        // Títulos
        tecTitulo: "Título de Técnico",
        licTitulo: "Título de Licenciatura",
        maestTitulo: "Título de Maestría",
        maest2Titulo: "Segundo Título de Maestría",
        espTitulo: "Título de Especialidad",
        esp2Titulo: "Segundo Título de Especialidad",
        subespTitulo: "Título de Subespecialidad",
        docTitulo: "Título de Doctorado",

        // Cédulas
        tecCed: "Cédula de Técnico",
        licCed: "Cédula de Licenciatura",
        maestCed: "Cédula de Maestría",
        maest2Ced: "Segunda Cédula de Maestría",
        espCed: "Cédula de Especialidad",
        esp2Ced: "Segunda Cédula de Especialidad",
        subespCed: "Cédula de Subespecialidad",
        docCed: "Cédula de Doctorado",

        // Otros
        fotosF: "Fotografía",
        compPagoF: "Comprobante de pago",
        domicilioF: "Comprobante de domicilio",
        registroF: "Registro profesional"
    };

    return nombres[tipo] || "Documento desconocido";
    }
    
    
     function verPdfModal(url) {
    document.getElementById('iframePdf').src = url;
    var modal = new bootstrap.Modal(document.getElementById('pdfModal'));
    modal.show();
  }
