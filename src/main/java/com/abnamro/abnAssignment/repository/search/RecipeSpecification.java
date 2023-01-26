package com.abnamro.abnAssignment.repository.search;

import com.abnamro.abnAssignment.domain.Ingredient;
import com.abnamro.abnAssignment.domain.Recipe;
import com.abnamro.abnAssignment.repository.search.filter.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RecipeSpecification implements Specification<Recipe> {
    private final SearchCriteria criteria;

    // I made it static because this filters isn't object-specific and no need to populate it with every new specification
    private static final List<SearchFilter> searchFilters = new ArrayList<>();

    public RecipeSpecification(SearchCriteria criteria) {
        super();
        filterList();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Optional<SearchOperation> operation = SearchOperation.getOperation(criteria.getOperation());
        String filterValue = criteria.getValue().toString().toLowerCase();
        String filterKey = criteria.getFilterKey();

        Join<Object, Object> subRoot = root.join("ingredients");

        return operation.flatMap(searchOperation -> searchFilters
                .stream()
                .filter(searchFilter -> searchFilter.couldBeApplied(searchOperation))
                .findFirst()
                .map(searchFilter -> searchFilter.apply(cb, filterKey, filterValue, root,subRoot))).orElse(null);
    }

    private void filterList() {
        searchFilters.add(new SearchFilterEqual());
        searchFilters.add(new SearchFilterNotEqual());
        searchFilters.add(new SearchFilterContains());
        searchFilters.add(new SearchFilterDoesNotContain());
    }
}
