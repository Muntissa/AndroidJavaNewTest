package ru.pin120.androidjava.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pin120.androidjava.Api.ClientsApi;
import ru.pin120.androidjava.Entities.Clients;
import ru.pin120.androidjava.R;
import ru.pin120.androidjava.REST.RESTHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText secondNameET = findViewById(R.id.clientForm_SecondName);
        TextInputEditText firstNameET = findViewById(R.id.clientForm_FirstName);
        TextInputEditText lastNameET = findViewById(R.id.clientForm_LastName);
        TextInputEditText emailET = findViewById(R.id.clientForm_Email);
        TextInputEditText phoneET = findViewById(R.id.clientForm_Phone);

        Button saveBTN = findViewById(R.id.clientForm_AddClientBTN);

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