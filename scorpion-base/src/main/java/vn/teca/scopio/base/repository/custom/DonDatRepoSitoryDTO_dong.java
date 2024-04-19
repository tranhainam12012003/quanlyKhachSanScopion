package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.DTOThongTinLoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDatDto_Dong;

import java.util.List;

public interface DonDatRepoSitoryDTO_dong {
    List<DTOThongTinLoaiPhong>detailDonDat(Integer id);
}
