package com.stc.system.services;

import com.stc.system.dtos.CreateRequest;
import com.stc.system.dtos.CreateResponse;
import com.stc.system.dtos.FileMetaDataResponse;
import com.stc.system.models.File;

/**
 * @author AhmedHakeem on 5/12/2023
 */
public interface StcAssessmentService {
    public CreateResponse createNewItem(CreateRequest createRequest);
    public File getFileDetails(Long id,String userEmail);

    public FileMetaDataResponse getFileMetaData(Long id, String userEmail);

}
