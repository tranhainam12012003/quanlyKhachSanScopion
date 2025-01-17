package vn.teca.scopio.base.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DetailThongTinPhongDatDTO_Dong;
import vn.teca.scopio.base.model.dto.PhongChuaGan_DTO_Dong;
import vn.teca.scopio.base.model.dto.PhongDaGanDTO_Dong;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public interface DetailPhongGan_ChuaGanRepository_dong {
    public DetailThongTinDonDatDTO_Dong detailDonDat(Integer id);
    public DetailThongTinPhongDatDTO_Dong detailPhongDat(Integer id);
//    public List<DetailThongTinDonDatDTO_Dong> detailPhongDaGan(Integer idPhongDat);
//    public List<DetailThongTinDonDatDTO_Dong> detailPhongChuaGan(Integer idPhongDat);
}
