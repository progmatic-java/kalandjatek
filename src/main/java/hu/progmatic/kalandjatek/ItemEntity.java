package hu.progmatic.kalandjatek;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemName;
    private String description;
    @Enumerated(EnumType.STRING)
    private ItemEnum typeOfItem;
    @ManyToOne
    private InventoryEntity inventory;

}
