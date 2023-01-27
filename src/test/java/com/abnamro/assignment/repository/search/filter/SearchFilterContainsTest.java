package com.abnamro.assignment.repository.search.filter;

import com.abnamro.assignment.repository.search.SearchOperation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchFilterContainsTest {

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNotEqual() {
        SearchFilterContains filter  = new SearchFilterContains();
        boolean b = filter.canBeApplied(SearchOperation.NOT_EQUAL);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsEqual() {
        SearchFilterContains filter  = new SearchFilterContains();
        boolean b = filter.canBeApplied(SearchOperation.EQUAL);
        assertFalse(b);

    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsDoesNotContain() {
        SearchFilterContains filter  = new SearchFilterContains();
        boolean b = filter.canBeApplied(SearchOperation.DOES_NOT_CONTAIN);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsTrueWhenOperationIsContain() {
        SearchFilterContains filter  = new SearchFilterContains();
        boolean b = filter.canBeApplied(SearchOperation.CONTAINS);
        assertTrue(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNull() {
        SearchFilterContains filter  = new SearchFilterContains();
        boolean b = filter.canBeApplied(null);
        assertFalse(b);
    }

}