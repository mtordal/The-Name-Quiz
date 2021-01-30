package no.hvl.dat153.classes;

import android.graphics.drawable.Drawable;

public class Person {
    Drawable image;
    String name;

    // Constructor
    public Person(Drawable image, String name) {
        this.image = image;
        this.name = name;
    }

    // Getters and setters
    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}