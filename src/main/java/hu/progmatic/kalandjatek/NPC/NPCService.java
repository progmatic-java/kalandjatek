package hu.progmatic.kalandjatek.NPC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class NPCService {
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
                .isItFriendly(entity.getFriendly())
                .hp(entity.getHp())
                .mp(entity.getMp())
                .gold(entity.getGold())
                .imgRef(entity.getImgRef())
                .build();
    }

    public void delete(Integer id) {
        npcRepository.deleteById(id);
    }

    public void saveAll(List<NPCEntity> list) {
        npcRepository.saveAllAndFlush(list);
    }

    public NPCEntity findByName(String name) {
        return npcRepository.findByName(name).orElseThrow();
    }

    public List<NPCEntity> findAllByFriendly(Boolean friendly) {
        return npcRepository.findAllByFriendly(friendly);
    }

    public List<NPCEntity> findAll() {
        return npcRepository.findAll();
    }
}