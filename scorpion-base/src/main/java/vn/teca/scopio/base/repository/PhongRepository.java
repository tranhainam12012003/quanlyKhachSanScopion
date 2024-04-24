package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.repository.custom.PhongCustomRepository;

import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer>, PhongCustomRepository {
    @Query(value = "SELECT * FROM [dbo].[phong] WHERE [loai_phong_Id_loai_phong] = :id", nativeQuery = true)
    List<Phong> loc(@PathVariable Integer id);

    @Query(value = "DECLARE @IdLoaiPhong int = :idLoaiPhong ; \n" +
            "CREATE TABLE #PhongCoCheckin (\n" +
            "    id_phong int\n" +
            ");\n" +
            "INSERT INTO #PhongCoCheckin (id_phong)\n" +
            "SELECT pd.phong_id_phong\n" +
            "FROM phong_dat pd\n" +
            "INNER JOIN phong p ON pd.phong_id_phong = p.id_phong\n" +
            "WHERE p.loai_phong_Id_loai_phong = @IdLoaiPhong AND (pd.trang_thai = 'checkin' OR pd.trang_thai = 'WAIT FOR CHECKIN');\n" +
            "SELECT p.id_phong,p.loai_phong_Id_loai_phong,p.so_phong,p.so_tang,p.trang_thai\n" +
            "FROM phong p\n" +
            "LEFT JOIN #PhongCoCheckin pc ON p.id_phong = pc.id_phong\n" +
            "WHERE p.loai_phong_Id_loai_phong = @IdLoaiPhong AND pc.id_phong IS NULL;\n" +
            "DROP TABLE #PhongCoCheckin;", nativeQuery = true)
    List<Phong> findPhongTrong(@PathVariable Integer idLoaiPhong);


    @Modifying
    @Query(value = "delete from phong where loai_phong_Id_loai_phong=:idLoaiPhong", nativeQuery = true)
    void deleteLoaiPhong(@Param("idLoaiPhong")Integer idLoaiPhong);
}