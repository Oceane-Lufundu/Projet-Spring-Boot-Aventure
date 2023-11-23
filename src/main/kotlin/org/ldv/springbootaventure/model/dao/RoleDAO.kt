package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleDAO: JpaRepository<Role, Long> {
}