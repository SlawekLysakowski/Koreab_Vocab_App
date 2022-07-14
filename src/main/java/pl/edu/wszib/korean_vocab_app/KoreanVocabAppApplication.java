package pl.edu.wszib.korean_vocab_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:db.properties")
public class KoreanVocabAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoreanVocabAppApplication.class, args);
    }

}
