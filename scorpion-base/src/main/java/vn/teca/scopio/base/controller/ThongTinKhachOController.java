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
@RequestMapping("/admin/khach-hang-o")
public class ThongTinKhachOController {
    @Autowired
    ThongtinKhachOSevices thongtinKhachOSevices;

    @GetMapping("/hien-thi-tat-ca")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(thongtinKhachOSevices.getAll());
    }
    @GetMapping("/view-chi-tiet/{id}")
    public ResponseEntity<?>getone(@PathVariable String id) {
        return ResponseEntity.ok(thongtinKhachOSevices.detail(Integer.parseInt(id)));
    }
    @GetMapping("/tim-kiem")
    public ResponseEntity<?> timKiem(@RequestParam("hovaten") String hoVaTen , @RequestParam("thoiGIanVao")Date thoiGianVao,@RequestParam("thoiGIanRa")Date thoiGianRa,@RequestParam("soGiayTo") String soGiayTo){
        return ResponseEntity.ok(thongtinKhachOSevices.timKiem(hoVaTen,thoiGianVao,thoiGianRa,soGiayTo));
    }
    @PostMapping("/them")
    public ResponseEntity<?> add(@RequestBody ThongTinKhachO thongTinKhachO){
        return ResponseEntity.ok(thongtinKhachOSevices.add(thongTinKhachO));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody ThongTinKhachO thongTinKhachO){
        return ResponseEntity.ok(thongtinKhachOSevices.update(thongTinKhachO,Integer.parseInt(id)));
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return ResponseEntity.ok(thongtinKhachOSevices.delete(Integer.parseInt(id)));
    }

}
