package com.muzadev.authenticatedfirestore.presenter;

import com.muzadev.authenticatedfirestore.model.Customer;
import com.muzadev.authenticatedfirestore.model.FirebaseInstance;

/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
public class Presenter implements Contract.Presenter{
    private Contract.View view;
    private Contract.Interactor interactor;

    public Presenter(Contract.View view) {
        this.view = view;
        interactor = new FirebaseInstance(this);
    }

    public Presenter() {
        interactor = new FirebaseInstance(this);
    }


    @Override
    public void login(String email, String password) {
        interactor.login(email, password);
    }

    @Override
    public void loginComplete(boolean isSuccess) {
        view.onLoginFinish(isSuccess);
    }

    @Override
    public void addNewCustomer(Customer customer) {
        interactor.addNewCustomer(customer);
    }

    @Override
    public void addNewCustomerComplete() {

    }
}
