package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.service.DichVuDatServices;

@RestController

@RequestMapping("/admin/dich-vu-dat/")
public class DichVuDatController {
    @Autowired
    private DichVuDatServices dichVuDatServices;
    @GetMapping("hien-thi")
    public ResponseEntity<?>getall(){
        return ResponseEntity.ok(dichVuDatServices.getall());
    }
    @PostMapping("add")
    public ResponseEntity<?>add(@RequestBody DichVuDat dichVuDat){
        return ResponseEntity.ok(dichVuDatServices.add(dichVuDat));
    }
    @PostMapping("update/{id}")
    public ResponseEntity<?>update(@RequestBody DichVuDat dichVuDat, @PathVariable String id){
        return ResponseEntity.ok(dichVuDatServices.update(dichVuDat,Integer.parseInt(id)));
    }
    @GetMapping("tong-tien-theo-id-phong/{id}")
    public ResponseEntity<?>sum(@PathVariable String id){
        return ResponseEntity.ok(dichVuDatServices.getTonggTienTheoIdPhong(Integer.parseInt(id)));
    }
}
