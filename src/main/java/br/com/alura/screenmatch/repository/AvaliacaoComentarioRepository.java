package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.AvaliacaoComentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoComentarioRepository extends JpaRepository<AvaliacaoComentario, Long> {
    List<AvaliacaoComentario> findBySerieId(Long serieId);
}
