package Custom.Adapter;

import android.app.usage.UsageStats;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bharat.kumar.digitracker.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<UsageStats> dataset ;
    private final ArrayMap<String,String > mAppLableMap;
    private ArrayMap<String,Drawable > mAppLogoMap;
    //VIEW HOLDER CLASS BIND VIEWS WITH CUSTOM ADAPTER
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.info_text);
            imageView = itemView.findViewById(R.id.apps_logo);
        }

    }

    public CustomAdapter(ArrayList<UsageStats> dataset, ArrayMap<String ,String> mAppLableMap, ArrayMap<String, Drawable> mAppLogoMap) {
        this.mAppLableMap = mAppLableMap;
        this.dataset = dataset;
        this.mAppLogoMap = mAppLogoMap;
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
        String timeUse = mAppLableMap.get(dataset.get(position).getPackageName());
//        if(timeUse.equals("00:00")){
//            return;
//        }
        holder.textView.setText(timeUse);
        holder.imageView.setImageDrawable(mAppLogoMap.get(dataset.get(position).getPackageName()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
