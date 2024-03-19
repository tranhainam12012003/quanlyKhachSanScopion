package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.Phong;

import java.util.List;
@Repository
public interface PhongRepository extends JpaRepository<Phong,Integer> {
    @Query(value = "SELECT * FROM [dbo].[phong] WHERE [loai_phong_Id_loai_phong] = ?1", nativeQuery = true)
    List<Phong> loc(Integer id);
}