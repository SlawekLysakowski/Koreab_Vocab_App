package pl.edu.wszib.koreab_vocab_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.edu.wszib.koreab_vocab_app.dao.VocabDao;
import pl.edu.wszib.koreab_vocab_app.model.Vocab;
import pl.edu.wszib.koreab_vocab_app.service.VocabService;

import java.util.ArrayList;

@Service
public class VocabServiceImpl implements VocabService {
    @Autowired
    private VocabDao dao;

    @Override
    public Vocab getVocab(Long id) throws ChangeSetPersister.NotFoundException {
        return dao.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Vocab createVocab(Vocab newVocab) {
        return dao.save(newVocab);
    }

    @Override
    public ArrayList<Vocab> getAllVocabs() {
        ArrayList<Vocab> vocabArrayList = new ArrayList<>(dao.findAll());
        return vocabArrayList;
    }

    @Override
    public Vocab updateVocab(Vocab updateVocab) throws ChangeSetPersister.NotFoundException {
        Vocab existing = getVocab(updateVocab.getId());
        return dao.save(updateVocab);
    }

    @Override
    public void deleteVocab(Long id) {
        dao.deleteById(id);
    }
}
