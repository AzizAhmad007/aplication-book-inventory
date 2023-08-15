package com.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "mst_item", schema = "public")
public class MstItem {
    @Id
    @SequenceGenerator(name = "mst_item_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_item_seq")
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

}
