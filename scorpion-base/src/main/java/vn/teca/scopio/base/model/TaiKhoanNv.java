package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "tai_khoan_nv")
public class TaiKhoanNv {
    @Id
    @Column(name = "id_tai_khoan", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quyen_han_id_quyen_han", nullable = false)
    private QuyenHan quyenHanIdQuyenHan;

    @Size(max = 50)
    @NotNull
    @Column(name = "ten_tai_khoan", nullable = false, length = 50)
    private String tenTaiKhoan;

    @Size(max = 50)
    @NotNull
    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Size(max = 10)
    @NotNull
    @Column(name = "so_dien_thoai", nullable = false, length = 10)
    private String soDienThoai;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 15)
    @NotNull
    @Column(name = "CCCD", nullable = false, length = 15)
    private String cccd;

}