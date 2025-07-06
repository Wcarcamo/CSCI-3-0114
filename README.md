# CSSI-3-0114

Computer Programming in Java

# JavaFX

Part of this course requires using [JavaFK](https://en.wikipedia.org/wiki/JavaFX).
Below are the steps I took to set this up on my machine.
I avoided build tools like **Maven** & **Gradle** because I wanted to keep things simple while I learn. 

* OS: Arch Linux
* Editor: VScode
* Packages:
    - jdk-openjdk
    - java-openjfk^AUR^

1. Create a `.vscode` directory in the root of your JavaFX application
2. Add `launch.json` file under `.vscode/` with the following content:
    - 🗒️ NOTE: Update path `/usr/lib/jvm/java-24.0.1-openjfx/lib` with your JavaFK package installation

```
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch JavaFX",
            "request": "launch",
            "mainClass": "Main",
            "vmArgs": "--module-path /usr/lib/jvm/java-24.0.1-openjfx/lib --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics"
        }
    ]
}
```

3. Add `settings.json`
    - 🗒️ NOTE: Update path `/usr/lib/jvm/java-24.0.1-openjfx/lib/*.jar` with your JavaFK package installation

```
{
    "java.project.referencedLibraries": [
        "/usr/lib/jvm/java-24.0.1-openjfx/lib/*.jar"
    ]
}
```