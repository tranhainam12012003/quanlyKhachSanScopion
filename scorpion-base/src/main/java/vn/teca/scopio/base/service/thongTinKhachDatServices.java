package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.ThongTinKhachDat;
import vn.teca.scopio.base.repository.ThongTinKhachDatRepository;

import java.util.Optional;


@Service
public class thongTinKhachDatServices {
    @Autowired
    ThongTinKhachDatRepository thongTinKhachDatRepository;
    public ThongTinKhachDat detail(Integer id) {
        Optional<ThongTinKhachDat> optional = thongTinKhachDatRepository.findById(id);
        return optional.orElse(null);
    }
}
