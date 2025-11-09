package com.gtalent.project_sb_aws_s3.service;

import com.gtalent.project_sb_aws_s3.file.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    private S3Client s3Client;

    //@Autowired 欄位式注入
    public FileServiceImpl(S3Client s3Client){ //建構式注入
        this.s3Client = s3Client;
    }

    @Override
    public String createBucket(String bucketName){
        s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        return "Bucket created: " + bucketName;
    }

    @Override
    public List<String> getBucketList(){
        return  s3Client.listBuckets()
                .buckets()
                .stream()
                .map(Bucket::name)
                .toList();
    }

    @Override
    public String fileUpload(String bucketName, MultipartFile file){
        // UUID.randomUUID() 產生隨機字串
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try{
            byte[] fileBytes = file.getBytes();

            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
            // RequestBody.fromBytes(fileBytes) 將上傳檔案包裝成AWS可接受格式
            s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
            // fromByte(byte []) 傳送byte,檔案在記憶體中
            // fromFile(Path) 傳送本地檔案,檔案在硬碟中
            // fromInputStream 傳送串聯資料
            // fromString(string) 傳送純文字
            return "File upload: " + fileName;
        }catch (IOException e){
            return "File uploaded failed";
        }
    }

    @Override
    public File fileDownload(String bucketName, String fileName){
        // getObjectAsBytes() 取得檔案內容並轉成 byte
        return new File(fileName,s3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket(bucketName).key(fileName).build()).asByteArray());
    }

    @Override
    public String fileDelete(String bucketName, String fileName){
        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(fileName).build());
        return "File deleted: " + fileName;
    }
}
