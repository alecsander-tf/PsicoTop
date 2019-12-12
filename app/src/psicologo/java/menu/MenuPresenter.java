package menu;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Psicologo;

import java.util.List;

public class MenuPresenter implements MenuContract.UserInteraction {

    private MenuContract.View view;
    private IPost post;

    public MenuPresenter(MenuContract.View view, IPost post) {
        this.view = view;
        this.post = post;
    }


    @Override
    public void buscarPacientes() {
        post.carregarPacientes((Psicologo) post.getCurrentUserLogged(), new IPost.IPostListCallback() {
            @Override
            public void onLoaded(List<?> list) {
                view.mostrarPacientes((List<Paciente>) list);
            }

            @Override
            public void onError(String msg) {
                view.mostrarMensagem(msg);
            }
        });
    }
}
