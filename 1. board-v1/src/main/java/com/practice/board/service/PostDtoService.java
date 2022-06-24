package com.practice.board.service;

import com.practice.board.dto.PostDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class PostDtoService {

    @PersistenceContext
    EntityManager em;

    public List<PostDto> getPostDtoList(){
        String query = "select * from posts";

        JpaResultMapper result = new JpaResultMapper();
        Query resultQuery = em.createNativeQuery(query);

        return result.list(resultQuery, PostDto.class);
    }


}
