package no.hvl.dat153.classes;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    public int pId;

    public Drawable image;
    public String name;

    // Constructor
    public Person(Drawable image, String name) {
        this.image = image;
        this.name = name;
    }

    // Getters
    public Drawable getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}