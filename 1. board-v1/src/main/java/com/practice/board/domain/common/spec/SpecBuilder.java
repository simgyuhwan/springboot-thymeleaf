package com.practice.board.domain.common.spec;

import com.practice.board.domain.post.repository.specification.SearchType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class SpecBuilder {
    public static <T> Builder<T> builder(Class<T> type){
        return new Builder<>();
    }

    public static class Builder<T>{
        private List<Specification<T>> specs = new ArrayList<>();

        public Builder<T> and(Specification<T> spec){
            spec.and(spec);
            return this;
        }


        public Builder<T> isHasText(String str,
                                    Function<String, Specification<T>> specSupplier){
            if(StringUtils.hasText(str)){
                specs.add(specSupplier.apply(str));
            }
            return this;
        }

        public Builder<T> isTrue(Boolean cond,
                                 Supplier<Specification<T>> specSupplier){
            if(cond != null && cond.booleanValue()){
                specs.add(specSupplier.get());
            }
            return this;
        }

        public Specification<T> toSpec(){
            Specification<T> spec = Specification.where(null);
            for (Specification<T> s : specs) {
                spec.and(s);
            }
            return spec;
        }
    }

}
