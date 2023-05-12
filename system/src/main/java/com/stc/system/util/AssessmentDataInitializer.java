package com.stc.system.util;

import com.stc.system.models.ItemType;
import com.stc.system.models.Permission;
import com.stc.system.models.PermissionGroup;
import com.stc.system.repo.ItemRepository;
import com.stc.system.repo.ItemTypeRepository;
import com.stc.system.repo.PermissionGroupRepository;
import com.stc.system.repo.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AhmedHakeem on 5/12/2023
 */

@Component
public class AssessmentDataInitializer implements CommandLineRunner {

    @Autowired
    PermissionGroupRepository permissionGroupRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    ItemTypeRepository itemTypeRepository;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public void run(String... args) throws Exception {

        // Init Types
        if (itemTypeRepository.findAll().isEmpty()){
            List<ItemType> initTypesList =
                    List.of(new ItemType(null,"SPACE"),
                            new ItemType(null,"FOLDER"),new ItemType(null,"FILE"));
            itemTypeRepository.saveAll(initTypesList).forEach(System.out::println);
        }


        if (permissionGroupRepository.findAll().isEmpty()) {

            //Create Group
            PermissionGroup group = new PermissionGroup();
            group.setGroupName("admin");
            PermissionGroup savedGroup = permissionGroupRepository.save(group);

            //Create First Permission
            Permission permissionView = new Permission();
            permissionView.setPermissionLevel("VIEW");
            permissionView.setUserEmail("view.user@stc.com");
            permissionView.setGroup(savedGroup);
            permissionRepository.save(permissionView);


            //Create Second Permission
            Permission permissionEdit = new Permission();
            permissionEdit.setPermissionLevel("EDIT");
            permissionEdit.setUserEmail("edit.user@stc.com");
            permissionEdit.setGroup(savedGroup);
            permissionRepository.save(permissionEdit);
        }




    }
}
