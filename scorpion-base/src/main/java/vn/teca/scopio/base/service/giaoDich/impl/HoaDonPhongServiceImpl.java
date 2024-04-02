package vn.teca.scopio.base.service.giaoDich.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.HoaDonPhong;
import vn.teca.scopio.base.model.dto.DichVuDatDto;
import vn.teca.scopio.base.model.dto.HoaDonRequestDto;
import vn.teca.scopio.base.model.dto.HoaDonResponseDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.DichVuDatRepository;
import vn.teca.scopio.base.repository.HoaDonPhongRepository;
import vn.teca.scopio.base.service.giaoDich.HoaDonPhongService;

import java.util.List;

@Service
public class HoaDonPhongServiceImpl implements HoaDonPhongService {
    @Autowired
    HoaDonPhongRepository hoaDonPhongRepository;

    @Autowired
    DichVuDatRepository dichVuDatRepository;

    @Override
    public List<HoaDonRequestDto> hienThiHoaDonTT(Integer id){
        return hoaDonPhongRepository.hienThiHoaDon(id);
    }
    @Override
    public HoaDonPhong add(HoaDonPhong hoaDonPhong){
        return hoaDonPhongRepository.save(hoaDonPhong);
    }

    @Override
    public List<HoaDonResponseDto> hienThiHoaDonCT(Integer id){
        //        List<LoaiPhongDto> result = loaiPhongRepository.searchLoaiPhongTrong(thoiGianVao,thoiGianRa);
//        if (result!=null){
//            result.forEach(p ->{
//                p = getMoreInfro(p);
//            });
//        }
//        return result;
        List<HoaDonResponseDto> result = hoaDonPhongRepository.layHoaDonChiTiet(id);
        if (result!=null){
            result.forEach(p ->{
                p = getMoreInfro(p);
            });
        }
        return result;
    }

    public HoaDonResponseDto getMoreInfro(HoaDonResponseDto dto) {
        // set dich vu dat
        try {
            dto.setDichVuDat(dichVuDatRepository.hienThiDichVuDat(dto.getIdPhongDat()));

        } catch (Exception e) {
            e.printStackTrace();
            dto.setDichVuDat(null);
        }

        return dto;
    }
}
