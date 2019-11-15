package com.gara.sericefeign.req;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-11-14 15:44
 * @Version: 1.0
 **/
public class FileDesc {

    private Long fileId;

    private Integer fileType;

    private String fileNick;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileNick() {
        return fileNick;
    }

    public void setFileNick(String fileNick) {
        this.fileNick = fileNick;
    }

    @Override
    public String toString() {
        return "FileDesc{" +
                "fileId=" + fileId +
                ", fileType=" + fileType +
                ", fileNick='" + fileNick + '\'' +
                '}';
    }
}
