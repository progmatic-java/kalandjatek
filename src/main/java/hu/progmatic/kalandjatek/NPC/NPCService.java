package hu.progmatic.kalandjatek.NPC;


import hu.progmatic.felhasznalo.UserType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class NPCService implements InitializingBean {
    @Autowired
    private NPCRepository npcRepository;

    public NPCEntity save(NPCEntity npc) {
        return npcRepository.save(npc);
    }

    public NPCEntity getById(Integer id) {
        return npcRepository.getById(id);
    }

    public NPCDto getNPCDtoById(Integer id) {
        NPCEntity entity = getById(id);
        return NPCDto.builder()
                .name(entity.getName())
                .id(entity.getId())
                .description(entity.getDescription())
                .isItFriendly(entity.getIsItFriendly())
                .hp(entity.getHp())
                .mp(entity.getMp())
                .gold(entity.getGold())
                .imgRef(entity.getImgRef())
                .build();
    }


    @RolesAllowed(UserType.Roles.USER_WRITE_ROLE)
    public void delete(Integer id) {
        npcRepository.deleteById(id);
    }

    public List<NPCEntity> saveAll(List<NPCEntity> list) {
        return npcRepository.saveAllAndFlush(list);
    }

    public List<NPCEntity> findAllByName(String name) {
        return npcRepository.findAllByNameContains(name);
    }

    public List<NPCEntity> findAllByIsItFriendly(Boolean isItFriendly) {
        return npcRepository.findAllByIsItFriendly(isItFriendly);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (findAll().isEmpty()) {
            saveAll(List.of(
                    NPCEntity.builder()
                            .name("Burrows")
                            .description("The drunk owner of The Black Hole Inn.")
                            .isItFriendly(true)
                            .hp(50)
                            .mp(50)
                            .gold(1000)
                            .imgRef("https://i.imgur.com/9AnmjLg.png")
                            .build(),
                    NPCEntity.builder()
                            .name("Switcher")
                            .description("Your old friend from the prison.")
                            .isItFriendly(true)
                            .hp(100)
                            .mp(20)
                            .gold(500)
                            .imgRef("https://i.imgur.com/3IOuWgJ.png")
                            .build(),
                    NPCEntity.builder()
                            .name("Lady Regex")
                            .description("A mysterious witch from another town.")
                            .isItFriendly(false)
                            .hp(200)
                            .mp(300)
                            .gold(250)
                            .imgRef("https://i.imgur.com/RpbOdHt.png")
                            .build()));
        }

    }

    public List<NPCEntity> findAll() {
        return npcRepository.findAll();
    }

}