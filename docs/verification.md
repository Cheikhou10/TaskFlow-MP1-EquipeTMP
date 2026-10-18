# Vérifications manuelles — TaskFlow

Ce document présente les scénarios de vérification exécutés sur l'application.

## Format

Pour chaque scénario :
- **Données utilisées**
- **Résultat attendu**
- **Résultat observé**
- **Conclusion**

## Scénarios

### US-07 — Marquer une tâche comme terminée

- **Données utilisées :** tâche #1 intitulée « Vérifier la sauvegarde du serveur », initialement non terminée.
- **Résultat attendu :** le statut de la tâche passe à terminée et un message de confirmation s'affiche.
- **Résultat observé :** le message de confirmation s'affiche et le statut devient `true`.
- **Conclusion :** réussi.

### US-08 — Signaler un identifiant inconnu lors du marquage

- **Données utilisées :** identifiant #99, qui ne correspond à aucune tâche.
- **Résultat attendu :** un message d'erreur s'affiche et aucune tâche n'est modifiée.
- **Résultat observé :** un message indiquant que l'identifiant #99 n'existe pas s'affiche.
- **Conclusion :** réussi.

### US-09 — Modifier la priorité d'une tâche

- **Données utilisées :** tâche #1 intitulée « Vérifier la sauvegarde du serveur ».
- **Résultat attendu :** une priorité valide est appliquée à la tâche.
- **Résultat observé :** la priorité devient « Élevée », puis « Basse » lors des tests.
- **Conclusion :** réussi.

### US-09 — Refuser une priorité invalide

- **Données utilisées :** tâche #1 avec la priorité « Élevée », puis saisie de la valeur « Urgente ».
- **Résultat attendu :** un message d'erreur s'affiche et la priorité précédente est conservée.
- **Résultat observé :** la priorité reste « Élevée » après le refus de « Urgente ».
- **Conclusion :** réussi.

### US-09 — Signaler un identifiant inconnu lors du changement de priorité

- **Données utilisées :** identifiant #99, qui ne correspond à aucune tâche.
- **Résultat attendu :** un message d'erreur s'affiche et aucune priorité n'est modifiée.
- **Résultat observé :** un message indiquant que l'identifiant #99 n'existe pas s'affiche.
- **Conclusion :** réussi.