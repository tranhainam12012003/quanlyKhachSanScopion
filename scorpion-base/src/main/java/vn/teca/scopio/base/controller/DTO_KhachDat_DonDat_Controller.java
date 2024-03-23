package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.service.DTOKhachDat_DonDatServices;

@RestController
@RequestMapping("/admin/thong-tin-khach_dat/")
public class DTO_KhachDat_DonDat_Controller {
    @Autowired
    DTOKhachDat_DonDatServices khachDat_donDatServices;
    @GetMapping("hien-thi")
    public ResponseEntity<?>getall(){
        return ResponseEntity.ok(khachDat_donDatServices.getThongTin());
    }
}
