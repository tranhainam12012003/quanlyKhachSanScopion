package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.ThongTinKhachDat;
import vn.teca.scopio.base.service.ThongTinKhachDatServices;
import vn.teca.scopio.base.service.ThongtinKhachOSevices;

import java.util.Optional;

@RestController
@Controller
@RequestMapping("/staff/khach-dat")
public class ThongTinKhachDatController {
    @Autowired
    ThongTinKhachDatServices thongTinKhachDatServices;
    ThongTinKhachDat thongTinKhachDat;
    @PostMapping("/them")
    public ResponseEntity<?> add(@RequestBody ThongTinKhachDat khachDat){
        if(thongTinKhachDatServices.findBySoDienThoai(thongTinKhachDat.getSoDienThoai()).equals(khachDat.getSoDienThoai())){
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(thongTinKhachDatServices.add(khachDat));
    }
    @GetMapping("/tim")
    public ResponseEntity<?> findbySoDT(@RequestParam("soDienThoai") String soDienThoai){
        return ResponseEntity.ok(thongTinKhachDatServices.findBySoDienThoai(soDienThoai));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findByID(@PathVariable("id") String id){
        return ResponseEntity.ok(thongTinKhachDatServices.detail(Integer.parseInt(id)));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ThongTinKhachDat ttkd){
        return ResponseEntity.ok(thongTinKhachDatServices.update(ttkd,Integer.parseInt(id)));
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(thongTinKhachDatServices.delete(Integer.parseInt(id)));
    }

}
