package vn.teca.scopio.base.service.giaoDich.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.PhongDatDto;
import vn.teca.scopio.base.repository.DonDatRepository;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.repository.PhongDatRepository;
import vn.teca.scopio.base.service.giaoDich.PhongDatServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhongDatServiceImpl implements PhongDatServices {
    @Autowired
    PhongDatRepository phongDatRepository;

    @Autowired
    LoaiPhongDatRepository loaiPhongDatRepository;

    @Autowired
    DonDatRepository donDatRepository;
    @Override
    public void save(PhongDatDto dto){
        String trangThai = "WAIT FOR CHECKIN";
        PhongDat phongDat = new PhongDat();
        phongDat.setPhongIdPhong(dto.getPhongIdPhong());
        phongDat.setDonDatIdDonDat(dto.getDonDatIdDonDat());
        phongDat.setThoiGianVao(dto.getThoiGianVao());
        phongDat.setThoiGianRa(dto.getThoiGianRa());
        phongDat.setSoTienPhong(dto.getSoTienPhong());
        phongDat.setTrangThai(trangThai);
        phongDatRepository.save(phongDat);

    }

    @Override
    public void checkin(Integer id){
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        optional.map(o ->{
            o.setThoiGianVao(LocalDateTime.now());
            o.setTrangThai("Checkin");
            return phongDatRepository.save(o);
        }).orElse(null);
        Integer idDonDat = optional.get().getDonDatIdDonDat().getId();
        Optional<DonDat> dd = donDatRepository.findById(idDonDat);
        dd.map(o ->{
            o.setTrangThai("DANG O");
            return donDatRepository.save(o);
        }).orElse(null);
    }
    @Override
    public void update(PhongDatDto dto,Integer id){
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        optional.map(o ->{
            o.setThoiGianRa(dto.getThoiGianRa());
            o.setSoTienPhong(dto.getSoTienPhong());
            o.setTrangThai("Checkin");
            return phongDatRepository.save(o);
        }).orElse(null);
    }
    @Override
    public void checkout(Integer id){
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        optional.map(o ->{
            o.setThoiGianRa(LocalDateTime.now());
            o.setTrangThai("Checkout");
            return phongDatRepository.save(o);
        }).orElse(null);
//        checkoutDonDat(idDonDat);

    }
    @Override
    public Integer countCheckout(Integer id){
        List<PhongDat> phongDat = phongDatRepository.findPhongDatByIdDonDat(id);
        int count = 0;
        for (PhongDat pd : phongDat){
            if(pd.getTrangThai().equalsIgnoreCase("Checkout")){
                count ++;
            }
        }
        return count;
    }
    @Override
    public void checkoutDonDat(Integer id){
        LoaiPhongDat optional = loaiPhongDatRepository.findByIdDonDat(id);
        int soLuong = optional.getSoLuong();
        int soLuongChechOut = countCheckout(id);
        if (soLuong == soLuongChechOut){
            Optional<DonDat> donDat = donDatRepository.findById(id);
            donDat.map(o->{
                o.setTrangThai("Checkout");
                return donDatRepository.save(o);
            }).orElse(null);
        }

    }

}
