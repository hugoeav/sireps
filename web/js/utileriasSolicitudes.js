 function obtenerNombreArchivo(tipo) {
    const nombres = {
        // Estatus
        pendienteDocs: "Pendiente de Documentos",
        resubirDocs: "Reemplazar Documentos",
        nueva: "Solicitud Creada Exitosamente",
        revision: "En revisión",
        rechazada: "Rechazada",
        completa: "Acuse",
        aprobada:"Aprobada",
        
        //Tipos de solicitudes
        primeraVez:"Primera Vez",
        especialidad:"Actualización de especialidad",
        reposicion:"Reposición de Registro",
        domicilio:"Actualización de Domicilio",
        vincularRegistro:"Vinculación de Registro Previo"
    };

    return nombres[tipo] || "Solicitud desconocida";
    }
    
   $(document).ready(function () 
{
    $('#tablaSolicitudes').DataTable({
            order: [[1, 'desc']], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No se encontraron registros",
            emptyTable: "No hay datos disponibles en la tabla",
            paginate: {
                first: "Primero",
                previous: "Anterior",
                next: "Siguiente",
                last: "Último"
            },
            aria: {
                sortAscending: ": Activar para ordenar de forma ascendente",
                sortDescending: ": Activar para ordenar de forma descendente"
            }
            }
        });
    
    
});