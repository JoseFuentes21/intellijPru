$(function (){
    console.log("Jquery esta funcionando");
    cargar_datos();
    $(document).on("change","#id_autor",function(e){
        console.log("el value de id autor es ",$("#id_autor").val());
        obtenet_libros($("#id_autor").val());
    });
});

function obtenet_libros(id_autor){
    var datos ={"consultar_datos":"si_consulta_autoresconlibros_especifico","id_autor":id_autor}
    console.log("datos",datos);
    mostrar_cargando("Espere","Consultando libros y autores");
    $.ajax({
        dataType:"json",
        method:"POST",
        url:"ConsAutoresLibros",
        data:datos

    }).done(function(json){
        console.log("consultar libros:",json);
        if (json[0].resultado=="exito"){
            $("#aqui_tabla").empty().html(json[0].tabla);
            $('#tabla_autores').DataTable();
        }else{
            Swal.fire(
                'Error',
                'No se pudo completar la peticion, intentelo mas tarde',
                'error'
            );
        }
    }).always(function(){
        Swal.close();
    });
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
function cargar_datos(estado=1){
    mostrar_cargando("Procesando Solicitud", "Espere mientras se Muestran Datos")
    var datos = {"consultar_datos":"si_consultar_autoresCombox", "estado":"1"};
    console.log("datos enviados: ",datos);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "ConsAutoresLibros",
        data: datos
    }).done(function (json){
        Swal.close();
        console.log("datos consultados1: ", json);
        if (json[0].resultado=="exito"){
            $("#id_autor").empty().html(json[0].opcionCombo);
        }else {
            Swal.fire('Error','No se pudo completar','error');
        }
    }).fail(function (){
    }).always(function (){
    })
}