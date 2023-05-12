package com.stc.system.repo;

import com.stc.system.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author AhmedHakeem
 */
@Repository
public interface FileRepository extends JpaRepository<File,Long> {
}
