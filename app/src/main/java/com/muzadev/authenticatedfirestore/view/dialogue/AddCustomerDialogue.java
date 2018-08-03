package com.muzadev.authenticatedfirestore.view.dialogue;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.muzadev.authenticatedfirestore.R;
import com.muzadev.authenticatedfirestore.model.Customer;
import com.muzadev.authenticatedfirestore.presenter.Contract;
import com.muzadev.authenticatedfirestore.presenter.Presenter;

import java.util.List;


/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
public class AddCustomerDialogue extends DialogFragment implements View.OnClickListener, Contract.View {
    private EditText etName, etAddress;
    private Contract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int theme = android.R.style.Theme_Holo_Light_Dialog;
        setStyle(DialogFragment.STYLE_NO_TITLE, theme);
        presenter = new Presenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogue_new_customer, container, false);
        etName = view.findViewById(R.id.etName);
        etAddress = view.findViewById(R.id.etAddress);
        view.findViewById(R.id.btnAdd).setOnClickListener(this);
        view.findViewById(R.id.btnCancel).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();
                Customer customer = new Customer(name, address, null);
                presenter.addNewCustomer(customer);
                getDialog().dismiss();
                break;
            case R.id.btnCancel:
                getDialog().dismiss();
                break;
        }
    }

    @Override
    public void onLogin(boolean isSuccess) {

    }

    @Override
    public void onGetCustomer(List<Customer> customers) {
    }

}
