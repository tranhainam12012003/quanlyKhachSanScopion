package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TienIch;
@Repository
public interface TienIchRepository extends JpaRepository<TienIch, Integer> {

}