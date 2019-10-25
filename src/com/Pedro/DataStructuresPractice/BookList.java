package com.Pedro.DataStructuresPractice;

import java.util.LinkedList;

public class BookList extends LinkedList {
    public BookList() {
        super();
    }

    public void display() {

        for (int i = 0; i < size(); i++) {
            System.out.println(get(i));
        }
    }
}
