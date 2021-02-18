package no.hvl.dat153.classes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Person.class}, version = 3)
@TypeConverters({Converter.class})
public abstract class PersonDatabase extends RoomDatabase {
    private static PersonDatabase instance;

    public static PersonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, "personDb")
                    .fallbackToDestructiveMigration()
                    // TODO: try not do do that
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract PersonDao personDao();
}
