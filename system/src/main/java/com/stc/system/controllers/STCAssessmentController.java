package com.stc.system.controllers;

import com.stc.system.dtos.CreateRequest;
import com.stc.system.dtos.CreateResponse;
import com.stc.system.dtos.FileMetaDataResponse;
import com.stc.system.enums.ItemTypeEnum;
import com.stc.system.models.File;
import com.stc.system.services.StcAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author AhmedHakeem
 */

@RestController
@RequestMapping("/stc-assessment")
public class STCAssessmentController {

    @Autowired
    StcAssessmentService stcAssessmentService;


    @PostMapping("space")
    public ResponseEntity<CreateResponse> createNewSpace(@RequestParam("userEmail") String userEmail,@RequestParam("name") String name)
    {
        CreateRequest createRequest=new CreateRequest(userEmail,name,ItemTypeEnum.SPACE.name(),null,null);
        CreateResponse newSpace = stcAssessmentService.createNewItem(createRequest);
        return new ResponseEntity<>(newSpace, HttpStatus.CREATED);
    }

    @PostMapping("folder")
    public ResponseEntity<CreateResponse> createNewFolder(@RequestParam("userEmail") String userEmail,@RequestParam("name") String name,@RequestParam("parentId") Long parentId)
    {
        CreateRequest createRequest=new CreateRequest(userEmail,name,ItemTypeEnum.FOLDER.name(),parentId,null);
        CreateResponse newSpace = stcAssessmentService.createNewItem(createRequest);
        return new ResponseEntity<>(newSpace, HttpStatus.CREATED);
    }

    @PostMapping("file")
    public ResponseEntity<CreateResponse> createNewFile(@RequestParam("userEmail") String userEmail,@RequestParam("parentId") Long parentId,@RequestParam("file") MultipartFile file)
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        CreateRequest createRequest=new CreateRequest(userEmail,fileName, ItemTypeEnum.FILE.name(),parentId,file);
        CreateResponse newSpace = stcAssessmentService.createNewItem(createRequest);
        return new ResponseEntity<>(newSpace, HttpStatus.CREATED);
    }


    @GetMapping("/download/{fileId}/{user-email}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileId") Long fileId,@PathVariable("user-email") String userEmail){
        File file = stcAssessmentService.getFileDetails(fileId,userEmail);
        byte[] bytes = file.getData();
        String fileName = file.getItem().getName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

    @GetMapping("/meta/{fileId}/{user-email}")
    public ResponseEntity<FileMetaDataResponse> getFileMetadata(@PathVariable("fileId") Long fileId, @PathVariable("user-email") String userEmail){
        FileMetaDataResponse fileMetaDataResponse = stcAssessmentService.getFileMetaData(fileId, userEmail);
        return new ResponseEntity<>(fileMetaDataResponse, HttpStatus.OK);
    }


}
