package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.repository.PhongDatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhongDatServices {
    @Autowired
    private PhongDatRepository phongDatRepository;
    public List<PhongDat> getall(){
        return phongDatRepository.findAll();
    }
    public PhongDat detail(Integer id) {
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        return optional.orElse(null);
    }
}
