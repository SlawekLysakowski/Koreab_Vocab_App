package pl.edu.wszib.korean_vocab_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.korean_vocab_app.model.Vocab;

@Repository
public interface VocabDao extends JpaRepository<Vocab, Long> {

}
