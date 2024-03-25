package com.example.do_an;

import java.util.HashMap;
import java.util.Map;

public class Ticket {


    private int Id;
    private String NoiDi, NoiDen;
    /*private String NgayDi;*/
    private String GiaTien;

    public  Ticket(){

    }
    public Ticket(int id, String noiDi, String noiDen, String giaTien) {
        Id = id;
        NoiDi = noiDi;
        NoiDen = noiDen;
        /*NgayDi = ngayDi;*/
        GiaTien = giaTien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNoiDi() {
        return NoiDi;
    }

    public void setNoiDi(String noiDi) {
        NoiDi = noiDi;
    }

    public String getNoiDen() {
        return NoiDen;
    }

    public void setNoiDen(String noiDen) {
        NoiDen = noiDen;
    }

    /*public String getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(String ngayDi) {
        NgayDi = ngayDi;
    }*/

    public String getGiaTien() {
        return GiaTien;
    }
    public void setGiaTien(String giaTien) {
        GiaTien = giaTien;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("noiDi", NoiDi);
        result.put("noiDen", NoiDen);
        result.put("giaTien", GiaTien);
        return result;
    }


}