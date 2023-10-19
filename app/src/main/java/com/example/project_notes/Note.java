package com.example.project_notes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String Title;
    private String Desc;

    public Note(){

    }
    public Note(String Title, String Desc){
        this.Title = Title;
        this.Desc = Desc;
    }

    public Note(Parcel in) {
        Title = in.readString();
        Desc = in.readString();
    }

    public String getTitle(){
        return Title;
    }

    public void setTitle(String Title){
        this.Title = Title;
    }

    public String getDesc(){
        return Desc;
    }

    public void setDesc(String Desc){
        this.Desc = Desc;
    }
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Desc);
    }
}
