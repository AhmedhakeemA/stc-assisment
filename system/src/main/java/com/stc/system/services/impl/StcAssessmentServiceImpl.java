package com.stc.system.services.impl;

import com.stc.system.dtos.CreateRequest;
import com.stc.system.dtos.CreateResponse;
import com.stc.system.dtos.FileMetaDataResponse;
import com.stc.system.dtos.ItemPermissionDTO;
import com.stc.system.enums.ItemTypeEnum;
import com.stc.system.exceptions.MissingRequestParamValueException;
import com.stc.system.exceptions.RequestValidationException;
import com.stc.system.exceptions.ResourceNotFoundException;
import com.stc.system.exceptions.UserNotAuthorizedException;
import com.stc.system.models.*;
import com.stc.system.repo.*;
import com.stc.system.services.StcAssessmentService;
import com.stc.system.services.StorageService;
import com.stc.system.util.FileMetaDataMapper;
import com.stc.system.util.ItemPermissionMapper;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AhmedHakeem
 */

@Service
public class StcAssessmentServiceImpl implements StcAssessmentService {

    @Autowired
    PermissionGroupRepository permissionGroupRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    ItemTypeRepository itemTypeRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    StorageService storageService;

    @Autowired
    ItemPermissionMapper itemPermissionMapper;

    @Autowired
    FileMetaDataMapper fileMetaDataMapper;



