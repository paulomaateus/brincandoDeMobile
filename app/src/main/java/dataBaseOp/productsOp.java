package dataBaseOp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import objects.products;
import objects.productsPack;

public class productsOp {

    private SQLiteDatabase db;

    public static final String TABLE_UNITS = "units";
    public static final String TABLE_PACKS = "packs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BUY_PRICE= "buyPrice";
    private static final String COLUMN_SELL_PRICE = "sellPrice;";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_PACK_UNITS = "unitsPerPack";

    public static final String CREATE_TABLE_PACKS = "CREATE TABLE " + TABLE_PACKS
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_BUY_PRICE + " REAL NOT NULL, "
            + COLUMN_SELL_PRICE +" REAL NOT NULL, " + COLUMN_AMOUNT + " INTEGER NOT NULL, "
            + COLUMN_PACK_UNITS + " INTEGER NOT NULL)";

    public static final String CREATE_TABLE_UNITS = "CREATE TABLE " + TABLE_UNITS
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_BUY_PRICE + " REAL NOT NULL, "
            + COLUMN_SELL_PRICE +" REAL NOT NULL, " + COLUMN_AMOUNT + " INTEGER NOT NULL)";


    public productsOp (Context ctx){
        db = SQLHelper.getInstance(ctx).getWritableDatabase();
    }

    public long insertUnity (products product){
        long id = db.insert(TABLE_UNITS, null, unityContentValue(product));

        return id;
    }

    public long insertPack (productsPack product){
        long id = db.insert(TABLE_PACKS, null, packContentValue(product));

        return id;
    }

    public long deletePack(productsPack product){
        return  0; //todo
    }
    private ContentValues packContentValue(productsPack product){
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, product.getName());
        cv.put(COLUMN_BUY_PRICE, product.getBuyPrice());
        cv.put(COLUMN_SELL_PRICE, product.getSellPrice());
        cv.put(COLUMN_AMOUNT, product.getAmount());
        cv.put(COLUMN_PACK_UNITS, product.getAmountUnits());

        return cv;
    }

    private ContentValues unityContentValue(products product){
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, product.getName());
        cv.put(COLUMN_BUY_PRICE, product.getBuyPrice());
        cv.put(COLUMN_SELL_PRICE, product.getSellPrice());
        cv.put(COLUMN_AMOUNT, product.getAmount());

        return cv;
    }
}
