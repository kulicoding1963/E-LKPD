package com.elkpd.apps.model;

import java.util.List;

public class Kuis {
    private String soal_1;
    private String soal_2;
    private String soal_3;
    private List<String> jawaban;
    private String type;

    public Kuis(String soal_1, String soal_2, String soal_3, List<String> jawaban, String type) {
        this.soal_1 = soal_1;
        this.soal_2 = soal_2;
        this.soal_3 = soal_3;
        this.jawaban = jawaban;
        this.type = type;
    }

    public String getSoal_1() {
        return soal_1;
    }

    public String getSoal_2() {
        return soal_2;
    }

    public String getSoal_3() {
        return soal_3;
    }

    public List<String> getJawaban() {
        return jawaban;
    }

    public String getType() {
        return type;
    }
}
