package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hinh_thuc_thanh_toan")
public class HinhThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hinh_thuc", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ten_hinh_thuc_tra", nullable = false, length = 200)
    private String tenHinhThucTra;

    @OneToMany(mappedBy = "hinhThucThanhToanIdHinhThuc")
    private Set<HoaDonPhong> hoaDonPhongs = new LinkedHashSet<>();

}