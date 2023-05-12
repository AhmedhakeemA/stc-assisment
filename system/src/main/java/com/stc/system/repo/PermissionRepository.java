package com.stc.system.repo;

import com.stc.system.models.Permission;
import com.stc.system.models.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author AhmedHakeem
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    public Optional<Permission> findPermissionByUserEmail(String userEmail);

    public Optional<List<Permission>> getPermissionsByUserEmail(String userEmail);

    public List<Permission> findPermissionByGroupIs(PermissionGroup group);
}
