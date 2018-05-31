package com.roomdb.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.azspeedometer.App;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = App.TABLE_NAME_ROUTE)
public class MyRoute implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long route_id;

    @ColumnInfo(name = "note_route_data")
    // column name will be "note_route_data" instead of "route_data" in table
    private String route_data;

    private String title;

    private Date date;

    public MyRoute(String route_data, String title) {
        this.route_data = route_data;
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    @Ignore
    public MyRoute() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(long route_id) {
        this.route_id = route_id;
    }

    public String getRoute_data() {
        return route_data;
    }

    public void setRoute_data(String route_data) {
        this.route_data = route_data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyRoute)) return false;

        MyRoute myRoute = (MyRoute) o;

        if (route_id != myRoute.route_id) return false;
        return title != null ? title.equals(myRoute.title) : myRoute.title == null;
    }


    @Override
    public int hashCode() {
        int result = (int) route_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "route_id=" + route_id +
                ", route_data='" + route_data + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}