package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("autor")
    private String autor;

    @JsonAlias("texto")
    private String texto;

    @JsonAlias("avatar_url")
    private String avatarUrl;

    private LocalDateTime data;

    private int curtidas;
    private int naoCurtidas;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    @JsonIgnoreProperties("comentarios") // evita loop de série -> comentarios -> série...
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "comentario_pai_id")
    @JsonIgnoreProperties({"comentarioPai", "respostas"}) // evita loop de pai/filhos
    private Comentario comentarioPai; // pode ser renomeado para "comentarioAnterior" se necessário

    // Construtores
    public Comentario() {}

    public Comentario(String autor, String texto, String avatarUrl, Serie serie) {
        this.autor = autor;
        this.texto = texto;
        this.avatarUrl = avatarUrl;
        this.serie = serie;
        this.data = LocalDateTime.now();
        this.curtidas = 0;
        this.naoCurtidas = 0;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getNaoCurtidas() {
        return naoCurtidas;
    }

    public void setNaoCurtidas(int naoCurtidas) {
        this.naoCurtidas = naoCurtidas;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
