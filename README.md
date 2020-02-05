# Jardín Botánico

### Prerequisites
```
Unity 2018.4.10f1 LTS
Google VR
Android SDK (API 29)
```

## Introducción
La aplicación Jardín Botánico permite activar una regadera de forma automática y leer el QR de una serie de plantas para su búsqueda en internet. Viene dada por varios fragments usados en un BottomNavigation. Éste está formado por:
*	**Inicio**: Tiene un par de fotos y se corresponde con el acceso por defecto
*	**Regadera**: Tiene un gif animado que se activa cuando se mueve el móvil en el eje X (simula una regadera). Además, dispone de audio al activarse. Se controla los eventos cuando se pausa una aplicación.
*	**Código QR**: Permite hacer la lectura de un código QR que tenga almacenado algún tipo de elemento. La idea es leer un QR con el nombre de una planta. Esta se buscará en Google Images. Se controla los eventos cuando se pausa una aplicación.
*	**Acerca de**: Se muestra información sobre mi persona. Se tiene un vínculo al Github propio.
## Aspectos técnicos
*	La versión MinimumSDK es 28 (Android 9.0 Pie). 
*	La versión TargetSDK es 29 (Android 10.0).
*   Escrito en Kotlin
*	La aplicación se adapta al tema que tenga el dispositivo configurado (Light or Dark).
*	Se ha decidido realizar un lector QR desde cero para integrarlo en la misma ventana.
*	La aplicación ha sido probada en un dispositivo con Android 10.0 y con Android 9.0. Se encuentra adaptada para una densidad de pantalla superior a 400dpi (cualquier móvil moderno).
*	Se hace uso de acelerómetro en la pestaña de Regadera. La activación de produce cuando el eje X tiene una inclinación de 11 a 69 grados (giro hacía la izquierda). Se hace normalización de los valores para evitar lecturas incorrectas en situaciones extrañas.
*	Se hace uso de la cámara en la pestaña QR. Se ha de aceptar el permiso de CAMERA. En caso negativo, el lector no se cargará. Si se acepta se va a activar la cámara y estará leyendo continuamente valores. Hay un delay para evitar carga desmesurada de generación de búsquedas. 
*	Además, se dispone de una ventana con acceso a mi información personal.

## Autor

* **Javier Martínez** - [jaluma](https://github.com/jaluma)

## License

This project is licensed under the GNU License - see the [LICENSE](LICENSE) file for details
