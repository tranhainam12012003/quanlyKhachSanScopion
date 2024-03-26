package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.ThongTinKhachO;
import vn.teca.scopio.base.repository.ThongTinKhachORepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ThongtinKhachOSevices {
    @Autowired
    ThongTinKhachORepository thongTinKhachORepository;

    public List<ThongTinKhachO> getAll(){
        return thongTinKhachORepository.findAll();
    }
    public ThongTinKhachO detail(Integer id) {
        Optional<ThongTinKhachO> optional = thongTinKhachORepository.findById(id);
        return optional.orElse(null);
    }
    public List<ThongTinKhachO> timKiem(String hovaten , java.sql.Date thoiGianVao , Date thoiGianRa,String soGiayTo){
        return thongTinKhachORepository.timkiem(hovaten,thoiGianVao,thoiGianRa,soGiayTo);
    }
}
