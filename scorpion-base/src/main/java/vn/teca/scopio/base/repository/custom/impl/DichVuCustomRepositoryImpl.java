package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.dto.DichVuDatDto;
import vn.teca.scopio.base.repository.custom.DichVuCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DichVuCustomRepositoryImpl implements DichVuCustomRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public List<DichVuDatDto> hienThiDichVuDat(Integer idPhongDat){
        StringBuilder str = new StringBuilder();
        str.append(" SELECT dv.ten_dich_vu, dvd.so_luong, dvd.so_tien ");
        str.append(" FROM dich_vu_dat dvd ");
        str.append(" JOIN dich_vu dv ON dv.id_dich_vu = dvd.dich_vu_id_dich_vu ");
        str.append(" WHERE dvd.phong_dat_id_phong_dat = :idPhongDat ");
        str.append(" GROUP BY dv.ten_dich_vu, dvd.so_luong, dvd.so_tien;");
        Query query = entityManager.createNativeQuery(str.toString());
        query.setParameter("idPhongDat", idPhongDat);
        List<Object[]> lst = query.getResultList();
        List<DichVuDatDto> result = new ArrayList<>();
        try {
            if (!DataConvertUtil.isNullOrEmpty(lst)){
                lst.forEach(objects ->{
                    DichVuDatDto dto = new DichVuDatDto();
                    dto.setTenDichVu(DataConvertUtil.safeToString(objects[0]));
                    dto.setSoLuong(DataConvertUtil.safeToInt(objects[1]));
                    dto.setSoTien(DataConvertUtil.safeToBigDecimal(objects[2]));
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
