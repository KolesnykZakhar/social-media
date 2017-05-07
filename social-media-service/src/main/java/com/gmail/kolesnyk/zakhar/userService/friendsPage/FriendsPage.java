package com.gmail.kolesnyk.zakhar.userService.friendsPage;

import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

public class FriendsPage {
    private List<User> page;
    private int amountPages;

    public FriendsPage(List<User> page, int amountOfPages) {
        this.page = page;
        this.amountPages = amountOfPages;
    }

    public List<User> getPage() {
        return page;
    }

    public void setPage(List<User> page) {
        this.page = page;
    }

    public int getAmountPages() {
        return amountPages;
    }

    public void setAmountPages(int amountPages) {
        this.amountPages = amountPages;
    }
}
