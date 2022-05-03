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
    private final List<Item> values = new ArrayList<>();

    public ItemsDB() {
        values.add(new Item("Yuni", "Front-end", "27"));
        values.add(new Item("William", "Fullstack", "27"));
        values.add(new Item("Baba", "Back-end", "23"));
    }

    public void addItem(String employeename, String role, String age) {
        values.add(new Item(employeename, role, age));
    }

    public void removeItem(String employeename) {
        for (Item t : values) {
            if (t.getWhat().equals(employeename)) {
                values.remove(t);
                break;
            }
        }
    }

    public Item getWhere(String employeename) {
        Item theItem = null;
        for (Item t : values) {
            if (t.getWhat().equals(employeename)) {
                theItem = t;
            }
        }
        return theItem;
    }


    public int size() {
        return values.size();
    }

    public String listItems() {
        StringBuilder r = new StringBuilder();
        for (Item i : values) r.append(i.toString()).append("\n");
        return r.toString();
    }

    public List<Item> getValues() {  return values;  }
}
