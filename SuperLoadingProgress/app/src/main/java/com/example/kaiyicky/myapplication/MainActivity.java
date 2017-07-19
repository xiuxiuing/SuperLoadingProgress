package com.example.kaiyicky.myapplication;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public class MainActivity extends FragmentActivity {
    SuperLoadingProgress mSuperLoadingProgress;
    private static int step = 1;
    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSuperLoadingProgress = (SuperLoadingProgress) findViewById(R.id.pro);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count = 0;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (count < 5) {
                                if (mSuperLoadingProgress.getProgress() >= 100) {
                                    mSuperLoadingProgress.setProgress(-100);
                                    count++;
                                }
                                // if (mSuperLoadingProgress.getProgress() <= 0) {
                                // step = 1;
                                // }

//                                Thread.sleep(10);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + step);
                            }
                            mSuperLoadingProgress.finishFail();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();



            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(10);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishSuccess();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

}
