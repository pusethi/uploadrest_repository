package com.psethi.api.entity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity class for FILE_META_DATA table primary key - file_id
 *
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */

@Entity
@Table(name = "FILE_META_DATA")
public class FileMetaData implements Comparable<FileMetaData>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id", nullable = false)
    private Long id;

    @Column(name = "description", length = 100)
    private String description;
    
    @Column(name = "author_name", length = 40)
    private String authorName;
    
    @Column(name = "file_name", length = 40, nullable = false, unique = true)
    private String fileName;
    
    @Column(name = "upload_timeStamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date uploadTimeStamp;

    /**
     * Default Constructor
     */
    public FileMetaData() {
        super();
    }
    
    
    /**
     * Constructor with fields.
     * 
     * @param description
     * @param authorName
     * @param fileName
     * @param uploadTimeStamp
     */
    public FileMetaData(String description, String authorName, String fileName, Date uploadTimeStamp) {
        super();
        this.description = description;
        this.authorName = authorName;
        this.fileName = fileName;
        this.uploadTimeStamp = uploadTimeStamp;
    }

    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }


    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * @return the uploadTimeStamp
     */
    public Date getUploadTimeStamp() {
        return uploadTimeStamp;
    }


    /**
     * @param uploadTimeStamp the uploadTimeStamp to set
     */
    public void setUploadTimeStamp(Date uploadTimeStamp) {
        this.uploadTimeStamp = uploadTimeStamp;
    }


    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(FileMetaData other) {
        return new CompareToBuilder().append(this.getId(), other.getId())
                .append(this.getFileName(), other.getFileName()).toComparison();
    }

}
