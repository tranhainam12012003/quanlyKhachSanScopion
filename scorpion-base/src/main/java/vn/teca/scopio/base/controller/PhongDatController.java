package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.service.PhongDatServices_Dong;

@RestController
@RequestMapping("staff/phong-dat/")
public class PhongDatController {
    @Autowired
    PhongDatServices_Dong phongDatServices;
    @GetMapping("phong-da-gan/{id}")
    public ResponseEntity<?>getPhongDaGan(@PathVariable String id){
        return ResponseEntity.ok(phongDatServices.getPhongDaGan(Integer.parseInt(id)));
    }
    @GetMapping("phong-chua-gan")
    public ResponseEntity<?>getPhongChuaGan(){
        return ResponseEntity.ok(phongDatServices.getPhongChuaGan());
    }
    @GetMapping("detail-phong-dat-byIdPhong/{id}")
    public ResponseEntity<?>detailPhongDatById(@PathVariable String id){
        return ResponseEntity.ok(phongDatServices.getDetailPhongDatByIdPhong(Integer.parseInt(id)));
    }
}
