package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TaiKhoanNv;
import vn.teca.scopio.base.model.authentication.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.repository.TaiKhoanNvRepository;

import java.util.List;
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
    public TaiKhoanNv add(TaiKhoanNv taiKhoanNv){
        return repository.save(taiKhoanNv);
    }
    public List<TaiKhoanNv> getAll(){
        return repository.findAll();
    }
    public TaiKhoanNv update(TaiKhoanNv taiKhoanNv) {
        Optional<TaiKhoanNv> optional = repository.findById(taiKhoanNv.getId());
        return optional.map(o -> {
            o.setHoTen(taiKhoanNv.getHoTen());
            o.setTenTaiKhoan(taiKhoanNv.getTenTaiKhoan());
            o.setQuyenHanIdQuyenHan(taiKhoanNv.getQuyenHanIdQuyenHan());
            o.setCccd(taiKhoanNv.getCccd());
            o.setEmail(taiKhoanNv.getEmail());
            o.setMatKhau(taiKhoanNv.getMatKhau());
            o.setSoDienThoai(taiKhoanNv.getSoDienThoai());
            return repository.save(o);
        }).orElse(null);
    }
    public TaiKhoanNv add(Integer id){
        return repository.findById(id).get();
    }

    public TaiKhoanNv detail(Integer id){
        return repository.findById(id).orElse(null);
    }

    public TaiKhoanNv delete(Integer id){
        Optional<TaiKhoanNv> optional = repository.findById(id);
        return optional.map(o -> {
             repository.delete(o);
             return o;
        }).orElse(null);
    }
}
