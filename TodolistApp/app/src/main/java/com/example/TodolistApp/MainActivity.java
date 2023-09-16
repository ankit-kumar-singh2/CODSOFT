package com.example.TodolistApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.TodolistApp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    todoAdpater adapter;
    ActivityMainBinding binding;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        taskViewModel= new ViewModelProvider(MainActivity.this,(ViewModelProvider.Factory)ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(TaskViewModel.class);


        binding.floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                intent.putExtra("type","Add Mode");
                startActivityForResult(intent,1);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new todoAdpater();
        binding.recyclerView.setAdapter(adapter);

        taskViewModel.getalldata().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {

                adapter.submitList(tasks);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT){
                    taskViewModel.delete(adapter.getask(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(MainActivity.this,InsertActivity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("Title",adapter.getask(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("Task",adapter.getask(viewHolder.getAdapterPosition()).getTask());
                    intent.putExtra("Id",adapter.getask(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);

                }
            }
        }).attachToRecyclerView(binding.recyclerView);


    }
    @Override
    protected void onActivityResult(int requestCode,int resultcode ,@Nullable Intent data){

        super.onActivityResult(requestCode,resultcode,data);
        if(requestCode==1){
            String title = data.getStringExtra("Title");
            String taskdesp = data.getStringExtra("Task");
            Task task = new Task(title,taskdesp);
            taskViewModel.insert(task);
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();

        }else if(requestCode==2){
            String title = data.getStringExtra("Title");
            String taskdesp = data.getStringExtra("Task");
            Task task = new Task(title,taskdesp);
            task.setId(data.getIntExtra("Id",0));
            taskViewModel.update(task);
            Toast.makeText(MainActivity.this, "Task updated", Toast.LENGTH_SHORT).show();

        }
    }
}