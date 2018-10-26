package base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;



public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        previewAction();
        setContentView(getContentView());
        initView();
        initData();
        setListener();
        setMoreAction();
    }

    public void previewAction() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public abstract int getContentView();

    public abstract void initView();

    public void initData(){

    }

    public void setListener(){

    }

    public void setMoreAction() {

    }
}
