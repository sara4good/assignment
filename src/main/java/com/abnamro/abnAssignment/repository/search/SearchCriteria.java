package com.abnamro.abnAssignment.repository.search;

import com.abnamro.abnAssignment.controller.dto.SearchCriteriaRequest;

public class SearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;

    public SearchCriteria() {
    }

    public SearchCriteria(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(SearchCriteriaRequest request) {
        this.filterKey = request.getFilterKey();
        this.operation = request.getOperation();
        this.value = request.getValue();
    }


    public String getFilterKey() {
        return filterKey;
    }

    public Object getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }


    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}
