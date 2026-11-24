# Diagnostic des mauvaises odeurs (Code Smells)

## R1 — Valeur magique

- **Emplacement :** `GestionnaireTaches.java`
- **Problème :** Les priorités sont écrites directement en texte (`Basse`, `Normale`, `Elevee`). Une faute de frappe peut provoquer des erreurs.
- **Refactoring :** Créer un Enum.

---

## R2 — Nom mystérieux

- **Emplacement :** `TaskFlow.java`
- **Problème :** Les noms courts comme `t` ne permettent pas de comprendre facilement le rôle des variables.
- **Refactoring :** Rename.

---

## R3 — Méthode trop longue

- **Emplacement :** `GestionnaireTaches.java` — `recharger()`
- **Problème :** La méthode effectue trop d'opérations : lecture du fichier, découpage, création des tâches et gestion des erreurs.
- **Refactoring :** Extract Method.

---

## R4 — Responsabilités mélangées (Couplage fort)

- **Emplacement :** `GestionnaireTaches.java`
- **Problème :** La classe gère les tâches ainsi que la lecture et l'écriture des fichiers.
- **Refactoring :** Extract Class (créer `GestionnaireFichier`).

---

## R5 — Code dupliqué

- **Emplacement :** `afficherTaches()` et `marquerTerminee()`
- **Problème :** La même boucle de recherche d'une tâche par son ID est répétée.
- **Refactoring :** Extract Method (créer `trouverTacheParId()`).

---

## R6 — Logique métier dans l'UI

- **Emplacement :** `TaskFlow.java` — `ajouterTache()`
- **Problème :** La méthode mélange la saisie utilisateur, la validation de la date et la logique métier.
- **Refactoring :** Move Method.
