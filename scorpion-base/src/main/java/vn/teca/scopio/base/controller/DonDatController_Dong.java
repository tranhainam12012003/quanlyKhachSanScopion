package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.service.ThongTinDatPhongServices;
import vn.teca.scopio.base.service.giaoDich.DonDatService;
import vn.teca.scopio.base.service.giaoDich.LoaiPhongDatService;

@RestController
@RequestMapping("staff/don-dat/")

public class DonDatController_Dong {
    @Autowired
    DonDatService donDatService;
    @Autowired
    LoaiPhongDatRepository loaiPhongDatRepository;
    @Autowired
    LoaiPhongDatService loaiPhongDatService;
    @Autowired
    ThongTinDatPhongServices thongTinDatPhongServices;
    @GetMapping("thong-tin-phong-dat/{id}")
    public ResponseEntity<?> detailByIdDonDat(@PathVariable String id) {
        return ResponseEntity.ok(thongTinDatPhongServices.detailTheoDonDat(Integer.parseInt(id)));
    }

    @GetMapping("phong-dat-online")
    public ResponseEntity<?> getAllOnline(@RequestParam(value = "page", defaultValue = "0") int page) {
//        Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok(donDatService.findAllOnline(page));
    }

    @GetMapping("phong-dat-offline")
    public ResponseEntity<?> getallOffline(@RequestParam(value = "page", defaultValue = "0") int page) {
//        Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok(donDatService.findAllOffline(page));
    }

    @GetMapping("hien-thi-tat-ca")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page) {
//        Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok(donDatService.findAllDonDat(page));
    }
////detail thong tin phong chua gan
//    @GetMapping("detail-thong-tin-don-dat/{id}")
//    public ResponseEntity<?>detailThongTinDonDat(@PathVariable String id){
////        return ResponseEntity.ok(donDatService.getThongTinDonDat(Integer.parseInt(id)));
//    }
}
