package com.jijo.module.command;

import com.jijo.module.model.User;

/**
 * @author jijo.lawrence
 *
 */
public class SearchUserCommand implements Command {

    private User user;

    private int pageNumber;

    private int pageSize;

    private String columnList;

    /**
     * @return the columnList
     */
    public String getColumnList() {
        return columnList;
    }

    /**
     * @param columnList the columnList to set
     */
    public void setColumnList(String columnList) {
        this.columnList = columnList;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

}
