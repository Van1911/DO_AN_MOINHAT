package com.example.do_an.model;

import java.io.Serializable;
import java.sql.Blob;

public class KhachHang implements Serializable {
    private int idKhachHang;
    private String tenKhachhang;
    private int soDT;
    private String gioiTinh;
    private int idAccount;
    private Blob avatar;

    public KhachHang() {

    }

    public KhachHang(int idKhachHang, String tenKhachhang, int soDT, String gioiTinh,Blob avatar,int idAccount) {
        this.idKhachHang = idKhachHang;
        this.tenKhachhang = tenKhachhang;
        this.soDT = soDT;
        this.gioiTinh = gioiTinh;
        this.avatar = avatar;
        this.idAccount=idAccount;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public String getTenKhachhang() {
        return tenKhachhang;
    }

    public int getSoDT() {
        return soDT;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }
    public int getIdAccount() {
        return idAccount;
    }
    public Blob getavatar() {
        return avatar;
    }
    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public void setSoDT(int soDT) {
        this.soDT = soDT;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public int setIdAccount() {
        return idAccount;
    }
    public Blob setavatar() {
        return avatar;
    }
}
