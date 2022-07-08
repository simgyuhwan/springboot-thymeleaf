package com.practice.board.domain.post.service;

import com.practice.board.domain.common.prop.FileProperties;
import com.practice.board.domain.post.dto.PostDto;
import com.practice.board.domain.post.entity.ImgFile;
import com.practice.board.domain.post.entity.Posts;
import com.practice.board.domain.post.repository.ImgFileRepository;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgFileService {
    private final FileService fileService;
    private final ImgFileRepository imgFileRepository;
    private final FileProperties fileProp;

    public void Register(Posts posts, List<MultipartFile> imgFileList) throws IOException {
        List<PostDto> postDtoList = new ArrayList<>();

        for (MultipartFile multipartFile : imgFileList) {
            saveImgFile(multipartFile, posts);
        }

    }

    private void saveImgFile(MultipartFile multipartFile, Posts posts) throws IOException {
        Assert.notNull(multipartFile, "multipartFile is null");

        String oriImgName = multipartFile.getOriginalFilename();
        String imgName = fileService.uploadFile(fileProp.getPath(), oriImgName, 
                multipartFile.getBytes());

        imgFileRepository.save(ImgFile.builder()
                .imgName(imgName)
                .oriImgName(oriImgName)
                .imgUrl("/posts/image/" + imgName)
                .posts(posts)
                .build());
    }



}
