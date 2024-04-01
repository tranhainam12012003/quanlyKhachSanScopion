package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.DichVuDatDto;

import java.util.List;

public interface DichVuCustomRepository {
    List<DichVuDatDto> hienThiDichVuDat(Integer idPhongDat);
}
