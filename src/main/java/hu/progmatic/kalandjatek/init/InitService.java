package hu.progmatic.kalandjatek.init;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.ItemEntity;
import hu.progmatic.kalandjatek.ItemEnum;
import hu.progmatic.kalandjatek.NPC.NPCEntity;
import hu.progmatic.kalandjatek.room.DoorEntity;
import hu.progmatic.kalandjatek.room.RoomEntity;
import hu.progmatic.kalandjatek.room.RoomRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InitService implements InitializingBean {

  @Autowired
  private RoomRepository roomRepository;

  @Override
  public void afterPropertiesSet() throws Exception {
    RoomEntity inn = RoomEntity.builder().name("Inn").build();
    RoomEntity cellar = RoomEntity.builder().name("Inn cellar").build();

    InventoryEntity innInv = InventoryEntity.builder().build();
    InventoryEntity cellarInv = InventoryEntity.builder().build();

    List<ItemEntity> innItems = List.of(
        ItemEntity.builder()
            .itemName("Beer")
            .description("A cold drink from Go'odor")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        ItemEntity.builder()
            .itemName("Knife")
            .description("Good for slicing bread")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        ItemEntity.builder()
            .itemName("Coins")
            .description("You should take it")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build()
    );

    List<ItemEntity> cellarItems = List.of(
        ItemEntity.builder()
            .itemName("Imported Beer")
            .description("It's a holy drink from Ko'olossy")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        ItemEntity.builder()
            .itemName("Wine")
            .description("It has a lot of antioxidants")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        ItemEntity.builder()
            .itemName("Dead body")
            .description("Dog food")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build()
    );

//    List<ItemEntity> charItems = List.of(
//        ItemEntity.builder()
//            .itemName("Long sword")
//            .description("Finest weapon in town")
//            .typeOfItem(ItemEnum.ATTACK)
//            .build(),
//        ItemEntity.builder()
//            .itemName("Wooden shield")
//            .description("Good for blocking")
//            .typeOfItem(ItemEnum.SHIELD)
//            .build(),
//        ItemEntity.builder()
//            .itemName("Potion")
//            .description("Heals your wounds")
//            .typeOfItem(ItemEnum.CONSUMABLE)
//            .build()
//    );

    List<NPCEntity> npcList = List.of(
        NPCEntity.builder()
            .name("Burrows")
            .description("The drunk owner of The Black Hole Inn.")
            .friendly(true)
            .hp(50)
            .mp(50)
            .gold(1000)
            .imgRef("https://i.imgur.com/9AnmjLg.png")
            .build(),
        NPCEntity.builder()
            .name("Switcher")
            .description("Your old friend from the prison.")
            .friendly(true)
            .hp(100)
            .mp(20)
            .gold(500)
            .imgRef("https://i.imgur.com/3IOuWgJ.png")
            .build(),
        NPCEntity.builder()
            .name("Lady Regex")
            .description("A mysterious witch from another town.")
            .friendly(false)
            .hp(200)
            .mp(300)
            .gold(250)
            .imgRef("https://i.imgur.com/RpbOdHt.png")
            .build()
    );

    NPCEntity madDog = NPCEntity.builder().name("Mad dog").description("Precious pet of Burrows").build();

    for (ItemEntity item : innItems) {
      innInv.getItems().add(item);
      item.setInventory(innInv);
    }
    inn.setInventory(innInv);

    for (ItemEntity item : cellarItems) {
      cellarInv.getItems().add(item);
      item.setInventory(cellarInv);
    }
    cellar.setInventory(cellarInv);

    for (NPCEntity npc : npcList) {
      npc.setNpcRoom(inn);
    }
    inn.setNpcEntities(npcList);

    madDog.setNpcRoom(cellar);
    cellar.getNpcEntities().add(madDog);

    DoorEntity innToCellar = DoorEntity.builder().room1(inn).room2(cellar).build();
    inn.getDoors1().add(innToCellar);
    cellar.getDoors2().add(innToCellar);

    roomRepository.saveAll(List.of(inn, cellar));
  }
}
