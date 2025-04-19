package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.AvaliacaoComentarioDTO;
import br.com.alura.screenmatch.service.AvaliacaoComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes-comentarios")
public class AvaliacaoComentarioController {

    @Autowired
    private AvaliacaoComentarioService service;

    @PostMapping("/{serieId}")
    public ResponseEntity<AvaliacaoComentarioDTO> adicionarAvaliacaoComentario(
            @PathVariable Long serieId,
            @RequestBody AvaliacaoComentarioDTO dto) {

        AvaliacaoComentarioDTO resposta = service.adicionarAvaliacaoComentario(
                serieId,
                dto.avaliacao(),
                dto.comentario()
        );
        return ResponseEntity.ok(resposta);
    }


    @GetMapping("/{serieId}")
    public ResponseEntity<List<AvaliacaoComentarioDTO>> obterAvaliacoesPorSerie(@PathVariable Long serieId) {
        List<AvaliacaoComentarioDTO> dtos = service.obterAvaliacoesPorSerie(serieId);
        return ResponseEntity.ok(dtos);
    }
}
