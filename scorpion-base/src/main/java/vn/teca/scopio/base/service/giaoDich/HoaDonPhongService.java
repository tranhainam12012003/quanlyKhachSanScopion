package vn.teca.scopio.base.service.giaoDich;

import vn.teca.scopio.base.model.HoaDonPhong;
import vn.teca.scopio.base.model.dto.HoaDonRequestDto;
import vn.teca.scopio.base.model.dto.HoaDonResponseDto;

import java.util.List;

public interface HoaDonPhongService {
    List<HoaDonRequestDto> hienThiHoaDonTT(Integer id);

    HoaDonPhong add(HoaDonPhong hoaDonPhong);

    List<HoaDonResponseDto> hienThiHoaDonCT(Integer id);
}
