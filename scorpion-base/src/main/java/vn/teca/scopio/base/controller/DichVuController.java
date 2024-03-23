package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.DichVu;
import vn.teca.scopio.base.service.DichVuServices;

@RestController
@RequestMapping("/admin/dich-vu/")
public class DichVuController {
    @Autowired
    private DichVuServices dichVuServices;

    @GetMapping("hien-thi")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(dichVuServices.getall());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody DichVu dv) {
        return ResponseEntity.ok(dichVuServices.add(dv));
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        return ResponseEntity.ok(dichVuServices.detail(Integer.parseInt(id)));
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody DichVu dichVu) {
        return ResponseEntity.ok(dichVuServices.update(dichVu, Integer.parseInt(id)));
    }

    @GetMapping("tim-kiem/{ten}")
    public ResponseEntity<?> timKiem(@PathVariable String ten) {
        return ResponseEntity.ok(dichVuServices.timkiem(ten));
    }
    @GetMapping("tim-kiem-theo-id/{id}")
    public ResponseEntity<?> timTheoID(@PathVariable String id) {
        return ResponseEntity.ok(dichVuServices.timkiemTheoId(Integer.parseInt(id)));
    }

}
