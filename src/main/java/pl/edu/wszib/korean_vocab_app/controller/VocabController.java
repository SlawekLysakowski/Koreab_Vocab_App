package pl.edu.wszib.korean_vocab_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.edu.wszib.korean_vocab_app.exception.NotFoundException;
import pl.edu.wszib.korean_vocab_app.model.Vocab;
import pl.edu.wszib.korean_vocab_app.service.VocabService;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("")
public class VocabController implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/list").setViewName("list-vocab");
    }

    @Autowired
    VocabService vocabService;

    @GetMapping("/get")
    public String getVocabView(@RequestParam Long id, Model model) throws NotFoundException ,ChangeSetPersister.NotFoundException {
        model.addAttribute("vocab", vocabService.getVocab(id));
        return "get-vocab";
    }


    @GetMapping("/list")
    public String getAllWordsView(Model model, Vocab vocab) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("vocabs", vocabService.getAllVocabs());
        return "list-vocab";
    }

    @GetMapping("/test")
    public String testView(Model model, Vocab vocab, VocabController controller) throws ChangeSetPersister.NotFoundException {
        ArrayList<Vocab> allVocabs = vocabService.getAllVocabs();
        int size = vocabService.getAllVocabs().size();

        ArrayList<String> japaneseWords = new ArrayList<String>();
        ArrayList<String> koreanWords = new ArrayList<String>();
        ArrayList<String> englishWords = new ArrayList<String>();
        ArrayList<String> polishWords = new ArrayList<String>();

        model.addAttribute("vocabs", vocabService.getAllVocabs());
        model.addAttribute("size", size);

        for (Vocab v : allVocabs) {
            japaneseWords.add(v.getJapanese());
            koreanWords.add(v.getKorean());
            englishWords.add(v.getEnglish());
            polishWords.add(v.getPolish());
        }

        model.addAttribute("japaneseWords", japaneseWords);
        model.addAttribute("koreanWords", koreanWords);
        model.addAttribute("englishWords", englishWords);
        model.addAttribute("polishWords", polishWords);
        return "test-vocab";
    }

    @GetMapping("/new")
    public String createView(Model model) {
        model.addAttribute("newVocab", new Vocab());
        return "create-vocab";
    }

    @PostMapping("/new")
    public String create(@Valid Vocab newVocab, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute(newVocab);
            model.addAttribute("org.springframework.validation.BindingResult.newVocab", bindingResult);
            return "create-vocab";
        }

        newVocab = vocabService.createVocab(newVocab);


        return "redirect:/list";
    }

    @GetMapping("/update")
    public String updateVocabView(@RequestParam Long id, Model model) throws ChangeSetPersister.NotFoundException {

        model.addAttribute("updateVocab", vocabService.getVocab(id));
        return "update-vocab";
    }

    @PostMapping("/update")
    public String updateVocab(@Valid Vocab updateVocab, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
        if(bindingResult.hasErrors()) {
            model.addAttribute(updateVocab);
            model.addAttribute("org.springframework.validation.BindingResult.updateVocab", bindingResult);
            return "update-vocab";
        }
        updateVocab = vocabService.updateVocab(updateVocab);
        return "redirect:/list";
    }


    @GetMapping("/delete")
    public String deleteVocabView(@RequestParam Long id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("vocab", vocabService.getVocab(id));
        return "delete-vocab";
    }

    @PostMapping("/delete")
    public String deleteVocab(Vocab vocab, Model model) {
        vocabService.deleteVocab(vocab.getId());
        return "redirect:/list";
    }

    @GetMapping("/about")
    public String aboutView(Model model)  {
        return "about";
    }

    @ExceptionHandler()
    public String notFoundView() {
        return "404-vocab";
    }




}
