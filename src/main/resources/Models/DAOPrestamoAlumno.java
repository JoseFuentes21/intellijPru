package Models;

import Clases.PrestamoAlumno;

import java.sql.*;

public class DAOPrestamoAlumno {
    private Connection con;

    public DAOPrestamoAlumno() {
    }

    public String insertarPrestamoAlumno(PrestamoAlumno objPrestamoAlumno) throws SQLException, ClassNotFoundException {
        Conexion conexion = new Conexion();
        con =  conexion.Abrir_Conexion();
        String resultado = "";
        int resultado_insertar = 0;

        try {
            String sql = "INSERT INTO prestamo_alumno(codigo_prestamo, carnet_alumno, codigo_libro, fecha_prestamo, fecha_devolucion, cantidadprestamo) VALUES (?,?,?,?,?,?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, objPrestamoAlumno.getCodigoPrestamo());
            st.setString(2,objPrestamoAlumno.getAlumno().getCarnet());
            st.setString(3,objPrestamoAlumno.getLibro().getCodigoLibro());
            st.setDate(4, (Date) objPrestamoAlumno.getFechaPrestamo());
            st.setDate(5, (Date) objPrestamoAlumno.getFechaDevolucion());
            st.setInt(6,objPrestamoAlumno.getCantidad());

            resultado_insertar = st.executeUpdate();
            st.close();
            if (resultado_insertar>0){
                resultado = "exito";
            }else{
                resultado = "erro_insertar";
            }
        }catch (SQLException e){
            resultado = "error_prestamo";
            System.out.println("El error al insertar: "+ e);
            System.out.println("El codigo error"+ e.getErrorCode());
        }
        return resultado;
    }

    public ResultSet consultarPrestamoAlumnoAll(){
        ResultSet resultSet =  null;
        try {
            Conexion cone = new Conexion();
            con = cone.Abrir_Conexion();
            String sql = "SELECT a.carnet_alumno, a.codigo_libro, a.fecha_prestamo, a.fecha_devolucion, a.codigo_prestamo, a.cantidadprestamo,\n" +
                    "b.nombre, b.apellido, l.titulolibro  \n" +
                    "FROM prestamo_alumno AS a INNER JOIN alumno AS b ON  a.carnet_alumno = b.carnet\n" +
                    "INNER JOIN libro AS l ON a.codigo_libro = l.codigolibro ";
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

    public String eliminarPrestamoLibros(String id_presta) throws SQLException{
        String resultado = "";
        try{
            con.setAutoCommit(false);
            String sql = "DELETE FROM prestamo_alumno where codigo_prestamo='"+id_presta+"'";
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
