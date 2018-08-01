package com.muzadev.authenticatedfirestore.model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.muzadev.authenticatedfirestore.presenter.Contract;
import com.muzadev.authenticatedfirestore.util.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
public class FirebaseInstance implements Contract.Interactor {
    private Contract.Presenter presenter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private CollectionReference customerCollRef;
    private String uid;

    public FirebaseInstance(Contract.Presenter presenter) {
        this.presenter = presenter;
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        customerCollRef = firestore.collection(Const.USER);

    }

    @Override
    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            presenter.onLogin(true);
                        } else {
                            presenter.onLogin(false);
                        }
                    }
                });
    }

    @Override
    public void addNewCustomer(Customer customer) {

        if (auth.getCurrentUser() != null) {
            uid = auth.getUid();
        }
        assert uid != null;
        customerCollRef
                .document(uid)
                .collection(Const.CUSTOMER)
                .add(customer)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            presenter.onAddNewCustomer();
                        }
                    }
                });
    }


    @Override
    public void getCustomer() {
        if (auth.getCurrentUser() != null) {
            uid = auth.getUid();
        }
        assert uid != null;
        customerCollRef
                .document(uid)
                .collection(Const.CUSTOMER)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Customer> customers = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                customers.add(document.toObject(Customer.class));
                            }
                            presenter.onGetCustomer(customers);
                        }
                    }
                });
    }
}
