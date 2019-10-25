package com.Pedro.DataStructuresPractice;

class CheckedOutBook {
    public Author author = null;
    public Book book = null;
    public CheckedOutBook() {
    }
    public boolean equals(Object node) {
        return book.title.equals(((CheckedOutBook) node).book.title) && author.name.equals(((CheckedOutBook) node).author.name);
    }

    public String toString() {
        return " * " + author.name + ", " + book.title + "\n";
    }
}
