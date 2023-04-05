package ZA_estruturaDados.models;

import java.time.LocalDate;
import java.util.Objects;

public class BookModel {
    // Nome, ISBN, Ano de Lançamento, e Autor
    private String nome;
    private String ISBN;
    private LocalDate anoLancamento;
    private String autor;

    public BookModel() {
    }

    public BookModel(String nome, String ISBN, LocalDate anoLancamento, String autor) {
        this.nome = nome;
        this.ISBN = ISBN;
        this.anoLancamento = anoLancamento;
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return Objects.equals(nome, bookModel.nome) && Objects.equals(ISBN, bookModel.ISBN) && Objects.equals(anoLancamento, bookModel.anoLancamento) && Objects.equals(autor, bookModel.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ISBN, anoLancamento, autor);
    }

    @Override
    public String toString() {
        return "BookModel{\n" +
                "\tnome='" + nome + '\'' +
                "\n\tISBN='" + ISBN + '\'' +
                "\n\tanoLancamento=" + anoLancamento +
                "\n\tautor='" + autor + '\n' +
                '}';
    }
}