    @Override
    public CreateResponse createNewItem(CreateRequest createRequest) {

        Item parent = null;

        CreateResponse response = null;

        List<ItemPermissionDTO> userItemPermissions = new ArrayList<>();

        //-------------- Generic Validation
        if (createRequest.name() == null || "".equalsIgnoreCase(createRequest.name())) {
            throw new MissingRequestParamValueException("Space Name Is Required");
        }

        if (createRequest.userEmail() == null || "".equalsIgnoreCase(createRequest.userEmail())) {
            throw new MissingRequestParamValueException("userEmail Is Required");
        }

        if (!itemRepository.findByName(createRequest.name()).isEmpty()) {
            throw new RequestValidationException("Space Name Already Exists");
        }

        ItemType itemType = itemTypeRepository.findByName(createRequest.typeName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot Find Type With Name:  %s", createRequest.name())));


        //-------------- DependOnType Validation

        if ((itemType.getName().equalsIgnoreCase(ItemTypeEnum.FOLDER.name()) || itemType.getName().equalsIgnoreCase(ItemTypeEnum.FILE.name()))
                && createRequest.parentId() == null) {
            throw new MissingRequestParamValueException("ParentId Is Required");
        }


        if ((itemType.getName().equalsIgnoreCase(ItemTypeEnum.FOLDER.name()) || itemType.getName().equalsIgnoreCase(ItemTypeEnum.FILE.name()))
                && createRequest.parentId() != null) {
            parent = itemRepository.findById(createRequest.parentId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot Find Parent With Id:  %s", createRequest.parentId())));

            userItemPermissions = findPermissionsByUserEmailAndItemId(createRequest.userEmail(), createRequest.parentId(), "EDIT");

            if (userItemPermissions.isEmpty()) {
                throw new UserNotAuthorizedException(String.format("UnAuthorized Access for  :  %s", createRequest.userEmail()));
            }
        }


        if (itemType.getName().equalsIgnoreCase(ItemTypeEnum.FILE.name()) && createRequest.file() == null) {
            throw new MissingRequestParamValueException("File Is Required");
        }



        PermissionGroup permissionGroup = permissionGroupRepository.getPermissionGroupByPermissionUserEmail(createRequest.userEmail())
                .orElseThrow(() -> new UserNotAuthorizedException(String.format("UnAuthorized Access for  :  %s", createRequest.userEmail())));


        if (itemType.getName().equals(ItemTypeEnum.SPACE.name())) {
            response = createNewSpace(createRequest, itemType, permissionGroup);
        }
        if (itemType.getName().equals(ItemTypeEnum.FOLDER.name())) {
            response = createNewFolder(createRequest, itemType, permissionGroup, parent);
        }
        if (itemType.getName().equals(ItemTypeEnum.FILE.name())) {
            response = createNewFile(createRequest, itemType, permissionGroup, parent);
        }


        return response;


    }


    @Override
    public File getFileDetails(Long fileId,String userEmail)
    {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot Find File With Id:  %s", fileId)));

        List<ItemPermissionDTO> permissionDTOS = findPermissionsByUserEmailAndItemIdAndFolderId(userEmail,file.getItem().getId(),fileId,"EDIT");
        if(permissionDTOS.isEmpty()){
            throw new UserNotAuthorizedException(String.format("UnAuthorized Access for File :  %s", fileId));
        }
        return file;
    }

    @Override
    public FileMetaDataResponse getFileMetaData(Long fileId, String userEmail) {

        List<FileMetaDataResponse>  fileMetaDataResponseList =  findFileMetaDataByFileId(fileId);

        if(fileMetaDataResponseList.isEmpty())
        {
           throw  new ResourceNotFoundException(String.format("Cannot Find File With Id:  %s", fileId));
        }

        FileMetaDataResponse fileMetaDataResponse = fileMetaDataResponseList.get(0);

        List<ItemPermissionDTO> permissionDTOS = findPermissionsByUserEmailAndItemIdAndFolderId(userEmail,fileMetaDataResponse.itemId(),fileMetaDataResponse.fileId(),"EDIT");
        if(permissionDTOS.isEmpty()){
            throw new UserNotAuthorizedException(String.format("UnAuthorized Access for File :  %s", fileId));
        }
        return fileMetaDataResponse;
    }

    @Override
    public Iterable<File> getDataForGraphQl() {
        return fileRepository.findAll();
    }


    public CreateResponse createNewSpace(CreateRequest createRequest, ItemType itemType, PermissionGroup permissionGroup) {
        Item space = new Item();
        space.setName(createRequest.name());
        space.setType(itemType);
        space.setPermissionGroup(permissionGroup);
        Item savedItem = itemRepository.save(space);
        return new CreateResponse(savedItem.getId(), savedItem.getName(), savedItem.getType().getName());
    }

    public CreateResponse createNewFolder(CreateRequest createRequest, ItemType itemType, PermissionGroup permissionGroup, Item parent) {
        Item savedItem = generateAndSaveNewItemFromRequest(createRequest, itemType, permissionGroup, parent);
        return new CreateResponse(savedItem.getId(), savedItem.getName(), savedItem.getType().getName());
    }

    public CreateResponse createNewFile(CreateRequest createRequest, ItemType itemType, PermissionGroup permissionGroup, Item parent) {
        Item savedItem = generateAndSaveNewItemFromRequest(createRequest, itemType, permissionGroup, parent);
        generateAndSaveFile(createRequest, savedItem);
        return new CreateResponse(savedItem.getId(), savedItem.getName(), savedItem.getType().getName());
    }

    public Item generateAndSaveNewItemFromRequest(CreateRequest createRequest, ItemType itemType, PermissionGroup permissionGroup, Item parent) {
        Item space = new Item();
        space.setName(createRequest.name());
        space.setType(itemType);
        space.setPermissionGroup(permissionGroup);
        space.setParent(parent);
        return itemRepository.save(space);
    }

    public void generateAndSaveFile(CreateRequest createRequest, Item savedItem) {
        byte[] bytes = storageService.getFileBytes(createRequest.file());
        File file = new File();
        file.setData(bytes);
        file.setItem(savedItem);
        fileRepository.save(file);
    }

    public List<ItemPermissionDTO> findPermissionsByUserEmailAndItemId(String userEmail, Long itemId, String permissionsLevel) {
        List<Tuple> itemPermissionTuples = itemRepository.findPermissionsByUserEmailAndItemId(userEmail, itemId, permissionsLevel);
        List<ItemPermissionDTO> itemPermissionDTOS = itemPermissionTuples.stream()
                .map(itemPermissionMapper)
                .collect(Collectors.toList());
        return itemPermissionDTOS;
    }

    public List<ItemPermissionDTO> findPermissionsByUserEmailAndItemIdAndFolderId(String userEmail, Long itemId,Long fileId, String permissionsLevel) {
        List<Tuple> itemPermissionTuples = itemRepository.findPermissionsByUserEmailAndItemIdAndFolderId(userEmail, itemId,fileId, permissionsLevel);
        List<ItemPermissionDTO> itemPermissionDTOS = itemPermissionTuples.stream()
                .map(itemPermissionMapper)
                .collect(Collectors.toList());
        return itemPermissionDTOS;
    }


    public List<FileMetaDataResponse> findFileMetaDataByFileId(Long fileId) {
        List<Tuple> fileMetaDataTuples = itemRepository.findFileMetaDataByFileId(fileId);
        List<FileMetaDataResponse> fileMetaDataDTOS = fileMetaDataTuples.stream()
                .map(fileMetaDataMapper)
                .collect(Collectors.toList());
        return fileMetaDataDTOS;
    }


}
