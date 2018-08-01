package com.muzadev.authenticatedfirestore.presenter;

import com.muzadev.authenticatedfirestore.model.Customer;

import java.util.List;

/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
public interface Contract {
    interface Presenter {
        void login(String email, String password);

        void onLogin(boolean isSuccess);

        void addNewCustomer(Customer customer);

        void onAddNewCustomer();

        void getCustomers();

        void onGetCustomer(List<Customer> customers);
    }

    interface Interactor {
        void login(String email, String password);

        void addNewCustomer(Customer customer);

        void getCustomer();
    }

    interface View {
        void onLogin(boolean isSuccess);

        void onGetCustomer(List<Customer> customers);

    }
}
