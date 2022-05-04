package dk.itu.navigationexample;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UIFragment extends Fragment {
    private TextView empname,designation;
    private Button search;
    //model: database of items
    private ItemsViewModel itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);

        //This code is to search the designation of the employee
        empname = v.findViewById(R.id.Employee_name);
        designation = v.findViewById(R.id.designation);
        search = v.findViewById(R.id.Search);

        // Shared data
        itemsDB= new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String Employee="";
                String Employee= empname.getText().toString().trim();
                //Item var = itemsDB.getWhere(Employee);
                empname.setBackgroundColor(Color.parseColor("#FFFFFF"));
                designation.setText(itemsDB.getWhere(Employee));
            }
        });
        //code for searching ends here
        return v;

    }
}

