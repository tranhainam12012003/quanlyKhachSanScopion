package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "thong_tin_khach_dat")
public class ThongTinKhachDat {
    @Id
    @Column(name = "id_khach_dat", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ho_ten", nullable = false, length = 200)
    private String hoTen;

    @Size(max = 20)
    @NotNull
    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh = false;

    @Nationalized
    @Lob
    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Size(max = 255)
    @Nationalized
    @Column(name = "quoc_tich")
    private String quocTich;

    @Size(max = 50)
    @Column(name = "so_giay_to", length = 50)
    private String soGiayTo;

    @Size(max = 20)
    @Nationalized
    @Column(name = "ten_giay_to", length = 20)
    private String tenGiayTo;

}