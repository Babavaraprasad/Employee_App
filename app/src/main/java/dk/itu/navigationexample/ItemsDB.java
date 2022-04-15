package dk.itu.navigationexample;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemsDB extends ViewModel{
    private static ItemsDB sItemsDB;
    private final Map<String,String> itemsMap = new HashMap<>();
    private final  List<Item> result = new ArrayList<>();
    public ItemsDB(Context context) {
        fillItemsDB(context, "garbage");
    }

    public static ItemsDB get() {
        if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDB;
    }

    public void fillItemsDB(Context context, String filename) {
        try {
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
            String line= reader.readLine();
            while (line != null) {
                String[] gItem= line.split(",");
                itemsMap.put(gItem[0], gItem[1]);
                line= reader.readLine();
            }
        } catch (IOException e) {}
    }

    public String getWhere(String name) {
        if (itemsMap.containsKey(name)) {
            return itemsMap.get(name);
        } else{
            return "not found";
        }
    }

    public String listItems() {
        String itemsList = "";
        for (String item : itemsMap.keySet()) {
            itemsList += item + " in " + itemsMap.get(item) + "\n";
        }
        return itemsList;
    }

    public void addItem(String name, String role)
    {
        itemsMap.put(name, role);
    }
    public void addItem2(String name, String age)
    {
        if (itemsMap.containsKey(name)) {
          itemsMap.put(name,age);
        }
    }

    public void removeItem(String what) {
        for (String item : itemsMap.keySet()) {
            if (item.equals(what)) {
                itemsMap.keySet().remove(item);
                break;
            }
        }
    }
    public String search(String query)
    {
        String result= "";
        if(query!=null) {
            if(itemsMap.containsKey(query))
            {
                String wherevalue = getWhere(query);
                result = query+" Designation is: "+wherevalue;
            }
            else { result = query + " Designation is: not found";
            }
        }
        return result;
    }
    public List<Item> getValues()
    {  return result;}

    public List<Item> getAll(){
        List<Item> result = new ArrayList<>();
        for (HashMap.Entry<String, String> item: itemsMap.entrySet()){
            result.add(new Item(item.getKey(), item.getValue()));
        }
        return result;
    }
    public int size() { return result.size(); }
}
