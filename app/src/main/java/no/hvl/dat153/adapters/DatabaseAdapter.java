package no.hvl.dat153.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import no.hvl.dat153.R;
import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;

public class DatabaseAdapter extends ArrayAdapter<Person> {
    private final Context mContext;
    private final int mResource;
    private final PersonDatabase db;

    // Constructor
    public DatabaseAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.db = PersonDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        // Set imageview to student image
        ImageView imageView = convertView.findViewById(R.id.image);
        imageView.setImageDrawable(getItem(position).getImage());

        // Set textview to student name
        TextView textName = convertView.findViewById(R.id.name);
        textName.setText(getItem(position).getName());

        // Listener on click delete button
        ImageButton deleteBtn = convertView.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(v -> {
            Person p = getItem(position);
            remove(p);
            db.personDao().removeStudent(p);
            Toast.makeText(mContext, "Successfully removed " + p.getName(), Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }
}