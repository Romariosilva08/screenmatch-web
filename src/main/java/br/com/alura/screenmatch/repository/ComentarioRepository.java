package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Busca comentários por ID da série
    List<Comentario> findBySerieId(Long serieId);

    // Busca respostas para um comentário específico
    List<Comentario> findByComentarioPaiId(Long comentarioPaiId);

    // Busca comentários principais (aqueles que não são respostas)
    List<Comentario> findBySerieIdAndComentarioPaiIsNull(Long serieId);

    // Incrementa o contador de curtidas de um comentário
    @Modifying
    @Query("UPDATE Comentario c SET c.curtidas = c.curtidas + 1 WHERE c.id = :id")
    void incrementarCurtidas(@Param("id") Long id);

    // Incrementa o contador de não curtidas de um comentário
    @Modifying
    @Query("UPDATE Comentario c SET c.naoCurtidas = c.naoCurtidas + 1 WHERE c.id = :id")
    void incrementarNaoCurtidas(@Param("id") Long id);

    // Busca os comentários mais curtidos de uma série
    @Query("SELECT c FROM Comentario c WHERE c.serie.id = :serieId ORDER BY c.curtidas DESC")
    List<Comentario> findTopComentariosMaisCurtidosPorSerie(@Param("serieId") Long serieId);

    // Conta o número de comentários de uma série
    long countBySerieId(Long serieId);
}
