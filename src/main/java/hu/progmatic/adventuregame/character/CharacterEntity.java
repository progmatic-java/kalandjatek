package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.Inventory;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  @Enumerated(EnumType.STRING)
  Race race;
  private String description;
  private Integer maxHp;
  private Integer maxMp;
  private Integer currMp;
  private Integer currHp;
  private Integer gold;
  private Integer attack;
  private Integer defence;
  private String imgRef;
  private String indexImg;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  Answer answer;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  @Builder.Default
  Inventory inventory = new Inventory();
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  @Builder.Default
  Inventory activeInventory = new Inventory();

}
