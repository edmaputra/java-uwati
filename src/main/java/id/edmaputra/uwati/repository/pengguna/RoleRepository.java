package id.edmaputra.uwati.repository.pengguna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pengguna.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>, QueryDslPredicateExecutor<Role>{

	Role findByNama(String nama);

}
