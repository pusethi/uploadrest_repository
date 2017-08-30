package com.psethi.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.psethi.api.FileMetaDataDto;
import com.psethi.api.entity.mapper.FileMetaDataMapper;
import com.psethi.api.entity.model.FileMetaData;
import com.psethi.api.entity.repository.FileMetaDataRepository;
import com.psethi.api.util.DateUtil;

/**
 * Service class for File management related operations
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@PropertySource("classpath:application.properties")
@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private Environment env;

    @Inject
    private FileMetaDataRepository fileMetaDataRepository;

    @Inject
    private FileMetaDataMapper fileMetaDataMapper;

    public static final String UPLOADED_FILE_LOCATION = "multipart.location";

    /**
     * Service Method to perform store the file and meta data operation
     * 
     * @param description
     * @param authorName
     * @param fileName
     * @param multiPartFile
     * @return FileMetaDataDto obj
     * @throws IOException
     */
    public FileMetaDataDto store(String description, String authorName, String fileName, MultipartFile multiPartFile)
            throws IOException {
        logger.info("Begin store operation");
        int newFileFlag = 1;
        FileMetaDataDto fileMetaDataDto = null;
        if (multiPartFile != null) {
            byte[] bytes = multiPartFile.getBytes();
            Path filePath = Paths
                    .get(env.getRequiredProperty(UPLOADED_FILE_LOCATION) + multiPartFile.getOriginalFilename());
            File directory = new File(env.getRequiredProperty(UPLOADED_FILE_LOCATION));
            if (!directory.exists()) {
                directory.mkdir();
            } 
            if(new File(filePath.toString()).exists()) {
                newFileFlag = 0;
            }
            filePath = Files.write(filePath, bytes);
            if (filePath != null) {
                FileMetaData fileMetaData = null;
                FileMetaData newFileMetaData = null;
                if (newFileFlag == 0) {
                    fileMetaData = fileMetaDataRepository.findByFileName(fileName);
                }  
                    if(fileMetaData != null) {
                        fileMetaData.setAuthorName(authorName);
                        fileMetaData.setDescription(description);
                        fileMetaData.setUploadTimeStamp(DateUtil.getCurrentDate()); 
                    } else {
                        fileMetaData = new FileMetaData(description, authorName, fileName, DateUtil.getCurrentDate());  
                    }
                    

                if(fileMetaData != null) {
                    newFileMetaData = fileMetaDataRepository.saveAndFlush(fileMetaData);  
                }
                
                if (newFileMetaData != null) {
                    fileMetaDataDto = fileMetaDataMapper.convertToFileMetaDataDto(newFileMetaData);
                }
            }
        }
        return fileMetaDataDto;
    }

    /**
     * Service to get all records from the database.
     * 
     * @return FileMetaDataDto List
     */
    public List<FileMetaDataDto> getAllFileMetaDataRecords() {
        List<FileMetaData> fetchedFileMetaDataList = fileMetaDataRepository.findAll();
        List<FileMetaDataDto> fileMetaDataList = null;
        if (fetchedFileMetaDataList != null) {
            fileMetaDataList = fileMetaDataMapper.convertToFileMetaDataListDto(fetchedFileMetaDataList);
        }
        return fileMetaDataList;
    }
}