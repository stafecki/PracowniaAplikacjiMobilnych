package edu.stafec.a2026_02_02;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class OutputDataFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.data, container, false);

        TextView emailTextView = view.findViewById(R.id.EmailTextView);
        TextView nameTextView = view.findViewById(R.id.NameTextView);
        TextView surnameTextView = view.findViewById(R.id.SurnameTextView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String email = bundle.getString("email");
            String name = bundle.getString("name");
            String surname = bundle.getString("surname");

            emailTextView.setText(email);
            nameTextView.setText(name);
            surnameTextView.setText(surname);
        }

        return view;
    }
}
