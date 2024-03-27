package vn.teca.scopio.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.HinhAnhRepository;
import vn.teca.scopio.base.repository.LoaiPhongRepository;
import vn.teca.scopio.base.repository.TienIchLoaiPhongRepository;
import vn.teca.scopio.base.repository.TienIchRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LoaiPhongServices {
    @Autowired
    LoaiPhongRepository loaiPhongRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    TienIchLoaiPhongRepository tienIchLoaiPhongRepository;

    @Autowired
    TienIchRepository tienIchRepository;

    public List<LoaiPhong>getall(){
        return loaiPhongRepository.findAll();
    }
//    public List<LoaiPhongDto>getAllHinhAnh(){return loaiPhongRepository.findAllAndHinhAnh();};
    public  LoaiPhong delete(Integer id){
        Optional<LoaiPhong> optional=loaiPhongRepository.findById(id);
        return optional.map(o->{
            loaiPhongRepository.delete(o);
            return o;
        }).orElse(null);
    }
    public  LoaiPhong findbyId(Integer id){
        Optional<LoaiPhong>optional=loaiPhongRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }
    public LoaiPhong add(LoaiPhong lp){
        return loaiPhongRepository.save(lp);
    }
    public LoaiPhong update(LoaiPhong lp,Integer id){
        Optional<LoaiPhong>optional=loaiPhongRepository.findById(id);
        return optional.map(o -> {
            o.setTenLoaiPhong(lp.getTenLoaiPhong());
            o.setDienTich(lp.getDienTich());
            o.setHuongNhin(lp.getHuongNhin());
            o.setGiaTien(lp.getGiaTien());
            o.setMoTa(lp.getMoTa());
            o.setTrangThai(lp.getTrangThai());
            o.setSoLuongNguoiO(lp.getSoLuongNguoiO());
            return loaiPhongRepository.save(o);
        }).orElse(null);
    }
    public List<LoaiPhong>timthemten(String name){
        return loaiPhongRepository.findByTenLoaiPhong(name);

    }
    public List<LoaiPhong>timTheoGia(BigDecimal gia){
        return loaiPhongRepository.findByGiaTienLessThanEqualOrderByGiaTienDesc(gia);
    }

    public List<LoaiPhong>timTheoSoNguoi(Integer sl){
        return loaiPhongRepository.findBySoLuongNguoiOLessThanEqualOrderBySoLuongNguoiODesc(sl);
    }
    public List<LoaiPhongDto> findAllHinhAnh() {
        List<LoaiPhongDto> result = loaiPhongRepository.findAllHinhAnh();
        List<LoaiPhongDto> newList = result;
        if (result != null) {
//            newList = result;
            newList.forEach(p -> {
                p = getMoreInfro(p);
            });
            return newList;
        }
       return result;
    }
    public LoaiPhongDto getMoreInfro(LoaiPhongDto dto){
        // set hinh anh
        try{
            dto.setHinhAnh(hinhAnhRepository.findByLoaiPhongIdLoaiPhong_Id(dto.getId()));
            }
            catch (Exception e){
                dto.setHinhAnh(null);
            }
        // set tien ich
        try{
//            List<TienIch> tienI =
//            dto.setTienTienIch();
        }
        catch (Exception e){
            dto.setTienTienIch(null);
        }
            return dto;
    }

}
