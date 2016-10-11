package edu.orangecoastcollege.cs273.tmorrissey1.todo2day;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        DBHelper db = new DBHelper(this);
        db.addTask(new Task(1, "Study for CS273 Midterm", 0));
        db.addTask(new Task(2, "Buy more beer", 0));
        db.addTask(new Task(3, "Get rich", 0));
        db.addTask(new Task(4, "Wash car", 0));
        db.addTask(new Task(5, "Eat Pao de Queijo", 0));
        db.addTask(new Task(6, "study for CS250 exam", 0));
        db.addTask(new Task(7, "study for micro econ exam", 0));
        db.addTask(new Task(8, "study for calc 2 exam", 0));
        db.addTask(new Task(9, "study for computer architecture exam", 0));



        db.close();

    }
}
