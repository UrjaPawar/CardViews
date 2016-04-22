package com.urjapawar.healthvingsapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.PersonViewHolder>  {

    List<Person> persons;

    CustomAdapter(List<Person> persons){
        this.persons = persons;

    }

    public  class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {

        TextView personName;
        TextView persondesc;
        ImageView personPhoto;
        CardView cv;


        public PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);

            personName = (TextView)itemView.findViewById(R.id.title);
            persondesc = (TextView)itemView.findViewById(R.id.desc);
            personPhoto=(ImageView)itemView.findViewById(R.id.thumbnail);

            cv.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            Toast.makeText(view.getContext(),"You clicked "+(Integer.toString(position+1))+ " position of series",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public PersonViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);


        return pvh;
    }
    @Override
    public int getItemCount() {
        return persons.size();
    }
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.persondesc.setText(persons.get(i).desc);
        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        personViewHolder.cv.setTag(i);

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
