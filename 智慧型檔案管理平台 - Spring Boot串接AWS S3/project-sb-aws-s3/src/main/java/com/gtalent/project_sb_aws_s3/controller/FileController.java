package com.gtalent.project_sb_aws_s3.controller;


import com.gtalent.project_sb_aws_s3.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3")
public class FileController {
    // 建構式注入
    private FileService fileService;

    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    // ========= 水桶操作 ===============
    // https://localhost:80/s3/create/{bucketName}
    @PostMapping("/bucket/create/{bucketName}")
    public String createBucket(@PathVariable String bucketName){
        return fileService.createBucket(bucketName);
    }

    // http://localhost:80/s3/list
    @GetMapping("/bucket/list")
    public List<String> getBucketList(){
        return fileService.getBucketList();
    }

    // ============= 檔案操作 ===========
    @PostMapping("/file/upload/{bucketName}")
    public String fileUpload(@PathVariable String bucketName, @RequestParam("file")MultipartFile file){
        return fileService.fileUpload(bucketName, file);
    }

    @GetMapping("/file/download/{bucketName}/{fileName}")
    public ResponseEntity<byte[]> fileDownload(
            @PathVariable String bucketName,
            @PathVariable String fileName) {
        byte[] data = fileService.fileDownload(bucketName, fileName).getFile();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .header("Content-Type", "application/octet-stream")
                .body(data);
    }


    @DeleteMapping("/file/delete/{bucketName}/{fileName}")
    public String deleteFile(@PathVariable String bucketName, @PathVariable String fileName){
        return fileService.fileDelete(bucketName, fileName);
    }
}

/*
    建立 Bucket - createBucket(),加上錯誤處理與存在檢查
    列出 Bucket - listBucket()
    上傳 File - putObject()
    下載 File - getObjectAsBytes()
    刪除 File - deleteObject()

 */

