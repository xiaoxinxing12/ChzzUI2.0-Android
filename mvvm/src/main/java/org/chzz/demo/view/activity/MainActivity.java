package org.chzz.demo.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.chzz.demo.R;
import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.util.RxSchedulers;
import org.chzz.demo.util.ToastUtil;

import java.util.List;

public class MainActivity extends BaseActivity {
    Button mTest;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mTest = (Button) findViewById(R.id.but_test);
    }

    @Override
    protected void setListener() {

        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEngine.loadNewData(1)
                        .compose(RxSchedulers.io_main())
                        .subscribe(list -> {
                            result(list);

                        }, t -> {
                            showError(t.getLocalizedMessage());
                        });
            }
        });


    }

    private void result(List<RefreshModel> list) {
        ToastUtil.show(list.size() + "<<");
        int a = 1 / 0;
    }

    private void showError(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }
}
