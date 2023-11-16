package org.ldv.springbootaventure.controller.admin

import org.ldv.springbootaventure.model.dao.TypeArmureDAO
import org.ldv.springbootaventure.model.entity.Armure
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

class TypeArmureController(val typeArmureDAO: TypeArmureDAO) {
    /**
     * Affiche la liste de toutes les armures.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/TypeArmure")
    fun index(model: Model): String {
        // Récupère toutes les armures depuis la base de données
        val armure = this.typeArmureDAO.findAll()

        // Ajoute la liste des armures au modèle pour affichage dans la vue
        model.addAttribute("armure", armure)

        // Retourne le nom de la vue à afficher
        return "admin/TypeArmure/index"
    }

    /**
     * Affiche les détails d'une armure en particulier.
     *
     * @param id L'identifiant unique de l'armure à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si l'armure avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/TypeArmure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère l'armure avec l'ID spécifié depuis la base de données
        val uneArmure = this.typeArmureDAO.findById(id).orElseThrow()

        // Ajoute l'armure' au modèle pour affichage dans la vue
        model.addAttribute("armure", uneArmure)

        // Retourne le nom de la vue à afficher
        return "admin/TypeArmure/show"
    }

}