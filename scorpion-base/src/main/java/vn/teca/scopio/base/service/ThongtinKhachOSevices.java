package vn.teca.scopio.base.service;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.ThongTinKhachO;
import vn.teca.scopio.base.repository.ThongTinKhachORepository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ThongtinKhachOSevices {
    @Autowired
    ThongTinKhachORepository thongTinKhachORepository;

    public List<ThongTinKhachO> getAll(){
        return thongTinKhachORepository.findAll();
    }
    public ThongTinKhachO detail(Integer id) {
//        Optional<ThongTinKhachO> optional = thongTinKhachORepository.findById(id);
        ThongTinKhachO detail = thongTinKhachORepository.findById(id).orElse(null);
        return detail;
    }
    public List<ThongTinKhachO> timKiem(String hovaten , java.sql.Date thoiGianVao , Date thoiGianRa,String soGiayTo){
        return thongTinKhachORepository.timkiem(hovaten,thoiGianVao,thoiGianRa,soGiayTo);
    }
    public ThongTinKhachO add(ThongTinKhachO thongTinKhachO,Integer idDonDat){
//        List<ThongTinKhachO> thongTinKhachO1 = thongTinKhachORepository.findByPhongDatIdPhongDat(thongTinKhachO.getPhongDatIdPhongDat());
        List<ThongTinKhachO> thongTinKhachO1 = thongTinKhachORepository.findByIdDonDat(idDonDat);
        if (!Objects.isNull(thongTinKhachO1)){
             thongTinKhachO1.forEach(thongTinKhachO2 -> {
                if (thongTinKhachO2.getSoGiayTo().equals(thongTinKhachO.getSoGiayTo())){
                     throw new RuntimeException("Số giấy tờ bị trùng");

                }

            });
        }
            return thongTinKhachORepository.save(thongTinKhachO);


    }
    public ThongTinKhachO update(ThongTinKhachO thongTinKhachO,Integer id){
        Optional<ThongTinKhachO> optional = thongTinKhachORepository.findById(id);
        return optional.map(o ->{
            o.setHoTen(thongTinKhachO.getHoTen());
            o.setPhongDatIdPhongDat(thongTinKhachO.getPhongDatIdPhongDat());
            o.setTenGiayTo(thongTinKhachO.getTenGiayTo());
            o.setSoGiayTo(thongTinKhachO.getSoGiayTo());
            o.setQuocTich(thongTinKhachO.getQuocTich());
            return thongTinKhachORepository.save(o);
        }).orElse(null);
    }
    public ThongTinKhachO delete(Integer id){
        Optional<ThongTinKhachO> optional = thongTinKhachORepository.findById(id);
        return optional.map(o ->{
            thongTinKhachORepository.delete(o);
            return o;
        }).orElse(null);
    }
}
