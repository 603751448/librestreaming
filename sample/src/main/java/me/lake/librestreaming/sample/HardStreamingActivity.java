package me.lake.librestreaming.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import me.lake.librestreaming.filter.hardvideofilter.BaseHardVideoFilter;
import me.lake.librestreaming.filter.hardvideofilter.HardVideoGroupFilter;
import me.lake.librestreaming.model.RESConfig;
import me.lake.librestreaming.sample.hardfilter.ColorMixHardFilter;
import me.lake.librestreaming.sample.hardfilter.SkinBlurHardVideoFilter;
import me.lake.librestreaming.sample.hardfilter.WhiteningHardVideoFilter;

/**
 * Created by lake on 16-5-31.
 */
public class HardStreamingActivity extends BaseStreamingActivity {
    EditText tv_r,tv_g,tv_b,tv_a,tv_w,tv_d;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        filtermode = RESConfig.FilterMode.HARD;
        super.onCreate(savedInstanceState);
        LinkedList<BaseHardVideoFilter> filters = new LinkedList<>();
        filters.add(new SkinBlurHardVideoFilter(2,0.08f));
        filters.add(new ColorMixHardFilter(0.98f, 0.72f, 0.82f, 0.3f));
        filters.add(new WhiteningHardVideoFilter(0.7f));
        resClient.setHardVideoFilter(new HardVideoGroupFilter(filters));
        findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float r, g, b, a, w, d;
                r = Integer.valueOf(tv_r.getText().toString()) / 255.0f;
                g = Integer.valueOf(tv_g.getText().toString()) / 255.0f;
                b = Integer.valueOf(tv_b.getText().toString()) / 255.0f;
                a = Integer.valueOf(tv_a.getText().toString()) / 255.0f;
                w = Integer.valueOf(tv_w.getText().toString()) / 100.0f;
                d = Float.valueOf(tv_d.getText().toString());
                LinkedList<BaseHardVideoFilter> filters = new LinkedList<>();
                filters.add(new SkinBlurHardVideoFilter(2,d));
                filters.add(new ColorMixHardFilter(r, g, b, a));
                if (Integer.valueOf(tv_w.getText().toString()) != 0) {
                    filters.add(new WhiteningHardVideoFilter(w));
                }
                resClient.setHardVideoFilter(new HardVideoGroupFilter(filters));
            }
        });
        tv_r = (EditText) findViewById(R.id.tv_r);
        tv_g = (EditText) findViewById(R.id.tv_g);
        tv_b = (EditText) findViewById(R.id.tv_b);
        tv_a = (EditText) findViewById(R.id.tv_a);
        tv_w = (EditText) findViewById(R.id.tv_w);
        tv_d = (EditText) findViewById(R.id.tv_d);
        tv_r.setText("250");
        tv_g.setText("184");
        tv_b.setText("209");
        tv_a.setText("77");
        tv_w.setText("70");
        tv_d.setText("0.08");
    }

}
