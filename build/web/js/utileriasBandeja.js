  function obtenerNombreArchivo(tipo) {
    const nombres = {
        // Estatus
        pendienteDocs: "Pendiente de Documentos",
        resubirDocs: "Reemplazar Documentos",
        nueva: "Nueva Solicitud",
        revision: "En revisión",
        rechazada: "Rechazada",
        completa: "Subir Registro",
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
    $('#nuevasTable').DataTable({
            order: [[0, 'desc']], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No se encontraron registros",
            emptyTable: "No hay nuevas solicitudes",
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

$(document).ready(function () 
{
    $('#especialidadTable').DataTable({
            order: [[0, 'desc']], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No hay nuevas solicitudes",
            emptyTable: "No hay nuevas solicitudes",
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

$(document).ready(function () 
{
    $('#reposicionTable').DataTable({
            order: [[0, 'desc']], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No hay nuevas solicitudes",
            emptyTable: "No hay nuevas solicitudes",
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

$(document).ready(function () 
{
    $('#domicilioTable').DataTable({
            order: [[0, 'desc']], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No hay nuevas solicitudes",
            emptyTable: "No hay nuevas solicitudes",
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

$(document).ready(function () 
{
    $('#aprobadasTable').DataTable({
            order: [[0, "desc"]], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            columnDefs: [
        { targets: 0, type: "num" }  // o "date", "string", etc.
             ],
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No hay solicitudes aprobdas",
            emptyTable: "No hay solicitudes aprobdas",
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



$(document).ready(function () 
{
    $('#rechazadasTable').DataTable({
            order: [[0, 'asc']], // Ordena la segunda columna (índice 1) en orden descendente (más reciente a más antigua)
            language: {
                  processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No hay solicitudes rechazadas",
            emptyTable: "No hay solicitudes rechazadas",
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