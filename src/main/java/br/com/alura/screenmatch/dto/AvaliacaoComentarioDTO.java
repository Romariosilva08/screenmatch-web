package br.com.alura.screenmatch.dto;

public record AvaliacaoComentarioDTO(
        Long id,
        Double avaliacao,
        String comentario,
        String data,
        Long serieId
) {
    public AvaliacaoComentarioDTO(Double avaliacao, String comentario, String data, Long serieId) {
        this(null, avaliacao, comentario, data, serieId);
    }
}