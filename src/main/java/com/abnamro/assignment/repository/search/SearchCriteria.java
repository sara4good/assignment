package com.abnamro.assignment.repository.search;

import com.abnamro.assignment.controller.dto.SearchCriteriaRequest;
import lombok.*;

import java.io.IOException;
import java.io.Serializable;
@Getter
@Setter
@RequiredArgsConstructor
public class SearchCriteria implements Serializable {
    private String filterKey;
    private Object value;
    private String operation;

    public SearchCriteria(SearchCriteriaRequest request) {
        this.filterKey = request.getFilterKey();
        this.operation = request.getOperation();
        this.value = request.getValue();
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
