package hu.progmatic.adventuregame.character;


import hu.progmatic.adventuregame.inventory.ItemDto;
import hu.progmatic.adventuregame.inventory.ItemEnum;

import java.util.List;

public enum Race {
    ORC("https://i.imgur.com/lXcw2hg.png",
            "You are an Orc! You are a fierce fighter who is ready for any challenge!",
            200, 50, 200, 15, 10,
            List.of(
                    ItemDto.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.(+2 ATK, 10 DMG)").attack(2).damage(10).defence(0).build(),
                    ItemDto.builder().itemName("Rock shield").typeOfItem(ItemEnum.SHIELD).description("So rock.(+2 DEF)").defence(2).attack(0).damage(0).build()
            ),
            List.of(
                ItemDto.builder().itemName("Battle Axe").typeOfItem(ItemEnum.ATTACK).description("So sharp.(+3 ATK, 10 DMG)").attack(3).damage(10).defence(0).build(),
                ItemDto.builder().itemName("Spoon").typeOfItem(ItemEnum.ATTACK).description("So handy!(+1 ATK, 1 DMG)").attack(1).damage(1).defence(0).build(),
                ItemDto.builder().itemName("Raw meat").typeOfItem(ItemEnum.CONSUMABLE).description("It's a bit chewy.(+10 HP)").hp(10).build()
            )
            , "https://i.imgur.com/jZpzqld.png"),
    HUMAN("https://i.imgur.com/MQRZudq.png",
            "You are a Human! You remind me of a piece of stale bread... kinda boring, but it's fine, I guess!",
            150, 100, 200, 10, 16,
            List.of(
                ItemDto.builder().itemName("Long Sword").typeOfItem(ItemEnum.ATTACK).description("So long... And a bit rusty.(+2 ATK, 8 DMG)").attack(2).damage(8).defence(0).build(),
                ItemDto.builder().itemName("Steel shield").typeOfItem(ItemEnum.SHIELD).description("So hard..(+3 DEF)").defence(3).attack(0).damage(0).build()
            ),
            List.of(
                ItemDto.builder().itemName("Fine wine").typeOfItem(ItemEnum.CONSUMABLE).description("So refreshing!(+10 HP)").hp(10).build(),
                ItemDto.builder().itemName("Liver pâté").typeOfItem(ItemEnum.CONSUMABLE).description("So ... Disgusting!!!!(+20 HP)").hp(20).build(),
                ItemDto.builder().itemName("Scroll of Lightning").typeOfItem(ItemEnum.CONSUMABLE).description("Thunder and Lightning!(20 DMG, -10 MP)").mp(20).damage(20).build()
            )
            , "https://i.imgur.com/GeilnJE.png"),
    ELF("https://i.imgur.com/13NVorA.png",
            "You are an Elf! You are a magical and mysterious being of human stature who are friendly with nature and animals... also you're pretty hot.",
            100, 200, 200, 8, 20,
            List.of(
                ItemDto.builder().itemName("Bow and Arrow").typeOfItem(ItemEnum.ATTACK).description("Fast and accurate.(+3 ATK, 7 DMG)").attack(3).damage(7).defence(0).build(),
                ItemDto.builder().itemName("Mithril chain mail").typeOfItem(ItemEnum.SHIELD).description("So shiny and stylish.(+5 DEF)").defence(5).attack(0).damage(0).build()
            ),
            List.of(
                ItemDto.builder().itemName("Nectar").typeOfItem(ItemEnum.CONSUMABLE).description("Juicy...!").hp(20).build(),
                ItemDto.builder().itemName("Elixir of Immortality").typeOfItem(ItemEnum.CONSUMABLE).description("Strengthens your vitality!(30 DMG, -30 MP)").mp(30).damage(30).build(),
                ItemDto.builder().itemName("Scroll of Thousand Calamities").typeOfItem(ItemEnum.CONSUMABLE).description("Unleashes chaotic forces!(40 DMG, -40 MP)").mp(40).damage(40).build()
            )
            , "https://i.imgur.com/bB49TdZ.png"),
    REPTILIAN("https://i.imgur.com/0f45Fce.png",
            "You are a Reptilian Humanoid! You are a brave, yet ssssly fighter! If you were a Hogwarts House, you would be Slytherin.",
            150, 150, 200, 10, 13,
            List.of(
                ItemDto.builder().itemName("Trident").typeOfItem(ItemEnum.ATTACK).description("So spiky.(+4 ATK, 8 DMG)").attack(4).damage(8).defence(0).build(),
                ItemDto.builder().itemName("Chitin shield").typeOfItem(ItemEnum.SHIELD).description("So stinky.(+3 DEF)").defence(3).attack(0).damage(0).build()
            ),
            List.of(
                ItemDto.builder().itemName("Can of Flies").typeOfItem(ItemEnum.CONSUMABLE).description("So crunchy.(+20 HP)").hp(20).build(),
                ItemDto.builder().itemName("Acid Spit").typeOfItem(ItemEnum.CONSUMABLE).description("So dangerous.(20 DMG, -20 MP)").mp(20).damage(20).build(),
                ItemDto.builder().itemName("Hardened Skin").typeOfItem(ItemEnum.SHIELD).description("I wonder whose this was?(+3 DEF)").defence(3).attack(0).damage(0).build()
            )
            , "https://i.imgur.com/dDierSp.png");

    final String img;
    final String description;
    final Integer hp;
    final Integer mp;
    final Integer gold;
    final Integer attack;
    final Integer defence;
    final List<ItemDto> activeItems;
    final List<ItemDto> invItems;
    final String indexImg;

    Race(String img, String description, Integer hp, Integer mp, Integer gold, Integer attack, Integer defence, List<ItemDto> activeItems, List<ItemDto> invItems, String indexImg) {
        this.img = img;
        this.description = description;
        this.hp = hp;
        this.mp = mp;
        this.gold = gold;
        this.attack = attack;
        this.defence = defence;
        this.activeItems = activeItems;
        this.invItems = invItems;
        this.indexImg = indexImg;
    }
}
