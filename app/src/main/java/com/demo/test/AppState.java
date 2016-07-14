package com.demo.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-07-07.
 */
public class AppState implements Parcelable {
    private int id;
    private boolean isOpen;
    private String mName;

    public AppState(int id, String name) {
        this.id = id;
        isOpen = true;
        this.mName = name;
    }

    public AppState(Parcel in) {
        id = in.readInt();
        isOpen = in.readInt() == 0;
        mName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(isOpen ? 0 : 1);
        dest.writeString(mName);
    }

    public static final Parcelable.Creator<AppState> CREATOR = new Parcelable.Creator<AppState>() {
        @Override
        public AppState createFromParcel(Parcel source) {
            return new AppState(source);
        }

        @Override
        public AppState[] newArray(int size) {
            return new AppState[size];
        }
    };
}
