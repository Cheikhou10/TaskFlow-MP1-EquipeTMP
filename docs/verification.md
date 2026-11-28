# Verifications manuelles - TaskFlow

Ce document presente les scenarios de verification executes sur l'application.

## Format

Pour chaque scenario :

- **Donnees utilisees**
- **Resultat attendu**
- **Resultat observe**
- **Conclusion**

## Scenarios

### US-07 - Marquer une tache comme terminee

- **Donnees utilisees :** tache #1 intitulee « Verifier la sauvegarde du serveur », initialement non terminee.
- **Resultat attendu :** le statut de la tache passe a terminee et un message de confirmation s'affiche.
- **Resultat observe :** le message de confirmation s'affiche et le statut devient `true`.
- **Conclusion :** reussi.

### US-08 - Signaler un identifiant inconnu lors du marquage

- **Donnees utilisees :** identifiant #99, qui ne correspond a aucune tache.
- **Resultat attendu :** un message d'erreur s'affiche et aucune tache n'est modifiee.
- **Resultat observe :** un message indiquant que l'identifiant #99 n'existe pas s'affiche.
- **Conclusion :** reussi.

### US-09 - Modifier la priorite d'une tache

- **Donnees utilisees :** tache #1 intitulee « Verifier la sauvegarde du serveur ».
- **Resultat attendu :** une priorite valide est appliquee a la tache.
- **Resultat observe :** la priorite devient « Elevee », puis « Basse » lors des tests.
- **Conclusion :** reussi.

### US-09 - Refuser une priorite invalide

- **Donnees utilisees :** tache #1 avec la priorite « Elevee », puis saisie de la valeur « Urgente ».
- **Resultat attendu :** un message d'erreur s'affiche et la priorite precedente est conservee.
- **Resultat observe :** la priorite reste « Elevee » apres le refus de « Urgente ».
- **Conclusion :** reussi.

### US-09 - Signaler un identifiant inconnu lors du changement de priorite

- **Donnees utilisees :** identifiant #99, qui ne correspond a aucune tache.
- **Resultat attendu :** un message d'erreur s'affiche et aucune priorite n'est modifiee.
- **Resultat observe :** un message indiquant que l'identifiant #99 n'existe pas s'affiche.
- **Conclusion :** reussi.