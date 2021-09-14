package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final  String  DATABASE_NAME = "DIGITAL_TRACKER.db";
    private static final  String  TABLE_NAME = "USAGE_TABLE";
    private static final  String  COL1 = "PACKAGE_NAME";
    private static final  String  COL2 = "USE_TIME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+
                "(PACKAGE_NAME TEXT," +
                "USE_TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String PackageName,String  usage){
        SQLiteDatabase db  = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,PackageName);
        contentValues.put(COL2,usage);
        //contentValues.put(COL2,COL1);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != -1;
    }

    public Boolean update(String PackageName,String  usage){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        //todo: check here^^^
        contentValues.put(COL2,usage);
        db.update(TABLE_NAME,contentValues,"PACKAGE_NAME=?",new String[]{PackageName});
        return  true;
    }

    public Cursor getDate(String PackageName){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE PACKAGE_NAME='"+PackageName+"'";
        return db.rawQuery(query,null);
    }


    public Integer delete(String PackageName){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME, "PACKAGE_NAME=?", new String[]{PackageName});
    }


    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }

}
