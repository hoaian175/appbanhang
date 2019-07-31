package com.example.cuahang2.model;

import java.io.Serializable;

public class PhanHoi implements Serializable {
    public int maphanhoi;
    public String tenphanhoi,noidungphanhoi;

    public int getMaphanhoi() {
        return maphanhoi;
    }

    public void setMaphanhoi(int maphanhoi) {
        this.maphanhoi = maphanhoi;
    }

    public String getTenphanhoi() {
        return tenphanhoi;
    }

    public void setTenphanhoi(String tenphanhoi) {
        this.tenphanhoi = tenphanhoi;
    }

    public String getNoidungphanhoi() {
        return noidungphanhoi;
    }

    public void setNoidungphanhoi(String noidungphanhoi) {
        this.noidungphanhoi = noidungphanhoi;
    }

    public PhanHoi(int maphanhoi, String tenphanhoi, String noidungphanhoi) {
        this.maphanhoi = maphanhoi;
        this.tenphanhoi = tenphanhoi;
        this.noidungphanhoi = noidungphanhoi;
    }
}
