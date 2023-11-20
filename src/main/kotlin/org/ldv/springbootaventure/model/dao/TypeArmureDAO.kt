package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.TypeArmure
import org.springframework.data.jpa.repository.JpaRepository

interface TypeArmureDAO : JpaRepository<TypeArmure, Long> {

}