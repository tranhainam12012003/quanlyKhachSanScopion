package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.authentication.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.repository.TaiKhoanNvRepository;

import java.util.Optional;

@Service
public class TaiKhoanNVService {
    @Autowired
    private TaiKhoanNvRepository repository;

    public Optional<TaiKhoanNVDtoLogin> findTaiKhoan(String soDienThoai, String password) {
        Optional<TaiKhoanNVDtoLogin> optionalTaiKhoan = repository.getInfoLoginNV(soDienThoai, password);

        if (optionalTaiKhoan.isPresent()) {
            // Result is present and not null
            TaiKhoanNVDtoLogin taiKhoanNVDtoLogin = optionalTaiKhoan.get();
            // Do something with taiKhoanNVDtoLogin
            return Optional.of(taiKhoanNVDtoLogin);
        } else {
            // Handle the case where the result is not present or is present but null
            System.out.println("Login failed. Invalid phone number or password.");
            return Optional.empty();
        }
    }
}
