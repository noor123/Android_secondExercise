package com.example.jeansmits.jsonexercise.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jeansmits.jsonexercise.models.Person;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PersonDB";
    private static final String TABLE_PERSONS = "persons";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_FAVORITE_NUMBER = "favoriteNumber";
    private static final String[] COLUMNS = {KEY_ID,KEY_FIRSTNAME,KEY_LASTNAME,KEY_FAVORITE_NUMBER};


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERSON_TABLE = "CREATE TABLE persons ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, "+
                "lastName TEXT, "+
                "favoriteNumber INTEGER )";
        db.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS persons");
        this.onCreate(db);
    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, person.getFirstName());
        values.put(KEY_LASTNAME, person.getLastName());
        values.put(KEY_FAVORITE_NUMBER, person.getFavoriteNumber());

        db.insert(TABLE_PERSONS, null, values);
        db.close();
    }

    public Person getPerson(int id) throws RuntimeException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PERSONS, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Person person = new Person();
        person.setId(Integer.parseInt(cursor.getString(0)));
        person.setFirstName(cursor.getString(1));
        person.setLastName(cursor.getString(2));
        person.setFavoriteNumber(Integer.parseInt(cursor.getString(3)));
        return person;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new LinkedList<Person>();

        String query = "SELECT * FROM " + TABLE_PERSONS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();

        Person person = null;
        if (cursor.moveToFirst()) {
            do {
                person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setFirstName(cursor.getString(1));
                person.setLastName(cursor.getString(2));
                person.setFavoriteNumber(Integer.parseInt(cursor.getString(3)));
                persons.add(person);
            } while (cursor.moveToNext());
        }
        return persons;
    }

    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, person.getFirstName());
        values.put(KEY_LASTNAME, person.getLastName());
        values.put(KEY_FAVORITE_NUMBER, person.getFavoriteNumber());

        int i = db.update(TABLE_PERSONS, values, KEY_ID+" = ?", new String[] { String.valueOf(person.getId())});
        db.close();

        return i;
    }

    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PERSONS, KEY_ID+" = ?", new String[] { String.valueOf(person.getId()) });
        db.close();
    }

}
