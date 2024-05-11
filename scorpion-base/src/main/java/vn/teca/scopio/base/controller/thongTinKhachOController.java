package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.ThongTinKhachO;
import vn.teca.scopio.base.service.ThongtinKhachOSevices;

import java.sql.Date;

@RestController
@Controller
@RequestMapping("admin")
public class thongTinKhachOController {
    @Autowired
    ThongtinKhachOSevices thongtinKhachOSevices;

    @GetMapping("/khach-hang-o/hien-thi-tat-ca")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(thongtinKhachOSevices.getAll());
    }
    @GetMapping("/khach-hang-o/view-chi-tiet/{id}")
    public ResponseEntity<?>getone(@PathVariable String id) {
        return ResponseEntity.ok(thongtinKhachOSevices.detail(Integer.parseInt(id)));
    }
    @GetMapping("/khach-hang-o/tim-kiem")
    public ResponseEntity<?> timKiem(@RequestParam("hovaten") String hoVaTen , @RequestParam("thoiGIanVao")Date thoiGianVao,@RequestParam("thoiGIanRa")Date thoiGianRa,@RequestParam("soGiayTo") String soGiayTo){
        return ResponseEntity.ok(thongtinKhachOSevices.timKiem(hoVaTen,thoiGianVao,thoiGianRa,soGiayTo));
    }
    @PostMapping("/khach-hang-o/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ThongTinKhachO thongTinKhachO) {
        return ResponseEntity.ok(thongtinKhachOSevices.update(thongTinKhachO, Integer.parseInt(id)));
    }
    @GetMapping("/khach-hang-o/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        return ResponseEntity.ok(thongtinKhachOSevices.detail(Integer.parseInt(id)));
    }
    @PostMapping("/khach-hang-o/add")
    public ResponseEntity<?> add(@RequestParam String idDonDat, @RequestBody ThongTinKhachO thongTinKhachO) {
        return ResponseEntity.ok(thongtinKhachOSevices.add(thongTinKhachO,Integer.parseInt(idDonDat)));
    }
    @DeleteMapping("/khach-hang-o/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(thongtinKhachOSevices.delete(Integer.parseInt(id)));
    }





}
