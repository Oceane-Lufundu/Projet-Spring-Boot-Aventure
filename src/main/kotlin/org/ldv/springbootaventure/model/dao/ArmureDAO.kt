package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Armure
import org.springframework.data.jpa.repository.JpaRepository

interface ArmureDAO : JpaRepository<Armure, Long> {
}