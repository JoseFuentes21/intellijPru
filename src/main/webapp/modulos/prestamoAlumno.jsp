<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/28/2023
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../layouts/header.jsp" %>
<!-- C3 charts css -->
<link href="public/plugins/c3/c3.min.css" rel="stylesheet" type="text/css"/>
<!-- DataTables -->
<link href="public/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<link href="public/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<!-- Responsive datatable examples -->
<link href="public/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<link href="public/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css"
      rel="stylesheet">
<%@ include file="../layouts/headerStyle.jsp" %>
<%@ include file="../layouts/loader.jsp" %>

<%@ include file="../layouts/navbar.jsp" %>

<div class="content-page">
    <!-- Start content -->
    <div class="content">
        <!-- Top Bar Start -->
        <%@ include file="../layouts/toolbar.jsp" %>
        <!-- Top Bar End -->
        <!-- ==================
        PAGE CONTENT START
        ================== -->
        <div class="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 col-xl-6">
                        <div class="mini-stat clearfix bg-white">
                            <span class="mini-stat-icon bg-blue-grey mr-0 float-right">
                                <i class="mdi mdi-black-mesa"></i>
                            </span>
                            <div class="mini-stat-info">
                                <span id="categorias_registradas" class="counter text-blue-grey">0</span>
                                Prestamos registrados
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xl-6" id="registrar_prestamo" style="cursor: pointer;">
                        <div class="mini-stat clearfix bg-white">
                            <span class="mini-stat-icon bg-teal mr-0 float-right">
                                <i class="mdi mdi-account"></i>
                            </span>
                            <div class="mini-stat-info">
                                <span class="counter text-teal">Registrar</span>
                                Prestamo Libro
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="card m-b-20">
                            <div class="card-body">
                                <div id="aqui_tabla_data"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- container -->
        </div> <!-- Page content Wrapper -->
    </div>
</div>

<!--Inicio del modal para formulario-->
<div class="modal fade" id="md_registrar_prestamo" tabindex="-1" role="dialog" arialabelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Registro Nuevo Prestamo<br>
                    <sub>Campos marcados con * son obligatorios</sub>
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form name="formulario_registro" id="formulario_registro">
                    <input type="hidden" id="consultar_datos" name="consultar_datos"
                           value="si_registro">
                    <input type="hidden" id="llave_persona" name="llave_persona"
                           value="">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label for="codigoPrestamo">Còdigo *</label>
                                <input type="text" autocomplete="off" name="codigoPrestamo"
                                       data-parsley-error-message="Campo requerido" id="codigoPrestamo"
                                       class="form-control"
                                       required placeholder="Ingrese còdigo prestamo"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="cantidad">Cantidad *</label>
                                <input type="number" id="cantidad" name="cantidad" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="codigoCarnet">Carnet Alumno *</label>
                                <select name="codigoCarnet" id="codigoCarnet" class="form-control select2">
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="codigoLibro">Codigo Libro *</label>
                                <select name="codigoLibro" id="codigoLibro" class="form-control select2">
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="fecha_pre">Fecha Prestamo *</label>
                                <input type="date" id="fecha_pre" name="fecha_pre" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="fecha_devo">Fecha Devoluciòn *</label>
                                <input type="date" id="fecha_devo" name="fecha_pre" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" datadismiss="md_registrar_prestamo">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../layouts/footerScript.jsp" %>
<!-- Peity chart JS -->
<script src="public/plugins/peity-chart/jquery.peity.min.js"></script>
<!--C3 Chart-->
<script src="public/plugins/d3/d3.min.js"></script>
<script src="public/plugins/c3/c3.min.js"></script>
<!-- KNOB JS -->
<script src="public/plugins/jquery-knob/excanvas.js"></script>
<script src="public/plugins/jquery-knob/jquery.knob.js"></script>
<!-- Page specific js -->
<script src="public/assets/pages/dashboard.js"></script>
<!-- App js -->
<script src="public/assets/js/app.js"></script>
<script src="public/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="public/plugins/datatables/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript" src="public/plugins/parsleyjs/parsley.min.js"></script>
<script src="public/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<%@page import="java.time.LocalDateTime" %>
<%@ page import="java.time.temporal.ChronoField" %>
<script src="./modulos/js/funciones_presAlumno.js"></script>

