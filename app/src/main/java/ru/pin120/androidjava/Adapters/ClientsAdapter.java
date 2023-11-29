package ru.pin120.androidjava.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.pin120.androidjava.Entities.Clients;
import ru.pin120.androidjava.R;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsHolder> {

    private List<Clients> clientsList;

    public ClientsAdapter(List<Clients> clientsList) {
        this.clientsList = clientsList;
    }

    @NonNull
    @Override
    public ClientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_clients_item, parent, false);

        return new ClientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsHolder holder, int position) {
        Clients client = clientsList.get(position);

        holder.name.setText(client.getSecondName() + " " + client.getFirstName() + " " + client.getLastName());
        holder.email.setText(client.getEmail());
        holder.phone.setText(client.getPhone());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.opened){
                    holder.ed_btn.setVisibility(View.GONE);
                    holder.del_btn.setVisibility(View.GONE);
                }
                else{
                    holder.ed_btn.setVisibility(View.VISIBLE);
                    holder.del_btn.setVisibility(View.VISIBLE);
                }
                holder.opened = !holder.opened;
            }
        });
        holder.ed_btn.setVisibility(View.GONE);
        holder.del_btn.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return clientsList.size();
    }
}
