package hu.progmatic.adventuregame.mainpage;

import hu.progmatic.adventuregame.character.CharacterEntity;
import hu.progmatic.adventuregame.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private CharacterService characterService;

    @RequestMapping("/")
    public String kezdolap() {
        return "/adventuregame/beforemainpage";
    }

    @GetMapping("/adventuregame")
    public String beforeMain() {
        return "/adventuregame/beforemainpage";
    }

    @GetMapping("/adventuregame/mainpage")
    public String mainPage() {
        return "/adventuregame/mainpage";
    }

    @ModelAttribute("dropDown")
    public List<CharacterEntity> dropDown() {
        return characterService.findAll();
    }
}
