package com.psethi.api.entity.repository;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.psethi.api.configuration.RepositoryConfigurationTest;
import com.psethi.api.entity.model.FileMetaData;
import com.psethi.api.util.DateUtil;

/**
 * Junit for Repository class for FileMetaData entity
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryConfigurationTest.class })
public class FileMetaDataRepositoryTest {

    @Inject
    private FileMetaDataRepository fileMetaDataRepository;
    
    /**
     * Junit to test saveAndFlush method
     */
    @Test
    public void saveAndFlushTest() {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setAuthorName("Puneet");
        fileMetaData.setFileName("resume4");
        fileMetaData.setDescription("Attached resume to test upload");
        fileMetaData.setUploadTimeStamp(DateUtil.getCurrentDate());
        FileMetaData newRecord = fileMetaDataRepository.saveAndFlush(fileMetaData);
        Assert.assertNotNull(newRecord);
        Assert.assertEquals("Puneet", newRecord.getAuthorName());
        Assert.assertEquals("Attached resume to test upload", newRecord.getDescription());        
    }
    
    /**
     * Junit test case to get work List Info Using work List Name
     */
    @Test 
    public void findByFileNameTest() {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setAuthorName("Puneet");
        fileMetaData.setFileName("resume1");
        fileMetaData.setDescription("Attached resume to test upload");
        fileMetaData.setUploadTimeStamp(DateUtil.getCurrentDate());
        fileMetaDataRepository.saveAndFlush(fileMetaData);
        FileMetaData fetchedRecord = fileMetaDataRepository.findByFileName("resume1"); 
        Assert.assertNotNull(fetchedRecord);
        Assert.assertEquals("Puneet", fetchedRecord.getAuthorName());
        Assert.assertEquals("resume1", fetchedRecord.getFileName());
        Assert.assertEquals("Attached resume to test upload", fetchedRecord.getDescription());     
    }
    
    /**
     * Junit test case to get all records from File_meta_data table
     */
    @Test
    public void getAllRecordsTest() {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setAuthorName("Puneet");
        fileMetaData.setFileName("resum2");
        fileMetaData.setDescription("Attached resume to test upload");
        fileMetaData.setUploadTimeStamp(DateUtil.getCurrentDate());
        fileMetaDataRepository.saveAndFlush(fileMetaData);
        fileMetaData = new FileMetaData();
        fileMetaData.setAuthorName("Puneet1");
        fileMetaData.setFileName("resume3");
        fileMetaData.setDescription("Attached resume to test upload1");
        fileMetaData.setUploadTimeStamp(DateUtil.getCurrentDate());
        fileMetaDataRepository.saveAndFlush(fileMetaData);
        List<FileMetaData> fileMetaDataList = fileMetaDataRepository.findAll();
        Assert.assertNotNull(fileMetaDataList);
        //Assert.assertEquals(2, fileMetaDataList.size());
    } 
    

}
