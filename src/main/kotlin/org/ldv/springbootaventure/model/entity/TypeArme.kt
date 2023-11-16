package org.ldv.springbootaventure.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TypeArme constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val nombreDes: Int,
    val valeurDeMax: Int,
    val multiplicateurCritique: Int,
    val activationCritique: Int,
) {
}