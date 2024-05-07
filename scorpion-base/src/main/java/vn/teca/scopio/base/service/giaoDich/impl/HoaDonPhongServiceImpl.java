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

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class HoaDonPhongServiceImpl implements HoaDonPhongService {
    @Autowired
    HoaDonPhongRepository hoaDonPhongRepository;

    @Autowired
    DichVuDatRepository dichVuDatRepository;

    @Override
    public List<HoaDonRequestDto> hienThiHoaDonTT(Integer id) {
        List<HoaDonRequestDto> result = hoaDonPhongRepository.hienThiHoaDon(id);

        if (result != null) {

            result.forEach(p -> {

                p = getMoreInfroRe(p);
            });
        }
        return result;

    }
    public HoaDonRequestDto getMoreInfroRe(HoaDonRequestDto dto) {
        // set dich vu dat
        try {
            if (dto.getHinhThucDat().equalsIgnoreCase("Online")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime thoiGianVao = LocalDateTime.ofInstant(dto.getThoiGianVao().toInstant(), ZoneOffset.UTC);
                LocalDateTime thoiGianRa = LocalDateTime.ofInstant(dto.getThoiGianRa().toInstant(), ZoneOffset.UTC);
//                LocalDateTime thoiGianVao = LocalDateTime.parse(dto.getThoiGianVao().toString(), formatter);
//                LocalDateTime thoiGianRa = LocalDateTime.parse(dto.getThoiGianRa().toString(), formatter);
                LocalDate ngayVao = thoiGianVao.toLocalDate();
                LocalDate ngayRa = thoiGianRa.toLocalDate();
                long soNgayChenhLech = ChronoUnit.DAYS.between(ngayVao, ngayRa);
                BigDecimal giaTien = dto.getTienLoaiPhong().multiply(BigDecimal.valueOf(soNgayChenhLech));

                BigDecimal tienPhong = dto.getTienPhong() != null ? dto.getTienPhong() : BigDecimal.ZERO;
                BigDecimal tienDichVu = dto.getTienDichVu() != null ? dto.getTienDichVu() : BigDecimal.ZERO;
//                BigDecimal tienDaThanhToan = dto.getTienDaThanhToan() != null ? dto.getTienDaThanhToan() : BigDecimal.ZERO;

                BigDecimal tienPhaiTra = tienPhong.add(tienDichVu).subtract(giaTien);
//                BigDecimal tienPhaiTra = (dto.getTienPhong().add(dto.getTienDichVu())).subtract(dto.getTienDaThanhToan());
                dto.setTienDaThanhToan(giaTien);
                dto.setTienPhaiTra(tienPhaiTra);
            }
            else {
                BigDecimal tienDaThanhToan = dto.getTienDaThanhToan() != null ? dto.getTienDaThanhToan() : BigDecimal.ZERO;
                BigDecimal tienPhaiTra = (dto.getTienPhong().add(dto.getTienDichVu()));
                dto.setTienPhaiTra(tienPhaiTra);
                dto.setTienDaThanhToan(tienDaThanhToan);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return dto;
    }

    @Override
    public HoaDonPhong add(HoaDonPhong hoaDonPhong) {
        return hoaDonPhongRepository.save(hoaDonPhong);
    }

    @Override
    public List<HoaDonResponseDto> hienThiHoaDonCT(Integer id) {
        //        List<LoaiPhongDto> result = loaiPhongRepository.searchLoaiPhongTrong(thoiGianVao,thoiGianRa);
//        if (result!=null){
//            result.forEach(p ->{
//                p = getMoreInfro(p);
//            });
//        }
//        return result;
        List<HoaDonResponseDto> result = hoaDonPhongRepository.layHoaDonChiTiet(id);
        if (result != null) {
            result.forEach(p -> {
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
