package com.example.lxkn.mobilneprojekt;

/**
 * Created by lxkn on 2017-01-09.
 */

public class MenuItem {
    String nazwa;
    int path;
    public MenuItem(String nazwa, int path) {
        this.nazwa=nazwa;
        this.path=path;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
