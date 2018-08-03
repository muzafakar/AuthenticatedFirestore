package com.muzadev.authenticatedfirestore.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.muzadev.authenticatedfirestore.R;
import com.muzadev.authenticatedfirestore.model.Customer;
import com.muzadev.authenticatedfirestore.presenter.Contract;
import com.muzadev.authenticatedfirestore.presenter.Presenter;
import com.muzadev.authenticatedfirestore.util.Const;
import com.muzadev.authenticatedfirestore.view.adapter.CustomerAdapter;
import com.muzadev.authenticatedfirestore.view.dialogue.AddCustomerDialogue;

import java.util.ArrayList;
import java.util.List;


public class CustomerActivity extends AppCompatActivity implements Contract.View {
    private RecyclerView rvItem;
    public static final String TAG = "CustomerActivity";
    private Contract.Presenter presenter;
    private ProgressBar progressBar;
    private CustomerAdapter adapter;
    private CollectionReference customerColRef;
    private String uid;
    private FloatingActionButton fabAdd, fabLogout;
    private List<Customer> customerList;
    private View parentView;
    private FirebaseAuth user;

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

        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getCurrentUser() != null) {
                    user.signOut();
                    Snackbar.make(parentView, "log out", Snackbar.LENGTH_SHORT).show();
                    startActivity(new Intent(CustomerActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    private void initUi() {
        rvItem = findViewById(R.id.rvItem);
        rvItem.setLayoutManager(new LinearLayoutManager(this));
        fabAdd = findViewById(R.id.fabAdd);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        fabLogout = findViewById(R.id.fabLogout);
        user = FirebaseAuth.getInstance();
        customerList = new ArrayList<>();
        parentView = findViewById(android.R.id.content);
        customerColRef = FirebaseFirestore.getInstance()
                .collection(Const.USER)
                .document(uid)
                .collection(Const.CUSTOMER);
        presenter = new Presenter(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        customerColRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documents, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e(TAG, "onEvent: " + e.getMessage());
                    return;
                }

                if (documents != null && !documents.isEmpty()) {
                    if (customerList != null) {
                        customerList.clear();
                    }
                    for (DocumentSnapshot docs : documents.getDocuments()) {
                        customerList.add(docs.toObject(Customer.class));
                    }
                    updateList();
                } else {
                    Log.d(TAG, "onEvent: " + "no new document(s)");
                }
            }
        });
    }

    @Override
    public void onLogin(boolean isSuccess) {

    }

    @Override
    public void onGetCustomer(List<Customer> customers) {
        if (customerList != null) {
            customerList.clear();
        }
        customerList = customers;
        updateList();
    }

    private void updateList() {
        if (customerList.size() > 0) {
            progressBar.setVisibility(View.GONE);
            adapter = new CustomerAdapter(this, customerList);
            rvItem.setAdapter(adapter);
        }
    }


}
