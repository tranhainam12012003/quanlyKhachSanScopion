package vn.teca.scopio.base.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.QuyenHan;
import vn.teca.scopio.base.repository.QuyenHanRepository;

import java.util.List;

@Service
public class QuyenHanSevices {
    @Autowired
    QuyenHanRepository quyenHanRepository;

    public List<QuyenHan> getall(){
        return quyenHanRepository.findAll();
    }

}
