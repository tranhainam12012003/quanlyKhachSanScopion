package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "hoa_don_phong")
public class HoaDonPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hoa_don", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hinh_thuc_thanh_toan_id_hinh_thuc", nullable = false)
    private HinhThucThanhToan hinhThucThanhToanIdHinhThuc;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phong_dat_id_phong_dat", nullable = false)
    private PhongDat phongDatIdPhongDat;

    @NotNull
    @Column(name = "tien_thanh_toan", nullable = false, precision = 30, scale = 2)
    private BigDecimal tienThanhToan;

}