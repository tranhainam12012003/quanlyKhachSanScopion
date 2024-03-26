package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.TaiKhoanKhach;
import vn.teca.scopio.base.model.authentication.LoginRequest;
import vn.teca.scopio.base.model.authentication.SignUpRequest;
import vn.teca.scopio.base.model.authentication.TaiKhoanKhachDtoLogin;
import vn.teca.scopio.base.service.TaiKhoanKhachService;

import java.util.Optional;

@RestController
@RequestMapping("/khach")
public class TaiKhoanKhachController {
    @Autowired
    private TaiKhoanKhachService service;

    @PostMapping("/login")
    public Optional<TaiKhoanKhachDtoLogin> checkUsernamePass(@RequestBody LoginRequest request){
        return service.findTaiKhoan(request.getSoDienThoai(),request.getPassword());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpTaiKhoanKhach(@RequestBody SignUpRequest info){
        String message = service.signUpTaiKhoanKhach(
                info.getHoVaTen(),
                info.getSoDienThoai(),
                info.getEmail(),
                info.isGioiTinh(),
                info.getPassword());
        return ResponseEntity.ok(message);
    }
    @GetMapping("/hien-thi-list")
    public ResponseEntity<?> getallnv(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TaiKhoanKhach taiKhoanKhach) {
        return ResponseEntity.ok(service.update(taiKhoanKhach, Integer.parseInt(id)));
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TaiKhoanKhach taiKhoanKhach) {
        return ResponseEntity.ok(service.add(taiKhoanKhach));
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        return ResponseEntity.ok(service.detail(Integer.parseInt(id)));
    }
}
