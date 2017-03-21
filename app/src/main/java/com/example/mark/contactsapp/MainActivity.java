package com.example.mark.contactsapp;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public final String[] projection = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};

    private static final int CONTACT_ID_INDEX = 0;
    private static final int DISPLAY_NAME_INDEX = 1;


    public final int CONTACT_LOADER_ID = 777;
    private ContactAdapter contactAdapter;

    private RecyclerView recyclerView;
    private int mPosition = RecyclerView.NO_POSITION;

    private Uri uriQueryContacts = ContactsContract.Contacts.CONTENT_URI;
    private Uri uriCalendarQuery = CalendarContract.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactAdapter = new ContactAdapter(this, null);
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        getSupportLoaderManager().initLoader(CONTACT_LOADER_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(this,
                uriQueryContacts,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        contactAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //remove refererence to data no longer used
        contactAdapter.swapCursor(null);
    }


}
