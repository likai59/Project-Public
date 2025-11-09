package com.gtalent.project_sb_aws_s3.service;

import com.gtalent.project_sb_aws_s3.file.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String createBucket(String bucketname);
    List<String> getBucketList();
    String fileUpload(String bucketName, MultipartFile file);
    File fileDownload(String bucketName, String fileName);
    String fileDelete(String bucketName, String fileName);
}
