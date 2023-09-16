package com.example.TodolistApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TodolistApp.databinding.NewListItemsBinding;

public class todoAdpater extends ListAdapter<Task,todoAdpater.viewHolder> {

    protected todoAdpater() {
    super(CALLBACK);
    }

    private  static final DiffUtil.ItemCallback<Task> CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {

            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle())) &&
                    (oldItem.getTask().equals(newItem.getTask()));
        }
    };

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_list_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Task task = getask(position);
        holder.binding.titleRv.setText(task.getTitle());
        holder.binding.NotesRv.setText(task.getTask());

    }
    public Task getask(int position){

        return getItem(position);
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        NewListItemsBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NewListItemsBinding.bind(itemView);

        }
    }
}
