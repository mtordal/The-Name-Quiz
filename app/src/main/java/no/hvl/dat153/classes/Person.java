package no.hvl.dat153.classes;

import android.graphics.drawable.Drawable;

public class Person {
    Drawable image;
    String name;

    public Person(Drawable image, String name) {
        this.image = image;
        this.name = name;
    }

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