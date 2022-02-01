package hu.progmatic.kalandjatek.character;


public enum Race {
    ORC("https://i.imgur.com/nAKPlhw.png", "Congratulations! You are an Orc! You are a fierce fighter who is ready for any challenge!", 200, 50, 150),
    HUMAN("https://i.imgur.com/MQRZudq.png", "Congratulations! You are a Human! You remind me of a piece of stale bread... kinda boring, but it's fine i guess!", 100, 100, 100),
    ELF("https://i.imgur.com/13NVorA.png", "Congratulations! You are an Elf! You are a magical and mysterious being of human stature who are friendly with nature and animals... also you're pretty hot.", 150, 200, 150),
    REPTILIAN("https://i.imgur.com/0f45Fce.png", "Congratulations! You are a Reptilian Humanoid! You are a brave, yet ssssly fighter! If you were a Hogwarts House, you would be Slytherin.", 150, 100, 100);

    final String img;
    final String description;
    final Integer hp;
    final Integer mp;
    final Integer gold;

    Race(String img, String description, Integer hp, Integer mp, Integer gold) {
        this.img = img;
        this.description = description;
        this.hp = hp;
        this.mp = mp;
        this.gold = gold;
    }
}
