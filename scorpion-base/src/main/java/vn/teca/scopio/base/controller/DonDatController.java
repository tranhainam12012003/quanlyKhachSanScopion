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


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DonDatDto donDatDto) {

        try{

            return ResponseEntity.ok(donDatService.luuDonDat(donDatDto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("loi khi luu");
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id){
        try {
            donDatService.update(Integer.parseInt(id));
            return ResponseEntity.ok().body("cap nhat thanh cong");
        }
        catch ( Exception e){
            return ResponseEntity.badRequest().body("Khong thuc hien duoc");
        }
    }

    @GetMapping("/theo-khach/{id}") // id cua khach
    public ResponseEntity<?> getListTheoKhach(@PathVariable String id){
        return ResponseEntity.ok(donDatService.getListTheoKhach(Integer.parseInt(id)));
    }
}
