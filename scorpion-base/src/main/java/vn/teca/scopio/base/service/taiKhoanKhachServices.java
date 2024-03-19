package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.teca.scopio.base.model.TaiKhoanKhach;
import vn.teca.scopio.base.repository.TaiKhoanKhachRepository;

import java.util.List;

@Service
public class taiKhoanKhachServices {
    @Autowired
    TaiKhoanKhachRepository taiKhoanKhachRepository;

    public List<TaiKhoanKhach> getall(){
        return taiKhoanKhachRepository.findAll();
    }
    public TaiKhoanKhach timkiem(String sdt) {
        List<TaiKhoanKhach> list = taiKhoanKhachRepository.findAll();
        for (TaiKhoanKhach taiKhoanKhach : list){
            if(taiKhoanKhach.getSoDienThoai().equalsIgnoreCase(sdt)){
                return taiKhoanKhach;
            }
        }
        return null;
    }


// update sql   (code vô hiệu hóa tài khoản  khách khàng   ---  sql tài khoản khách hàng cần thêm trạng thái )

//    public TaiKhoanKhach update(TaiKhoanKhach taiKhoanKhach, Integer id){
//        Optional<TaiKhoanKhach>optional=taiKhoanKhachRepository.findById(id);
//        return optional.map(o -> {
//            o.set();
//            return taiKhoanKhachRepository.save(taiKhoanKhach);
//        }).orElse(null);
//    }



}
