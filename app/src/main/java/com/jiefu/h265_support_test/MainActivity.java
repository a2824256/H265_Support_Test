package com.jiefu.h265_support_test;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.test_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean H265_Encode = isH265EncoderSupport();
                boolean H265_Decode = isH265DecoderSupport();
                String tips = "";
                if(H265_Encode){
                    tips += "支持H265硬编码，";
                }else{
                    tips += "不支持H265硬编码，";
                }

                if(H265_Decode){ 
                    tips += "支持H265硬解码。";
                }else{
                    tips += "不支持H265硬解码。";
                }
                Toast toast=Toast.makeText(MainActivity.this,tips, Toast.LENGTH_SHORT    );
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

//  检测是否支持H265编码
    public static boolean isH265EncoderSupport(){
        boolean result = false;
        int count = MediaCodecList.getCodecCount();
        for(int i=0;i<count;i++){
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            String name = info.getName();
            boolean b = info.isEncoder();
            if(b && name.contains("hevc")){
                return true;
            }
        }
        return false;
    }
//  检测是否支持H265解码
    public static boolean isH265DecoderSupport(){
        int count = MediaCodecList.getCodecCount();
        for(int i=0;i<count;i++){
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            String name = info.getName();
            if(name.contains("decoder") && name.contains("hevc")){
                return true;
            }
        }
        return false;
    }
}
