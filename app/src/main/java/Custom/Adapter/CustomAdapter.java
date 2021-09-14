package Custom.Adapter;

import android.app.usage.UsageStats;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Database.DataBaseHelper;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bharat.kumar.digitracker.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final DataClass data;
    private long useTime;
    private DataBaseHelper dataBaseHelper;
    //VIEW HOLDER CLASS BIND VIEWS WITH CUSTOM ADAPTER
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView usageView,labelview;
        public ProgressBar progressBar;
        public ImageView logoView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usageView = itemView.findViewById(R.id.info_text);
            logoView = itemView.findViewById(R.id.apps_logo);
            labelview = itemView.findViewById(R.id.app_label);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

    }

    public CustomAdapter(DataClass data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //HERE VIEW-GROUP BIND THE CUSTOM LAYOUT WITH THIS ADAPTER
        ViewGroup vg = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_app_details,parent,false);
        useTime = totalUsage();
        dataBaseHelper = new DataBaseHelper(vg.getContext());
        return new ViewHolder(vg);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String packageName = data.getmUsageState().get(position).getPackageName();
        long appUse = data.getmAppUsageTime().get(packageName);
        Cursor cursor = dataBaseHelper.getDate(packageName);
        long previousAppUse =-1;
        if (cursor.moveToNext()){
            Log.d("appUse", "onBindViewHolder: package name "+cursor.getString(0));
            Log.d("appUse", "onBindViewHolder:time: "+cursor.getString(1));
            previousAppUse = Long.parseLong(cursor.getString(1));
            appUse = appUse-previousAppUse;
        }

        int usagePercentage = (int)(appUse/useTime)*100;
        holder.progressBar.setProgress(usagePercentage);
        holder.usageView.setText(DateUtils.formatElapsedTime(appUse/1000));
        holder.logoView.setImageDrawable(data.getmAppLogo().get(packageName));
        holder.labelview.setText(data.getmAppLable().get(packageName));

    }

    @Override
    public int getItemCount() {
        return data.getmUsageState().size();
    }

    public long totalUsage(){
        long use =0l;
        for(UsageStats stats : data.getmUsageState()){
            use += data.getmAppUsageTime().get(stats.getPackageName());
        }
        return use;
    }
}
