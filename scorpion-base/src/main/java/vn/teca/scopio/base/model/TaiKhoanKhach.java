package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "tai_khoan_khach")
public class TaiKhoanKhach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tai_khoan_khach", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thong_tin_khach_dat_id_khach_dat", nullable = false)
    private ThongTinKhachDat thongTinKhachDatIdKhachDat;

    @Size(max = 10)
    @NotNull
    @Column(name = "so_dien_thoai", nullable = false, length = 10)
    private String soDienThoai;

    @Size(max = 200)
    @NotNull
    @Column(name = "mat_khau", nullable = false, length = 200)
    private String matKhau;

}