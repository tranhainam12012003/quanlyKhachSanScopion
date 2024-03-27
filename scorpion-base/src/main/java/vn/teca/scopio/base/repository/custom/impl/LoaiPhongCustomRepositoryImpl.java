package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.HinhAnh;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.custom.LoaiPhongCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class LoaiPhongCustomRepositoryImpl implements LoaiPhongCustomRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public List<LoaiPhongDto> findAllHinhAnh() {
        StringBuilder str = new StringBuilder();
        str.append("SELECT lp.ten_loai_phong, lp.huong_nhin, lp.dien_tich, lp.gia_tien, lp.mo_ta,\n");
        str.append("ha.hinh_anh_loai_phong,ti.ten_tien_ich");
        str.append("FROM loai_phong lp");
        str.append("INNER JOIN hinh_anh ha ON lp.Id_loai_phong = ha.loai_phong_Id_loai_phong");
        str.append("INNER JOIN tien_ich_loai_phong tilp ON lp.Id_loai_phong = tilp.loai_phong_Id_loai_phong");
        str.append("INNER JOIN tien_ich ti ON tilp.tien_ich_id_tien_ich = ti.id_tien_ich;");
        Query query = entityManager.createNativeQuery(str.toString());
        List<Object[]> lst = query.getResultList();
        List<LoaiPhongDto> result = new ArrayList<>();
        try {
            if (DataConvertUtil.isNullOrEmpty(lst)) {
                lst.forEach(objects -> {
                    LoaiPhongDto dto = new LoaiPhongDto();
                    dto.setTenLoaiPhong(DataConvertUtil.safeToString(objects[1]));
                    dto.setHuongNhin(DataConvertUtil.safeToString(objects[2]));
                    dto.setDienTich(DataConvertUtil.safeToString(objects[3]));
                    dto.setGiaTien(DataConvertUtil.safeToBigDecimal(objects[4]));
                    dto.setMoTa(DataConvertUtil.safeToString(objects[5]));
                    result.add(dto);
                });
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
