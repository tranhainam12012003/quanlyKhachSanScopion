package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.ThongTinKhachDat;
import vn.teca.scopio.base.repository.ThongTinKhachDatRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ThongTinKhachDatServices {
    @Autowired
    ThongTinKhachDatRepository thongTinKhachDatRepository;

    public ThongTinKhachDat detail(Integer id) {
        Optional<ThongTinKhachDat> optional = thongTinKhachDatRepository.findById(id);
        return optional.orElse(null);
    }
    public ThongTinKhachDat findBySoDienThoai(String soDT){
        Optional<ThongTinKhachDat> optional = thongTinKhachDatRepository.getThongTinKhachDatBySoDienThoai(soDT);
        return optional.isPresent()? optional.get() : null;
    }
    public ThongTinKhachDat add(ThongTinKhachDat kd){
        return thongTinKhachDatRepository.save(kd);
    }
    public ThongTinKhachDat update(ThongTinKhachDat kd, Integer id){
        Optional<ThongTinKhachDat> optional = thongTinKhachDatRepository.findById(id);
        return optional.map(o -> {
            o.setHoTen(kd.getHoTen());
            o.setEmail(kd.getEmail());
            o.setDiaChi(kd.getDiaChi());
            o.setNgaySinh(kd.getNgaySinh());
            o.setGioiTinh(kd.getGioiTinh());
            o.setQuocTich(kd.getQuocTich());
            o.setSoDienThoai(kd.getSoGiayTo());
            o.setTenGiayTo(kd.getTenGiayTo());
            o.setSoGiayTo(kd.getSoGiayTo());
            return thongTinKhachDatRepository.save(kd);
        }).orElse(null);
    }
    public ThongTinKhachDat delete(Integer id){
        Optional<ThongTinKhachDat> optional = thongTinKhachDatRepository.findById(id);
        return optional.map(o -> {
             thongTinKhachDatRepository.delete(o);
             return o;
        }).orElse(null);
    }

    public List<ThongTinKhachDat> findAll() {
        return thongTinKhachDatRepository.findAll();
    }
//    public List<LoaiPhong>getall(){
//        return loaiPhongRepository.findAll();
//    }
}
