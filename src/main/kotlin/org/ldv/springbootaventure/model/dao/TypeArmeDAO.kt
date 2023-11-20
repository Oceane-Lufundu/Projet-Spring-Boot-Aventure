package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Qualite
import org.ldv.springbootaventure.model.entity.TypeArme
import org.ldv.springbootaventure.model.entity.TypeArmure
import org.springframework.data.jpa.repository.JpaRepository

interface TypeArmeDAO : JpaRepository<TypeArme, Long> {
}