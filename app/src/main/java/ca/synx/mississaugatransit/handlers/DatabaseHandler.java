package ca.synx.mississaugatransit.handlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ca.synx.mississaugatransit.db.CacheRouteStopsTable;
import ca.synx.mississaugatransit.db.CacheRoutesTable;
import ca.synx.mississaugatransit.db.CacheStopTimesTable;
import ca.synx.mississaugatransit.db.CacheStopsTable;
import ca.synx.mississaugatransit.db.FavoriteTable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "transit.db";
    private static DatabaseHandler mDatabaseHandler;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mDatabaseHandler = this;
    }

    public static DatabaseHandler getInstance() {
        if (mDatabaseHandler == null)
            throw new IllegalArgumentException("DatabaseHandler class not initialized!");

        return mDatabaseHandler;
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CacheRoutesTable.CREATE_TABLE());
        db.execSQL(CacheRouteStopsTable.CREATE_TABLE());
        db.execSQL(CacheStopsTable.CREATE_TABLE());
        db.execSQL(CacheStopTimesTable.CREATE_TABLE());
        db.execSQL(FavoriteTable.CREATE_TABLE());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(CacheRoutesTable.DROP_TABLE());
        db.execSQL(CacheRouteStopsTable.DROP_TABLE());
        db.execSQL(CacheStopsTable.DROP_TABLE());
        db.execSQL(CacheStopTimesTable.DROP_TABLE());
        db.execSQL(FavoriteTable.DROP_TABLE());

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
