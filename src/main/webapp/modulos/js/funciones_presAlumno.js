$(function (){
    cargar_datos();
    $(document).on("click","#registrar_prestamo",function (e){
        e.preventDefault();
        $("#consultar_datos").val("si_registro");///para insertar nuevo registro
        $("#formulario_registro").trigger("reset");
        $("#md_registrar_prestamo").modal("show");
    });




});

function  cargar_datos(){
    mostrar_cargando("Procesando Solicitud", "Espere mientras se Muestran Datos")
    var datos = {"consultar_datos":"si_consulta_prestamo", "estado":"1"};
    console.log("datos enviados: ",datos);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "RegPresAlum",
        data: datos
    }).done(function (json){
        Swal.close();
        console.log("datos consultados1: ", json);
        if (json[0].resultado=="exito"){
            $("#aqui_tabla_data").empty().html(json[0].tabla);
            $("#tabla_prestamosAlu").DataTable();
        }else {
            Swal.fire('Error','No se pudo completar','error');
        }
    }).fail(function (){
    }).always(function (){
    })
}

function  mostrar_cargando(titulo, mensaje=""){
    Swal.fire({
        title: titulo,
        html: mensaje,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading()
        },
        willClose: () => {
        }
    }).then((result) => {
        if(result.dismiss === Swal.DismissReason.timer){
            console.log('I was closed by the timer')
        }
    })
}