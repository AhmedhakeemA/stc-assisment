package com.stc.system.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author AhmedHakeem on 5/12/2023
 */
public interface StorageService {

    public byte[] getFileBytes(MultipartFile file);
}
