package dataBaseOp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLHelper extends SQLiteOpenHelper {

    private static SQLHelper instance;

    private static final String DATABASE_NAME = "";
    private static final int VERSION = 1;

    private SQLHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION);
    }

    public static SQLHelper getInstance(Context ctx){
        if(instance == null){
            instance = new SQLHelper(ctx);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(productsOp.CREATE_TABLE_PACKS);
        db.execSQL(productsOp.CREATE_TABLE_UNITS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + productsOp.TABLE_UNITS);
        db.execSQL("DROP TABLE IF EXISTS " + productsOp.TABLE_PACKS);

        onCreate(db);
    }
}