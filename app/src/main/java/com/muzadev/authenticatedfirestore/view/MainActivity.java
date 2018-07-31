package com.muzadev.authenticatedfirestore.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.muzadev.authenticatedfirestore.R;
import com.muzadev.authenticatedfirestore.presenter.Contract;
import com.muzadev.authenticatedfirestore.view.dialogue.AddCustomerDialogue;


public class MainActivity extends AppCompatActivity implements Contract.View{
    private RecyclerView rvItem;
    private FloatingActionButton fabAdd;
    View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCustomerDialogue dialogue = new AddCustomerDialogue();
                dialogue.show(getFragmentManager(), "dialogue");
            }
        });
    }

    private void initUi() {
        rvItem = findViewById(R.id.rvItem);
        fabAdd = findViewById(R.id.fabAdd);
        parentView = findViewById(android.R.id.content);
    }

    @Override
    public void onLoginFinish(boolean isSuccess) {

    }
}
