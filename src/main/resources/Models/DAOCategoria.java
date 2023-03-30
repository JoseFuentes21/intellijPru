package Models;

import Clases.CategoriaLibros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCategoria {
    private Connection con;

    public DAOCategoria() throws SQLException, ClassNotFoundException {
        Conexion cone = new Conexion();
        this.con = cone.Abrir_Conexion();
    }

    public String insertarCategoria(CategoriaLibros catLibr) throws SQLException, ClassNotFoundException {
        Conexion cone = new Conexion();
        con =  cone.Abrir_Conexion();
        String resultado = "";
        int resultado_insertar = 0;
        int resultado_categoria = 0;

        try {
            String sql = "INSERT INTO categorialibro(codigocategoria, nombrecategoria) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,catLibr.getIdCategoiraLibro());
            st.setString(2,catLibr.getCategoriaLibro());
            resultado_insertar = st.executeUpdate();
            st.close();
            if(resultado_insertar>0){
                resultado = "exito";
            }else {
                resultado = "error_categoria";
            }

        }catch (SQLException e){
            resultado = "error_categoria";
            System.out.println("El error al insertar: "+ e);
            System.out.println("El codigo error"+ e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public String actualizarCategoria(CategoriaLibros catLibr, String id_persona) throws SQLException {
        String sql = "UPDATE categorialibro SET nombrecategoria = ? WHERE codigocategoria='"+id_persona+"'";
        System.out.println("El id_persona es: "+id_persona);
        System.out.println("El sql de update es: "+sql);

        String resultado = "";
        int resultado_actualiza = 0;

        try {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,catLibr.getCategoriaLibro());
            String cat_modific = catLibr.getCategoriaLibro();
            System.out.println("cat_modific en el modelo DAOCategoria"+cat_modific);
            resultado_actualiza=st.executeUpdate();
            if (resultado_actualiza>0){
                resultado="exito";
            }else {
                resultado="error_categoria";
            }
            con.commit();
        }catch (SQLException e){
            con.rollback();
            resultado = "error_categoria";
            System.out.println("El error al actualizar: "+ e);
            System.out.println("El codigo error actua"+ e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ResultSet consultar_categoria(Integer estado, String quien){
        ResultSet resultSet =  null;
        try {
            Conexion cone = new Conexion();
            con = cone.Abrir_Conexion();
            String sql = "SELECT * FROM categorialibro";
            System.out.println("El sql estados: "+sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }

    public ResultSet consultar_categoriaEspecifica(String quien){
        ResultSet resultSet =  null;
        try {
            Conexion cone = new Conexion();
            con = cone.Abrir_Conexion();
            String sql = "SELECT * FROM categorialibro WHERE codigocategoria='"+quien+"'";
            System.out.println("El sql estados: "+sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }

    public String eliminar_categoriaLibros(String id_categoria) throws SQLException{
        String resultado = "";
        int eliminado = 0;
        try{
            con.setAutoCommit(false);
            String sql = "DELETE FROM categorialibro where codigocategoria='"+id_categoria+"'";
            System.out.println("query eliminado: "+ sql);
            PreparedStatement prepare;
            prepare = con.prepareStatement(sql);
            prepare.executeUpdate();
            resultado = "exito";
            con.commit();

        }catch (SQLException e){
            con.rollback();
            System.out.println("Excepcion al eliminar");
            resultado = "error";
            e.printStackTrace();
        }
        return resultado;
    }
}
