package org.ldv.springbootaventure.model.dao
import org.ldv.springbootaventure.model.entity.Qualite
import org.ldv.springbootaventure.model.entity.TypeAccessoire
import org.springframework.data.jpa.repository.JpaRepository

interface TypeAccessoireDAO :JpaRepository<TypeAccessoire,Long>{
}