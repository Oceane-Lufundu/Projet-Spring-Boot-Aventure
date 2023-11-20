package org.ldv.springbootaventure.controller.admin

import org.ldv.springbootaventure.model.dao.TypeArmureDAO
import org.ldv.springbootaventure.model.entity.Armure
import org.ldv.springbootaventure.model.entity.TypeArmure
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
@Controller
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
        model.addAttribute("armures", armure)

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

        // Ajoute l'armure au modèle pour affichage dans la vue
        model.addAttribute("armure", uneArmure)

        // Retourne le nom de la vue à afficher
        return "admin/TypeArmure/show"
    }

    /**
     * Affiche le formulaire de création d'une nouvelle armure.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/TypeArmure/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance d'armure avec des valeurs par défaut
        val nouvelleTypeArmure = TypeArmure(0, "",0)

        // Ajoute la nouvelle armure au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleArmure", nouvelleTypeArmure)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/TypeArmure/create"
    }

    /**
     * Gère la soumission du formulaire d'ajout d'armure.
     *
     * @param nouvelleTypeArmure L'objet armure créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armures après l'ajout réussi.
     */
    @PostMapping("/admin/TypeArmure")
    fun store(@ModelAttribute nouvelleTypeArmure: TypeArmure, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle armure dans la base de données
        val savedTypeArmure = this.typeArmureDAO.save(nouvelleTypeArmure)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedTypeArmure.nom} réussi")
        // Redirige vers la page d'administration des armures
        return "redirect:/admin/TypeArmure"
    }

    @GetMapping("/admin/TypeArmure/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère l'armure avec l'ID spécifié depuis la base de données
        val uneArmure = this.typeArmureDAO.findById(id).orElseThrow()

        // Ajoute l'armure au modèle pour affichage dans la vue
        model.addAttribute("armure", uneArmure)

        // Retourne le nom de la vue à afficher
        return "admin/TypeArmure/edit"
    }


    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param armure L'objet Armure mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armures après la mise à jour réussie.
     * @throws NoSuchElementException si l'armure avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/TypeArmure/update")
    fun update(@ModelAttribute  typeArmure: TypeArmure, redirectAttributes: RedirectAttributes): String {
        // Recherche de l'armure existante dans la base de données
        val armureModifier = this.typeArmureDAO.findById(typeArmure.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de l'armure avec les nouvelles valeurs du formulaire
        armureModifier.nom = typeArmure.nom
        armureModifier.bonusType = typeArmure.bonusType

        // Sauvegarde l'armure modifiée dans la base de données
        val savedArmure = this.typeArmureDAO.save(armureModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedArmure.nom} réussie")

        // Redirige vers la page d'administration des armures
        return "redirect:/admin/TypeArmure"
    }

    /**
     * Gère la suppression d'une armure par son identifiant.
     *
     * @param id L'identifiant de l'armure' à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armures après la suppression réussie.
     * @throws NoSuchElementException si l'armure avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/TypeArmure/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de l'armure' à supprimer dans la base de données
        val armure: TypeArmure? = this.typeArmureDAO.findById(id).orElseThrow()

        // Suppression de l'armure de la base de données
       //this.typeArmureDAO.delete(typeArmureDAO)

        // Ajoute un message de succès pour être affiché à la vue suivante
        //redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${TypeArmure.nom} réussie")

        // Redirige vers la page d'administration des armures
        return "redirect:/admin/TypeArmure"
    }
}