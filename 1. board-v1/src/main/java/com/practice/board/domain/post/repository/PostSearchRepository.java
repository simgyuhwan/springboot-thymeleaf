package com.practice.board.domain.post.repository;

import com.practice.board.domain.post.dto.PostSearchDto;
import com.practice.board.domain.post.entity.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class PostSearchRepository {

    private final PostRepository postRepository;

    @PersistenceContext
    EntityManager em;

    public Page<Posts> findAllBySearchDto(PostSearchDto postSearchDto, Pageable pageable){
        if(StringUtils.hasText(postSearchDto.getSearchQuery())){
            String jpql = "select p From Posts p where "+postSearchDto.addWhereQuery() + " like :query";
            TypedQuery<Posts> query = getQuery(postSearchDto, pageable, jpql);
            Integer total = query.getResultList().size();
            return new PageImpl<>(query.setMaxResults(pageable.getPageSize()).getResultList(), pageable, total);
        }
        return postRepository.findAll(pageable);
    }

    private TypedQuery<Posts> getQuery(PostSearchDto postSearchDto, Pageable pageable, String jpql) {
        return em.createQuery(jpql, Posts.class)
                .setParameter("query", "%" + postSearchDto.getSearchQuery() + "%")
                .setFirstResult((int) pageable.getOffset());
    }
}
