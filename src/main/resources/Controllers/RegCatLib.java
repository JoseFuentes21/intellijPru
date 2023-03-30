package Controllers;

import Clases.CategoriaLibros;
import Models.DAOCategoria;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegCatLib", value = "/RegCatLib")
public class RegCatLib extends HttpServlet {
    private final String directorio = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombre = (String)session.getAttribute("nombre");
        System.out.println("Inicio session: "+ nombre);
        request.getRequestDispatcher("/modulos/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se le dice que recibira informacion en formato json y se optiene el parametro
        //por el cual se realizara la operacion
        response.setContentType("aplication/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");
        System.out.println("El filtro es: "+filtro);
        HttpSession session = request.getSession();

        if (filtro==null){
            return;
        }

        switch(filtro){
            case "si_registro":
                JSONArray arrayCateLib = new JSONArray();
                JSONObject jsonCatLib = new JSONObject();
                String resultado_insertar = "";
                CategoriaLibros categoriaLibros = new CategoriaLibros();
                try {
                    DAOCategoria daoCategoria = new DAOCategoria();
                    categoriaLibros = new CategoriaLibros(request.getParameter("codigoCategoria"),
                                        request.getParameter("nombreCategoria"));
                    resultado_insertar = daoCategoria.insertarCategoria(categoriaLibros);
                    if (resultado_insertar=="exito"){
                        jsonCatLib.put("resultado","exito");
                        jsonCatLib.put("nombreCategoria", categoriaLibros.getCategoriaLibro());
                        jsonCatLib.put("codigoCategoira", categoriaLibros.getIdCategoiraLibro());
                    }else {
                        jsonCatLib.put("resultado", "error");
                        jsonCatLib.put("resultado_insertar",resultado_insertar);
                    }

                }catch (SQLException e){
                    jsonCatLib.put("resultado","error_sql");
                    jsonCatLib.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado"+e);
                    System.out.println("Error code"+e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    jsonCatLib.put("resultado","error_class");
                    jsonCatLib.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                arrayCateLib.put(jsonCatLib);
                response.getWriter().write(arrayCateLib.toString());
                break;
            case "si_consulta_categoria":
                JSONArray array_catLibrito = new JSONArray();
                JSONObject json_catLibrito = new JSONObject();
                String html_categorias = "";
                String el_estado = request.getParameter("estado");
                try {
                    DAOCategoria cl = new DAOCategoria();
                    ResultSet res_categorias = cl.consultar_categoria(Integer.valueOf(el_estado),"todos");
                    html_categorias+="<table id=\"tabla_categorias\""+
                            "class=\"table table-bordered dt-responsive nowrap\" "+
                            "cellspacing=\"0\" width=\"100%\">\n" +
                            "                           <thead>\n"+
                            "                               <tr>\n"+
                            "           <th>Codigo Categoria</th>\n"+
                            "           <th>Nombre Categoria</th>\n"+
                            "           <th>Acciones</th>\n"+
                            "</tr\n>"+
                            "</thead\n>"+
                            "</tbody>\n";

                    while (res_categorias.next()){
                        html_categorias+="<tr>";
                        html_categorias+="<td>"+res_categorias.getString("codigocategoria")+"</td>";
                        html_categorias+="<td>"+res_categorias.getString("nombrecategoria")+"</td>";
                        html_categorias+="<td>";
                        html_categorias+="<div class = 'dropdown m-b-10'>";
                        html_categorias+="<button class ='btn btn-secondary dropdown-toggle'"+
                                "type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true'"+
                                "aria-expanded='false'>Seleccione</button>";

                        html_categorias+="<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html_categorias+="<a class='dropdown-item btn_eliminar' data-id='"+res_categorias.getString("codigocategoria")+"' href='javascript:void(0)'>Eliminar</a>";
                        html_categorias+="<a class='dropdown-item btn_editar' data-id='"+res_categorias.getString("codigocategoria")+"' href='javascript:void(0)'>Actualizar</a>";
                        html_categorias+="</div>";
                        html_categorias+="</div>";
                        html_categorias+="</td>";
                        html_categorias+="</tr>";

                    }

                    html_categorias+="</tbody>\n"+
                            "\t\t</table>";
                    json_catLibrito.put("resultado","exito");
                    json_catLibrito.put("tabla",html_categorias);

                }catch (SQLException e){
                    json_catLibrito.put("resultado","error sql");
                    json_catLibrito.put("error",e.getMessage());
                    json_catLibrito.put("code error",e.getErrorCode());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_catLibrito.put("resultado","clas no found");
                    json_catLibrito.put("error",e.getMessage());
                    throw new RuntimeException(e);
                }

                array_catLibrito.put(json_catLibrito);
                response.getWriter().write(array_catLibrito.toString());
                break;
            case "si_eliminalo":

                JSONArray array_eliminado = new JSONArray();
                JSONObject json_eliminado = new JSONObject();
                String resultado = "";
                DAOCategoria ob = null;

                try {
                    ob = new DAOCategoria();

                    resultado = ob.eliminar_categoriaLibros(request.getParameter("id"));

                    if (resultado == "exito"){
                        json_eliminado.put("resultado","exito");
                    }else{
                        json_eliminado.put("resultado","error_eliminar");
                    }

                }catch (SQLException e){
                    json_eliminado.put("resultado","error_excepcion");
                    json_eliminado.put("excepcion",e.getMessage());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_eliminado.put("resultado","errorClass");
                    json_eliminado.put("excepcion",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_eliminado.put(json_eliminado);
                response.getWriter().write(array_eliminado.toString());
                break;
            case "si_concategoria_especifica":
                JSONArray array_especifico = new JSONArray();
                JSONObject json_especifico = new JSONObject();
                String resultadoJ = "";
                DAOCategoria ob_cli = null;

                try {
                    ob_cli = new DAOCategoria();
                    ResultSet res_individual = ob_cli.consultar_categoriaEspecifica(request.getParameter("id"));
                    System.out.println("request.getParametrer():"+request.getParameter("id"));
                    while (res_individual.next()){
                        json_especifico.put("resultado","exito");
                        json_especifico.put("id_persona",res_individual.getString("codigocategoria"));
                        json_especifico.put("id_categoria",res_individual.getString("codigocategoria"));
                        json_especifico.put("nombre_categoria",res_individual.getString("nombrecategoria"));
                    }
                } catch (SQLException e) {
                    json_especifico.put("resultado","error_sql");
                    json_especifico.put("el_error",e.getMessage());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    json_especifico.put("resultado","error_not_found");
                    json_especifico.put("el_error",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_especifico.put(json_especifico);
                response.getWriter().write(array_especifico.toString());
                break;
            case "si_actualizalo":
                JSONArray arrayActualizar = new JSONArray();
                JSONObject jsonActualizar = new JSONObject();
                String resultado_modifica = "";
                CategoriaLibros objcategoriaLibros = new CategoriaLibros();

                try {
                    DAOCategoria daoCategoria = new DAOCategoria();
                    objcategoriaLibros.setCategoriaLibro(request.getParameter("nombreCategoria"));

                    resultado_modifica = daoCategoria.actualizarCategoria(objcategoriaLibros,request.getParameter("llave_persona"));
                    System.out.println("LA LLAVE DE PERSONA ES:"+request.getParameter("llave_persona"));
                    if (resultado_modifica=="exito"){
                        jsonActualizar.put("resultado","exito");
                        jsonActualizar.put("nombreCategoria",objcategoriaLibros.getCategoriaLibro());
                        jsonActualizar.put("id_categoria",objcategoriaLibros.getIdCategoiraLibro());
                    }else {
                        jsonActualizar.put("resultado","error");
                        jsonActualizar.put("resultado_modifi",resultado_modifica);
                    }

                } catch (SQLException e) {
                    jsonActualizar.put("resultado","error_sql");
                    jsonActualizar.put("error_mostrado",e.getMessage());
                    System.out.println("Error Mostrado: "+e);
                    System.out.println("Error Code"+e.getErrorCode());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    jsonActualizar.put("resultado","error_class");
                    jsonActualizar.put("error_mostrado",e);
                    throw new RuntimeException(e);
                }
                arrayActualizar.put(jsonActualizar);
                response.getWriter().write(arrayActualizar.toString());
                break;
        }

    }
}
