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
@Table(name = "quyen_han")
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quyen_han", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "ten_quyen_han", nullable = false, length = 50)
    private String tenQuyenHan;

    @OneToMany(mappedBy = "quyenHanIdQuyenHan")
    private Set<TaiKhoanNv> taiKhoanNvs = new LinkedHashSet<>();

}