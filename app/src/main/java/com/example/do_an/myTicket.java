//package com.example.do_an;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class myTicket {
//        private int Id;
//        private String email;
//        private String NoiDi, NoiDen;
//        /*private String NgayDi;*/
//        private String GiaTien;
//
//    public  myTicket(){
//
//    }
//
//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int id) {
//        Id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getNoiDi() {
//        return NoiDi;
//    }
//
//    public void setNoiDi(String noiDi) {
//        NoiDi = noiDi;
//    }
//
//    public String getNoiDen() {
//        return NoiDen;
//    }
//
//    public void setNoiDen(String noiDen) {
//        NoiDen = noiDen;
//    }
//
//    public String getGiaTien() {
//        return GiaTien;
//    }
//
//    public void setGiaTien(String giaTien) {
//        GiaTien = giaTien;
//    }
//
//    public myTicket(int id, String email, String noiDi, String noiDen, String giaTien) {
//        Id = id;
//        this.email = email;
//        NoiDi = noiDi;
//        NoiDen = noiDen;
//        GiaTien = giaTien;
//    }
//    public Map<String,Object> toMap(){
//        HashMap<String,Object> result = new HashMap<>();
//        result.put("email",email);
//        result.put("noiDi", NoiDi);
//        result.put("noiDen", NoiDen);
//        result.put("giaTien", GiaTien);
//        return result;
//    }
//}
