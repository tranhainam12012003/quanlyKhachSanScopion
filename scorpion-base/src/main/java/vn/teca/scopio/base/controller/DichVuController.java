package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody DichVu dichVu) {
        return ResponseEntity.ok(dichVuServices.update(dichVu));
    }

    @GetMapping("tim-kiem/{ten}")
    public ResponseEntity<?> timKiem(@PathVariable String ten) {
        return ResponseEntity.ok(dichVuServices.timkiem(ten));
    }

    @DeleteMapping("xoa/{id}")
    public ResponseEntity<?>xoa(@PathVariable String id){
        return ResponseEntity.ok(dichVuServices.delete(Integer.parseInt(id)));
    }
    @GetMapping("tim-kiem-theo-id/{id}")
    public ResponseEntity<?> timTheoID(@PathVariable String id) {
        return ResponseEntity.ok(dichVuServices.timkiemTheoId(Integer.parseInt(id)));
    }

    @GetMapping("/loc-theo-loai-dich-vu")
    public ResponseEntity<?> timTheoIdLoaiDichVu(@RequestParam(required = false) String id) {
        if (id==null) {

            return ResponseEntity.ok(dichVuServices.getall());
        } else {
            return ResponseEntity.ok(dichVuServices.timKiemTheoTenLoaiDichVu(Integer.parseInt(id)));
        }
    }
}
