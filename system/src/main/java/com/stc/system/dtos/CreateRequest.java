package com.stc.system.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author AhmedHakeem on 5/12/2023
 */
public record CreateRequest(String userEmail, String name, String typeName, Long parentId, MultipartFile file) {
}
