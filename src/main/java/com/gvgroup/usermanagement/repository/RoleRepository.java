package com.gvgroup.usermanagement.repository;

import com.gvgroup.usermanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query("""
                    SELECT DISTINCT r from Role r
                    LEFT JOIN FETCH r.authorities a
                    WHERE r.id IN (:roleIds)
            """)
    List<Role> fetchRolesWithAuthorities(@Param("roleIds") List<Long> roleIds);
}
