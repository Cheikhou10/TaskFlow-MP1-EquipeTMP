# Contribuer au projet TaskFlow

## Règles de collaboration

- **Aucun commit direct sur `main`** : tout passe par une pull request.
- **Une branche par tâche** : `feature/<numéro>-<sujet>` ou `fix/<numéro>-<sujet>`.
- **Chaque PR doit être relue** par un autre membre avant fusion.
- **Chaque PR référence son Issue** : ajouter `Closes #numéro` dans la description.
- **Messages de commit clairs** : commencer par un verbe à l'infinitif.

## Communication

- Canal principal : (à compléter : Discord / Teams / WhatsApp)
- Réunions : (à compléter : jour et heure)
- Blocages : signalés dans l'Issue avec le label `blocage`

## Processus d'une tâche

1. Choisir une Issue dans le Project (colonne `À faire`).
2. Déplacer la carte dans `En cours`.
3. Créer une branche : `git switch -c feature/<numéro>-<sujet>`.
4. Coder, tester, commiter.
5. Pousser la branche et ouvrir une pull request.
6. Un autre membre relit et approuve.
7. Fusionner la PR, déplacer la carte dans `Terminé`.
8. Fermer l'Issue.
