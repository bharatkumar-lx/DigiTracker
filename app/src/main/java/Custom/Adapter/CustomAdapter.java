package Custom.Adapter;

import android.service.autofill.TextValueSanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bharat.kumar.digitracker.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<DataClass> dataset = new ArrayList<>();
    //TODO: CREATE A CLASS FOR DATA AND ARRAYLIST AND BIND IT WITH THE VIEW;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.info_text);
        }

    }

    public CustomAdapter(ArrayList<DataClass> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //HERE VIEWGROUP BIND THE CUSTOM LAYOUT WITH THIS ADAPTER
        ViewGroup vg = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_app_details,parent,false);
        return new ViewHolder(vg);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(dataset.get(position).getTimeUse());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
