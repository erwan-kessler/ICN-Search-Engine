## SETUP

- DOWNLOADS:

    - You First need to download a 13.X JDK [here](https://adoptopenjdk.net/releases.html?variant=openjdk13&jvmVariant=hotspot)
    - <span style="color:orange" id="1">Optional 1</span>: You can download SceneBuilder 11 [here](https://gluonhq.com/products/scene-builder/)
    - <span style="color:orange" id="2">Optional 2</span>: You can download openJFX SDK 13 to run without gradle [here](https://gluonhq.com/products/javafx/)
    - <span style="color:orange" id="3">Optional 3</span>: You can download Intellij [here](https://www.jetbrains.com/fr-fr/idea/download/) 
---
- SETUP
    - [Optional 1](#1):
        - You need to install Scene Builder, on Windows just double click it and install as usual, on linux if debian
        then use the .deb else use the rpm, for arch ofc use PKGBUILD with rpmExtract or you can simply extract it into 
        /opt and move the .desktop as it is meant to be installed.
        - This SceneBuilder from gluon is sadly for Java 11 so to remove the warning we advise you to edit your FXML 
        manually to upgrade the tag.
    - [Optional 2](#2) :
        - You need first to extract the SDK to a folder, a good one is `C:\Program Files\Java` for 
        Windows and `/opt/` for Linux.
        - You need to setup a PATH variable, you can edit ~/.bashrc, ~/.profile, /etc/bash.bashrc, 
        /etc/profile... on Linux and your global Environment Variable on Windows (Computer -> Properties ->
        Advanced System Settings -> Environment Variables -> Add) and add this one: 
            * Name: PATH_TO_JFX
            * Value: `C:\Program Files\Java\javafx-sdk-13.0.1\lib` or `/opt/javafx-sdk-13.0.1/lib`
    - [Optional 3](#3): 
        - Install Intellij as you should do for every system
        - You will need to specify those options to run locally: 
            - Inside the Project Structure -> Libraries -> + menu, add either `C:\Program Files\Java\javafx-sdk-13.0.1\lib` 
            or `/opt/javafx-sdk-13.0.1/lib`
            - Inside the Run -> Edit Configurations... menu you need to add one of those options: 
                - `--module-path "C:\Program Files\Java\javafx-sdk-13.0.1\lib" --add-modules javafx.controls,javafx.fxml`
                - `--module-path "/opt/javafx-sdk-13.0.1/lib" --add-modules javafx.controls,javafx.fxml`
        - To run gradle you need to set up the gradle jvm to 13 in Settings -> Build, Exec, Deploy -> Build tools -> Gradle
    - To setup Gradle simply type `gradlew`
---
- RUN
    - On Intellij, click the nifty little green arrow (if you installed optionals) else use the gradle side bar and click on `Tasks->Aplication->run`
    - Via terminal, use gradle and type `./gradlew run`
---
- JARIFY
    - On Intellij use the gradle panel and use the Assemble or fatJar option
    - Via terminal, use gradle and type `./gradlew fatJar` (result will be in build/libs) or `./gradlew assemble` for a native app, results will be in build/distributions. You can also use `./gradlew runtime` to make a runtime in build/image/bin.
