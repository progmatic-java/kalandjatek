package hu.progmatic.adventuregame.npc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class NPCService {
    @Autowired
    private NPCRepository npcRepository;

    public NPC save(NPC npc) {
        return npcRepository.save(npc);
    }

    public NPC getById(Integer id) {
        return npcRepository.getById(id);
    }

    public NPCDto getNPCDtoById(Integer id) {
        NPC entity = getById(id);
        return NPCDto.builder()
                .name(entity.getName())
                .id(entity.getId())
                .description(entity.getDescription())
                .friendly(entity.getFriendly())
                .hp(entity.getHp())
                .mp(entity.getMp())
                .gold(entity.getGold())
                .imgRef(entity.getImgRef())
                .build();
    }

    public void delete(Integer id) {
        npcRepository.deleteById(id);
    }

    public void saveAll(List<NPC> list) {
        npcRepository.saveAllAndFlush(list);
    }

    public NPC findByName(String name) {
        return npcRepository.findByName(name).orElseThrow();
    }

    public List<NPC> findAllByFriendly(Boolean friendly) {
        return npcRepository.findAllByFriendly(friendly);
    }

    public List<NPC> findAll() {
        return npcRepository.findAll();
    }
}