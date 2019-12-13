package menuPrincipal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.psicotop.R;
import menuPrincipal.metas.MetasFragment;
import menuPrincipal.resumo.ResumoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.add("RESUMO", new ResumoFragment());
        adapter.add("METAS", new MetasFragment());

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    class TabsAdapter extends FragmentPagerAdapter {

        private List<Fragment> listFragments = new ArrayList<>();
        private List<String> listFragmentsTitle =  new ArrayList<>();

        public void add(String fragmentTitle, Fragment fragment){
            listFragmentsTitle.add(fragmentTitle);
            listFragments.add(fragment);
        }

        public TabsAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return listFragmentsTitle.get(position);
        }

        @Override
        public int getCount() {
            return listFragments.size();
        }
    }
}
