package com.micro0.rewardsprogram.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.analytics.tracking.android.EasyTracker;


public class SharedPreff extends Activity {

    SharedPreferences sharedPreferences;
    //private final android.os.Handler mHandler = new android.os.Handler();
    //private ColorAnimationDrawable mActionBarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preff);

        //Action Bar Color Animation
        //mActionBarBackground = new ColorAnimationDrawable();
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
           // mActionBarBackground.setCallback(mDrawableCallback);
        //} else {
         //   getActionBar().setBackgroundDrawable(mActionBarBackground);
       // }
        // getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        //make action bar navigate back
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
       // mActionBarBackground.start();
        //EditTexts
        final EditText nametext = (EditText)findViewById(R.id.editText);
        final EditText branchtext = (EditText)findViewById(R.id.editText2);
        final RadioButton RBmale = (RadioButton)findViewById(R.id.radioButton);
        final RadioButton RBfemale = (RadioButton)findViewById(R.id.radioButton2);

        //check shared prefrences exist or not
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String sharedusername = sharedPreferences.getString("UserName",null);
        String sharedbranchname = sharedPreferences.getString("BranchName",null);
        Boolean sharedkind = sharedPreferences.getBoolean("Kind",true);
        Boolean sharedkind1 = sharedPreferences.getBoolean("Kind",false);
        if (sharedusername != null) {
            nametext.setText(sharedusername.toString());
            branchtext.setText(sharedbranchname.toString());
            if (sharedkind==true)
            {
                RBmale.setChecked(true);
                RBfemale.setChecked(false);
            }
            else
            {
                RBfemale.setChecked(true);
                RBmale.setChecked(false);
            }

        }

        //Button Save
        Button btn_save = (Button)findViewById(R.id.button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UserName",nametext.getText().toString());
                editor.putString("BranchName",branchtext.getText().toString());
                if (RBmale.isChecked())
                {
                    editor.putBoolean("Kind",true);
                }
                else if (RBfemale.isChecked())
                {
                    editor.putBoolean("Kind",false);
                }
                editor.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mActionBarBackground.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // mActionBarBackground.stop();
    }

    @Override
    public void onStart() {
        super.onStart();
         // The rest of your onStart() code.
        EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        // The rest of your onStop() code.
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shared_preff, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        //if (id == R.id.action_settings) {
         //   return true;
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
   // private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        //@Override
        //public void invalidateDrawable(Drawable who) {
         //   getActionBar().setBackgroundDrawable(who);
        //}

        //@Override
        //public void scheduleDrawable(Drawable who, Runnable what, long when) {
         //   mHandler.postAtTime(what, when);
        //}

        //@Override
        //public void unscheduleDrawable(Drawable who, Runnable what) {
          //  mHandler.removeCallbacks(what);
        //}
    //};
    @Override
    public void onBackPressed() {
        this.finish();
    }

}
