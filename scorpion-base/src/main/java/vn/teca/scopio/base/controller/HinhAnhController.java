package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.teca.scopio.base.model.HinhAnh;
import vn.teca.scopio.base.service.HinhAnhServices;

import java.io.IOException;

@Controller
@RequestMapping("/admin/hinh-anh")
public class HinhAnhController {
    @Autowired
    private  HinhAnhServices hinhAnhServices;
    @GetMapping("/hien-thi")
    public ResponseEntity<?>getall(){
        return ResponseEntity.ok(hinhAnhServices.getall());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        return ResponseEntity.ok(hinhAnhServices.delete(Integer.parseInt(id)));
    }
    @GetMapping("/detail/{id}")//id này là id của loai phong
    public ResponseEntity<?>detail(@PathVariable String id){
        return ResponseEntity.ok(hinhAnhServices.detail(Integer.parseInt(id)));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(HinhAnh hinhAnh ,@RequestParam("image")MultipartFile file,String id) throws IOException {

        String uploadImage = hinhAnhServices.uploadImage(file,Integer.parseInt(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable String id, @RequestBody HinhAnh ha){
        return ResponseEntity.ok(hinhAnhServices.update(ha,Integer.parseInt(id)));
    }
}
