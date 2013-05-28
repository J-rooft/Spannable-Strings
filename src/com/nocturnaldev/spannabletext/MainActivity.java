package com.nocturnaldev.spannabletext;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class MainActivity extends Activity {
    
    private static final String UNDERLINED_KEY = "UNDERLINED_KEY";
    private static final String COLORED_KEY = "COLORED_KEY";
    
    private TextView tv;
    
    private boolean colored = false;
    private boolean underlined = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.textView1);
        
        if (savedInstanceState != null) {
            colored = savedInstanceState.getBoolean(COLORED_KEY);
            underlined = savedInstanceState.getBoolean(UNDERLINED_KEY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onColorRed(View view) {
        if (colored) {
            return;
        }
        
        String text = getString(R.string.sentence);
        SpannableString spannable = new SpannableString(text);
        
        colorRed(text, spannable);
        
        if (underlined) {
            underline(text, spannable);
        }
        
        tv.setText(spannable, BufferType.SPANNABLE);
        
        colored = true;
    }

    public void onUnderline(View view) {
        if (underlined) {
            return;
        }
        
        String text = getString(R.string.sentence);
        SpannableString spannable = new SpannableString(text);

        underline(text, spannable);
        
        if (colored) {
            colorRed(text, spannable);
        }
        
        tv.setText(spannable, BufferType.SPANNABLE);
        
        underlined = true;
    }

    public void onClear(View view) {
        if (!colored && !underlined) {
            return;
        }
        
        String text = getString(R.string.sentence);
        tv.setText(text);
        
        colored = false;
        underlined = false;
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
        outState.putBoolean(COLORED_KEY, colored);
        outState.putBoolean(UNDERLINED_KEY, underlined);
    }
    
    private void colorRed(String text, SpannableString spannable) {
        String textToColor = " red";
        int start = text.indexOf(textToColor);
        if (start == -1) {
            return;
        }
        int end = start + textToColor.length();
        spannable.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    
    private void underline(String text, SpannableString spannable) {
        String textToUnderline = "underlined";
        int start = text.indexOf(textToUnderline);
        if (start == -1) {
            return;
        }
        int end = start + textToUnderline.length();
        spannable.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
