package com.Pedro.DataStructuresPractice;

import java.util.LinkedList;

public class AuthorList extends LinkedList {
    public AuthorList() {
        super();
    }

    public void display() {
        Object[] authors = toArray();
        for (int i = 0; i < authors.length; i++)
            ((Author) authors[i]).display();
    }

}
