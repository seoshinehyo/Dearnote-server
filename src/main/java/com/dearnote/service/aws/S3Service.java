package com.dearnote.service.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    private final String bucketName = "mybucket54";

    public String uploadFile(MultipartFile file) throws IOException {

        // 서버에 저장될 파일 이름
        String storeFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // MultipartFile -> File 변환 없이 InputStream을 사용해 직접 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucketName, storeFileName, file.getInputStream(), metadata));

        // 업로드된 파일의 URL 반환(S3에 올라간 파일 url)
        return amazonS3.getUrl(bucketName, storeFileName).toString();
    }
}
