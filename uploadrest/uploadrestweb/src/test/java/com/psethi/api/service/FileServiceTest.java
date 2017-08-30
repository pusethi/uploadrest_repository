package com.psethi.api.service;

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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.psethi.api.FileMetaDataDto;
import com.psethi.api.configuration.RepositoryConfigurationTest;

/**
 * Junit for File Service Class
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryConfigurationTest.class })
public class FileServiceTest {

    @Inject
    private FileService fileService;
    
    /**
     * Junit to test the store method
     * 
     * @throws IOException 
     */
    @Test
    public void storeTest() throws IOException {
        String description = "Attached resume to test upload";
        String authorName = "Puneet";
        String fileName = "resume";
        Path resourceDirectory = Paths.get("src/test/resources/testFile/Sample.txt");
        FileInputStream inputFile = new FileInputStream(resourceDirectory.toString());  
        MockMultipartFile multiPartfile = new MockMultipartFile("newfile", "Sample.txt", "multipart/form-data", inputFile);
        FileMetaDataDto fileMetaDataDto = fileService.store(description, authorName, fileName, multiPartfile);
        Assert.assertNotNull(fileMetaDataDto);
        Assert.assertEquals("Puneet", fileMetaDataDto.getAuthorName());
        Assert.assertEquals("resume", fileMetaDataDto.getFileName());
        Assert.assertEquals("Attached resume to test upload", fileMetaDataDto.getDescription());
    }
    
    
    /**
     * Junit test case to get all records from File_meta_data table
     * @throws IOException 
     */
    @Test
    public void getAllFileMetaDataRecordsTest() throws IOException {
        Path resourceDirectory = Paths.get("src/test/resources/testFile/Sample.txt");
        FileInputStream inputFile = new FileInputStream(resourceDirectory.toString());  
        MockMultipartFile multiPartfile = new MockMultipartFile("newfile", "Sample.txt", "multipart/form-data", inputFile);
        fileService.store("Attached resume to test upload", "Puneet", "resume", multiPartfile);
        List<FileMetaDataDto> fileMetaDataDtoList = fileService.getAllFileMetaDataRecords();
        Assert.assertNotNull(fileMetaDataDtoList);
        //Assert.assertEquals(1, fileMetaDataDtoList.size());
    }
    
}
