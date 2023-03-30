package Controllers;

import Models.DAOCategoria;
import Models.DAOConsultas;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ConsAutoresLibros", value = "/ConsAutoresLibros")
public class ConsAutoresLibros extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/modulos/consulAutoresLib.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("aplication/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");
        System.out.println("El filtro es: "+filtro);
        HttpSession session = request.getSession();

        if (filtro==null){
            return;
        }

        switch(filtro){
            case "si_consultar_autoresCombox":
                JSONArray array_autorLibrito = new JSONArray();
                JSONObject json_autorLibrito = new JSONObject();
                DAOConsultas daoConsultas = null;
                String html_autores = "";

                try {
                    daoConsultas =  new DAOConsultas();
                    ResultSet res = daoConsultas.consultar_Autores();
                    while (res.next()){
                        html_autores += "<option value='"+res.getString("codigoautor")+"'>"
                                +res.getString("nombreautor")+" "+res.getString("apellidoautor")+"</option>";
                    }
                    json_autorLibrito.put("resultado","exito");
                    json_autorLibrito.put("opcionCombo",html_autores);
                } catch (SQLException e) {
                    json_autorLibrito.put("resultado","error");
                    json_autorLibrito.put("el_error",e.getMessage());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                array_autorLibrito.put(json_autorLibrito);
                response.getWriter().write(array_autorLibrito.toString());
                break;
            case "si_consulta_autoresconlibros_especifico":
                JSONArray array_autorLibEsp = new JSONArray();
                JSONObject json_autorLibEsp = new JSONObject();

                String html_autoresTb = "";

                try {
                    DAOConsultas daoConsultas2 = new DAOConsultas();
                    ResultSet resultadoSet  =  daoConsultas2.consultar_AutoresEspecifico(request.getParameter("id_autor"));
                    html_autoresTb += "<table id=\"tabla_autores\"" +
                            "class=\"table table-bordered dt-responsive nowrap\"" +
                            "cellspacing=\"0\" width=\"100%\">\n" +
                            "                           <thead>\n"+
                            "                               <tr>\n"+
                            "           <th>Codigo Libro</th>\n"+
                            "           <th>Nombre Libro</th>\n"+
                            "           <th>EXISTENCIA</th>\n"+
                            "           <th>PRECIO</th>\n"+
                            "</tr\n>"+
                            "</thead\n>"+
                            "</tbody>\n";
                    while (resultadoSet.next()){
                        html_autoresTb += "<tr>";
                        html_autoresTb+="<td>"+resultadoSet.getString("codigolibro")+"</td>";
                        html_autoresTb+="<td>"+resultadoSet.getString("titulolibro")+"</td>";
                        html_autoresTb+="<td>"+resultadoSet.getString("existencia")+"</td>";
                        html_autoresTb+="<td>"+resultadoSet.getString("precio")+"</td>";
                        html_autoresTb+="</tr>";
                    }
                    html_autoresTb+="</tbody>\n"+
                            "\t\t</table>";
                    json_autorLibEsp.put("resultado","exito");
                    json_autorLibEsp.put("tabla",html_autoresTb);


                } catch (SQLException e) {
                    json_autorLibEsp.put("resultado","error_sql");
                    json_autorLibEsp.put("el_error",e.getMessage());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                array_autorLibEsp.put(json_autorLibEsp);
                response.getWriter().write(array_autorLibEsp.toString());

                break;
        }
    }
}
