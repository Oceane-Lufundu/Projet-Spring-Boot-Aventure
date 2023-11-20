package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*


class Armure ( nom: String, description: String, val qualite: Qualite, val typeArmure: TypeArmure) {
}


