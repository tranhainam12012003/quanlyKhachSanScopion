package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.dto.PhongDto;
import vn.teca.scopio.base.repository.custom.PhongCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PhongCustomRepositoryImpl implements PhongCustomRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;


    @Override
    public List<PhongDto> getPhongCheckin(){
        StringBuilder str = new StringBuilder();
        str.append(" SELECT pd.don_dat_id_don_dat, p.id_phong, p.so_phong,  ");
        str.append(" p.loai_phong_Id_loai_phong, l.ten_loai_phong, ");
        str.append(" pd.id_phong_dat, pd.thoi_gian_vao, pd.thoi_gian_ra ");
        str.append(" FROM phong p ");
        str.append(" JOIN phong_dat pd ON p.id_phong = pd.phong_id_phong ");
        str.append(" JOIN loai_phong l ON p.loai_phong_Id_loai_phong = l.Id_loai_phong");
        str.append(" WHERE pd.trang_thai = 'checkin'; ");
        Query query = entityManager.createNativeQuery(str.toString());
        List<Object[]> list = query.getResultList();
        List<PhongDto> result = new ArrayList<>();
        try {
            if(!DataConvertUtil.isNullOrEmpty(list)){
                list.forEach(objects -> {
                    PhongDto dto = new PhongDto();
                    dto.setIdDonDat(DataConvertUtil.safeToInt(objects[0]));
                    dto.setIdPhong(DataConvertUtil.safeToInt(objects[1]));
                    dto.setTenPhong(DataConvertUtil.safeToString(objects[2]));
                    dto.setIdLoaiPhong(DataConvertUtil.safeToInt(objects[3]));
                    dto.setTenLoaiPhong(DataConvertUtil.safeToString(objects[4]));
                    dto.setIdPhongDat(DataConvertUtil.safeToInt(objects[5]));
                    dto.setThoiGianVao((Timestamp) objects[6]);
                    dto.setThoiGianRa((Timestamp) objects[7]);
                    result.add(dto);
                });
                return new ArrayList<>(result);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>(result);

    }

    @Override
    public List<PhongDto> getPhongTrangThaiTrong(){
        StringBuilder str = new StringBuilder();
        str.append(" SELECT p.id_phong, p.so_phong,  l.ten_loai_phong ");
        str.append(" FROM phong p ");
        str.append(" JOIN loai_phong l ON p.loai_phong_Id_loai_phong = l.Id_loai_phong");
        str.append(" WHERE p.id_phong NOT IN ( ");
        str.append(" SELECT pd.phong_id_phong ");
        str.append(" FROM phong_dat pd ");
        str.append(" WHERE pd.trang_thai = 'checkin' ");
        str.append(" ); ");
        Query query = entityManager.createNativeQuery(str.toString());
        List<Object[]> list = query.getResultList();
        List<PhongDto> result = new ArrayList<>();
        try {
            if (!DataConvertUtil.isNullOrEmpty(list)){
                list.forEach(objects -> {
                    PhongDto dto = new PhongDto();
                    dto.setIdPhong(DataConvertUtil.safeToInt(objects[0]));
                    dto.setTenPhong(DataConvertUtil.safeToString(objects[1]));
                    dto.setTenLoaiPhong(DataConvertUtil.safeToString(objects[2]));
                    result.add(dto);
                });
                return new ArrayList<>(result);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>(result);

    }

}
