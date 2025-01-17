package vn.teca.scopio.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.service.PhongServices;
@RestController
@Controller
@RequestMapping("/admin/phong")
public class PhongController {
    @Autowired
    private PhongServices phongServices;
    @GetMapping("/hien-thi")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(phongServices.getall());
    }
    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        return ResponseEntity.ok(phongServices.xoa(Integer.parseInt(id)));
    }
    @PutMapping("/sua")
    public ResponseEntity<?>update(@RequestBody Phong phong){
        return ResponseEntity.ok(phongServices.update(phong));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody Phong phong){
        return ResponseEntity.ok(phongServices.them(phong));

    }
    @GetMapping("/loc")
    public ResponseEntity<?>locLoaiPhong(@RequestParam(required = false) String id){
        if(id == null){
            return ResponseEntity.ok(phongServices.getall());
        }
        return ResponseEntity.ok(phongServices.locLoaiPhong(Integer.parseInt(id)));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?>getone(@PathVariable String id){
        return ResponseEntity.ok(phongServices.detail(Integer.parseInt(id)));
    }
    // hien thi phong con trong de gan phong
    @GetMapping("/searchTrong/{id}") //id o day la id cua loai phong
    public ResponseEntity<?> searchTrong(@PathVariable String id){
        return ResponseEntity.ok(phongServices.findPhongTrong(Integer.parseInt(id)));
    }

    // So do trang thai phong

    // lay ra danh sach phong trong
    @GetMapping("/phong-trong")
    public ResponseEntity<?> layPhongTrong(){
        return ResponseEntity.ok(phongServices.getPhongTrong());
    }

    // lay ra danh sach phong checkin/dang o
    @GetMapping("/phong-dang-o")
    public ResponseEntity<?> layPhongDangO(){
        return ResponseEntity.ok(phongServices.getPhongDangO());
    }

}
