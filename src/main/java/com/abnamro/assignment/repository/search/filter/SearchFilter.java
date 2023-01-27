package com.abnamro.assignment.repository.search.filter;


import com.abnamro.assignment.domain.Recipe;
import com.abnamro.assignment.repository.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
/**
 * this is an interface for search filters
 */
public interface SearchFilter {
    /**
     *to validate if operation is applicable
     * @param opt search operation can be cn, cn, nc, eq, ne
     * @return boolean
     */
    boolean canBeApplied(SearchOperation opt);

    /**
     *to create predicate based on filter
     * @return predicate
     */
    Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root, Join<Object, Object> subRoot);
}