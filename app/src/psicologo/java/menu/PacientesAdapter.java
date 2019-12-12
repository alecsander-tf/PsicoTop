package menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psicotop.R;
import com.example.psicotop.modal.Paciente;

import java.util.List;

public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.ViewHolder> {

    private List<Paciente> mPacientes;

    public PacientesAdapter(List<Paciente> mPacientes) {
        this.mPacientes = mPacientes;
    }

    public void replaceData(List<Paciente> pacientes){
        this.mPacientes = pacientes;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.paciente_item, parent, false);

        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Paciente paciente = mPacientes.get(position);

        holder.nomePaciente.setText(paciente.getNome() + " " + paciente.getSobrenome());
        holder.emailPaciente.setText(paciente.getEmail());

    }

    @Override
    public int getItemCount() {
        return mPacientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomePaciente;
        private TextView emailPaciente;

        public ViewHolder(View itemView) {
            super(itemView);
            nomePaciente = itemView.findViewById(R.id.tvNomePaciente);
            emailPaciente = itemView.findViewById(R.id.tvEmailPaciente);
        }
    }
}
