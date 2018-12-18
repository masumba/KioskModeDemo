package com.ads.agropay.kioskmodedemo;


import android.app.ActivityOptions;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLock;

    /**/
    public int clickCount = 0;
    static final int RESULT_ENABLE = 1;
    DevicePolicyManager devicePolicyManager;
    ComponentName componentName;
    private boolean kioskModeActive = false;
    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLock = (Button)findViewById(R.id.btnLock);

        /**/
        devicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = DeviceAdminReceiver.getComponentName(MainActivity.this);

        boolean active = devicePolicyManager.isAdminActive(componentName);

        /**/

        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**/
                runKioskMode();
                /**/
            }
        });
    }

    private void runKioskMode() {
        clickCount = 4;

        if (!kioskModeActive){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startLockTask();
                kioskModeActive = true;
            }
        } else if (kioskModeActive){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                stopLockTask();
                kioskModeActive = false;
            }
        }
    }

    public void kioskMode(View view){
        clickCount = clickCount+1;

        Toast.makeText(this,"Count: "+clickCount,Toast.LENGTH_SHORT).show();

        if (clickCount == 5){
            runKioskMode();
        }
    }

}
