package com.roomdb;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.azspeedometer.App;
import com.roomdb.dao.MyRouteDao;
import com.roomdb.dao.NoteDao;
import com.roomdb.model.MyRoute;
import com.roomdb.model.Note;
import com.utils.DateRoomConverter;

@Database(entities = { Note.class, MyRoute.class }, version = 1)
@TypeConverters({DateRoomConverter.class})
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();
    public abstract MyRouteDao getMyRouteDao();

    private static MyRoomDatabase noteDB;

    public static MyRoomDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static MyRoomDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                MyRoomDatabase.class,
                App.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }

}
