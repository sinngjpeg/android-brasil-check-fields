package com.sinngjpeg.alurafood.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.sinngjpeg.alurafood.R;
import com.sinngjpeg.alurafood.ui.activities.validator.validatorFields;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class FormRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        initializerFields();

    }

    private void initializerFields() {
        configureFieldFullName();
        configureFieldCPF();
        configureFieldPhone();
        configureFieldEmail();
        configureFieldPassword();
    }

    private void configureFieldPassword() {
        TextInputLayout textInputPassword = findViewById(R.id.edt_password);
        addValidateField(textInputPassword);
    }

    private void configureFieldEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.edt_email);
        addValidateField(textInputEmail);
    }

    private void configureFieldPhone() {
        TextInputLayout textInputPhone = findViewById(R.id.edt_phone);
        addValidateField(textInputPhone);
    }

    private void configureFieldCPF() {
        TextInputLayout textInputCpf = findViewById(R.id.edt_cpf);
        EditText fieldCPF = textInputCpf.getEditText();
        CPFFormatter cpfFormatter = new CPFFormatter();

        fieldCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String cpf = fieldCPF.getText().toString();
                if (!hasFocus) {
                    if (!validator.isValid()) return;
                    if (!validateFieldWith11Digits(cpf, textInputCpf)) return;
                    if (!validateCalculationCPF(cpf, textInputCpf)) return;

                    String cpfFormatted = cpfFormatter.format(cpf);
                    fieldCPF.setText(cpfFormatted);
                } else {
                    try {
                        String cpfNotFormatted = cpfFormatter.unformat(cpf);
                        fieldCPF.setText(cpfNotFormatted);
                    } catch (IllegalArgumentException e) {
                        Log.e("erro formatação cpf", e.getMessage());
                    }
                }
            }
        });
    }



    private void configureFieldFullName() {
        TextInputLayout textInputFullName = findViewById(R.id.edt_full_name);
        addValidateField(textInputFullName);
    }

    private void addValidateField(final TextInputLayout textInputField) {
        final EditText field = textInputField.getEditText();
        validatorFields validator = new validatorFields(textInputField);
        field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validator.isValid()) return;

                }
            }
        });
    }

}