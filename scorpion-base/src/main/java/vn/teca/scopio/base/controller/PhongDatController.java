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

   @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PhongDatDto dto){
       try {
           phongDatServices.save(dto);
           return ResponseEntity.ok().body("Thanh Cong");
       }catch (Exception e){
           return ResponseEntity.badRequest().body("That bai");
       }

   }

   @PostMapping("/checkin/{id}")
    public ResponseEntity<?> checkin(@PathVariable String id){
       phongDatServices.checkin(Integer.parseInt(id));
       return ResponseEntity.ok().body("checkin thanh cong");
   }

   @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody PhongDatDto dto){
       phongDatServices.update(dto,Integer.parseInt(id));
       return ResponseEntity.ok().body("update thanh cong");
   }
   @PostMapping("/checkout/{id}")
    public ResponseEntity<?> checkout(@PathVariable String id, @RequestParam("idDonDat") String idDonDat){
       phongDatServices.checkout(Integer.parseInt(id),Integer.parseInt(idDonDat));
       return ResponseEntity.ok().body("check out thanh cong");
   }

}
