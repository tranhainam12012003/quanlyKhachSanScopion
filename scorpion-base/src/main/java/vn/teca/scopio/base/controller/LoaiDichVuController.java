package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.LoaiDichVu;
import vn.teca.scopio.base.service.LoaiDichVuSerices;

@RestController
@RequestMapping("/admin/loai-dich-vu/")
public class LoaiDichVuController {
    @Autowired
    private LoaiDichVuSerices loaiDichVuSerices;

    @GetMapping("hien-thi")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(loaiDichVuSerices.getall());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody LoaiDichVu loaiDichVu) {
        return ResponseEntity.ok(loaiDichVuSerices.add(loaiDichVu));
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody LoaiDichVu loaiDichVu) {
        return ResponseEntity.ok(loaiDichVuSerices.update(loaiDichVu, Integer.parseInt(id)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(loaiDichVuSerices.delete(Integer.parseInt(id)));
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        return ResponseEntity.ok(loaiDichVuSerices.detail(Integer.parseInt(id)));
    }
    @GetMapping("tim-kiem/{ten}")
    public ResponseEntity<?>timKiem(@PathVariable String ten){
        return ResponseEntity.ok(loaiDichVuSerices.search(ten));
    }
    @GetMapping("tim-dich-vu/{id}")
    public ResponseEntity<?>findById(@PathVariable String id){
        return ResponseEntity.ok(loaiDichVuSerices.getDichVuTheoID(Integer.parseInt(id)));
    }
}
