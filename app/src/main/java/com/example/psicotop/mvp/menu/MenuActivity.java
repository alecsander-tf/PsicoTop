package com.example.psicotop.mvp.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.psicotop.R;
import com.example.psicotop.mvp.configuracoes.ConfiguracoesActivity;
import com.example.psicotop.mvp.menu.diario.DiarioFragment;
import com.example.psicotop.mvp.menu.resumo.ResumoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuContract.View {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DiarioFragment diarioFragment = new DiarioFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabsAdapter adapter = new TabsAdapter( getSupportFragmentManager() );
        adapter.add( new DiarioFragment() , "Resumo");
        adapter.add( new ResumoFragment() , "Diário");

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.custom_toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_settings){
            this.carregarActivity(ConfiguracoesActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void carregarActivity(Class<?> arg) {
        startActivity(new Intent(getApplicationContext(), arg));
    }

    class TabsAdapter extends FragmentPagerAdapter {

        private List<Fragment> listFragments = new ArrayList<>();
        private List<String> listFragmentsTitle =  new ArrayList<>();

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        public void add(Fragment frag, String title){
            this.listFragments.add(frag);
            this.listFragmentsTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Override
        public int getCount() {
            return listFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return listFragmentsTitle.get(position);
        }
    }
}
