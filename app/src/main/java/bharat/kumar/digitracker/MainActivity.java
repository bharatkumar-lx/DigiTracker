package bharat.kumar.digitracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import usage.tracker.UsageStatsActivity;

import android.Manifest;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i  = new Intent(this, UsageStatsActivity.class);
        ImageButton das = findViewById(R.id.dash_button);
        das.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
        Boolean granted = false;
        int internet = getApplicationContext().checkPermission(Manifest.permission.INTERNET,Process.myPid(),Process.myUid());
        Log.d("permissionfuck","internet permission "+ internet);
        AppOpsManager appOps = (AppOpsManager) getApplicationContext()
                                .getSystemService(Context.APP_OPS_SERVICE);

        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getApplicationContext().getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (getApplicationContext().checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        if(!granted){
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }
    }



}