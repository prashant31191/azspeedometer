package com.roomdb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.azspeedometer.R;
import com.roomdb.adapter.MyRoutesAdapter;
import com.roomdb.model.MyRoute;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MyRouteListActivity extends AppCompatActivity implements MyRoutesAdapter.OnMyRouteItemClick{

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private MyRoomDatabase mMyRoomDatabase;
    private List<MyRoute> listMyRoutes;
    private MyRoutesAdapter mMyRoutesAdapter;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);
        initializeVies();
        displayList();
    }

    private void displayList(){
        mMyRoomDatabase = MyRoomDatabase.getInstance(MyRouteListActivity.this);
        new RetrieveTask(this).execute();
    }

    private static class RetrieveTask extends AsyncTask<Void,Void,List<MyRoute>>{

        private WeakReference<MyRouteListActivity> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(MyRouteListActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<MyRoute> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().mMyRoomDatabase.getMyRouteDao().getMyRoutes();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<MyRoute> listMyRoutes) {
            if (listMyRoutes!=null && listMyRoutes.size()>0 ){
                activityReference.get().listMyRoutes.clear();
                activityReference.get().listMyRoutes.addAll(listMyRoutes);
                // hides empty text view
                activityReference.get().textViewMsg.setVisibility(View.GONE);
                activityReference.get().mMyRoutesAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initializeVies(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewMsg =  (TextView) findViewById(R.id.tv_empty);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(listener);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRouteListActivity.this));
        listMyRoutes = new ArrayList<>();
        mMyRoutesAdapter = new MyRoutesAdapter(listMyRoutes,MyRouteListActivity.this);
        recyclerView.setAdapter(mMyRoutesAdapter);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(new Intent(MyRouteListActivity.this,AddNoteActivity.class),100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode > 0 ){
            if( resultCode == 1){
                listMyRoutes.add((MyRoute) data.getSerializableExtra("myroute"));
            }else if( resultCode == 2){
                listMyRoutes.set(pos,(MyRoute) data.getSerializableExtra("myroute"));
            }
            listVisibility();
        }
    }

    @Override
    public void onMyRouteClick(final int pos) {
        new AlertDialog.Builder(MyRouteListActivity.this)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                mMyRoomDatabase.getMyRouteDao().deleteMyRoute(listMyRoutes.get(pos));
                                listMyRoutes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
                                MyRouteListActivity.this.pos = pos;
                                startActivityForResult(
                                        new Intent(MyRouteListActivity.this,AddNoteActivity.class)
                                                .putExtra("myroute",listMyRoutes.get(pos)),
                                        100);

                                break;
                        }
                    }
                }).show();

    }

    private void listVisibility(){
        int emptyMsgVisibility = View.GONE;
        if (listMyRoutes.size() == 0){ // no item to display
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        mMyRoutesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mMyRoomDatabase.cleanUp();
        super.onDestroy();
    }
}