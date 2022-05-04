package dk.itu.navigationexample;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import dk.itu.navigationexample.database.ItemBaseHelper;
import dk.itu.navigationexample.database.ItemCursorWrapper;
import dk.itu.navigationexample.database.ItemsDbSchema;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class ItemsDB extends ViewModel{
    private static SQLiteDatabase mDatabase;

    //creating the database
    public void initialize(Context context){
        if (mDatabase == null) {
            mDatabase= new ItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
            if (getValues().size() == 0) fillItemsDB();
        }
    }

    public void addItem(String empname, String role,String age){
        Item newItem= new Item(empname, role, age);
        ContentValues values= getContentValues(newItem);
        mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);
    }

    public void removeItem(String empname){
        Item newItem= new Item(empname, "","");
        String selection= ItemsDbSchema.ItemTable.Cols.NAME + " LIKE ?";
        int changed= mDatabase.delete(ItemsDbSchema.ItemTable.NAME, selection, new String[]{newItem.getWhat()});
    }

    @SuppressLint("Range")
    public String getWhere(String name){
        String designation="";
        String employeename=name;
        Cursor cursor = mDatabase.query(ItemsDbSchema.ItemTable.NAME, new String[]{"role"},
                ItemsDbSchema.ItemTable.Cols.NAME + " = ?", new String[]{employeename},
                null, null, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            designation = cursor.getString(cursor.getColumnIndex("role"));
        }
        return designation;
    }

    public void fillItemsDB() {
        addItem("baba", "backend","24");
        addItem("paul", "deployment lead","26");
        addItem("georguis", "frontend","28");
    }

    public ArrayList<Item> getValues() {
        ArrayList<Item> items= new ArrayList<Item>();
        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    // Database helper methods to convert between Items and database rows
    private static ContentValues getContentValues(Item item) {
        ContentValues values=  new ContentValues();
        values.put(ItemsDbSchema.ItemTable.Cols.NAME, item.getWhat());
        values.put(ItemsDbSchema.ItemTable.Cols.ROLE, item.getWhere());
        values.put(ItemsDbSchema.ItemTable.Cols.AGE, item.getAge());
        return values;
    }

    static private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor= mDatabase.query(
                ItemsDbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new ItemCursorWrapper(cursor);
    }
    public int size() {
        return getValues().size();
    }

    public void close() { mDatabase.close();   }
}
