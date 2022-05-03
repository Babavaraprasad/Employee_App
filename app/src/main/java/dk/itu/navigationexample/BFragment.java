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
    Fragment fragmentUI,fragmentList;
    private FragmentManager fm;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Dashboard");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_b, container, false);
        //This code is for navigation to the Homepage component(A fragment) from the dashboard component(B component)
        fm = getParentFragmentManager();
        fragmentUI= fm.findFragmentById(R.id.container_ui);
        fragmentList = fm.findFragmentById(R.id.container_list);
        setUpFragments();

        backToAButton = v.findViewById(R.id.back_to_A_button);
        backToAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_BFragment_to_AFragment);
            }
        });
        //code for navigation ends here

        return v;
    }
    private void setUpFragments() {
        fragmentUI= fm.findFragmentById(R.id.container_ui);
        fragmentList= fm.findFragmentById(R.id.container_list);

        fragmentUI= new UIFragment();
        fm.beginTransaction()
                .add(R.id.container_ui, fragmentUI)
                .commit();

        fragmentList= new ListFragment();
        fm.beginTransaction()
                .add(R.id.container_list, fragmentList)
                .commit();

    }

}
