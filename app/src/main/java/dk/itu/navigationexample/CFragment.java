package dk.itu.navigationexample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class CFragment extends Fragment {
    Button nextButton_to_B;
    private Button nextButton_to_A;
    private TextView name,age,role;
    private Button Addbtn;

    //model: database of items
   private ItemsViewModel itemsDB;
    //private ItemsDB itemsDB;
    //multimap concept

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v =inflater.inflate(R.layout.fragment_c, container, false);

        nextButton_to_B = v.findViewById(R.id.next_dashboard);
        nextButton_to_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_CFragment_to_BFragment);
            }
        });
        nextButton_to_A = v.findViewById(R.id.next_Homepage);
        nextButton_to_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_CFragment_to_AFragment);
            }
        });
        name = v.findViewById(R.id.add_employee_name);
        age = v.findViewById(R.id.add_age);
        role = v.findViewById(R.id.add_role);
        Addbtn = v.findViewById(R.id.Add_items);

        itemsDB= new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);

        //code for adding the employee information into data structure(hashmap/Arraylist)
        Addbtn.setOnClickListener(view -> {
            String nameofemployee= name.getText().toString().trim();
            String ageofemployee= age.getText().toString().trim();
            String roleofemployee= role.getText().toString().trim();
            Log.d("name","name is: "+nameofemployee);
            Log.d("name","role is: "+roleofemployee);
            Log.d("name","age is: "+ageofemployee);

            if ((nameofemployee.length() > 0) &&(ageofemployee.length()>0) &&(roleofemployee.length()>0)) {
                Log.d("name","name is: "+nameofemployee.length());
                Log.d("name","role is: "+roleofemployee.length());

                itemsDB.addItem(nameofemployee, roleofemployee,ageofemployee);
                Log.d("Emp1","details are:"+itemsDB.getWhere(nameofemployee));

                Log.d("Emp","details are:"+itemsDB.getWhere(nameofemployee));
                name.setText("");
                role.setText("");
                age.setText("");
            } else Toast.makeText(getActivity(), "please type something in name & age & role fields", Toast.LENGTH_LONG).show();
        });

        return v;
    }
}
