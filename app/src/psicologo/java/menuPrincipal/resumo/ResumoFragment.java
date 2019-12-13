package menuPrincipal.resumo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.psicotop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumoFragment extends Fragment {

    private View view;

    public ResumoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_resumo, container, false);
        return view;
    }
}
