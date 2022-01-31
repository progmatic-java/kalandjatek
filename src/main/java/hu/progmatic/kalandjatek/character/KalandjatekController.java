package hu.progmatic.kalandjatek.character;

import hu.progmatic.felhasznalo.FelhasznaloService;
import hu.progmatic.felhasznalo.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class KalandjatekController {
    private final List<Character> defaultCharacters = List.of(
            new Character("Orkeo", Race.ORC, 100, 50, 150),
            new Character("Elfy", Race.ELF, 100, 50, 150),
            new Character("Bence", Race.HUMAN, 50, 50, 50),
            new Character("Sugar Alien", Race.REPTILIAN, 200, 200, 200)
    );

    @Autowired
    private KalandjatekService kalandjatekService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private FelhasznaloService felhasznaloService;

    @GetMapping("/kalandjatek/szemelyisegtesztmixelt")
    public String teszMix() {
        return "/kalandjatek/szemelyisegtesztmixelt";
    }

    @GetMapping("/kalandjatek/characterpage/{id}")
    public String characterId(
            @PathVariable Integer id, Model model) {
        CharacterEntity chosenCharacter = characterService.getById(id);

        model.addAttribute("chosenCharacter", chosenCharacter);

        return "/kalandjatek/characterpage";
    }

    @PostMapping("/kalandjatek/characterpage/delete/{id}")
    public String delete(@PathVariable Integer id) {
        characterService.delete(id);
        return "/kalandjatek/fooldal";
    }

    @PostMapping("/kalandjatek/szemelyisegtesztmixelt")
    public String tesztResult(
            @ModelAttribute("testAnswer") @Valid Answer answer,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "/kalandjatek/szemelyisegtesztmixelt";
        }
        Race resultRace = kalandjatekService.getResults(answer);
        model.addAttribute("selectedCharacter", getSelectedCharacter(resultRace));
        return "/kalandjatek/karaktertemplate";
    }

    private Character getSelectedCharacter(Race resultRace) {
        List<Character> defaultChars = getDefaultCharacters();
        return defaultChars.stream()
                .filter(character -> character.race.equals(resultRace))
                .findAny()
                .get();
    }

    @ModelAttribute("selectedCharacter")
    public Character selectedCharacter() {
        return new Character(
                "asdasd",
                Race.ELF,
                10,
                10,
                10
        );
    }

    @ModelAttribute("chosenCharacter")
    public CharacterEntity chosenCharacter() {
        return new CharacterEntity();
    }


    @ModelAttribute("testAnswer")
    public Answer testAnswer() {
        return new Answer();
    }

    @ModelAttribute("defaultCharacters")
    public List<Character> getDefaultCharacters() {
        return defaultCharacters;
    }

    @ModelAttribute("hasUserWriteRole")
    public boolean userWriteRole() {
        return felhasznaloService.hasRole(UserType.Roles.USER_WRITE_ROLE);
    }

    @ModelAttribute("hasUserReadRole")
    public boolean userReadRole() {
        return felhasznaloService.hasRole(UserType.Roles.USER_READ_ROLE);
    }
}
