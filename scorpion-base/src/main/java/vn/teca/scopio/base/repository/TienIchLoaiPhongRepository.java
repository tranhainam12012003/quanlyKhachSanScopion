package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TienIchLoaiPhong;

import javax.transaction.Transactional;

@Repository
public interface TienIchLoaiPhongRepository extends JpaRepository<TienIchLoaiPhong, Integer> {

}