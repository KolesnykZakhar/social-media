package com.gmail.kolesnyk.zakhar.userService.friendsPage;

import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

public class UsersPage {
    private List<User> page;
    private int amountPages;
    private String searchText;

    public UsersPage(List<User> page, int amountOfPages) {
        this.page = page;
        this.amountPages = amountOfPages;
    }

    public UsersPage(List<User> page, int amountPages, String searchText) {
        this.page = page;
        this.amountPages = amountPages;
        this.searchText = searchText;
    }

    public List<User> getPage() {
        return page;
    }

    public int getAmountPages() {
        return amountPages;
    }

    public String getSearchText() {
        return searchText;
    }
}
