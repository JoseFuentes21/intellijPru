package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOConsultas {
    private Connection con;

    public DAOConsultas() throws SQLException, ClassNotFoundException {
        Conexion cone = new Conexion();
        this.con = cone.Abrir_Conexion();
    }

    public ResultSet consultar_Autores(){
        ResultSet resultSet =  null;
        try {
            Conexion cone = new Conexion();
            con = cone.Abrir_Conexion();
            String sql = "select a.codigoautor, a.nombreautor, a.apellidoautor  from autor a inner join libro l " +
                    "on a.codigoautor = l.codigoautor group by a.codigoautor, a.nombreautor, a.apellidoautor \n";
            System.out.println("El sql estados: "+sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }
    public ResultSet consultar_AutoresEspecifico(String id_espe){
        ResultSet resultSet =  null;
        try {
            Conexion cone = new Conexion();
            con = cone.Abrir_Conexion();
            String sql = "select a.codigolibro, a.titulolibro, a.existencia, a.precio from libro a inner join autor b \n" +
                    "on a.codigoautor = b.codigoautor where a.codigoautor = '"+id_espe+"'";
            System.out.println("El sql estados: "+sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }
}
