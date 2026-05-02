# Lab: BroadcastReceiver Demo

Ce projet est une application Android démontrant l'utilisation des **Broadcast Receivers** (statiques et dynamiques) pour intercepter des événements système et personnalisés.

## Fonctionnalités

1.  **Suivi du Mode Avion (Dynamique)** :
    *   Enregistrement du receiver uniquement lorsque l'utilisateur le décide via l'interface.
    *   Utilise `Context.registerReceiver` avec les flags de sécurité modernes (`RECEIVER_EXPORTED`).
    *   Affiche un message (`Toast`) dès que l'état du réseau change.

2.  **Démarrage du Système (Statique)** :
    *   Intercepte l'événement `BOOT_COMPLETED` déclaré dans le `AndroidManifest`.
    *   Permet à l'application de réagir dès que le téléphone a fini de s'allumer.

3.  **Broadcast Personnalisé (Custom)** :
    *   Démonstration de l'envoi d'un message interne à l'application.
    *   Utilisation d'une **Intent explicite** pour respecter les restrictions de sécurité des versions récentes d'Android (API 26+).

## Installation & Test

1.  **Clonage du projet** :
    ```bash
    git clone https://github.com/Sultan-zd/Lab17-BroadcastReceiver.git
    ```
2.  **Configuration** :
    *   Ouvrez le projet dans **Android Studio**.
    *   Assurez-vous d'utiliser un émulateur ou un appareil avec **Android 8.0 (API 26)** ou supérieur.

3.  **Tests** :
    *   **Mode Avion** : Cliquez sur "Activer Receiver Avion", puis activez/désactivez le mode avion depuis les paramètres rapides du téléphone.
    *   **Custom Broadcast** : Cliquez sur "Envoyer Custom Broadcast" pour voir le message s'afficher immédiatement.
    *   **Boot** : Redémarrez l'appareil pour voir le Toast apparaître après le chargement du système.

## Structure du Code

*   `MainActivity.java` : Gère l'interface et l'enregistrement dynamique du receiver.
*   `AirplaneModeReceiver.java` : Logique de détection du changement de mode avion.
*   `BootReceiver.java` : Écoute le démarrage du système.
*   `CustomEventReceiver.java` : Reçoit les messages personnalisés envoyés par l'application.
*   `AndroidManifest.xml` : Contient les permissions et les déclarations des receivers statiques.

## License
Ce projet est réalisé dans le cadre d'un laboratoire d'apprentissage Android.
