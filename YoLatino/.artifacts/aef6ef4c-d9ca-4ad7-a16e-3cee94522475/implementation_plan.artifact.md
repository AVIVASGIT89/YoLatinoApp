# Actualizar a Android 16 (Nivel de API 36)

Este plan detalla los pasos necesarios para actualizar la aplicación YoLatino para que esté orientada a Android 16 (API 36), cumpliendo con los requisitos de Google Play.

## User Review Required

> [!IMPORTANT]
> La actualización a Android 16 requiere el uso de versiones más recientes del Android Gradle Plugin (AGP) y de Gradle. Este plan propone actualizar a AGP 9.3.1 y Gradle 9.7.
> Se recomienda realizar una copia de seguridad o un commit en Git antes de proceder, ya que los cambios en el sistema de construcción pueden requerir ajustes adicionales en el código.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/GIT Apps/YoLatinoApp/YoLatino/gradle/libs.versions.toml)
Actualizar las versiones de AGP y de las bibliotecas principales a sus versiones estables más recientes para asegurar la compatibilidad con el nivel de API 36.

*   `agp`: `8.5.1` -> `9.3.1`
*   `material`: `1.13.0` -> `1.14.0`
*   `lifecycleLivedataKtx`: `2.9.3` -> `2.11.0`
*   `lifecycleViewmodelKtx`: `2.9.3` -> `2.11.0`
*   `navigationFragment`: `2.9.4` -> `2.9.8`
*   `navigationUi`: `2.9.4` -> `2.9.8`
*   `activity`: `1.8.0` -> `1.13.0`

#### [MODIFY] [gradle-wrapper.properties](file:///D:/GIT Apps/YoLatinoApp/YoLatino/gradle/wrapper/gradle-wrapper.properties)
Actualizar Gradle a la versión `9.7` para soportar el nuevo AGP.

#### [MODIFY] [build.gradle.kts (app)](file:///D:/GIT Apps/YoLatinoApp/YoLatino/app/build.gradle.kts)
Actualizar `compileSdk` y `targetSdk` a `36`.

## Verification Plan

### Automated Tests
1.  Sincronizar el proyecto con los archivos de Gradle actualizados.
2.  Ejecutar `./gradlew assembleDebug` para verificar que el proyecto compila correctamente con el nuevo SDK.
3.  Ejecutar las pruebas unitarias: `./gradlew test`.

### Manual Verification
1.  Desplegar la aplicación en un emulador o dispositivo físico con Android 16 (si está disponible) o la versión más reciente.
2.  Verificar las funcionalidades principales de la aplicación (Navegación, Volley, ViewBinding).
