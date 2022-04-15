package dk.itu.navigationexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import androidx.fragment.app.FragmentManager;

import androidx.navigation.Navigation;

public class BFragment extends Fragment {
    Button backToAButton;
    Fragment fragmentList;
    private FragmentManager fm;


    private TextView empname,designation;
    private Button search;
    //model: database of items
    private ItemsViewModel itemsDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_b, container, false);
        //This code is for navigation to the Homepage component(A fragment) from the dashboard component(B component)
        backToAButton = v.findViewById(R.id.back_to_A_button);



        backToAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_BFragment_to_AFragment);
            }
        });
        //code for navigation ends here

        //This code is to search the designation of the employee
        empname = v.findViewById(R.id.Employee_name);
        designation = v.findViewById(R.id.designation);
        search = v.findViewById(R.id.Search);

        // Shared data
        itemsDB= new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Employee="";
                Employee= empname.getText().toString().trim();
                empname.setBackgroundColor(Color.parseColor("#FFFFFF"));
                designation.setText(itemsDB.getWhere(Employee));
            }
        });
        //code for searching ends here
        return v;
    }

}
