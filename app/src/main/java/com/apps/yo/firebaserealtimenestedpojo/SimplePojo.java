package com.apps.yo.firebaserealtimenestedpojo;

import android.os.Parcel;
import android.os.Parcelable;

//First it will ask you to implement the methods
//then it will ask you to complete some other methods...
//it all got filled in automatically clicking on the red bulb

public class SimplePojo implements Parcelable {

    int x;
    String str;

    SimplePojo(){}

    public SimplePojo(int x, String str) {
        this.x = x;
        this.str = str;
    }


    protected SimplePojo(Parcel in) {
        x = in.readInt();
        str = in.readString();
    }

    public static final Creator<SimplePojo> CREATOR = new Creator<SimplePojo>() {
        @Override
        public SimplePojo createFromParcel(Parcel in) {
            return new SimplePojo(in);
        }

        @Override
        public SimplePojo[] newArray(int size) {
            return new SimplePojo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeString(str);
    }
}
