package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.service.LoaiPhongServices;

import java.math.BigDecimal;

@Controller
@RestController
@RequestMapping("/admin/loai-phong")
public class LoaiPhongController {
    @Autowired
    LoaiPhongServices loaiPhongServices;

    @GetMapping("/list")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(loaiPhongServices.getall());
    }

    // hien thi tat ca hinh anh + loai phong + tien ich
    @GetMapping("/all")
    public ResponseEntity<?> getAllList(){
            return ResponseEntity.ok(loaiPhongServices.findAllHinhAnh());
    }
//    @GetMapping("/listAllHinhAnh")
//    public ResponseEntity<?> getAllHinhAnh(){return ResponseEntity.ok(loaiPhongServices.getAllHinhAnh());};
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getone(@PathVariable String id) {
        return ResponseEntity.ok(loaiPhongServices.findbyId(Integer.parseInt(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(loaiPhongServices.delete(Integer.parseInt(id)));
    }

    @PostMapping("/sua/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody LoaiPhong loaiPhong) {
        return ResponseEntity.ok(loaiPhongServices.update(loaiPhong, Integer.parseInt(id)));
    }

    @PostMapping("/luu")
    public ResponseEntity<?> add(@RequestBody LoaiPhong loaiPhong) {
        return ResponseEntity.ok(loaiPhongServices.add(loaiPhong));

    }

    @GetMapping("/tim-theo-ten/{ten}")
    public ResponseEntity<?>timtheoten(@PathVariable String ten){
        return ResponseEntity.ok(loaiPhongServices.timthemten(ten));
    }

    @GetMapping("tim-kiem-theo-gia/{gia}")
    public ResponseEntity<?>timtheogia(@PathVariable String gia){
        return ResponseEntity.ok(loaiPhongServices.timTheoGia(BigDecimal.valueOf(Long.parseLong(gia))));
    }
    @GetMapping("tim-kiem-theo-so-luong/{sl}")
    public ResponseEntity<?>timTheoSoLuong(@PathVariable String sl){
        return ResponseEntity.ok(loaiPhongServices.timTheoSoNguoi(Integer.parseInt(sl)));
    }
}
