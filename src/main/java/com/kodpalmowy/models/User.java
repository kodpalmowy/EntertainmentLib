package com.kodpalmowy.models;

public class User {
    private String Name;
    private int booksRead;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBooksRead() {
        return booksRead;
    }

    public void setBooksRead(int booksRead) {
        this.booksRead = booksRead;
    }
}
