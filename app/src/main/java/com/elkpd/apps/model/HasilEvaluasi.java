package com.elkpd.apps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HasilEvaluasi implements Parcelable {
    private String id;
    private String nama;
    private String status;
    private String nilai;
    private String anwswer1;
    private String anwswer2;
    private String anwswer3;
    private String anwswer4;
    private String anwswer5;
    private String anwswer6;

    public HasilEvaluasi() {}

    protected HasilEvaluasi(Parcel in) {
        id = in.readString();
        nama = in.readString();
        status = in.readString();
        nilai = in.readString();
        anwswer1 = in.readString();
        anwswer2 = in.readString();
        anwswer3 = in.readString();
        anwswer4 = in.readString();
        anwswer5 = in.readString();
        anwswer6 = in.readString();
    }

    public static final Creator<HasilEvaluasi> CREATOR = new Creator<HasilEvaluasi>() {
        @Override
        public HasilEvaluasi createFromParcel(Parcel in) {
            return new HasilEvaluasi(in);
        }

        @Override
        public HasilEvaluasi[] newArray(int size) {
            return new HasilEvaluasi[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public String getNilai() {
        return nilai;
    }

    public String getAnwswer1() {
        return anwswer1;
    }

    public String getAnwswer2() {
        return anwswer2;
    }

    public String getAnwswer3() {
        return anwswer3;
    }

    public String getAnwswer4() {
        return anwswer4;
    }

    public String getAnwswer5() {
        return anwswer5;
    }

    public String getAnwswer6() {
        return anwswer6;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public void setAnwswer1(String anwswer1) {
        this.anwswer1 = anwswer1;
    }

    public void setAnwswer2(String anwswer2) {
        this.anwswer2 = anwswer2;
    }

    public void setAnwswer3(String anwswer3) {
        this.anwswer3 = anwswer3;
    }

    public void setAnwswer4(String anwswer4) {
        this.anwswer4 = anwswer4;
    }

    public void setAnwswer5(String anwswer5) {
        this.anwswer5 = anwswer5;
    }

    public void setAnwswer6(String anwswer6) {
        this.anwswer6 = anwswer6;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nama);
        parcel.writeString(status);
        parcel.writeString(nilai);
        parcel.writeString(anwswer1);
        parcel.writeString(anwswer2);
        parcel.writeString(anwswer3);
        parcel.writeString(anwswer4);
        parcel.writeString(anwswer5);
        parcel.writeString(anwswer6);
    }
}
