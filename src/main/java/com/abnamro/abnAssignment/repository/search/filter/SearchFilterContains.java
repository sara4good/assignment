package com.abnamro.abnAssignment.repository.search.filter;




import com.abnamro.abnAssignment.domain.Recipe;
import com.abnamro.abnAssignment.repository.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.*;


public class SearchFilterContains implements SearchFilter {

    @Override
    public boolean couldBeApplied(SearchOperation opt) {
        return opt == SearchOperation.CONTAINS;
    }

    @Override
    public Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root, Join<Object, Object> subRoot) {
        if (filterKey.equals("ingredients"))
            return cb.like(cb.lower(subRoot.get("name").as(String.class)), "%" + filterValue + "%");

        return cb.like(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");
    }
}
