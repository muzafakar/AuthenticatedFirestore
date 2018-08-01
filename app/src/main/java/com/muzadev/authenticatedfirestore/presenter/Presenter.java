package com.muzadev.authenticatedfirestore.presenter;

import com.muzadev.authenticatedfirestore.model.Customer;
import com.muzadev.authenticatedfirestore.model.FirebaseInstance;

import java.util.List;

/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
public class Presenter implements Contract.Presenter {
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
    public void onLogin(boolean isSuccess) {
        view.onLogin(isSuccess);
    }

    @Override
    public void addNewCustomer(Customer customer) {
        interactor.addNewCustomer(customer);
    }

    @Override
    public void onAddNewCustomer() {

    }

    @Override
    public void getCustomers() {
        interactor.getCustomer();
    }

    @Override
    public void onGetCustomer(List<Customer> customers) {
        view.onGetCustomer(customers);
    }
}
