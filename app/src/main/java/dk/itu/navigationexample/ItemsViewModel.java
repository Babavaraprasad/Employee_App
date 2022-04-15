package dk.itu.navigationexample;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ItemsViewModel extends AndroidViewModel {
    private static MutableLiveData<ItemsDB> items;

    public ItemsViewModel(Application application) {
        super(application);
        items= new MutableLiveData<>();
        items.setValue(new ItemsDB(application));
    }

    public MutableLiveData<ItemsDB> getValue() { return items; }

    public String getWhere(String nameofemployee){
        ItemsDB temp= items.getValue();
        return temp.getWhere(nameofemployee);
    }

    public void addItem(String name, String role){
        ItemsDB temp= items.getValue();
        temp.addItem(name, role);
        items.setValue(temp);
    }
/*
    public void addItem2(String name, String age){
        ItemsDB temp= items.getValue();
        temp.addItem2(name, age);
        items.setValue(temp);
    }
 */
    public List<Item> getList() {
        return items.getValue().getAll();
    }

    public int size() { return items.getValue().getAll().size();}

    public void removeItem(String what){
        ItemsDB temp = items.getValue();
        temp.removeItem(what);
        items.setValue(temp);
    }
}