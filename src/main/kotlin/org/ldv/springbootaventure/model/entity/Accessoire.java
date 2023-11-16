package org.ldv.springbootaventure.model.entity;

public class Accessoire constructor (
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", nullable = false)
nom: String, description: String, val qualite: Qualite, val typeArme: TypeArme) {
}

{