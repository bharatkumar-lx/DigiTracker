package dashboard;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import Custom.Adapter.CustomAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bharat.kumar.digitracker.R;

public class DashboardFragment extends Fragment  {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private UsageStatsManager mUsageStatsManager ;
    private PackageManager mPacketManager;
    private final ArrayMap<String, String> mAppUsageMap = new ArrayMap<String, String>();
    private final ArrayMap<String, Drawable> mAppLogoMap = new ArrayMap<>();
    private final ArrayList<UsageStats> mPackageStats = new ArrayList<>();

    public static class UsageTimeComparator implements Comparator<UsageStats> {
        @Override
        public final int compare(UsageStats a, UsageStats b) {
            return (int) (b.getTotalTimeInForeground() - a.getTotalTimeInForeground());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.app_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        dataInitializer();
        Collections.sort(mPackageStats,new UsageTimeComparator());
        adapter = new CustomAdapter(mPackageStats, mAppUsageMap,mAppLogoMap);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.app.Activity activity = getActivity();
        mUsageStatsManager = (UsageStatsManager) activity.getSystemService(Context.USAGE_STATS_SERVICE);
        mPacketManager = activity.getPackageManager();
    }

    public void dataInitializer() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -5);
        // mUsageStatsManager.queryUsageStats use for get usage statistics for 5 days
        final List<UsageStats> stats =
                mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST,
                        cal.getTimeInMillis(), System.currentTimeMillis());
        if (stats == null) {
            return;
        }

        ArrayMap<String, UsageStats> map = new ArrayMap<>();
        final int statCount = stats.size();
        for (int i = 0; i < statCount; i++) {
            final android.app.usage.UsageStats pkgStats = stats.get(i);

            //load application labels for each application
            try {
                ApplicationInfo appInfo = mPacketManager.getApplicationInfo(pkgStats.getPackageName(), 0);
                String label = appInfo.loadLabel(mPacketManager).toString();
                String useTime = DateUtils.formatElapsedTime(pkgStats.getTotalTimeInForeground() / 1000);
                if(!useTime.equals("00:00")){
                    mAppUsageMap.put(pkgStats.getPackageName(),useTime );
                    mAppLogoMap.put(pkgStats.getPackageName(),appInfo.loadIcon(mPacketManager));
                    UsageStats existingStats =
                            map.get(pkgStats.getPackageName());
                    if (existingStats == null) {
                        map.put(pkgStats.getPackageName(), pkgStats);
                    } else {
                        existingStats.add(pkgStats);
                    }
                    Log.e("one", map.get(pkgStats.getPackageName())+"");
                }
            } catch (PackageManager.NameNotFoundException e) {
                // This package may be gone.
            }
        }
        mPackageStats.addAll(map.values());
    }
}