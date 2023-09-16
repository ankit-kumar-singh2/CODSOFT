package com.example.TodolistApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.TodolistApp.databinding.ActivityInsertBinding;

public class InsertActivity extends AppCompatActivity {

    ActivityInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=ActivityInsertBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        String type=getIntent().getStringExtra("type");


        if(type.equals("update")){

            setTitle("update");
            binding.title.setText(getIntent().getStringExtra("Title"));
            binding.NotesRv.setText(getIntent().getStringExtra("Task"));
            int id=getIntent().getIntExtra("Id",0);
            binding.addtask.setText("update taks");
            binding.addtask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("Title",binding.title.getText().toString());
                    intent.putExtra("Task",binding.NotesRv.getText().toString());
                    intent.putExtra("Id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });

        }else{

            setTitle("Add Mode");
            binding.addtask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("Title",binding.title.getText().toString());
                    intent.putExtra("Task",binding.NotesRv.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }



    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(InsertActivity.this,MainActivity.class));
    }
}