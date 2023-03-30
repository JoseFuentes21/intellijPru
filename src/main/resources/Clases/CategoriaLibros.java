package Clases;

public class CategoriaLibros {
    private String idCategoiraLibro;
    private String categoriaLibro;

    public CategoriaLibros() {
    }

    public CategoriaLibros(String idCategoiraLibro, String categoriaLibro) {
        this.idCategoiraLibro = idCategoiraLibro;
        this.categoriaLibro = categoriaLibro;
    }

    public String getIdCategoiraLibro() {
        return idCategoiraLibro;
    }

    public void setIdCategoiraLibro(String idCategoiraLibro) {
        this.idCategoiraLibro = idCategoiraLibro;
    }

    public String getCategoriaLibro() {
        return categoriaLibro;
    }

    public void setCategoriaLibro(String categoriaLibro) {
        this.categoriaLibro = categoriaLibro;
    }
}
