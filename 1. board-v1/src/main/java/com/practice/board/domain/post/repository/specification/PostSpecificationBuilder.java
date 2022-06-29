package com.practice.board.domain.post.repository.specification;

import com.practice.board.domain.post.entity.Posts;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class PostSpecificationBuilder {

    public static PostSpecificationBuilder.Builder builder(){
        return new PostSpecificationBuilder.Builder();
    }

    public static class Builder{

        public static List<Specification> spec = new ArrayList<>();

        public Builder searchBy(String searchBy, String query){
            spec.add(Arrays.stream(PostSearchType.values())
                    .filter(n -> n.name().equals(searchBy.toUpperCase()))
                    .map(n -> n.equalSearchBy(query))
                    .findFirst()
                    .get());
            return this;
        }

        public Specification<Posts> build(){
            return Specification.where(spec.get(0));
        }
    }



}
