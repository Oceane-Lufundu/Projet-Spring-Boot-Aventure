package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository

interface UtilisateurDAO: JpaRepository<Utilisateur, Long> {
}