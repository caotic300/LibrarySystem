package com.Pedro.DataStructuresPractice;

public class Author {
    public String name;
    public BookList books = new BookList();
    public Author() {

    }

    public boolean equals(Object node) {
        return name.equals(((Author) node).name);
    }

    public void display() {
        System.out.println(name);
        books.display();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookList getBooks() {
        return books;
    }

}
