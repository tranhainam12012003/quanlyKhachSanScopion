package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DTOThongTinLoaiPhong;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.ThongTinKhachDat;
import vn.teca.scopio.base.repository.DonDatRepository;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.repository.ThongTinKhachDatRepository;

import java.util.List;

@Service
public class DTO_ThongTinLoaiPhong_services {
    @Autowired
    private LoaiPhongDatRepository loaiPhongDatRepository;
    @Autowired
    private ThongTinKhachDatRepository thongTinKhachDatRepository;
    @Autowired
    private DonDatRepository donDatRepository;

    public DTOThongTinLoaiPhong detail(Integer id){
        LoaiPhongDat loaiPhongDats=loaiPhongDatRepository.findById(id).orElse(null);
        DonDat donDats=donDatRepository.findById(id).orElse(null);
        ThongTinKhachDat thongTinKhachDats=thongTinKhachDatRepository.findById(id).orElse(null);
        return new DTOThongTinLoaiPhong(loaiPhongDats,donDats,thongTinKhachDats);
    }
}
