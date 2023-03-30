package Clases;

import java.util.Date;
import java.util.List;

public class PrestamoAlumno {
    private String codigoPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Integer cantidad;

    private Libro libro;
    private Alumno alumno;

    public PrestamoAlumno() {
    }

    public PrestamoAlumno(String codigoPrestamo, Date fechaPrestamo, Date fechaDevolucion, Integer cantidad, Libro libro, Alumno alumno) {
        this.codigoPrestamo = codigoPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.cantidad = cantidad;
        this.libro = libro;
        this.alumno = alumno;
    }

    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public void setCodigoPrestamo(String codigoPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
