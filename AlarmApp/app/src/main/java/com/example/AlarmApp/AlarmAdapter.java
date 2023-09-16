package com.example.AlarmApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AlarmApp.databinding.NewAlarmBinding;

public class AlarmAdapter extends ListAdapter<Alarm,AlarmAdapter.viewHolder> {

    protected AlarmAdapter(){
        super(CALLBACK);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_alarm,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Alarm alarm = getAlarm(position);
        holder.binding.txtTime.setText(alarm.getHour()+":"+ alarm.getMinute());
        holder.binding.txtTitle.setText(alarm.getTitle());
        holder.binding.switchOnOff.setChecked(true);
    }

    public Alarm getAlarm(int position){

        return getItem(position);
    }

    private  static final DiffUtil.ItemCallback<Alarm> CALLBACK = new DiffUtil.ItemCallback<Alarm>() {
        @Override
        public boolean areItemsTheSame(@NonNull Alarm oldItem, @NonNull Alarm newItem) {

            return oldItem.getAlarm_id()==newItem.getAlarm_id();
        }



        @Override
        public boolean areContentsTheSame(@NonNull Alarm oldItem, @NonNull Alarm newItem) {
            return oldItem.getHour()==(newItem.getHour()) &&
                    oldItem.getMinute()==newItem.getMinute();
        }
    };
    public class viewHolder extends RecyclerView.ViewHolder{
        NewAlarmBinding binding;
     public viewHolder(@NonNull View itemview){
         super(itemview);
         binding = NewAlarmBinding.bind(itemview);
     }
    }
}
