package com.abnamro.assignment.repository.search.filter;

import com.abnamro.assignment.repository.search.SearchOperation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchFilterDoesNotContainTest {

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNotEqual() {
        SearchFilterDoesNotContain filter  = new SearchFilterDoesNotContain();
        boolean b = filter.canBeApplied(SearchOperation.NOT_EQUAL);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsEqual() {
        SearchFilterDoesNotContain filter  = new SearchFilterDoesNotContain();
        boolean b = filter.canBeApplied(SearchOperation.EQUAL);
        assertFalse(b);

    }

    @Test
    public void canBeAppliedReturnsTrueWhenOperationIsDoesNotContain() {
        SearchFilterDoesNotContain filter  = new SearchFilterDoesNotContain();
        boolean b = filter.canBeApplied(SearchOperation.DOES_NOT_CONTAIN);
        assertTrue(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsContain() {
        SearchFilterDoesNotContain filter  = new SearchFilterDoesNotContain();
        boolean b = filter.canBeApplied(SearchOperation.CONTAINS);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNull() {
        SearchFilterDoesNotContain filter  = new SearchFilterDoesNotContain();
        boolean b = filter.canBeApplied(null);
        assertFalse(b);
    }

}