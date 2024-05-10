package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.dto.PhongDto;
import vn.teca.scopio.base.repository.PhongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhongServices {
    @Autowired
    PhongRepository phongRepository;

    public List<Phong> getall(){
        return phongRepository.findAll();
    }
    // load danh sách phòng theo loại phòng ;
    public List<Phong> locLoaiPhong(Integer id){
        return phongRepository.loc(id);
    }
    public Phong them(Phong phong){
        return phongRepository.save(phong);
    }

    public Phong xoa(Integer id){
        Optional<Phong> optional=phongRepository.findById(id);
        return optional.map(o->{
            phongRepository.delete(o);
            return o;
        }).orElse(null);
    }
    public Phong update(Phong phong){
        Optional<Phong>optional=phongRepository.findById(phong.getId());
        return optional.map(o -> {
            o.setSoPhong(phong.getSoPhong());
            o.setLoaiPhongIdLoaiPhong(phong.getLoaiPhongIdLoaiPhong());
            o.setTrangThai(phong.getTrangThai());
            o.setSoTang(phong.getSoTang());
            //   o.setPhongDats(phong.getPhongDats()); (cái này là khóa rì đây a thấy sql không có kết nối)
            return phongRepository.save(phong);
        }).orElse(null);
    }

    // detail cho phần chuyển màn
    public Phong detail(Integer id) {
        Optional<Phong> optional = phongRepository.findById(id);
        return optional.orElse(null);
    }

    // lay ra so phong trong de gan phong
    public List<Phong> findPhongTrong(Integer id){
        return phongRepository.findPhongTrong(id);
    }

    // so do trang thai phong

    // lay ra danh sach phong co trang thai trong
    public List<PhongDto> getPhongTrong(){
        return phongRepository.getPhongTrangThaiTrong();
    }

    // lay ra danh sach phong co trang thai checkin ( dang o)
    public List<PhongDto> getPhongDangO(){
        return phongRepository.getPhongCheckin();
    }

}
