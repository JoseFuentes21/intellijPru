$(function (){
    var fecha = new Date();
    console.log("Jquery esta funcionando");
    cargar_datos();
    $(document).on("click","#registrar_categoria",function (e){
       e.preventDefault();
       $("#consultar_datos").val("si_registro");///para insertar nuevo registro
       $("#formulario_registro").trigger("reset");
       $("#md_registrar_categoria").modal("show");
    });

    $(document).on("submit", "#formulario_registro",function (e){
        e.preventDefault();
        mostrar_cargando("Procesando Solicitud", "Espere Mientras se Almacenan los Datos")
        var datos = $("#formulario_registro").serialize();
        console.log("formulario: ", datos);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "RegCatLib",
            data:datos
        }).done(function (json){
            Swal.close();
            console.log("datos_insertados",json);
            if (json[0].resultado=="exito"){
                Swal.fire('Exito','Categoria Registrada','success');
                $("#md_registrar_categoria").modal("hide");
                cargar_datos();
            }else {
                Swal.fire('Accion no Completada', 'No se Registro los Datos', 'error')
            }
        }).fail(function (){
        }).always(function (){
        })
    });
    $(document).on("click" , ".btn_eliminar",function(e){
        e.preventDefault();
        Swal.fire({
            title: 'Desea eliminar el registro?',
            text:'Al continuar con esta accion no podra ser revertida y los datos seran borrados completamente',
            showDenyButton:false,
            confirmButtonText:'si',
            denyButtonText:'No',
        }).then((result) => {
          if(result.isConfirmed){
          eliminar($(this).attr('data-id'));
        }else if (result.isDenied) {
              Swal.fire('Accion Cancelada por el Usuario','',"info");
        }

        });
    });

    $(document).on("click", ".btn_editar",function (e){
       e.preventDefault();
       mostrar_cargando("Espere", "Obteniendo Datos");
       let id = $(this).attr("data-id");
       console.log("El id a editar es: ",id);
       let datos = {"consultar_datos":"si_concategoria_especifica","id":id};
       $.ajax({
           dataType: "json",
           method: "POST",
           url: "RegCatLib",
           data: datos
       }).done(function (json){
           console.log("El consultar especifico", json);
           if (json[0].resultado=="exito"){
               $("#formulario_registro").trigger("reset");
               $("#consultar_datos").val("si_actualizalo");
               $("#llave_persona").val(json[0].id_persona);
               $("#codigoCategoria").val(json[0].id_categoria);
               $("#nombreCategoria").val(json[0].nombre_categoria);
               $("#md_registrar_categoria").modal('show');
           }
       }).fail(function (){
       }).always(function (){
       });
    });


});

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
    var datos = {"consultar_datos":"si_consulta_categoria", "estado":"1"};
    console.log("datos enviados: ",datos);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "RegCatLib",
        data: datos
    }).done(function (json){
        Swal.close();
        console.log("datos consultados1: ", json);
        if (json[0].resultado=="exito"){
            $("#aqui_tabla").empty().html(json[0].tabla);
            $("#tabla_categorias").DataTable();
        }else {
            Swal.fire('Error','No se pudo completar','error');
        }
    }).fail(function (){
    }).always(function (){
    })

}

function eliminar(id){
    mostrar_cargando("procesando solicitud","Espere mientras se eliminan los datos")
    var datos ={"consultar_datos":"si_eliminalo","id":id};
    console.log("entra al funtion eliminar id= "+ id);
    console.log("entra al funtion eliminar id= "+ id);
    console.log("function eliminar (id)= "+ datos);

    $.ajax({
        dataType: "json",
        method: "POST",
        ur: "RegCatLib",
        data: datos
    }).done(function (json){
    Swal.close();
    console.log("datos consultados",json);

    if(json[0].resultado == "exito"){
        Swal.fire(
            'Excelente',
            'El dato fue eliminado',
            'success'
        );
        cargar_datos();
    }else{
        Swal.fire('Error',
            'No se pudo eliminar el dato intentelo mas tarde',
            'error'
        );
    }

}).fail(function(){

}).always(function(){

});


}

//fin de funcion eliminar id
