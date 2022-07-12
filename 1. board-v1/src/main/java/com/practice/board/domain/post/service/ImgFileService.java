package com.practice.board.domain.post.service;

import com.practice.board.domain.common.prop.FileProperties;
import com.practice.board.domain.post.dto.ImgFileDto;
import com.practice.board.domain.post.dto.PostDto;
import com.practice.board.domain.post.entity.ImgFile;
import com.practice.board.domain.post.entity.Posts;
import com.practice.board.domain.post.repository.ImgFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgFileService {
    private final FileService fileService;
    private final ImgFileRepository imgFileRepository;
    private final FileProperties fileProp;

    public PostDto Register(Posts posts, List<MultipartFile> imgFileList) throws IOException {
        PostDto postDto = PostDto.of(posts);

        List<ImgFileDto> imgFileDtos = new ArrayList<>();

        if (validateImgFileList(imgFileList)){
            return postDto;
        }
        
        for (MultipartFile multipartFile : imgFileList) {
            imgFileDtos.add(saveImgFile(multipartFile, posts).toDto());
        }
        postDto.setImgFileDtoList(imgFileDtos);
        return postDto;
    }

    public List<ImgFile> getImgFile(Posts posts){
        return imgFileRepository.findByPosts(posts);
    }

    private boolean validateImgFileList(List<MultipartFile> imgFileList) {
        if(imgFileList.size() == 1){
            return true;
        }
        return false;
    }

    private ImgFile saveImgFile(MultipartFile multipartFile, Posts posts) throws IOException {
        Assert.notNull(multipartFile, "multipartFile is null");

        String oriImgName = multipartFile.getOriginalFilename();
        String imgName = fileService.uploadFile(fileProp.getPath(), oriImgName, 
                multipartFile.getBytes());

        return imgFileRepository.save(ImgFile.builder()
                .imgName(imgName)
                .oriImgName(oriImgName)
                .imgUrl(fileProp.getImgUrl() + imgName)
                .posts(posts)
                .build());
    }
}
