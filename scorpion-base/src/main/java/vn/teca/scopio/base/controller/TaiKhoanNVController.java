
package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.TaiKhoanNv;
import vn.teca.scopio.base.model.authentication.LoginRequest;
import vn.teca.scopio.base.model.authentication.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.service.TaiKhoanNVService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class TaiKhoanNVController {
    @Autowired
    private TaiKhoanNVService taiKhoanNVService;

    @PostMapping("/login")
    public Optional<TaiKhoanNVDtoLogin> checkUsernamePass(@RequestBody LoginRequest request){
        return taiKhoanNVService.findTaiKhoan(request.getSoDienThoai(),request.getPassword());
    }

    @GetMapping("/hien-thi-list")
    public ResponseEntity<?> getallnv(){
        return ResponseEntity.ok(taiKhoanNVService.getAll());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable String id){
        return ResponseEntity.ok(taiKhoanNVService.detail(Integer.parseInt(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return ResponseEntity.ok(taiKhoanNVService.delete(Integer.parseInt(id)));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TaiKhoanNv taiKhoanNv) {
        return ResponseEntity.ok(taiKhoanNVService.update(taiKhoanNv, Integer.parseInt(id)));
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TaiKhoanNv taiKhoanNv) {
        return ResponseEntity.ok(taiKhoanNVService.add(taiKhoanNv));
    }

}
