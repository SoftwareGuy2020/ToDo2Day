package edu.orangecoastcollege.cs273.tmorrissey1.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper database;
    private List<Task> taskList;
    private TaskListAdapter taskListAdapter;

    private EditText taskEditText;
    private ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DBHelper(this);


        taskList = database.getAllTasks();
        taskListAdapter = new TaskListAdapter(this, R.layout.task_item, taskList);
        taskListView = (ListView) findViewById(R.id.taskListView);
        taskEditText = (EditText) findViewById(R.id.taskEditText);
        taskListView.setAdapter(taskListAdapter);




    }

    public void addTask(View view) {
        String desc = taskEditText.getText().toString();
        if (desc.isEmpty())
            Toast.makeText(this, "Task description cannot be empty", Toast.LENGTH_SHORT).show();

        else {
            Task newTask = new Task(desc, 0);
            taskListAdapter.add(newTask);
            database.addTask(newTask);
            taskEditText.setText("");
        }
    }

    public void changeTaskStatus(View view) {
        CheckBox box = (CheckBox) view;
        Task task = (Task) box.getTag();
        task.setIsDone(box.isChecked() ? 1 : 0);
        database.updateTask(task);
    }

    public void clearAllTasks(View view) {
        taskList.clear();
        database.deleteAllTasks();
        taskListAdapter.notifyDataSetChanged();
    }
}