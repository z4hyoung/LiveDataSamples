package com.z4hyoung.livedatasamples;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModelFactory factory = new MyViewModelFactory(this.toString());
        MyViewModel model = ViewModelProviders.of(this, factory).get(MyViewModel.class);
        Observer<String> textObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "text updated to " + s);
            }
        };
        model.getText().observe(this, textObserver);
        model.getSequence().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d(TAG, "sequence updated to " + integer);
            }
        });
        model.increaseSequence();
        model.updateText(textObserver.toString());
    }
}
