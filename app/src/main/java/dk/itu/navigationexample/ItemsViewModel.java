package dk.itu.navigationexample;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ItemsViewModel extends ViewModel {
    private static MutableLiveData<ItemsDB> items;

    public void initialize(Context context){
        items.getValue().initialize(context);
    }

    public ItemsViewModel() {
        items= new MutableLiveData<>();
        items.setValue(new ItemsDB());
    }

    public MutableLiveData<ItemsDB> getValue() { return items; }

    public void addItem(String employeename, String role, String age){
        ItemsDB temp= items.getValue();
        temp.addItem(employeename, role, age);
        items.setValue(temp);
    }

    public void removeItem(String what){
        ItemsDB temp= items.getValue();
        temp.removeItem(what);
        items.setValue(temp);
    }

    public String getWhere(String employeename){
        ItemsDB temp = items.getValue();
        String designation=temp.getWhere(employeename);
        return designation;
    }

    public List<Item> getList() {  return items.getValue().getValues();  }

    public int size() { return items.getValue().size(); }
}