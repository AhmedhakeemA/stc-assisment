package com.stc.system.services.impl;

import com.stc.system.services.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author AhmedHakeem on 5/12/2023
 */

@Service
public class StorageServiceImpl implements StorageService {

    @Override
    public byte[] getFileBytes(MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }

}
