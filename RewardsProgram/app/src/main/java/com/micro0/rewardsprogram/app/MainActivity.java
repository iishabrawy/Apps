package com.micro0.rewardsprogram.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import java.util.Locale;


public class MainActivity extends Activity {

    //private final android.os.Handler mHandler = new android.os.Handler();
    Button mm;
    //private ColorAnimationDrawable mActionBarBackground;
    SharedPreferences sharedPreferences;
    EditText exce , v_good , good , fail;
    TextView TotalView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check if language is english  or arabic
        checklang();



        //check shared prefrences exist or not
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        String value = sharedPreferences.getString("UserName",null);
        if (value == null) {
            Intent pp = new Intent(getApplicationContext(), SharedPreff.class);
            startActivity(pp);
        }
        else {
            TextView namee = (TextView) findViewById(R.id.name);
            namee.setTextColor(Color.RED);
            if (sharedPreferences.getBoolean("Kind",true)) {
                namee.setText(getResources().getText(R.string.mr_welcome)+sharedPreferences.getString("UserName", "").toString());

            }
            else {
                namee.setText(getResources().getText(R.string.mrs_welcome)+sharedPreferences.getString("UserName", "").toString());
            }
        }
        variables();


        //Action Bar Color Animation
        //mActionBarBackground = new ColorAnimationDrawable();
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
         //   mActionBarBackground.setCallback(mDrawableCallback);
       // } else {
         //   getActionBar().setBackgroundDrawable(mActionBarBackground);
       // }
        // getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        //make action bar navigate back
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //mActionBarBackground.start();
        //Button
        mm = (Button) findViewById(R.id.button);
        //Button To Calculate
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent pp = new Intent(getApplicationContext(), cam.class);
                //startActivity(pp);
                //variables();
                checkcalc();

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
         // The rest of your onStart() code.
        EasyTracker.getInstance(this).activityStart(this);  // Add this method.

    }

    public void variables()
    {
        exce = (EditText)findViewById(R.id.editText4);
        v_good = (EditText)findViewById(R.id.editText3);
        good = (EditText)findViewById(R.id.editText2);
        fail = (EditText)findViewById(R.id.editText);
        fail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId== EditorInfo.IME_ACTION_DONE) {
                    mm.performClick();
                    handled=true;
                }
                return handled;
            }
        });
        TotalView = (TextView)findViewById(R.id.textView7);


    }
    public  void checkcalc()
    {
        if (exce.getText().toString().matches("")){
            exce.setText("0");
        }
        if (v_good.getText().toString().matches("")){
            v_good.setText("0");
        }
        if (good.getText().toString().matches("")){
            good.setText("0");
        }
        if (fail.getText().toString().matches("")){
            fail.setText("0");
        }
        Calculate cal = new Calculate();
        int e,v,g,f,tootal;
        e =Integer.parseInt(exce.getText().toString());
        v=Integer.valueOf(v_good.getText().toString());
        g=Integer.valueOf(good.getText().toString());
        f=Integer.valueOf(fail.getText().toString());

        cal.calculatee(e,v,g,f);
        tootal = cal.totall();
        Toast ttoast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.reward_result) + tootal, Toast.LENGTH_LONG);
        ttoast.setGravity(Gravity.BOTTOM,0,0);
        ttoast.show();

        TotalView.setVisibility(View.VISIBLE);
        String Resullt = (getResources().getString(R.string.reward_result)+"<b>"+tootal+"</b>");
        TotalView.setTextColor(Color.RED);
        //TotalView.setTypeface(null, Typeface.BOLD);
        TotalView.setText(Html.fromHtml(Resullt));
        //Blink or make text Flashing :D
       // Animation anim = new AlphaAnimation(0.0f, 1.0f);
        //anim.setDuration(500); //You can manage the time of the blink with this parameter
        //anim.setStartOffset(0);
        //anim.setRepeatMode(Animation.REVERSE);
        //anim.setRepeatCount(Animation.INFINITE);
        //TotalView.startAnimation(anim);
        //To Hide The Keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(fail.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
    @Override
    protected void onResume() {
        super.onResume();
       // mActionBarBackground.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //mActionBarBackground.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
         // The rest of your onStop() code.
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.

    }

    public void checklang() {
        ImageView mmm = (ImageView) findViewById(R.id.imageView);
        TextView tt = (TextView) findViewById(R.id.textView5);

        if (Locale.getDefault().getDisplayLanguage().startsWith("En")) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            //params1.addRule(RelativeLayout.ALIGN_PARENT_END);
            params1.addRule(RelativeLayout.RIGHT_OF, mmm.getId());
            mmm.setLayoutParams(params);
            tt.setLayoutParams(params1);
        } else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_START);
            params1.addRule(RelativeLayout.LEFT_OF, mmm.getId());
            mmm.setLayoutParams(params);
            tt.setLayoutParams(params1);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.dialog_title))
                .setMessage(getResources().getString(R.string.dialog_message))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.report) {
            Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.menu_toast), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
           // Intent main = new Intent(getApplicationContext(), MainActivity2.class);
            //startActivity(main);
            return true;
        }
        return super.onOptionsItemSelected(item);
        //switch (item.getItemId()) {
          //  case android.R.id.home:
               // Intent main = new Intent(this, MainActivity.class);
                //startActivity(main);
            //    return true;
            //default:
                //return super.onOptionsItemSelected(item);
        //}
    }
    //private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
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
        //    mHandler.removeCallbacks(what);
      //  }
    //};

}
