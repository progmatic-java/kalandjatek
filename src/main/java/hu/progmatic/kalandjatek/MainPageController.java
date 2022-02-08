package hu.progmatic.kalandjatek;

import hu.progmatic.kalandjatek.character.Character;
import hu.progmatic.kalandjatek.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/kalandjatek")
    public String beforeMain() {
        return "/kalandjatek/beforemainpage";
    }

    @GetMapping("/kalandjatek/fooldal")
    public String mainPage() {
        return "/kalandjatek/fooldal";
    }

    @ModelAttribute("dropDown")
    public List<Character> dropDown() {
        return characterService.findAll();
    }
}
