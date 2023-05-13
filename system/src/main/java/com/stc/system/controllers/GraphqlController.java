package com.stc.system.controllers;

import com.stc.system.dtos.FileMetaDataResponse;
import com.stc.system.models.File;
import com.stc.system.models.Item;
import com.stc.system.repo.FileRepository;
import com.stc.system.repo.ItemRepository;
import com.stc.system.services.StcAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 * @author AhmedHakeem
 */


@Controller
public class GraphqlController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    StcAssessmentService stcAssessmentService;

    @QueryMapping
    public Iterable<File> files()
    {
        return stcAssessmentService.getDataForGraphQl();
    }

    @QueryMapping
    public FileMetaDataResponse getFileMetaDataById(@Argument Long fileId, @Argument String userEmail)
    {
        return stcAssessmentService.getFileMetaData(fileId,userEmail);
    }


}
