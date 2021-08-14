package Custom.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bharat.kumar.digitracker.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    //TODO: CREATE A CLASS FOR DATA AND ARRAYLIST AND BIND IT WITH THE VIEW;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public CustomAdapter() {
        super();
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
