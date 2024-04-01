package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.dto.HoaDonResponseDto;
import vn.teca.scopio.base.repository.custom.HoaDonResponseRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HoaDonResponseRepositoryImpl implements HoaDonResponseRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public List<HoaDonResponseDto> layHoaDonChiTiet(Integer id){
        StringBuilder str = new StringBuilder();
        str.append(" SELECT id_phong_dat, lp.ten_loai_phong, p.so_phong, pd.thoi_gian_vao,pd.thoi_gian_ra,pd.so_tien_phong ");
        str.append(" FROM phong_dat pd ");
        str.append(" JOIN phong p ON pd.phong_id_phong = p.id_phong ");
        str.append(" JOIN loai_phong lp ON p.loai_phong_Id_loai_phong = lp.Id_loai_phong ");
        str.append(" WHERE pd.id_phong_dat = :id ; ");
        Query query = entityManager.createNativeQuery(str.toString());
        query.setParameter("id", id);
        List<Object[]> lst = query.getResultList();
        List<HoaDonResponseDto> result = new ArrayList<>();
        try {
            if (!DataConvertUtil.isNullOrEmpty(lst)){
                lst.forEach(objects -> {
                    HoaDonResponseDto dto = new HoaDonResponseDto();
                    dto.setIdPhongDat(DataConvertUtil.safeToInt(objects[0]));
                    dto.setLoaiPhong(DataConvertUtil.safeToString(objects[1]));
                    dto.setTenPhong(DataConvertUtil.safeToString(objects[2]));
                    dto.setThoiGianVao((Timestamp)(objects[3]));
                    dto.setThoiGianRa((Timestamp)(objects[4]));
                    dto.setTienPhong(DataConvertUtil.safeToBigDecimal(objects[5]));
                    result.add(dto);
                });
                return new ArrayList<>(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>(result);
    }
}
