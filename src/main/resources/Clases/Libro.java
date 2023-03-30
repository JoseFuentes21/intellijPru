package Clases;

public class Libro {
    private String codigoLibro;
    private String tituloLibro;
    private Integer existencia;
    private Double precio;

    private CategoriaLibros categoriaLibros;
    private Autor autor;

    public Libro() {
    }

    public Libro(String codigoLibro, String tituloLibro, Integer existencia, Double precio, CategoriaLibros categoriaLibros, Autor autor) {
        this.codigoLibro = codigoLibro;
        this.tituloLibro = tituloLibro;
        this.existencia = existencia;
        this.precio = precio;
        this.categoriaLibros = categoriaLibros;
        this.autor = autor;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public CategoriaLibros getCategoriaLibros() {
        return categoriaLibros;
    }

    public void setCategoriaLibros(CategoriaLibros categoriaLibros) {
        this.categoriaLibros = categoriaLibros;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
