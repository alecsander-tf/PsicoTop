package menu.resumo;

import android.content.Intent;

import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.Meta;

import java.util.List;

public interface ResumoContract {

    interface View {
        void abrirActivity(Intent intent);
        void exibirEmocoes(List<Emocao> emocoes);
        void exibirMetas(List<Meta> metas);
        void mostrarMensagem(String msg);
    }

    interface UserActionsListener {

        void carregarOutraActivity(Intent intent);
        void carregarDetalhesEmocoes();
        void carregarEmocoes();
        void carregarMetas();

    }

}
