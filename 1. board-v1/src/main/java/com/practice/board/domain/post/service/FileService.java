package com.practice.board.domain.post.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileService {

    public String uploadFile(String path, String fileName, byte[] fileData) throws IOException {
        UUID uuid = UUID.randomUUID();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String saveFileName = uuid.toString() + extension;

        createDir(path);

        String fileUploadPath = path + File.separator + saveFileName;
        FileOutputStream fs = new FileOutputStream(fileUploadPath);

        fs.write(fileData);
        log.info(fileUploadPath + " 파일을 업로드했습니다.");
        fs.close();
        return saveFileName;
    }

    private void createDir(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
            log.info(path +" 폴더를 생성했습니다.");
            return;
        }
        log.info(path + " 폴더가 이미 존재합니다.");
    }
}
