package com.muzadev.authenticatedfirestore.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muzadev.authenticatedfirestore.R;
import com.muzadev.authenticatedfirestore.model.Customer;
import com.muzadev.authenticatedfirestore.presenter.Contract;
import com.muzadev.authenticatedfirestore.presenter.Presenter;
import com.muzadev.authenticatedfirestore.util.Const;
import com.muzadev.authenticatedfirestore.view.adapter.CustomerAdapter;
import com.muzadev.authenticatedfirestore.view.dialogue.AddCustomerDialogue;

import java.util.List;


public class CustomerActivity extends AppCompatActivity implements Contract.View {
    private RecyclerView rvItem;
    private FloatingActionButton fabAdd;
    private Contract.Presenter presenter;
    private ProgressBar progressBar;
    private CustomerAdapter adapter;
    private CollectionReference customerColRef;
    private String uid;
//    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        presenter.getCustomers();
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
        rvItem.setLayoutManager(new LinearLayoutManager(this));
        fabAdd = findViewById(R.id.fabAdd);
//        parentView = findViewById(android.R.id.content);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        customerColRef = FirebaseFirestore.getInstance()
                .collection(Const.USER)
                .document(uid)
                .collection(Const.CUSTOMER);
        presenter = new Presenter(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onLogin(boolean isSuccess) {

    }

    @Override
    public void onGetCustomer(List<Customer> customers) {
        if (customers.size() > 0) {
            progressBar.setVisibility(View.GONE);
            adapter = new CustomerAdapter(this, customers);
            rvItem.setAdapter(adapter);
        }
    }

}
