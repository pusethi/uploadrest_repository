package com.psethi.api.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psethi.api.FileMetaDataDto;
import com.psethi.api.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for managing File (CRUD Operations)
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */

@Component
@Api(value = "/file", description = "Controller for managing File (CRUD Operations)")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private FileService fileService;

    /**
     * Webservice to upload multipart file and store meta data fields.
     * 
     * @param description
     * @param authorName
     * @param fileName
     * @param multiPartFile
     * @return ResponseEntity
     * @throws IOException
     * @throws Exception
     */
    @ApiResponses({ @ApiResponse(code = 202, message = "Import Successful"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @ApiOperation("Accepts a multipart file upload")
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileMetaDataDto> uploadFileAndPersistMetaData(
            @ApiParam("File Description") @RequestParam(value = "description", required = false) String description,
            @ApiParam("File Author Name") @RequestParam(value = "authorName", required = false) String authorName,
            @ApiParam("File Name") @RequestParam(value = "fileName", required = true) String fileName,
            @ApiParam("File to be uploaded") @RequestParam(value = "file", required = true) MultipartFile multiPartFile) throws IOException {
        logger.info("******Begin uploadFileAndPersistMetaData********");
        HttpStatus status = HttpStatus.ACCEPTED;
        FileMetaDataDto fileMetaDataDto = null;
        if (multiPartFile != null && fileName != null) {
            fileMetaDataDto = fileService.store(description, authorName, fileName, multiPartFile);
            if (fileMetaDataDto == null) {
                throw new NullPointerException("Error occured - Not able to insert record! : ");
            }
        } else {
            throw new IOException("Error occured - FileName and File Attachment are required.");
        }
        return new ResponseEntity<FileMetaDataDto>(fileMetaDataDto, status);
    }

    /**
     * Webservice to get all records from the database.
     * 
     * @throws IOException
     */
    @ApiResponses({ @ApiResponse(code = 200, message = "Get all file meta data records Successful"),
            @ApiResponse(code = 404, message = "No file meta data records Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @ApiOperation("Get All File Meta Data Information")
    @RequestMapping(value = "/uploadfile/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileMetaDataDto>> getAllFileMetaDataRecords() {
        HttpStatus status = HttpStatus.OK;
        List<FileMetaDataDto> fileMetaDataRecords = fileService.getAllFileMetaDataRecords();
        if (fileMetaDataRecords == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<FileMetaDataDto>>(fileMetaDataRecords, status);
    }
}
