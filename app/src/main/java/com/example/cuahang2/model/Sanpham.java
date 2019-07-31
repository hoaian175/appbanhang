package com.example.cuahang2.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    public int Idsp;
    public String Tensanpham;
    public int Giasanpham;
    public String Hinhanhsanpham;
    public String Motasanpham;
    public int Idloaisanpham;

    public int getIdsp() {
        return Idsp;
    }

    public void setIdsp(int idsp) {
        Idsp = idsp;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public int getIdloaisanpham() {
        return Idloaisanpham;
    }

    public void setIdloaisanpham(int idloaisanpham) {
        Idloaisanpham = idloaisanpham;
    }

    public Sanpham(int idsp, String tensanpham, int giasanpham, String hinhanhsanpham, String motasanpham, int idloaisanpham) {
        Idsp = idsp;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        Idloaisanpham = idloaisanpham;
    }
}
