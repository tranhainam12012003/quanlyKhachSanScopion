package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TaiKhoanKhach;
import vn.teca.scopio.base.model.ThongTinKhachDat;
import vn.teca.scopio.base.model.authentication.TaiKhoanKhachDtoLogin;
import vn.teca.scopio.base.model.dto.TaiKhoanKhachAddDto;
import vn.teca.scopio.base.repository.TaiKhoanKhachRepository;
import vn.teca.scopio.base.repository.ThongTinKhachDatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanKhachService {
    @Autowired
    private TaiKhoanKhachRepository repository;
    @Autowired
    private ThongTinKhachDatRepository thongTinKhachDatRepository;

    public Optional<TaiKhoanKhachDtoLogin> findTaiKhoan(String soDienThoai, String password) {
        Optional<TaiKhoanKhachDtoLogin> optionalTaiKhoan = Optional.ofNullable(repository.getInfoKhachByLogin(soDienThoai, password));

        if (optionalTaiKhoan.isPresent()) {
            // Result is present and not null
            TaiKhoanKhachDtoLogin taiKhoanKhachDtoLogin = optionalTaiKhoan.get();
            // Do something with taiKhoanNVDtoLogin
            return Optional.of(taiKhoanKhachDtoLogin);
        } else {
            // Handle the case where the result is not present or is present but null
            System.out.println("Login failed. Invalid phone number or password.");
            return Optional.empty();
        }
    }
    //get all info from sign up the check duplicate and add new info then return a message no matter what
    public String signUpTaiKhoanKhach(String hoVaTen, String soDienThoai, String email, boolean gioiTinh, String password) {
        String message;
        if(repository.isExistSoDienThoai(soDienThoai) == 1){
            message = "số điện thoại này đã được đăng ký trên 1 tài khoản";
        }else {
            message = "đăng ký thành công";
            repository.addNewInfoTaiKhoanKhachAndThongTinKhachDat(hoVaTen,soDienThoai,email,gioiTinh?1:0,password);
        }
        return message;
    }
    public TaiKhoanKhach add(TaiKhoanKhachAddDto taiKhoanKhach){
        // tạo thông tin khách đặt
        ThongTinKhachDat thongTinKhachDat = new ThongTinKhachDat();
        thongTinKhachDat.setHoTen(taiKhoanKhach.getHoTen());
        thongTinKhachDat.setSoDienThoai(taiKhoanKhach.getSdt());
        thongTinKhachDat.setEmail(taiKhoanKhach.getEmail());
        thongTinKhachDat.setGioiTinh(taiKhoanKhach.getGioiTinh());
        thongTinKhachDatRepository.save(thongTinKhachDat);
        TaiKhoanKhach taiKhoan = new TaiKhoanKhach();
        taiKhoan.setSoDienThoai(taiKhoanKhach.getSdt());
        taiKhoan.setMatKhau(taiKhoanKhach.getMatKhau());
        taiKhoan.setThongTinKhachDatIdKhachDat(thongTinKhachDat);
        repository.save(taiKhoan);
        return taiKhoan;
    }
    public List<TaiKhoanKhach> getAll(){
        return repository.findAll();
    }
    public TaiKhoanKhach update(TaiKhoanKhach taiKhoanKhach, Integer id) {
        Optional<TaiKhoanKhach> optional = repository.findById(id);
        return optional.map(o -> {
            o.setSoDienThoai(taiKhoanKhach.getSoDienThoai());
            o.setMatKhau(taiKhoanKhach.getMatKhau());
            o.setThongTinKhachDatIdKhachDat(taiKhoanKhach.getThongTinKhachDatIdKhachDat());
            return repository.save(o);
        }).orElse(null);
    }
    public TaiKhoanKhach detail(Integer id){
        return repository.findById(id).get();
    }
}
