package pl.edu.wszib.koreab_vocab_app.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.edu.wszib.koreab_vocab_app.model.Vocab;

import java.util.ArrayList;

@Service
public interface VocabService {

    Vocab getVocab(Long id) throws ChangeSetPersister.NotFoundException;
    Vocab createVocab (Vocab newVocab);
    ArrayList<Vocab> getAllVocabs();
    Vocab updateVocab (Vocab updateVocab) throws ChangeSetPersister.NotFoundException;
    void deleteVocab (Long id);






}
