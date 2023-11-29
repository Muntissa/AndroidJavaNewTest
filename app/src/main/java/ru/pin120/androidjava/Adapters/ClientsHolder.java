package ru.pin120.androidjava.Adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import ru.pin120.androidjava.R;

public class ClientsHolder extends RecyclerView.ViewHolder {

    TextView name, email, phone;

    ImageButton ed_btn, del_btn;
    CardView card;
    boolean opened = false;


    public ClientsHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.clientsListItem_Name);
        email = itemView.findViewById(R.id.clientsListItem_Email);
        phone = itemView.findViewById(R.id.clientsListItem_Phone);
        ed_btn = itemView.findViewById(R.id.ed_btn);
        del_btn = itemView.findViewById(R.id.del_btn);
        card = itemView.findViewById(R.id.item_card);
    }
}
