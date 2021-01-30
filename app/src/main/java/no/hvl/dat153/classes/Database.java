package no.hvl.dat153.classes;

import android.app.Application;

import java.util.ArrayList;

public class Database extends Application {
    private ArrayList<Person> database = new ArrayList<>(); // Arraylist to create database

    public ArrayList<Person> getDatabase() { // Get database
        return database;
    }

    public void addStudent(Person student) { // Add student to the database
        this.database.add(student);
    }
}
