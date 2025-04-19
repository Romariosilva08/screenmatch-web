package br.com.alura.screenmatch.dto;

import java.time.LocalDateTime;

public record ComentarioDTO(
        Long id,
        String autor,
        String texto,
        String avatarUrl,
        LocalDateTime data,
        int curtidas,
        int naoCurtidas,
        Long serieId,
        Long comentarioPaiId
) {
    public ComentarioDTO(String autor, String texto, String avatarUrl, Long serieId) {
        this(null, autor, texto, avatarUrl, LocalDateTime.now(), 0, 0, serieId, null);
    }

    public ComentarioDTO(String autor, String texto, String avatarUrl, Long serieId, Long comentarioPaiId) {
        this(null, autor, texto, avatarUrl, LocalDateTime.now(), 0, 0, serieId, comentarioPaiId);
    }
}
