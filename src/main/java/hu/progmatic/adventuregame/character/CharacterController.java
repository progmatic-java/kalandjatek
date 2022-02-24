package hu.progmatic.adventuregame.character;

import hu.progmatic.felhasznalo.FelhasznaloService;
import hu.progmatic.felhasznalo.UserType;
import hu.progmatic.adventuregame.room.Room;
import hu.progmatic.adventuregame.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class CharacterController {

  @Autowired
  private CharacterService characterService;

  @Autowired
  private FelhasznaloService felhasznaloService;

  @Autowired
  private RoomService roomService;


  @GetMapping("/adventuregame/personalitytest")
  public String teszMix() {
    return "/adventuregame/personalitytest";
  }

  @GetMapping("/adventuregame/menutest")
  public String menuTest() {
    return "/adventuregame/menutest";
  }

  @GetMapping("/adventuregame/characterpage/{id}")
  public String characterId(
      @PathVariable Integer id, Model model) {
    CharacterDto chosenCharacter = characterService.getCharacterDtoById(id);
    model.addAttribute("chosenCharacter", chosenCharacter);
    model.addAttribute("newCharacter", false);
    return "/adventuregame/characterpage";
  }

  @PostMapping("/adventuregame/characterpage/delete/{id}")
  public String delete(@PathVariable Integer id) {
    characterService.delete(id);
    return "redirect:/adventuregame/mainpage";
  }

  @PostMapping("/adventuregame/personalitytest")
  public String tesztResult(
      @ModelAttribute("testAnswer") @Valid Answer answer,
      BindingResult bindingResult,
      Model model
  ) {
    if (bindingResult.hasErrors()) {
      return "/adventuregame/personalitytest";
    }
    CharacterDto resultCharacter = characterService.getResultCharacter(answer);
    model.addAttribute("chosenCharacter", resultCharacter);
    model.addAttribute("retakeTest", true);
    model.addAttribute("newCharacter", true);
    return "/adventuregame/characterpage";
  }

  @PostMapping("/adventuregame/unwantedcharacter/{id}")
  public String unwantedCharacter(
      @PathVariable Integer id,
      Model model
  ) {
  //  CharacterEntity character = characterService.getById(id);
  //  Answer answer = character.getAnswer();
    CharacterDto characterDto = characterService.getCharacterDtoById(id);
    characterService.delete(id);
    model.addAttribute("testAnswer", characterDto.getAnswer());
    return "/adventuregame/personalitytest";
  }

  @GetMapping("/adventuregame/introduction/{id}")
  public String intro(@PathVariable Integer id, Model model) {
    CharacterEntity chosenCharacter = characterService.getById(id);
    model.addAttribute("chosenCharacter", chosenCharacter);
    return "/adventuregame/introduction";
  }

  @ModelAttribute("chosenCharacter")
  public CharacterDto chosenCharacter() {
    return new CharacterDto();
  }

  @ModelAttribute("startingRoom")
  public Room startRoom() {
    return roomService.getByName("The Black Hole Inn");
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
