package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemDAO : JpaRepository<Item,Long> {

//methode pour trouver un item par son nom
    fun findByNomContains(nom: String): List<Item>

}