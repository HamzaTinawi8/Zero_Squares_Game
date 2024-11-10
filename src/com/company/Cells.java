package com.company;

import java.util.Objects;

abstract class Cells {

    char name;

    public Cells(char name) {
        this.name = name;
    }

    void printName(Cells[][] cells){
        for (Cells[] cell : cells) {
            for (Cells value : cell) {
                System.out.print(value.name);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cells cells = (Cells) o;
        return name == cells.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}