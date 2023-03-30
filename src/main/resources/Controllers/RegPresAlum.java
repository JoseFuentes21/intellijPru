package Controllers;

import Clases.Alumno;
import Clases.Libro;
import Clases.PrestamoAlumno;
import Models.DAOCategoria;
import Models.DAOPrestamoAlumno;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "RegPresAlum", value = "/RegPresAlum")
public class RegPresAlum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/modulos/prestamoAlumno.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("aplication/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");
        System.out.println("El filtro es: "+filtro);

        if (filtro==null){
            return;
        }
        switch (filtro){
            case "si_registro":
                JSONArray arrayPresAlu = new JSONArray();
                JSONObject jsonPresAlu = new JSONObject();
                String resultado_insertar = "";
                PrestamoAlumno prestamoAlumno = new PrestamoAlumno();
                Alumno alumno = new Alumno();
                Libro libro = new Libro();
                try {
                    DAOPrestamoAlumno daoPreAlum = new DAOPrestamoAlumno();
                    alumno.setCarnet(request.getParameter("codigoCarnet"));
                    libro.setCodigoLibro(request.getParameter("codigoLibro"));
                    Date fechapre = new Date(request.getParameter("fecha_pre"));
                    Date fechadev = new Date(request.getParameter("fecha_devo"));

                    prestamoAlumno = new PrestamoAlumno(request.getParameter("codigoPrestamo"),
                            fechapre , fechadev, Integer.valueOf(request.getParameter("cantidad")),
                            libro,alumno);
                    resultado_insertar = daoPreAlum.insertarPrestamoAlumno(prestamoAlumno);
                    if (resultado_insertar=="exito"){
                        jsonPresAlu.put("resultado","exito");
                    }else {
                        jsonPresAlu.put("resultado", "error");
                        jsonPresAlu.put("resultado_insertar",resultado_insertar);
                    }

                }catch (SQLException e){
                    jsonPresAlu.put("resultado","error_sql");
                    jsonPresAlu.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado"+e);
                    System.out.println("Error code"+e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonPresAlu.put("resultado","error_class");
                    jsonPresAlu.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                arrayPresAlu.put(jsonPresAlu);
                response.getWriter().write(arrayPresAlu.toString());
                break;
            case "si_consulta_prestamo":
                JSONArray array_presAlum = new JSONArray();
                JSONObject json_presAlum = new JSONObject();
                String html_categorias = "";
                String el_estado = request.getParameter("estado");
                try {
                    DAOPrestamoAlumno cl = new DAOPrestamoAlumno();
                    ResultSet res_presalum = cl.consultarPrestamoAlumnoAll();
                    html_categorias+="<table id=\"tabla_prestamosAlu\""+
                            "class=\"table table-bordered dt-responsive nowrap\" "+
                            "cellspacing=\"0\" width=\"100%\">\n" +
                            "                           <thead>\n"+
                            "                               <tr>\n"+
                            "           <th>Codigo Prestamo</th>\n"+
                            "           <th>Nombre Alumno</th>\n"+
                            "           <th>Nombre Libro</th>\n"+
                            "           <th>Fecha Prestamo</th>\n"+
                            "           <th>Fecha Devolucion</th>\n"+
                            "           <th>Cantidad</th>\n"+
                            "           <th>Acciones</th>\n"+
                            "</tr\n>"+
                            "</thead\n>"+
                            "</tbody>\n";

                    while (res_presalum.next()){
                        html_categorias+="<tr>";
                        html_categorias+="<td>"+res_presalum.getString("codigo_prestamo")+"</td>";
                        html_categorias+="<td>"+res_presalum.getString("nombre")+ " "+res_presalum.getString("apellido")+"</td>";
                        html_categorias+="<td>"+res_presalum.getString("titulolibro")+"</td>";
                        html_categorias+="<td>"+res_presalum.getString("fecha_prestamo")+"</td>";
                        html_categorias+="<td>"+res_presalum.getString("fecha_devolucion")+"</td>";
                        html_categorias+="<td>"+res_presalum.getString("cantidadprestamo")+"</td>";
                        html_categorias+="<td>";
                        html_categorias+="<div class = 'dropdown m-b-10'>";
                        html_categorias+="<button class ='btn btn-secondary dropdown-toggle'"+
                                "type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'"+
                                "aria-expanded='false'>Seleccione</button>";

                        html_categorias+="<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_categorias+="<a class='dropdown-item btn_eliminar' data-id='"+res_presalum.getString("codigo_prestamo")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_categorias+="<a class='dropdown-item btn_editar' data-id='"+res_presalum.getString("codigo_prestamo")+"' href='javascript:void(0)'>Actualizar</a>";
                        html_categorias+="</div>";
                        html_categorias+="</div>";
                        html_categorias+="</td>";
                        html_categorias+="</tr>";

                    }

                    html_categorias+="</tbody>\n"+
                            "\t\t</table>";
                    json_presAlum.put("resultado","exito");
                    json_presAlum.put("tabla",html_categorias);

                }catch (SQLException e){
                    json_presAlum.put("resultado","error sql");
                    json_presAlum.put("error",e.getMessage());
                    json_presAlum.put("code error",e.getErrorCode());
                    throw new RuntimeException(e);
                }

                array_presAlum.put(json_presAlum);
                response.getWriter().write(array_presAlum.toString());
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray array_eliminado = new JSONArray();
        JSONObject json_eliminado = new JSONObject();
        String resultado = "";
        DAOPrestamoAlumno ob = null;

        try {
            ob = new DAOPrestamoAlumno();

            resultado = ob.eliminarPrestamoLibros(request.getParameter("id"));

            if (resultado == "exito"){
                json_eliminado.put("resultado","exito");
            }else{
                json_eliminado.put("resultado","error_eliminar");
            }

        }catch (SQLException e){
            json_eliminado.put("resultado","error_excepcion");
            json_eliminado.put("excepcion",e.getMessage());
            throw new RuntimeException(e);
        }
        array_eliminado.put(json_eliminado);
        response.getWriter().write(array_eliminado.toString());
    }
}
