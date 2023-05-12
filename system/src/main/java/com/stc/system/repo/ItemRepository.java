package com.stc.system.repo;

import com.stc.system.dtos.ItemPermissionDTO;
import com.stc.system.models.Item;
import com.stc.system.models.ItemType;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author AhmedHakeem
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {


    public Optional<Item> findByName(String name);

    @Query(value = "select i.id as itemId,p.permission_level as permissionLevel,pg.id as groupId from items i \n" +
            "join premission_groups pg on pg.id=i.permission_group_id \n" +
            "join premissions p on p.group_id = pg.id \n" +
            "where p.user_email =:userEmail and i.id =:itemId and p.permission_level =:permissionsLevel",nativeQuery = true)
    public List<Tuple> findPermissionsByUserEmailAndItemId(@Param("userEmail") String userEmail, @Param("itemId") Long itemId, @Param("permissionsLevel") String permissionsLevel);


    @Query(value = "select i.id as itemId,p.permission_level as permissionLevel,pg.id as groupId from items i \n" +
            "join premission_groups pg on pg.id=i.permission_group_id \n" +
            "join premissions p on p.group_id = pg.id \n" +
            "join file f on f.item_id =i.id \n" +
            "where p.user_email =:userEmail and i.id =:itemId and  f.id =:fileId and   p.permission_level =:permissionsLevel",nativeQuery = true)
    public List<Tuple> findPermissionsByUserEmailAndItemIdAndFolderId(@Param("userEmail") String userEmail, @Param("itemId") Long itemId, @Param("fileId") Long fileId, @Param("permissionsLevel") String permissionsLevel);


    @Query(value = "select i.id as itemId,f.id as fileId,p.id parentFolderId, i.name as itemName ,it.name as type,pg.group_name  as groupName,p.name AS parentItemName\n" +
            "from items i \n" +
            "join item_type it on i.type=it.id\n" +
            "join premission_groups pg on pg.id=i.permission_group_id\n" +
            "join file f on f.item_id =i.id\n" +
            "LEFT JOIN items p ON i.parent_id = p.id\n" +
            "where it.name='FILE' and f.id =:fileId",nativeQuery = true)
    public  List<Tuple> findFileMetaDataByFileId(@Param("fileId") Long fileId);


}
