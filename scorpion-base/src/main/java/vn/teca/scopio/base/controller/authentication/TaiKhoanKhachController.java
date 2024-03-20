package vn.teca.scopio.base.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.authentication.LoginRequest;
import vn.teca.scopio.base.model.authentication.SignUpRequest;
import vn.teca.scopio.base.model.authentication.TaiKhoanKhachDtoLogin;
import vn.teca.scopio.base.service.authentication.TaiKhoanKhachService;

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
}
