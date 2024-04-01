package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.HoaDonPhong;
import vn.teca.scopio.base.service.giaoDich.HoaDonPhongService;

@RestController
@Controller
@RequestMapping("/hoa-don-phong")
public class HoaDonPhongController {
    @Autowired
    HoaDonPhongService hoaDonPhongService;

    //Hien thi hoa don chua thanh toan cua phong day
    @GetMapping("/{id}")
    public ResponseEntity<?> hienthiHoaDon(@PathVariable String id){
        return ResponseEntity.ok(hoaDonPhongService.hienThiHoaDonTT(Integer.parseInt(id)));
    }

    // add hoa don
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody HoaDonPhong hoaDonPhong){
        return ResponseEntity.ok(hoaDonPhongService.add(hoaDonPhong));
    }

    //Hien thi hoa don chi tiet de xuat hoa don
    // id la id phong dat
    @GetMapping("/chi-tiet/{id}")
    public ResponseEntity<?> hienThiChiTiet(@PathVariable String id){
        return ResponseEntity.ok(hoaDonPhongService.hienThiHoaDonCT(Integer.parseInt(id)));
    }
}
