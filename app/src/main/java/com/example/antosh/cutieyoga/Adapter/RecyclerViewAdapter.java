package com.example.antosh.cutieyoga.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antosh.cutieyoga.Interface.ItemClickListener;
import com.example.antosh.cutieyoga.Model.Exercise;
import com.example.antosh.cutieyoga.R;
import com.example.antosh.cutieyoga.ViewExercise;

import java.util.List;

/**
 * Created by Antosh on 6/21/2018.
 */



class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView image;
    public TextView text;
    private ItemClickListener itemClickListener;


    public RecyclerViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.ex_img);
        text = itemView.findViewById(R.id.ex_name);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition());

    }
}


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<Exercise> listExercises;
    private Context context;
    MediaPlayer mediaPlayer ;

    public RecyclerViewAdapter(List<Exercise> listExercises, Context context) {
        this.listExercises = listExercises;
        this.context = context;
    }

    @NonNull
    @Override

    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.image.setImageResource(listExercises.get(position).getImage_id());
            holder.text.setText(listExercises.get(position).getName());
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    mediaPlayer = MediaPlayer.create(context,R.raw.button_click);
                    mediaPlayer.start();
                    //Call to new Activity (List Items)
                    Intent intent = new Intent(context, ViewExercise.class);
                    intent.putExtra("image_id",listExercises.get(position).getImage_id());
                    intent.putExtra("name",listExercises.get(position).getName());
                    context.startActivity(intent);


                }
            });

    }

    @Override
    public int getItemCount() {
        return listExercises.size();
    }
}
