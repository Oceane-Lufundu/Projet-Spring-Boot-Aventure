package org.ldv.springbootaventure.controller.admin

import org.ldv.springbootaventure.model.dao.TypeArmeDAO
import org.ldv.springbootaventure.model.entity.TypeArme
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeArmeController (val typeArmeDAO: TypeArmeDAO ) {
    /**
     * Fonction qui affiche la liste de tous les types d'arme.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/TypeArme")
    fun index(model: Model): String {
        // Récupère tous les types d'arme depuis la base de données
        val typeArme = this.typeArmeDAO.findAll()
        // Ajoute la liste des armes au modèle pour affichage dans la vue
        model.addAttribute("typearme", typeArme)
        // Retourne le nom de la vue à afficher
        return "admin/TypeArme/index"
    }

    /**
     * Affiche les détails d'un type d'arme en particulier.
     *
     * @param id L'identifiant unique du type d'arme à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/TypeArme/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'arme avec l'ID spécifié depuis la base de données
        val unTypeArme = this.typeArmeDAO.findById(id).orElseThrow()
        // Ajoute le type d'arme au modèle pour affichage dans la vue
        model.addAttribute("typearme", unTypeArme)
        // Retourne le nom de la vue à afficher
        return "admin/TypeArme/show"
    }


    /**
     * Affiche le formulaire de création d'un nouveau type d'arme.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/TypeArme/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de TypeArme avec des valeurs par défaut
        val nouvelleTypeArme = TypeArme(null, null, null, null,null,null)
        // Ajoute le nouveau type d'arme au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleTypeArme", nouvelleTypeArme)
        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/TypeArme/create"
    }


    /**
     * Gère la soumission du formulaire d'ajout d'un type d'arme
     * @param nouvelleTypeArme L'objet TypeArme créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'accessoires après l'ajout réussi.
     */
    @PostMapping("/admin/TypeArme")
    fun store(@ModelAttribute nouvelleTypeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde le nouveau type d'arme dans la base de données
        val savedTypeArme = this.typeArmeDAO.save(nouvelleTypeArme)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedTypeArme.nom} réussi")
        // Redirige vers la page d'administration des types d'armes
        return "redirect:/admin/TypeArme"
    }

    @GetMapping("/admin/TypeArme/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'arme avec l'ID spécifié depuis la base de données
        val unTypeArme = this.typeArmeDAO.findById(id).orElseThrow()

        // Ajoute le type d'arme au modèle pour affichage dans la vue
        model.addAttribute("typearme", unTypeArme)

        // Retourne le nom de la vue à afficher
        return "admin/TypeArme/update"
    }

    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param TypeArme L'objet TypeArme mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'arme après la mise à jour réussie.
     * @throws NoSuchElementException si le type d'arme avec l'ID spécifié n'est pas trouvée dans la base de données.
     */

    @PostMapping("/admin/TypeArme/update")
    fun update(@ModelAttribute typeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        // Recherche le type d'arme existante dans la base de données
        val typeArmeModifier = this.typeArmeDAO.findById(typeArme.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de type d'arme avec les nouvelles valeurs du formulaire
        typeArmeModifier.nom = typeArme.nom
        typeArmeModifier.activationCritique = typeArme.activationCritique
        typeArmeModifier.multiplicateurCritique = typeArme.multiplicateurCritique
        typeArmeModifier.nombreDes = typeArme.nombreDes
        typeArmeModifier.valeurDeMax = typeArme.valeurDeMax

        // Sauvegarde le type d'arme modifiée dans la base de données
        val savedTypeArme = this.typeArmeDAO.save(typeArmeModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedTypeArme.nom} réussie")

        // Redirige vers la page d'administration des armes
        return "redirect:/admin/TypeArme"
    }


    /**Voici la méthode delete du contrôleur :
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant de la qualité à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la suppression réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/TypeArme/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche le type d'arme à supprimer dans la base de données
        val typeArme: TypeArme = this.typeArmeDAO.findById(id).orElseThrow()

        // Suppression du type d'arme de la base de données
        this.typeArmeDAO.delete(typeArme)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeArme.nom} réussie")

        // Redirige vers la page d'administration des types d'arme
        return "redirect:/admin/TypeArme"
    }
}

