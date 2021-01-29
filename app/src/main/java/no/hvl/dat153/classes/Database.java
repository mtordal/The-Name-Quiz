package no.hvl.dat153.classes;

import android.app.Application;

import java.util.ArrayList;

public class Database extends Application {
    private ArrayList<Person> database = new ArrayList<>();

    public ArrayList<Person> getDatabase() {
        return database;
    }

    public void addStudent(Person student) {
        this.database.add(student);
    }
}
