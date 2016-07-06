package com.example.ben.itrans;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helen_000 on 6/26/2016.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private List<String> BusNumbers;
    private List<Integer> BusIDs;
    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }
    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

    public MyApplication(){
        BusNumbers = new ArrayList<String>();
        BusIDs = new ArrayList<Integer>();
    }

    public long addToDatabase(String entryNo, Context c){
        DbAdapter db = new DbAdapter(c);
        db.open();

        long rowIDofInsertedEntry = db.insertEntry(entryNo);

        db.close();

        return rowIDofInsertedEntry;
    }

    public boolean deleteFromDatabase(int rowID, Context c){
        DbAdapter db = new DbAdapter(c);
        db.open();

        int id = BusIDs.get(rowID);

        boolean updateStatus = db.removeEntry(id);

        db.close();

        return updateStatus;
    }

    public List<String> retrieveAll(Context c){

        Cursor myCursor;
        String myString = "";

        DbAdapter db = new DbAdapter(c);
        db.open();
        BusIDs.clear();
        BusNumbers.clear();
        myCursor = db.retrieveAllEntriesCursor();

        if(myCursor != null && myCursor.getCount()>0){
            myCursor.moveToFirst();

            do{
                BusIDs.add(myCursor.getInt(db.COLUMN_KEY_ID));

                myString = myCursor.getString(db.COLUMN_BN_ID);

                BusNumbers.add(myString);
            }while(myCursor.moveToNext());
        }
        db.close();

        return BusNumbers;
    }

}
