package vehicle.info.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
/**
 * Entity representing the config files
 */
public class VehicleConfig {

    /**
    * File name
    */
    private String fileName;

    /**
    * File type (mime)
    */
    private String fileType;

    /**
    * File size in bytes
    */
    private long fileSize;

    /**
    * File extension without dot
    */
    private String fileExt;

    @JsonCreator
    public VehicleConfig(@JsonProperty("fileName") String fileName,
                         @JsonProperty("fileType") String fileType,
                         @JsonProperty("fileSize") long fileSize,
                         @JsonProperty("fileExt") String fileExt
                         ) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileExt = fileExt;
    }

    @NotBlank
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @NotBlank
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @NotBlank
    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @NotBlank
    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    @Override
    public String toString() {
        return "VehicleConfig { " +
                "fileName='" + fileName + "', " +
                "fileType='" + fileType + "', " +
                "fileSize='" + fileSize + "', " +
                "fileExt='" + fileExt + "' " +
                "}";
    }
}
