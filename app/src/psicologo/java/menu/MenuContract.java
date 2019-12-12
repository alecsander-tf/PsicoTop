package menu;

import com.example.psicotop.modal.Paciente;

import java.util.List;

public interface MenuContract {

    interface View{
        void mostrarMensagem(String msg);
        void mostrarPacientes(List<Paciente> lista);
    }

    interface UserInteraction{
        void buscarPacientes();
    }

}
