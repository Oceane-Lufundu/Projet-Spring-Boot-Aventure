package org.ldv.springbootaventure.controller.admin

import org.ldv.springbootaventure.model.dao.ArmeDAO
import org.ldv.springbootaventure.model.dao.ArmureDAO
import org.ldv.springbootaventure.model.dao.QualiteDAO
import org.ldv.springbootaventure.model.dao.TypeArmureDAO
import org.ldv.springbootaventure.model.entity.Armure
import org.ldv.springbootaventure.model.entity.TypeArmure
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
@Controller
class ArmureController (val armureDAO: ArmureDAO,  val typeArmureDAO: TypeArmureDAO,
                         val qualiteDAO: QualiteDAO
){
    /**
     * Affiche la liste de toutes les armures.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/Armure")
    fun index(model: Model): String {
        // Récupère toutes les armures depuis la base de données
        val armures = this.armureDAO.findAll()

        // Ajoute la liste des armures au modèle pour affichage dans la vue
        model.addAttribute("armures", armures)

        // Retourne le nom de la vue à afficher
        return "admin/Armure/index"
    }

    /**
     * Affiche les détails d'une armure en particulier.
     *
     * @param id L'identifiant unique de l'armure à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si l'armure avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/Armure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère l'armure avec l'ID spécifié depuis la base de données
        val uneArmure = this.armureDAO.findById(id).orElseThrow()

        // Ajoute l'armure au modèle pour affichage dans la vue
        model.addAttribute("armure", uneArmure)

        // Retourne le nom de la vue à afficher
        return "admin/Armure/show"
    }

    /**
     * Affiche le formulaire de création d'une nouvelle armure.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/Armure/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance d'armure avec des valeurs par défaut
        val nouvelleArmure = Armure(0, "","","",null,null)

        // Récupère les valeurs de Qualite
        val lesQualites = qualiteDAO.findAll()

        // Récupère les valeurs de TypeArmure
        val lesTypeArmures = typeArmureDAO.findAll()

        // Ajoute les valeurs de Qualite à la nouvelle armure
        model.addAttribute("qualites",lesQualites)

        // Ajoute les valeurs de TypeArmure à la nouvelle armure
        model.addAttribute("typeArmures",lesTypeArmures)

        // Ajoute la nouvelle armure au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleArmure", nouvelleArmure)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/Armure/create"
    }

    /**
     * Gère la soumission du formulaire d'ajout d'armure.
     *
     * @param nouvelleArmure L'objet armure créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armures après l'ajout réussi.
     */
    @PostMapping("/admin/Armure")
    fun store(@ModelAttribute nouvelleArmure: Armure, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle armure dans la base de données
        val savedArmure = this.armureDAO.save(nouvelleArmure)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedArmure.nom} réussi")
        // Redirige vers la page d'administration des armures
        return "redirect:/admin/Armure"
    }

    @GetMapping("/admin/Armure/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère l'armure avec l'ID spécifié depuis la base de données
        val uneArmure = this.armureDAO.findById(id).orElseThrow()

        // Récupère les valeurs de Qualite
        val lesQualites = qualiteDAO.findAll()

        // Récupère les valeurs de TypeArmure
        val lesTypeArmures = typeArmureDAO.findAll()

        // Ajoute les valeurs de Qualite à la nouvelle armure
        model.addAttribute("qualites",lesQualites)

        // Ajoute les valeurs de TypeArmure à la nouvelle armure
        model.addAttribute("typeArmures",lesTypeArmures)


        // Ajoute l'armure au modèle pour affichage dans la vue
        model.addAttribute("armure", uneArmure)

        // Retourne le nom de la vue à afficher
        return "admin/Armure/update"
    }

    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param armure L'objet Armure mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armures après la mise à jour réussie.
     * @throws NoSuchElementException si l'armure avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Armure/update")
    fun update(@ModelAttribute  armure: Armure, redirectAttributes: RedirectAttributes): String {
        // Recherche de l'armure existante dans la base de données
        val armureModifier = this.armureDAO.findById(armure.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de l'armure avec les nouvelles valeurs du formulaire
        armureModifier.nom = armure.nom
        armureModifier.description = armure.description
        armureModifier.cheminImage = armure.cheminImage
        armureModifier.qualite = armure.qualite
        armureModifier.typeArmure = armure.typeArmure

        // Sauvegarde l'armure modifiée dans la base de données
        val savedArmure = this.armureDAO.save(armureModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedArmure.nom} réussie")

        // Redirige vers la page d'administration des armures
        return "redirect:/admin/Armure"
    }

    /**
     * Gère la suppression d'une armure par son identifiant.
     *
     * @param id L'identifiant de l'armure' à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armures après la suppression réussie.
     * @throws NoSuchElementException si l'armure avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Armure/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de l'armure' à supprimer dans la base de données
        val armure: Armure = this.armureDAO.findById(id).orElseThrow()

        //Suppression de l'armure de la base de données
        this.armureDAO.delete(armure)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${armure.nom}réussie")

        // Redirige vers la page d'administration des armures
        return "redirect:/admin/Armure"
    }
}

