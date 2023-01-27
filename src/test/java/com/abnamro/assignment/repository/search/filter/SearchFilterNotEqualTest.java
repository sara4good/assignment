package com.abnamro.assignment.repository.search.filter;

import com.abnamro.assignment.repository.search.SearchOperation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SearchFilterNotEqualTest {

    @Test
    public void canBeAppliedReturnsTrueWhenOperationIsNotEqual() {
        SearchFilterNotEqual filterNotEqual  = new SearchFilterNotEqual();
        boolean b = filterNotEqual.canBeApplied(SearchOperation.NOT_EQUAL);
        assertTrue(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsEqual() {
        SearchFilterNotEqual filterNotEqual  = new SearchFilterNotEqual();
        boolean b = filterNotEqual.canBeApplied(SearchOperation.EQUAL);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsDoesNotContain() {
        SearchFilterNotEqual filterNotEqual  = new SearchFilterNotEqual();
        boolean b = filterNotEqual.canBeApplied(SearchOperation.DOES_NOT_CONTAIN);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsContain() {
        SearchFilterNotEqual filterNotEqual  = new SearchFilterNotEqual();
        boolean b = filterNotEqual.canBeApplied(SearchOperation.CONTAINS);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNull() {
        SearchFilterNotEqual filterNotEqual  = new SearchFilterNotEqual();
        boolean b = filterNotEqual.canBeApplied(null);
        assertFalse(b);
    }


}