package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.repository.TienIchRepository;

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
}
