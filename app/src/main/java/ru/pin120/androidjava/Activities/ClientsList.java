package ru.pin120.androidjava.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pin120.androidjava.Adapters.ClientsAdapter;
import ru.pin120.androidjava.Api.ClientsApi;
import ru.pin120.androidjava.Entities.Clients;
import ru.pin120.androidjava.R;
import ru.pin120.androidjava.REST.RESTHelper;

public class ClientsList extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listclients);

        recyclerView = findViewById(R.id.clientsList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadClients();
    }

    private void loadClients() {
        RESTHelper restHelper = new RESTHelper();
        ClientsApi clientsApi = restHelper.getRetrofit().create(ClientsApi.class);

        clientsApi.getAllClients()
                .enqueue(new Callback<List<Clients>>() {
                    @Override
                    public void onResponse(Call<List<Clients>> call, Response<List<Clients>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Clients>> call, Throwable t) {
                        Toast.makeText(ClientsList.this, "Не удалось загрузить клиентов", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Clients> clientsList) {
        ClientsAdapter clientsAdapter = new ClientsAdapter(clientsList);
        recyclerView.setAdapter(clientsAdapter);
    }

    /*    private void loadClients(){
        List<Clients> list = new ArrayList<>();

        Clients client = new Clients(0, "Name", "Second", "Last", "em", "phone");
        list.add(client);
        list.add(client);
        list.add(client);

        ClientsAdapter clientsAdapter = new ClientsAdapter(list);
        recyclerView.setAdapter(clientsAdapter);
    }*/
}