package dk.itu.navigationexample.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dk.itu.navigationexample.database.ItemsDbSchema.ItemTable;

public class ItemBaseHelper extends SQLiteOpenHelper {
  private static final int VERSION = 2;
  public static final String DATABASE_NAME = "shopping.db";

  public ItemBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + ItemTable.NAME + "(" +
        ItemTable.Cols.NAME + ", " + ItemTable.Cols.ROLE +", "+ ItemTable.Cols.AGE +")"
    );
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
