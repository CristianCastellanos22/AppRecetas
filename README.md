<div id="top"></div>

[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- PROJECT LOGO -->
<br />
<div align="center">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Proyecto App Recetas</h3>

  <p align="center">
    App que muestra un listado de recetas de comida
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Tabla de contenidos</summary>
  <ol>
    <li>
      <a href="#Acerca del projecto">Acerca del projecto</a>
      <ul>
        <li><a href="#built-with">Construido con</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Primeros pasos</a>
      <ul>
        <li><a href="#prerequisites">Pre-requisitos</a></li>
        <li><a href="#installation">Instalación</a></li>
      </ul>
    </li>
    <li>
      <a href="#automatizadas">Pruebas Automatizadas</a>
      <ul>
        <li><a href="#framework">Framework utilizado</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contacto</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## Acerca del projecto

[![Product Name Screen Shot][product-screenshot]]

La app

La APP cuenta con tres pantallas.
* Listado y busqueda, por nombre e ingredientes.
* Vista de detalle para la receta seleccionada.
* Mapa con un marcador, indicando el país de origen de la receta.

<p align="right">(<a href="#top">back to top</a>)</p>

### Construido con

* [Kotlin](https://kotlinlang.org/)
* [Jeppack Compose](https://developer.android.com/jetpack?hl=es-419)
* [Retrofit](https://square.github.io/retrofit/)
* [Coil](https://coil-kt.github.io/coil/compose/)
* [Google Maps](https://developers.google.com/maps/documentation/android-sdk/maps-compose?hl=es-419)
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack?hl=es-419)


### Arquitectura usada
* [MVVM]
* [Clean architecture]

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Primeros pasos

Para poder correr el proyecto se debe contar con el IDE Android Studio

### Pre-requisitos

El proyecto cuenta con dependencias, las cuales se encuentran en el archivo build.gradle, la instalación de estas dependencias se realiza automaticamente por el IDE.

### Instalación

Pasos para la ejecución del proyecto.

1. Clonar el repositorio
   ```sh
   git clone https://github.com/CristianCastellanos22/AppRecetas.git
   ```
2. Abrir con el IDE Android Studio

<p align="right">(<a href="#top">back to top</a>)</p>

### Automatizadas

Para las pruebas automatizadas de UI se utiliza el framework Maestro

* [Maestro](https://maestro.mobile.dev/)

Se debe instalar el CLI

* [Instalar CLI](https://maestro.mobile.dev/getting-started/installing-maestro)

EL framework cuenta con una interfaz gráfica, la cual podremos utilizar para escribir los archivo .yaml de los test

* [Maestro Studio](https://maestro.mobile.dev/getting-started/maestro-studio)

Para ejecutar los test debemos navegar hasta el directorio donde se encuentren los archivos .yaml y ejecutar el comando

```sh
   maestro test nombreArchivo.yaml
   ```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contacto

Project Link: [https://github.com/CristianCastellanos22/ProyectoMercadoLibreTest.git](https://github.com/CristianCastellanos22/ProyectoMercadoLibreTest.git)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/cristianjcb/
[product-screenshot]: images/Screenshot_App1.png