package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.teca.scopio.base.model.TienIchLoaiPhong;
import vn.teca.scopio.base.service.TienIchLoaiPhongServices;

@Controller
@RequestMapping("tien-ich-loai-phong")
public class tienIchLoaiPhongController {
    @Autowired
    public TienIchLoaiPhongServices tienIchLoaiPhongServices;
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody TienIchLoaiPhong tienIch){
        return ResponseEntity.ok(tienIchLoaiPhongServices.add(tienIch));
    }
    @GetMapping("/view")
    public ResponseEntity<?>getall(){
        return ResponseEntity.ok(tienIchLoaiPhongServices.getall());
    }

}
