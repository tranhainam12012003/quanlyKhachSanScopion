package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.dto.PhongDatDto;
import vn.teca.scopio.base.service.giaoDich.PhongDatServices;

@RestController
@Controller
@RequestMapping("/phong-dat")
public class PhongDatController {
    @Autowired
    PhongDatServices phongDatServices;

    // load ra cac phong dat de chekc in
    @GetMapping("/load/{id}")
    public ResponseEntity<?> getDonDatToCheckin(@PathVariable String id){
        return ResponseEntity.ok(phongDatServices.getDonDatToChekIn(Integer.parseInt(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PhongDatDto dto){
        try {

            return ResponseEntity.ok(phongDatServices.save(dto));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("That bai");
        }

    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(@RequestParam String id){
        phongDatServices.checkin(Integer.parseInt(id));
        return ResponseEntity.ok().body("checkin thanh cong");
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PhongDatDto dto){
        phongDatServices.update(dto);
        return ResponseEntity.ok().body("update thanh cong");
    }
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam String idDonDat,@RequestParam String idPhongDat){
//        Integer idDonDat = dto.getDonDatIdDonDat();
        phongDatServices.checkout(Integer.parseInt(idPhongDat));
        phongDatServices.checkoutDonDat(Integer.parseInt(idDonDat));
        return ResponseEntity.ok().body("check out thanh cong");
    }

}
