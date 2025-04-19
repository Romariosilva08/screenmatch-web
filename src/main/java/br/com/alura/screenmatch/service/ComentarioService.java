package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.ComentarioDTO;
import br.com.alura.screenmatch.model.Comentario;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.ComentarioRepository;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService implements ComentarioServiceInterface {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private SerieRepository serieRepository;

    public ComentarioDTO criarComentario(ComentarioDTO dto) {
        Serie serie = serieRepository.findById(dto.serieId())
                .orElseThrow(() -> new RuntimeException("Série não encontrada"));

        Comentario comentario = new Comentario(dto.autor(), dto.texto(), dto.avatarUrl(), serie);

        if (dto.comentarioPaiId() != null) {
            Comentario comentarioPai = comentarioRepository.findById(dto.comentarioPaiId())
                    .orElseThrow(() -> new RuntimeException("Comentário pai não encontrado"));
            comentario.setComentarioPai(comentarioPai);
        }

        comentarioRepository.save(comentario);
        return convertToDTO(comentario);
    }

    @Transactional
    public ComentarioDTO curtirComentario(Long id) {
        comentarioRepository.incrementarCurtidas(id);
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        return convertToDTO(comentario);
    }

    @Transactional
    public ComentarioDTO naoCurtirComentario(Long id) {
        comentarioRepository.incrementarNaoCurtidas(id);
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        return convertToDTO(comentario);
    }

    public List<ComentarioDTO> obterComentariosPrincipaisPorSerie(Long serieId) {
        return comentarioRepository.findBySerieIdAndComentarioPaiIsNull(serieId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> obterRespostas(Long comentarioPaiId) {
        return comentarioRepository.findByComentarioPaiId(comentarioPaiId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ComentarioDTO convertToDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getId(),
                comentario.getAutor(),
                comentario.getTexto(),
                comentario.getAvatarUrl(),
                comentario.getData(),
                comentario.getCurtidas(),
                comentario.getNaoCurtidas(),
                comentario.getSerie().getId(),
                comentario.getComentarioPai() != null ? comentario.getComentarioPai().getId() : null
        );
    }
}
