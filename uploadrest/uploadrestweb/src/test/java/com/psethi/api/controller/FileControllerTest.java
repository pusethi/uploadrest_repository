package com.psethi.api.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.psethi.api.FileMetaDataDto;
import com.psethi.api.configuration.RepositoryConfigurationTest;
import com.psethi.api.controller.FileController;

/**
 * Junit for File Controller
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@PropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryConfigurationTest.class })
public class FileControllerTest {

    @Inject
    private FileController fileController;
    
    /**
     * Junit to test uploadFileAndPersistMetaData service
     * 
     * @throws IOException 
     */
    @Test
    public void uploadFileAndPersistMetaDataTest() throws IOException {
        String description = "Attached resume to test upload";
        String authorName = "Puneet";
        String fileName = "resume";
        ResponseEntity<FileMetaDataDto> resp = null;
        Path resourceDirectory = Paths.get("src/test/resources/testFile/Sample.txt");
        FileInputStream inputFile = new FileInputStream(resourceDirectory.toString());  
        MockMultipartFile multiPartfile = new MockMultipartFile("newfile", "Sample.txt", "multipart/form-data", inputFile);
        try {
            resp = fileController.uploadFileAndPersistMetaData(description, authorName, fileName, multiPartfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(resp);    
        Assert.assertEquals(HttpStatus.ACCEPTED, resp.getStatusCode());  
        Assert.assertEquals("Puneet", resp.getBody().getAuthorName());
        Assert.assertEquals("resume", resp.getBody().getFileName());
        Assert.assertEquals("Attached resume to test upload", resp.getBody().getDescription());
    }
    
    /**
     * Junit to get all FileMetaData Records service
     * @throws IOException 
     */
    @Test
    public void getAllFileMetaDataRecordsTest() throws IOException {
        String description = "Attached resume to test upload";
        String authorName = "Puneet";
        String fileName = "resume";
        Path resourceDirectory = Paths.get("src/test/resources/testFile/Sample.txt");
        FileInputStream inputFile = new FileInputStream(resourceDirectory.toString());  
        MockMultipartFile multiPartfile = new MockMultipartFile("newfile", "Sample.txt", "multipart/form-data", inputFile);
        fileController.uploadFileAndPersistMetaData(description, authorName, fileName, multiPartfile);
        ResponseEntity<List<FileMetaDataDto>> resp = fileController.getAllFileMetaDataRecords();
        Assert.assertNotNull(resp);    
        Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());      
    }
    
}
