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
import vn.teca.scopio.base.service.phongServices;
@RestController
@Controller
@RequestMapping("admin/phong")
public class phongController {
    @Autowired
    private phongServices phongServices;
    @GetMapping("/hien-thi")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(phongServices.getall());
    }
    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        return ResponseEntity.ok(phongServices.xoa(Integer.parseInt(id)));
    }
    @PutMapping("/sua/{id}")
    public ResponseEntity<?>update(@PathVariable String id, @RequestBody Phong phong){
        return ResponseEntity.ok(phongServices.update(phong,Integer.parseInt(id)));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody Phong phong){
        return ResponseEntity.ok(phongServices.them(phong));

    }
    @GetMapping("/loc")
    public ResponseEntity<?>locLoaiPhong(@RequestParam("id") int id){
        return ResponseEntity.ok(phongServices.locLoaiPhong(id));
    }

}
