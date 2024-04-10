package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.repository.TienIchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TienIchServices {
    @Autowired
    private TienIchRepository tienIchRepositpry;
    public List<TienIch>getall(){
        return  tienIchRepositpry.findAll();
    }
    public TienIch add(TienIch tienIch){
        return tienIchRepositpry.save(tienIch);
    }
    public TienIch delete(Integer id){
        Optional<TienIch>optional=tienIchRepositpry.findById(id);
        return optional.map(o->{
            tienIchRepositpry.delete(o);
            return o;
        }).orElse(null);
    }
    private TienIch mapToObject(Object[] result) {
        TienIch tienIch = new TienIch();
        tienIch.setTenTienIch((String) result[0]);
        tienIch.setId((Integer) result[1]);
        return tienIch;
    }
    public List<TienIch> getTienIchTheoID(Integer id) {
        List<Object[]> results = tienIchRepositpry.findTienIchByIdLoaiPhong(id);
        List<TienIch> tienIchList = new ArrayList<>();
        for (Object[] result : results) {
            tienIchList.add(mapToObject(result));
        }
        return tienIchList;
    }
    public List<TienIch>getTienIchTheoTen(String ten){
        return tienIchRepositpry.findTienIchByTenTienIchContaining(ten);
    }
}
