package com.dih.animepedia;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.dih.animepedia.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private AnimeAdapter adapter;
    private String[] dataName;
    private String[] dataDescription;
    private String[] dataSynopsis;
    private TypedArray dataPhoto;
    private ArrayList<Anime> animes;
    int[] animeImages = {R.drawable.lapanenam, R.drawable.mushokutensei,R.drawable.onepiece,
            R.drawable.kimetsunoyaiba,R.drawable.komisanwa,R.drawable.holonogravity,
            R.drawable.ousama,R.drawable.detectiveconan};
//    SearchView searchView;
//    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent();
                link.setAction(Intent.ACTION_VIEW);
                link.addCategory(Intent.CATEGORY_BROWSABLE);
                link.setData(Uri.parse("https://myanimelist.net/"));
                startActivity(link);
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


//        searchView = findViewById(R.id.search_bar);
        ListView listView = findViewById(R.id.lv_list);
        adapter = new AnimeAdapter(this);
        listView.setAdapter(adapter);

//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,dataName);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),DescriptionActivity.class);
                intent.putExtra("name",dataName[i]);
                intent.putExtra("desc",dataDescription[i]);
                intent.putExtra("image",animeImages[i]);
                intent.putExtra("synops",dataSynopsis[i]);
                startActivity(intent);
            }
        })
        ;


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                MainActivity.this.arrayAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                MainActivity.this.arrayAdapter.getFilter().filter(newText);
//                return false;            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void prepare(){
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataSynopsis = getResources().getStringArray(R.array.data_synopsis);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem(){
        animes = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            Anime anime = new Anime();
            anime.setPhoto(dataPhoto.getResourceId(i, -1));
            anime.setName(dataName[i]);
            anime.setDescription(dataDescription[i]);
            anime.setSynopsis(dataSynopsis[i]);
            animes.add(anime);
        }
        adapter.setAnimes(animes);
    }
}