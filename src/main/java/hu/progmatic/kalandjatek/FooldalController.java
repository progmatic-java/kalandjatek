package hu.progmatic.kalandjatek;

import hu.progmatic.kozos.kalandjatek.character.CharacterEntity;
import hu.progmatic.kozos.kalandjatek.character.CharacterService;
import hu.progmatic.kozos.kalandjatek.character.KalandjatekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class FooldalController {
    @Autowired
    private KalandjatekService kalandjatekService;

    @Autowired
    private CharacterService characterService;

    @GetMapping("/kalandjatek")
    public String beforeMain() {
        return "/kalandjatek/jatekfooldalelotti";
    }

    @GetMapping("/kalandjatek/fooldal")
    public String mainPage() {
        return "/kalandjatek/fooldal";
    }

    @ModelAttribute("dropDown")
    public List<CharacterEntity> dropDown() {
        return characterService.findAll();
    }
}
