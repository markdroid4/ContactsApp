package com.example.mark.contactsapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mark on 3/14/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public ContactAdapter(Context context, Cursor cursor)
    {
        mContext=context;
        mCursor=cursor;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.rv_contact_view, parent, false);

        ContactViewHolder contactViewHolder = new ContactViewHolder(inflatedView);
        return contactViewHolder;

    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int position) {
        Log.d("INFO", "onBindViewHolder");
        mCursor.moveToPosition(position);
        String displayName = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        Log.d("INFO", "displayName=" + displayName);
        contactViewHolder.mTextViewFullName.setText(displayName);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null)
            return 0;
        return mCursor.getCount();
    }

            public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
            {
                //ImageView mImageView;
                TextView mTextViewFullName;

                ContactViewHolder(View view)
                {
                    super(view);
                    //mImageView = (ImageView) view.findViewById(R.id.iv_contact_image);
                    mTextViewFullName = (TextView) view.findViewById(R.id.tv_full_name);
                    view.setOnClickListener(this);
                }

                @Override
                public void onClick(View view) {

                    Log.d("INFO", "Hello clcickers");

                }
            }

    public void swapCursor(Cursor cursor)
    {
        Log.d("INFO", "Cursor swapped");

        mCursor = cursor;
        notifyDataSetChanged();
    }

}
