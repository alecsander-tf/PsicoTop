package com.example.psicotop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DiarioFragment diarioFragment = new DiarioFragment();

        TabsAdapter adapter = new TabsAdapter( getSupportFragmentManager() );
        adapter.add( new DiarioFragment() , "Resumo");
        adapter.add( new DiarioFragment() , "Diário");

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
