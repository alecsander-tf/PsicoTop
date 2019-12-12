package menu.resumo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psicotop.R;
import com.example.psicotop.modal.Meta;

import java.util.Collections;
import java.util.List;

public class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.ViewHolder> {

    private List<Meta> mMetas;
    private Context context;

    public MetaAdapter(List<Meta> mMetas, Context context) {
        this.mMetas = mMetas;
        this.context = context;
    }

    public void replaceData(List<Meta> novaLista){
        Collections.reverse(novaLista);
        mMetas = novaLista;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.meta_item, parent, false);

        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meta meta = mMetas.get(position);

        holder.tvTituloMeta.setText(meta.getTitulo());
        holder.tvDescricaoMeta.setText(meta.getDescricao());

    }

    @Override
    public int getItemCount() {
        if (mMetas.size() <= 3){
            return mMetas.size();
        }else {
            return 3;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView tvTituloMeta;
        TextView tvDescricaoMeta;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            tvDescricaoMeta = itemView.findViewById(R.id.tvDescricaoMeta);
            tvTituloMeta = itemView.findViewById(R.id.tvTituloMeta);
        }
    }
}
