package com.pos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_role", schema = "public")
public class MstRole {
    @Id
    @SequenceGenerator(name = "mst_role_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_role_seq")
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;
}
