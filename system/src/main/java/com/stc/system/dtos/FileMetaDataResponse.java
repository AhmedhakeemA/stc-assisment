package com.stc.system.dtos;

/**
 * @author AhmedHakeem on 5/12/2023
 */
public record FileMetaDataResponse(Long itemId, Long fileId, Long parentFolderId, String itemName,String type,String groupName,String parentItemName) {
}
