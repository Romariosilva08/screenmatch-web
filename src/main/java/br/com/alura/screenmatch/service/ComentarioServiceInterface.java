package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioServiceInterface {
    ComentarioDTO curtirComentario(Long id);
    ComentarioDTO naoCurtirComentario(Long id);
    List<ComentarioDTO> obterComentariosPrincipaisPorSerie(Long serieId);
    ComentarioDTO criarComentario(ComentarioDTO dto);
}
