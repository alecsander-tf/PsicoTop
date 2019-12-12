package menu.diario;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;

public class DiarioFragment extends Fragment implements DiarioContract.View {

    ProgressDialog progDailog;
    Button btnEnviarEmocao;
    EditText etComentario;
    Spinner spinnerEmocoes;

    DiarioContract.UserActionsListener presenter;

    public DiarioFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diario, container, false);


        presenter = new DiarioPresenter(this, new Post());

        etComentario = view.findViewById(R.id.etComentario);
        spinnerEmocoes = view.findViewById(R.id.spinnerEmocoes);
        btnEnviarEmocao = view.findViewById(R.id.btnEnviarEmocao);

        btnEnviarEmocao.setOnClickListener(registrarEmocao());

        progDailog = new ProgressDialog(DiarioFragment.this.getContext());
        progDailog.setMessage("Enviando registro...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);



        return view;
    }

    private View.OnClickListener registrarEmocao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Emocao e = new Emocao();

                e.setComentario(etComentario.getText().toString());
                e.setTipoEmocao(spinnerEmocoes.getSelectedItem().toString());

                presenter.registrarEmocao(e);
            }
        };
    }

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCarregando(boolean carregando) {
        if (carregando){
            progDailog.show();
        }else {
            progDailog.dismiss();
        }
    }
}
