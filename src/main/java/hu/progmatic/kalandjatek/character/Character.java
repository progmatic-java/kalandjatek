package hu.progmatic.kalandjatek.character;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Character {
    @NotEmpty(message = "Please choose a name!")
    String name;
    @NotNull(message = "Please choose a race!")
    Race race;
    String description;
    @NotNull
    @Min(50)
    @Max(200)
    Integer hp;
    @NotNull
    @Min(50)
    @Max(200)
    Integer mp;
    @NotNull
    @Min(50)
    @Max(200)
    Integer gold;
    String imgRef;

    public Character(String name, Race race, Integer hp, Integer mp, Integer gold) {
        this.name = name;
        this.race = race;
        this.hp = hp;
        this.mp = mp;
        this.gold = gold;
        this.description = race.description;
        this.imgRef = race.img;
//        switch (race) {
//            case ORC:
//                this.description = "Congratulations! You are an Orc! You are a fierce potato..khm..fighter who is ready for any challenge!";
//                this.imgRef = "https://i.imgur.com/nAKPlhw.png";
//                break;
//            case ELF:
//                this.description = "Congratulations! You are an Elf! You are a magical and mysterious being of human stature\n" +
//                        "    who are friendly with nature and animals... also you're pretty hot.";
//                this.imgRef = "https://i.imgur.com/13NVorA.png";
//                break;
//            case HUMAN:
//                this.description = "Congratulations! You are a Human! You remind me of a piece of stale bread... kinda boring\n" +
//                        "    but it's fine i guess!";
//                this.imgRef = "https://i.imgur.com/MQRZudq.png";
//                break;
//            case REPTILIAN:
//                this.description = "Congratulations! You are a Reptilian Humanoid! You are a brave, yet ssssly fighter! If you were a Hogwarts House, you would be Slytherin.";
//                this.imgRef = "https://i.imgur.com/0f45Fce.png";
//                break;
//        }
    }


}
