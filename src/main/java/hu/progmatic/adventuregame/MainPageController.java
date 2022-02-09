package hu.progmatic.adventuregame;

import hu.progmatic.adventuregame.character.Character;
import hu.progmatic.adventuregame.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/adventuregame")
    public String beforeMain() {
        return "/adventuregame/beforemainpage";
    }

    @GetMapping("/adventuregame/mainpage")
    public String mainPage() {
        return "/adventuregame/mainpage";
    }

    @ModelAttribute("dropDown")
    public List<Character> dropDown() {
        return characterService.findAll();
    }
}
