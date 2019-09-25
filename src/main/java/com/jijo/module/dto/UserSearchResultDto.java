package com.jijo.module.dto;

import java.util.List;

/**
 * @author jijo.lawrence
 * @param <T>
 *
 */
public class UserSearchResultDto {

    private List<Object> resultList;

    /**
     * @return the resultList
     */
    public List<Object> getResultList() {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }

}
