package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.dto.PhongDto;
import vn.teca.scopio.base.repository.custom.PhongCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        str.append(" SELECT p.id_phong, p.so_phong, pd.id_phong_dat ");
        str.append(" FROM phong p ");
        str.append(" JOIN phong_dat pd ON p.id_phong = pd.phong_id_phong ");
        str.append(" WHERE pd.trang_thai = 'checkin'; ");
        Query query = entityManager.createNativeQuery(str.toString());
        List<Object[]> list = query.getResultList();
        List<PhongDto> result = new ArrayList<>();
        try {
            if(!DataConvertUtil.isNullOrEmpty(list)){
                list.forEach(objects -> {
                    PhongDto dto = new PhongDto();
                    dto.setIdPhong(DataConvertUtil.safeToInt(objects[0]));
                    dto.setTenPhong(DataConvertUtil.safeToString(objects[1]));
                    dto.setIdPhongDat(DataConvertUtil.safeToInt(objects[2]));
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
        str.append(" SELECT p.id_phong, p.so_phong ");
        str.append(" FROM phong p ");
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