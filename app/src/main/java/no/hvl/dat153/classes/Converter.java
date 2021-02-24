package no.hvl.dat153.classes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converter {
    @TypeConverter
    public static Drawable fromString(String s) {
        try {
            byte[] encodeByte = Base64.decode(s, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return new BitmapDrawable(Resources.getSystem(), bitmap);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @TypeConverter
    public static String fromDrawable(Drawable d) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap b = bd.getBitmap();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
