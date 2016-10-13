package edu.orangecoastcollege.cs273.tmorrissey1.todo2day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    private static final String DATABASE_NAME = "ToDo2Day";
    private static final String DATABASE_TABLE = "Tasks";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_IS_DONE = "is_done";


    public DBHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_IS_DONE + " INTEGER" + ")";
        database.execSQL (table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    public void addTask(Task newTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(KEY_FIELD_ID, newTask.getId());
        values.put(FIELD_DESCRIPTION, newTask.getDescription());
        values.put(FIELD_IS_DONE, newTask.getIsDone());

        db.insert(DATABASE_TABLE, null, values);
    }

    public ArrayList<Task> getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Task> taskList = new ArrayList<>();
        Cursor results = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        if (results.moveToFirst()) {
            do {
                int id = results.getInt(0);
                String desc = results.getString(1);
                int isDone = results.getInt(2);
                taskList.add(new Task(id, desc, isDone));

            } while (results.moveToNext());


        }
        db.close();
        results.close();
        return taskList;
    }

    public void updateTask(Task existingTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(KEY_FIELD_ID, newTask.getId());
        values.put(FIELD_DESCRIPTION, existingTask.getDescription());
        values.put(FIELD_IS_DONE, existingTask.getIsDone());

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + "=?", new String[] {String.valueOf(existingTask.getId())});
    }

    public void deleteTask(Task taskToDelete) {

    }

    public void editTask(Task taskToEdit) {

    }

   /* public Task getTask(int id) {
        return new Task();
    }*/

    public void deleteAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

}
