
package vn.teca.scopio.base.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.authentication.LoginRequest;
import vn.teca.scopio.base.model.authentication.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.service.authentication.TaiKhoanNVService;

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


}
