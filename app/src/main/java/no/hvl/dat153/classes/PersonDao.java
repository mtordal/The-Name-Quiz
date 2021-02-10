package no.hvl.dat153.classes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getDb();

    @Insert
    void addStudent(Person student);

    @Delete
    void removeStudent(Person student);
}
