package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.service.giaoDich.DonDatService;

@RestController
@Controller
@RequestMapping("/staff/don-dat")
public class DonDatController {
    @Autowired
    DonDatService donDatService;
    @GetMapping("/hien-thi")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") String page){
        Pageable pageable = PageRequest.of(Integer.parseInt(page),20);
        return ResponseEntity.ok(donDatService.findAll(pageable));
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DonDat donDat){
        return ResponseEntity.ok(donDatService.save(donDat));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody DonDat donDat){
        return ResponseEntity.ok(donDatService.update(donDat,Integer.parseInt(id)));
    }

}
