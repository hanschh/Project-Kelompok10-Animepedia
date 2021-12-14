package com.dih.animepedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimeAdapter extends BaseAdapter  {
    private Context context;
    private ArrayList<Anime> heroes = new ArrayList<>();

    public void setAnimes(ArrayList<Anime> heroes) {
        this.heroes = heroes;
    }

    public AnimeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public Object getItem(int i) {
        return heroes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_anime, viewGroup, false);
        }
        ViewHolder viewHolder  = new ViewHolder(itemView);
        Anime hero = (Anime) getItem(i);
        viewHolder.bind(hero);
        return itemView;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private TextView txtSynopsis;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            txtSynopsis = view.findViewById(R.id.txt_synopsis);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Anime hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            txtSynopsis.setText(hero.getSynopsis());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}