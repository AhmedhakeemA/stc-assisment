package com.stc.system.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author AhmedHakeem
 */

@Entity
@Table(name = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_binary")
    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;
}
