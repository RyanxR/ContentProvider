package com.example.ryanxr.contentprovider;

/**
 * Created by RyanxR on 12/15/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.LinkedList;
import java.util.List;

public class Data extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "CompanyDB";

    public Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE =
                "CREATE TABLE companies ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "adress TEXT, " +
                "contact TEXT )";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS companies");
        this.onCreate(db);
    }

    private static final String companyTable = "offices";
    private static final String KEY_ID = "id";
    private static final String KEY_TEL = "tel";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";

    private static final String[] COLUMNS = {KEY_ID, KEY_TEL, KEY_NAME, KEY_ADDRESS};

    public void addOffice(Company COMPANY) {
        Log.d("addCompany", COMPANY.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEL, COMPANY.getTel());
        values.put(KEY_NAME, COMPANY.getName());
        values.put(KEY_ADDRESS, COMPANY.getAddress());
        db.insert(companyTable, null, values); // key/value -> keys = column names/ values = column values
        db.close();
    }

    public Company getCompany(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(companyTable, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null // h. limit
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Company COMPANY = new Company();
        COMPANY.setId(Integer.parseInt(cursor.getString(0)));
        COMPANY.setTel(Integer.parseInt(cursor.getString(1)));
        COMPANY.setName(cursor.getString(2));
        COMPANY.setAddress(cursor.getString(3));
        Log.d("getCompany(" + id + ")", COMPANY.toString());

        return COMPANY;
    }


    public List<Company> getAllOffices() {
        List<Company> Offices = new LinkedList<Company>();

        String query = "SELECT  * FROM " + companyTable;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Company COMPANY;
        if (cursor.moveToFirst()) {
            do {
                COMPANY = new Company();
                COMPANY.setId(Integer.parseInt(cursor.getString(0)));
                COMPANY.setTel(Integer.parseInt(cursor.getString(1)));
                COMPANY.setName(cursor.getString(2));
                COMPANY.setAddress(cursor.getString(3));
                Offices.add(COMPANY);
            } while (cursor.moveToNext());
        }
        Log.d("getAllCompanies()", Offices.toString());
        return Offices;
    }

    public int UpdateOffice(Company COMPANY) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("tel", COMPANY.getTel());
        values.put("name", COMPANY.getName());
        values.put("address", COMPANY.getAddress());
        int i = db.update(companyTable, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(COMPANY.getId())}
        ); //selection args
        db.close();
        return i;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS companies");
        String CREATE_BOOK_TABLE = "CREATE TABLE companies ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tel TEXT, " +
                "name TEXT, " +
                "address TEXT )";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    public void deleteOffice(Company COMPANY) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(companyTable,
                KEY_ID + " = ?",
                new String[]{String.valueOf(COMPANY.getId())}
        );
        db.close();
        Log.d("delete company", COMPANY.toString());
    }
}