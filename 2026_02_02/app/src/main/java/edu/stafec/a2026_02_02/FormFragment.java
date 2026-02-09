package edu.stafec.a2026_02_02;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FormFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.form, container, false);

        EditText emailField = view.findViewById(R.id.EmailEditText);
        EditText nameField = view.findViewById(R.id.NameEditText);
        EditText surnameField = view.findViewById(R.id.SurnameEditText);
        Button submitButton = view.findViewById(R.id.SubmitButton);

        submitButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String name = nameField.getText().toString();
            String surname = surnameField.getText().toString();

            if(email.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                Toast.makeText(getActivity(), "Pola nie moga byc puste", Toast.LENGTH_SHORT).show();
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(getActivity(), "Niepoprawny adres e-mail", Toast.LENGTH_SHORT).show();
            }
            else{
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("name", name);
                bundle.putString("surname", surname);

                OutputDataFragment outputDataFragment = new OutputDataFragment();
                outputDataFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.FragmentContainer, outputDataFragment)
                        .commit();
            }
        });
        return view;
    }
}
