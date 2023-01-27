package com.abnamro.assignment.repository.search.filter;

import com.abnamro.assignment.repository.search.SearchOperation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SearchFilterEqualTest {

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNotEqual() {
        SearchFilterEqual filterEqual  = new SearchFilterEqual();
        boolean b = filterEqual.canBeApplied(SearchOperation.NOT_EQUAL);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsTrueWhenOperationIsEqual() {
        SearchFilterEqual filterEqual  = new SearchFilterEqual();
        boolean b = filterEqual.canBeApplied(SearchOperation.EQUAL);
        assertTrue(b);

    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsDoesNotContain() {
        SearchFilterEqual filterEqual  = new SearchFilterEqual();
        boolean b = filterEqual.canBeApplied(SearchOperation.DOES_NOT_CONTAIN);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsContain() {
        SearchFilterEqual filterEqual  = new SearchFilterEqual();
        boolean b = filterEqual.canBeApplied(SearchOperation.CONTAINS);
        assertFalse(b);
    }

    @Test
    public void canBeAppliedReturnsFalseWhenOperationIsNull() {
        SearchFilterEqual filterEqual  = new SearchFilterEqual();
        boolean b = filterEqual.canBeApplied(null);
        assertFalse(b);
    }


}