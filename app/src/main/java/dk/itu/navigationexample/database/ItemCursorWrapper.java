package dk.itu.navigationexample.database;
import android.database.Cursor;
import android.database.CursorWrapper;
import dk.itu.navigationexample.Item;
import dk.itu.navigationexample.database.ItemsDbSchema.ItemTable;

public class ItemCursorWrapper extends CursorWrapper {
  public ItemCursorWrapper(Cursor cursor) {
    super(cursor);
  }

  public Item getItem() {
    String name = getString(getColumnIndex(ItemTable.Cols.NAME));
    String role = getString(getColumnIndex(ItemTable.Cols.ROLE));
    String age = getString(getColumnIndex(ItemTable.Cols.AGE));
    return new Item(name, role,age);
  }
}