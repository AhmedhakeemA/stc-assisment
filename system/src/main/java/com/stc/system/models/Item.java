package com.stc.system.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

/**
 * @author AhmedHakeem
 */
@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type")
    private ItemType type;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id")
    @JsonManagedReference
    private PermissionGroup permissionGroup;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    private Item parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Item> children;

}
