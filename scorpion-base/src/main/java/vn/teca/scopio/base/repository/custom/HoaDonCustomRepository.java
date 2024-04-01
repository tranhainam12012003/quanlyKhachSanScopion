package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.HoaDonRequestDto;

import java.util.List;

public interface HoaDonCustomRepository {

    List<HoaDonRequestDto> hienThiHoaDon(Integer idPhongDat);
}
