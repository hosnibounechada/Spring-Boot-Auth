package com.hb.auth.common.service;

import com.hb.auth.exception.FileSizeLimitException;
import com.hb.auth.exception.FileTypeException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;
    @Value("${file.upload.max-size}")
    private Long fileMaxSize;

    public String uploadFile(String objectName, MultipartFile file) {

        if (file.getSize() > fileMaxSize) throw new FileSizeLimitException("File must not exceed 1MB");

        if (file.getContentType() != null && !file.getContentType().contains("image"))
            throw new FileTypeException("File must be of type Image");

        try {
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(objectName)
                    .bucket(bucket)
                    .build());

            System.out.println();
            // Retrieve the object URL
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket).object(objectName).method(Method.GET).build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
    }

}
