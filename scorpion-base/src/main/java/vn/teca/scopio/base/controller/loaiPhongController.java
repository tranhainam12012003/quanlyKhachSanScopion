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

@Controller
@RestController
@RequestMapping("admin/loai-phong")
public class loaiPhongController {
    @Autowired
    LoaiPhongServices loaiPhongServices;
    @GetMapping("/list")
    public ResponseEntity<?>getall(){
        return ResponseEntity.ok(loaiPhongServices.getall());
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?>getone(@PathVariable String id){
        return ResponseEntity.ok(loaiPhongServices.findbyId(Integer.parseInt(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        return ResponseEntity.ok(loaiPhongServices.delete(Integer.parseInt(id)));
    }
    @PostMapping("/sua/{id}")
    public ResponseEntity<?>update(@PathVariable String id, @RequestBody LoaiPhong loaiPhong){
        return ResponseEntity.ok(loaiPhongServices.update(loaiPhong,Integer.parseInt(id)));
    }
    @PostMapping("/luu")
    public ResponseEntity<?>add(@RequestBody LoaiPhong loaiPhong){
        return ResponseEntity.ok(loaiPhongServices.add(loaiPhong));

    }
}
