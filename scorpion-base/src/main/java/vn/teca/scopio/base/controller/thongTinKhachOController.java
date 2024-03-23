package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.service.ThongtinKhachOSevices;

import java.sql.Date;

@RestController
@Controller
@RequestMapping("admin")
public class thongTinKhachOController {
//    @Autowired
//    ThongtinKhachOSevices thongtinKhachOSevices;
//
//    @GetMapping("/khach-hang-o/hien-thi-tat-ca")
//    public ResponseEntity<?> getall(){
//        return ResponseEntity.ok(thongtinKhachOSevices.getAll());
//    }
//    @GetMapping("/khach-hang-o/view-chi-tiet/{id}")
//    public ResponseEntity<?>getone(@PathVariable String id) {
//        return ResponseEntity.ok(thongtinKhachOSevices.detail(Integer.parseInt(id)));
//    }
//    @GetMapping("/khach-hang-o/tim-kiem")
//    public ResponseEntity<?> timKiem(@RequestParam("hovaten") String hoVaTen , @RequestParam("thoiGIanVao")Date thoiGianVao,@RequestParam("thoiGIanRa")Date thoiGianRa){
//        return ResponseEntity.ok(thongtinKhachOSevices.timKiem(hoVaTen,thoiGianVao,thoiGianRa));
//    }
}
