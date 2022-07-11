package pl.edu.wszib.korean_vocab_app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.wszib.korean_vocab_app.model.Vocab;

import javax.transaction.Transactional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class VocabDaoTest {

    public static final String  JAPANESE_WORD = "家族";
    public static final String  KOREAN_WORD = "가적";
    public static final String  ENGLISH_WORD = "family";
    public static final String  POLISH_WORD = "rodzina";
    public static final int TOTAL_WORDS = 3;


    @Autowired
    VocabDao vocabDao;


    @BeforeEach
    void setUp() {
        Vocab vocab1 = new Vocab(JAPANESE_WORD, KOREAN_WORD, ENGLISH_WORD, POLISH_WORD);
        Vocab vocab2 = new Vocab(JAPANESE_WORD + "2", KOREAN_WORD + "2", ENGLISH_WORD + "2", POLISH_WORD + "2");
        Vocab vocab3 = new Vocab(JAPANESE_WORD + "3", KOREAN_WORD + "3", ENGLISH_WORD + "3", POLISH_WORD + "3");

        assertNull(vocab1.getId());
        assertNull(vocab2.getId());
        assertNull(vocab3.getId());

        vocabDao.save(vocab1);
        vocabDao.save(vocab2);
        vocabDao.save(vocab3);

        assertNotNull(vocab1.getId());
        assertNotNull(vocab2.getId());
        assertNotNull(vocab3.getId());
    }

    @Test
    void testFetchData() throws Exception {
    Vocab vocab = vocabDao.findById(1L).orElseThrow(Exception::new);
    assertEquals(JAPANESE_WORD, vocab.getJapanese(), "wrong vocab found");
    }

    @Test
    void testFetchAllData() {
        Collection vocab = (Collection)vocabDao.findAll();
        assertEquals(TOTAL_WORDS, vocab.size(), "wrong number of vocabulary");
    }

}
