package com.roomdb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azspeedometer.App;
import com.roomdb.model.MyRoute;

import java.util.List;

@Dao
public interface MyRouteDao {

    @Query("SELECT * FROM "+ App.TABLE_NAME_ROUTE)
    List<MyRoute> getMyRoutes();

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    long insertMyRoute(MyRoute myRoute);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void updateMyRoute(MyRoute repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void deleteMyRoute(MyRoute myRoute);

    // MyRoute... is varargs, here note is an array
    /*
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    void deleteMyRoutes(MyRoute... myRoute);

}