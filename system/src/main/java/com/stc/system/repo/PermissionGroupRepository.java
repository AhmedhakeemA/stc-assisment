package com.stc.system.repo;

import com.stc.system.models.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author AhmedHakeem
 */
@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup,Long> {

    @Query(value = "select pg.* from premission_groups pg join premissions p  on p.group_id  = pg.id where p.user_email =:userEmail limit 1",nativeQuery = true)
    public Optional<PermissionGroup> getPermissionGroupByPermissionUserEmail(@Param("userEmail") String userEmail);
}
