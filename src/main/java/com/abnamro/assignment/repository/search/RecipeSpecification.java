package com.abnamro.assignment.repository.search;

import com.abnamro.assignment.domain.Recipe;
import com.abnamro.assignment.repository.search.filter.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RecipeSpecification implements Specification<Recipe>  {
    private static final List<SearchFilter> searchFilters = new ArrayList<>();
    private final SearchCriteria criteria;

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

        return operation.flatMap(searchOperation -> searchFilters.stream().filter(searchFilter -> searchFilter.canBeApplied(searchOperation)).findFirst().map(searchFilter -> searchFilter.apply(cb, filterKey, filterValue, root, subRoot))).orElse(null);
    }

    private void filterList() {
        searchFilters.add(new SearchFilterEqual());
        searchFilters.add(new SearchFilterNotEqual());
        searchFilters.add(new SearchFilterContains());
        searchFilters.add(new SearchFilterDoesNotContain());
    }
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
}
