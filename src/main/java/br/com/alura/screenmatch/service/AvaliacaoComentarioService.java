package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.AvaliacaoComentarioDTO;
import br.com.alura.screenmatch.model.AvaliacaoComentario;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.AvaliacaoComentarioRepository;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvaliacaoComentarioService {

    @Autowired
    private AvaliacaoComentarioRepository avaliacaoComentarioRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Transactional
    public AvaliacaoComentarioDTO adicionarAvaliacaoComentario(Long serieId, Double avaliacao, String comentario) {
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new IllegalArgumentException("Série não encontrada"));

        if (avaliacao < 1 || avaliacao > 5) {
            throw new IllegalArgumentException("A avaliação deve ser entre 1 e 5");
        }

        AvaliacaoComentario avaliacaoComentario = new AvaliacaoComentario(
                avaliacao,
                comentario,
                LocalDate.now(),
                serie
        );

        avaliacaoComentario = avaliacaoComentarioRepository.save(avaliacaoComentario);
        return avaliacaoComentario.toDTO();
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoComentarioDTO> obterAvaliacoesPorSerie(Long serieId) {
        return avaliacaoComentarioRepository.findBySerieId(serieId).stream()
                .map(AvaliacaoComentario::toDTO)
                .toList();
    }
}