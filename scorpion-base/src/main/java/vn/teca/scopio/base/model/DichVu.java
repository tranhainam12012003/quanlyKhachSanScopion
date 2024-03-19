package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dich_vu")
public class DichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dich_vu", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loai_dich_vu_id_loai_dich_vu", nullable = false)
    private LoaiDichVu loaiDichVuIdLoaiDichVu;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ten_dich_vu", nullable = false, length = 200)
    private String tenDichVu;

    @NotNull
    @Column(name = "gia_tien", nullable = false, precision = 30, scale = 2)
    private BigDecimal giaTien;

    @NotNull
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    @OneToMany(mappedBy = "dichVuIdDichVu")
    private Set<DichVuDat> dichVuDats = new LinkedHashSet<>();

}