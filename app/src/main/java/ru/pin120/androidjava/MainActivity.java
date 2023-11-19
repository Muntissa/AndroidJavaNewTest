package ru.pin120.androidjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pin120.androidjava.Api.ClientsApi;
import ru.pin120.androidjava.Entities.Clients;
import ru.pin120.androidjava.REST.RESTHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText secondNameET = findViewById(R.id.form_secondName);
        TextInputEditText firstNameET = findViewById(R.id.form_firstName);
        TextInputEditText lastNameET = findViewById(R.id.form_lastName);
        TextInputEditText emailET = findViewById(R.id.form_email);
        TextInputEditText phoneET = findViewById(R.id.form_phone);

        MaterialButton saveBTN = findViewById(R.id.buttonSave);

        RESTHelper restHelper = new RESTHelper();
        ClientsApi clientsApi = restHelper.getRetrofit().create(ClientsApi.class);

        saveBTN.setOnClickListener(view -> {
            String secondName = String.valueOf(secondNameET.getText());
            String firstName = String.valueOf(firstNameET.getText());
            String lastName = String.valueOf(lastNameET.getText());
            String email = String.valueOf(emailET.getText());
            String phone = String.valueOf(phoneET.getText());

            Clients client = new Clients();
            client.setSecondName(secondName);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setPhone(phone);

            clientsApi.save(client)
                    .enqueue(new Callback<Clients>() {
                        @Override
                        public void onResponse(Call<Clients> call, Response<Clients> response) {
                            Toast.makeText(MainActivity.this, "Сохранение получилось!!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Clients> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Сохранение провалилось!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
        });
    }
}