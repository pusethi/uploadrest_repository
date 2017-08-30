package com.psethi.api.entity.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.psethi.api.entity.model.FileMetaData;

/**
 * Repository class for FileMetaData entity
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@Transactional
@Repository
public interface FileMetaDataRepository extends JpaRepository<FileMetaData, Long> {

    /**
     * Method to perform save and flush operation
     */
    FileMetaData saveAndFlush(FileMetaData fileMetaData);
    
    /**
     * Method to find file meta data record by filename
     */
    FileMetaData findByFileName(String fileName);
    
    /**
     * Get all file meta data records
     */
    List<FileMetaData> findAll();
    
}
