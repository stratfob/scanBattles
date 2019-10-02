package com.example.scanbattles;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scanbattles.models.Monster;

import java.util.ArrayList;
import java.util.Locale;


public class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.MyViewHolder> {

    private ArrayList<Monster> monsterArrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView monsterImage;
        public TextView monsterName;
        public TextView monsterHP;
        public TextView monsterAttack;
        public TextView monsterDefense;
        public TextView monsterSpeed;
        public TextView monsterLevel;
        public TextView monsterRarity;

        public MyViewHolder(View v) {
            super(v);
            monsterImage = v.findViewById(R.id.monsterImage);
            monsterName = v.findViewById(R.id.monsterName);
            monsterHP = v.findViewById(R.id.monsterHP);
            monsterAttack = v.findViewById(R.id.monsterAttack);
            monsterDefense = v.findViewById(R.id.monsterDefense);
            monsterSpeed = v.findViewById(R.id.monsterSpeed);
            monsterLevel = v.findViewById(R.id.monsterLevel);
            monsterRarity = v.findViewById(R.id.monsterRarity);
        }
    }

    public MonsterAdapter(ArrayList<Monster> monsterArrayList){
        this.monsterArrayList = monsterArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override @NonNull
    public MonsterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.monster_item, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Monster currentMonster = monsterArrayList.get(position);

        holder.monsterImage.setImageResource(new AllMonsters().getMonsterPictureId(currentMonster.id));
        holder.monsterName.setText(currentMonster.name);
        holder.monsterHP.setText(String.format(Locale.getDefault(),"%d / %d HP", currentMonster.currentHP, currentMonster.maxHP));
        holder.monsterLevel.setText(String.format(Locale.getDefault(),"Level: %d", currentMonster.level));
        switch (currentMonster.level){
            case 1:
                holder.monsterAttack.setText(String.format(Locale.getDefault(),"Attack: %d", currentMonster.attackLvl1));
                holder.monsterDefense.setText(String.format(Locale.getDefault(),"Defense: %d", currentMonster.defenseLvl1));
                holder.monsterSpeed.setText(String.format(Locale.getDefault(),"Speed: %d", currentMonster.speedLvl1));
                break;
            case 2:
                holder.monsterAttack.setText(String.format(Locale.getDefault(),"Attack: %d", currentMonster.attackLvl2));
                holder.monsterDefense.setText(String.format(Locale.getDefault(),"Defense: %d", currentMonster.defenseLvl2));
                holder.monsterSpeed.setText(String.format(Locale.getDefault(),"%d", currentMonster.speedLvl2));
                break;
            case 3:
                holder.monsterAttack.setText(String.format(Locale.getDefault(),"Attack: %d", currentMonster.attackLvl3));
                holder.monsterDefense.setText(String.format(Locale.getDefault(),"Defense: %d", currentMonster.defenseLvl3));
                holder.monsterSpeed.setText(String.format(Locale.getDefault(),"Speed: %d", currentMonster.speedLvl3));
                break;
        }
        switch (currentMonster.rarity){
            case 1:
                holder.monsterRarity.setText(R.string.Rare);
                break;
            case 2:
                holder.monsterRarity.setText(R.string.Uncommon);
                break;
            case 3:
                holder.monsterRarity.setText(R.string.Common);
                break;
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return monsterArrayList.size();
    }
}