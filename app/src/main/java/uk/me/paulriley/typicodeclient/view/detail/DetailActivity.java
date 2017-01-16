package uk.me.paulriley.typicodeclient.view.detail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @Inject DetailPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ((TypicodeApplication) getApplication()).inject(this);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initialiseListView();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.initialise(this);
        mPresenter.getPost();
    }

    private void initialiseListView() {

    }
}
