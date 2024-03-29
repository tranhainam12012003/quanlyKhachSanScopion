package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.dto.DonDatDto;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.service.ThongTinDatPhongServices;
import vn.teca.scopio.base.service.giaoDich.DonDatService;
import vn.teca.scopio.base.service.giaoDich.LoaiPhongDatService;

import java.util.Optional;

@RestController
@Controller
@RequestMapping("/staff/don-dat")
public class DonDatController {
    @Autowired
    DonDatService donDatService;
    @Autowired
    LoaiPhongDatRepository loaiPhongDatRepository;
    @Autowired
    LoaiPhongDatService loaiPhongDatService;
    @Autowired
    ThongTinDatPhongServices thongTinDatPhongServices;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DonDatDto donDatDto) {

        try {
            donDatService.luuDonDat(donDatDto);
            return ResponseEntity.ok().body("luu thanh cong");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("loi khi luu");
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody DonDatDto donDatDto) {
        try {
            donDatService.update(donDatDto, Integer.parseInt(id));
            return ResponseEntity.ok().body("cap nhat thanh cong");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Khong thuc hien duoc");
        }
    }

    @GetMapping("thong-tin-phong-dat/{id}")
    public ResponseEntity<?> detailByIdDonDat(@PathVariable String id) {
        return ResponseEntity.ok(thongTinDatPhongServices.getThongTInPhongDat(Integer.parseInt(id)));
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
}
