package Custom.Adapter;

import android.app.usage.UsageStats;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;

import java.util.ArrayList;

public class DataClass {
    private final ArrayMap<String,String> mAppLable;
    private final ArrayMap<String,Long> mAppUsageTime;
    private final ArrayMap<String, Drawable> mAppLogo;
    private final ArrayList<UsageStats> mUsageState;

    public DataClass(ArrayList<UsageStats> mUsageState, ArrayMap<String, String> mAppLable, ArrayMap<String, Long> mAppUsage, ArrayMap<String, Drawable> mAppLogo) {
        this.mAppLable = mAppLable;
        this.mAppUsageTime = mAppUsage;
        this.mAppLogo = mAppLogo;
        this.mUsageState = mUsageState;
    }

    public ArrayMap<String, String> getmAppLable() {
        return mAppLable;
    }

    public ArrayMap<String, Long> getmAppUsageTime() {
        return mAppUsageTime;
    }

    public ArrayMap<String, Drawable> getmAppLogo() {
        return mAppLogo;
    }

    public ArrayList<UsageStats> getmUsageState() {
        return mUsageState;
    }
}
