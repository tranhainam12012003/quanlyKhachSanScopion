package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.service.DTO_ThongTinLoaiPhong_services;

@RestController
@RequestMapping("admin/thong-tin-loai-phong/")
public class DTO_ThongTinLoaiPhong_Controller {
    @Autowired
    DTO_ThongTinLoaiPhong_services thongTinLoaiPhong_services;
    @GetMapping("detail/{id}")
    public ResponseEntity<?>detail(@PathVariable String id){
        return ResponseEntity.ok(thongTinLoaiPhong_services.detail(Integer.parseInt(id)));

    }
}
