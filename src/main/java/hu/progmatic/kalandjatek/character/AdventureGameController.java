package hu.progmatic.kalandjatek.character;

import hu.progmatic.felhasznalo.FelhasznaloService;
import hu.progmatic.felhasznalo.UserType;
import hu.progmatic.kalandjatek.room.Room;
import hu.progmatic.kalandjatek.room.RoomService;
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
public class AdventureGameController {



  @Autowired
  private CharacterService characterService;

  @Autowired
  private FelhasznaloService felhasznaloService;

  @Autowired
  private RoomService roomService;


  @GetMapping("/kalandjatek/szemelyisegtesztmixelt")
  public String teszMix() {
    return "/kalandjatek/szemelyisegtesztmixelt";
  }

  @GetMapping("/kalandjatek/menutest")
  public String menuTest() {
    return "/kalandjatek/menutest";
  }

  @GetMapping("/kalandjatek/characterpage/{id}")
  public String characterId(
      @PathVariable Integer id, Model model) {
    Character chosenCharacter = characterService.getById(id);
    model.addAttribute("chosenCharacter", chosenCharacter);
    model.addAttribute("newCharacter", false);
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
    Character resultCharacter = characterService.getResultCharacter(answer);
    characterService.save(resultCharacter);
    model.addAttribute("chosenCharacter", resultCharacter);
    model.addAttribute("retakeTest", true);
    model.addAttribute("newCharacter", true);
    return "/kalandjatek/characterpage";
  }

  @PostMapping("/kalandjatek/unwantedcharacter/{id}")
  public String unwantedCharacter(
      @PathVariable Integer id,
      Model model
  ) {
  //  CharacterEntity character = characterService.getById(id);
  //  Answer answer = character.getAnswer();
    CharacterDto characterDto = characterService.getCharacterDtoById(id);
    characterService.delete(id);
    model.addAttribute("testAnswer", characterDto.getAnswer());
    return "/kalandjatek/szemelyisegtesztmixelt";
  }

  @GetMapping("/kalandjatek/introduction/{id}")
  public String intro(@PathVariable Integer id, Model model) {
    Character chosenCharacter = characterService.getById(id);
    model.addAttribute("chosenCharacter", chosenCharacter);
    return "/kalandjatek/introduction";
  }

  @ModelAttribute("chosenCharacter")
  public Character chosenCharacter() {
    return new Character();
  }

  @ModelAttribute("startingRoom")
  public Room startRoom() {
    return roomService.getByName("Inn");
  }

  @ModelAttribute("testAnswer")
  public Answer testAnswer() {
    return new Answer();
  }


  @ModelAttribute("retakeTest")
  public boolean retakeTest() {
    return false;
  }

  @ModelAttribute("newCharacter")
  public boolean newCharacter() {
    return false;
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
