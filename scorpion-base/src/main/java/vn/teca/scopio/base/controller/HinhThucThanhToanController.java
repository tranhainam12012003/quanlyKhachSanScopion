package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.service.HinhThucThanhToanService;

@RestController
@RequestMapping("/httt")
public class HinhThucThanhToanController {
    @Autowired
    HinhThucThanhToanService hinhThucThanhToanService;
    @GetMapping()
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok(hinhThucThanhToanService.getList());
    }
}
