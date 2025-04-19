package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.ComentarioDTO;
import br.com.alura.screenmatch.service.ComentarioServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioServiceInterface comentarioService;

    public ComentarioController(ComentarioServiceInterface comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> criarComentario(@RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.criarComentario(dto));
    }

    @GetMapping("/serie/{serieId}")
    public ResponseEntity<List<ComentarioDTO>> listarPorSerie(@PathVariable Long serieId) {
        return ResponseEntity.ok(comentarioService.obterComentariosPrincipaisPorSerie(serieId));
    }

    @PostMapping("/{id}/curtir")
    public ResponseEntity<ComentarioDTO> curtir(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.curtirComentario(id));
    }

    @PostMapping("/{id}/nao-curtir")
    public ResponseEntity<ComentarioDTO> naoCurtir(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.naoCurtirComentario(id));
    }
}
