package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.HoaDonPhong;

import java.util.List;

@Repository
public interface HoaDonPhongRepository extends JpaRepository<HoaDonPhong, Integer> {


}