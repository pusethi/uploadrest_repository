package com.psethi.api.entity.mapper;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.psethi.api.FileMetaDataDto;
import com.psethi.api.entity.model.FileMetaData;

/**
 * Model Mapper class for FileMetaData entity
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@Component
public class FileMetaDataMapper {
    
    @Inject 
    ModelMapper modelMapper;
     
    /**
     * Method to convert from FileMetaData Object to FileMetaDataDto Object
     * 
     * @param fetchedFileMetaData
     * @return fileMetaDataDto
     */
    public FileMetaDataDto convertToFileMetaDataDto(FileMetaData fetchedFileMetaData) {
        FileMetaDataDto fileMetaDataDto = null;
        if(fetchedFileMetaData != null) {
            fileMetaDataDto = modelMapper.map(fetchedFileMetaData, FileMetaDataDto.class);  
        }        
        return fileMetaDataDto;
    }
    
    /**
     * Method to convert from List of FileMetaData Object to List of FileMetaDataDto Object
     * 
     * @param fetchedFileMetaDataList
     * @return FileMetaDataDto List
     */
    public List<FileMetaDataDto> convertToFileMetaDataListDto(List<FileMetaData> fetchedFileMetaDataList) {
        List<FileMetaDataDto> fileMetaDataList = null;
        if(CollectionUtils.isNotEmpty(fetchedFileMetaDataList)) {
            Type listType = new TypeToken<List<FileMetaDataDto>>() {}.getType();
            fileMetaDataList = modelMapper.map(fetchedFileMetaDataList, listType); 
        }                
        return fileMetaDataList;
    }

}
