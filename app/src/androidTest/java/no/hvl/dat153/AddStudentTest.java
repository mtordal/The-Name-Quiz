package no.hvl.dat153;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDao;
import no.hvl.dat153.classes.PersonDatabase;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class AddStudentTest {
    private PersonDao dao;
    private PersonDatabase db;
    private Context mContext;

    @Before
    public void createDb() {
        mContext = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(mContext, PersonDatabase.class).build();
        dao = db.personDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testAddStudent() {
        assertThat(dao.getDb().size(), equalTo(0));

        dao.addStudent(new Person(ContextCompat.getDrawable(mContext, R.drawable.jon), "Jon"));
        assertThat(dao.getDb().size(), equalTo(1));
    }
}
