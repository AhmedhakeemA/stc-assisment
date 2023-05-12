package com.stc.system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author AhmedHakeem
 */

@Entity
@Table(name = "item_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
