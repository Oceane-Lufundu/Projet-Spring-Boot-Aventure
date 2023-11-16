package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Qualite
import org.springframework.data.jpa.repository.JpaRepository

interface TypeArmeDAO : JpaRepository<Qualite, Long> {
}