package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.dto.AvaliacaoComentarioDTO;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacao_comentario")
public class AvaliacaoComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double avaliacao;

    @Column(length = 1000)
    private String comentario;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    // Construtor completo
    public AvaliacaoComentario(Double avaliacao, String comentario, LocalDate data, Serie serie) {
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.data = data;
        this.serie = serie;
    }

    // Construtor padrão exigido pelo JPA
    public AvaliacaoComentario() {}

    // Getters e Setters
    // ... (métodos getters e setters para todos os campos)

    public AvaliacaoComentarioDTO toDTO() {
        return new AvaliacaoComentarioDTO(
                this.id,
                this.avaliacao,
                this.comentario,
                this.data.toString(),
                this.serie.getId()
        );
    }
}