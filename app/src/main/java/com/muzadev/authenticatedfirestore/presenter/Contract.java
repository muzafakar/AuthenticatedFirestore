package com.muzadev.authenticatedfirestore.presenter;

import com.muzadev.authenticatedfirestore.model.Customer;

/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
public interface Contract {
    interface Presenter {
        void login(String email, String password);

        void loginComplete(boolean isSuccess);

        void addNewCustomer(Customer customer);
        void addNewCustomerComplete();
    }

    interface Interactor {
        void login(String email, String password);

        void addNewCustomer(Customer customer);
    }

    interface View {
        void onLoginFinish(boolean isSuccess);
    }
}
