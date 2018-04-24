package com.himly.jpaDynamicSql.repository;


import com.himly.jpaDynamicSql.model.Test;
import com.himly.jpaDynamicSql.model.Test_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TestRepositorySpec {

    public static Specification<Test> getAllByCondition(final String name,final String code) {

        return new Specification<Test>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Test> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>(16);

                if (name != null && !name.isEmpty()) {
                    predicates.add(cb.equal(root.get(Test_.name),name));
                }

                if (code != null && !code.isEmpty()) {
                    predicates.add(cb.equal(root.get(Test_.code),code));
                }

                if (predicates.size() > 0) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }

                return cb.conjunction();
            }
        };
    }
}
