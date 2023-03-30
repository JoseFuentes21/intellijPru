package Clases;

import java.util.Date;
import java.util.List;

public class Alumno {
    private String carnet;
    private String nombre;
    private String apellido;
    private String direccion;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String genero;
    private Boolean estado;

    private List<PrestamoAlumno> prestamoAlumno;

    public Alumno() {
    }

    public Alumno(String carnet, String nombre, String apellido, String direccion, Date fechaNacimiento, Date fechaIngreso, String genero, Boolean estado) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.genero = genero;
        this.estado = estado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<PrestamoAlumno> getPrestamoAlumno() {
        return prestamoAlumno;
    }

    public void setPrestamoAlumno(List<PrestamoAlumno> prestamoAlumno) {
        this.prestamoAlumno = prestamoAlumno;
    }
}
