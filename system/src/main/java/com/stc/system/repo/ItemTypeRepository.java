package com.stc.system.repo;

import com.stc.system.models.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author AhmedHakeem
 */
@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType,Long> {
    public Optional<ItemType> findByName(String name);
}
