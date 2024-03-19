package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Table(name = "loai_phong")
public class LoaiPhong {
    @Id
    @Column(name = "Id_loai_phong", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ten_loai_phong", nullable = false, length = 200)
    private String tenLoaiPhong;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "huong_nhin", nullable = false, length = 200)
    private String huongNhin;

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "dien_tich", nullable = false, length = 10)
    private String dienTich;

    @NotNull
    @Column(name = "gia_tien", nullable = false, precision = 30, scale = 2)
    private BigDecimal giaTien;

    @NotNull
    @Nationalized
    @Lob
    @Column(name = "mo_ta", nullable = false)
    private String moTa;

    @NotNull
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    @NotNull
    @Column(name = "so_luong_nguoi_o", nullable = false)
    private Integer soLuongNguoiO;

}