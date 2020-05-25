import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class createDatabase extends  SQLiteOpenHelper{


    private static final String NAME = "data";
    private static final String TABLE = "products";
    private static final String ID = "_id";
    private static final String PRODUCTNAME = "name";
    private static final String BUYPRICE = "buy-price";
    private static final String SELLPRICE = "sell-price";
    private static final String AMOUNT =  "amount";
    private static final int VERSION = 1;


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public createDatabase(Context context) {
        super(context, NAME, null, VERSION);
    }
}
