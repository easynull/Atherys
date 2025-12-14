# **What this**

> **Beyond the veil of the familiar world lies an ancient and silent void - _Atherys_. It is neither dark nor light, but the primordial matter that existed before all else. Few can hear its call, and fewer still can withstand the whispers of its secrets.**
>
> **This is a return to the roots of a forgotten magic that is older than the world itself. These are not just spells, but dark arts that call upon an ancient power that slumbers in the void between worlds. Awaken it, and reality will become pliable clay in your hands.**

# **Downloads**

>#### The mod can be installed both on the [CurseForge](https://www.curseforge.com/minecraft/mc-mods/atherys) page and on the [Modrinth](https://modrinth.com/mod/atherys) page
><p align="left">
><a href="https://www.curseforge.com/minecraft/mc-mods/atherys">
>    <img src="https://img.shields.io/badge/Atherys (Curseforge)-d48526?style=for-the-badge&logo=curseforge&logoColor=000000&labelColor=FFFFFF" /></a>
>  <a href="https://modrinth.com/mod/atherys">
>    <img src="https://img.shields.io/badge/Atherys(Modrinth)-349a46?style=for-the-badge&logo=modrinth&logoColor=000000&labelColor=FFFFFF" /></a>
></p>
>
>#### To use this mod, you need to download the author's API - [NullCore](https://modrinth.com/mod/nullcore/versions)
# **Features**
>#### Here empty yet :D

# **Licensing**

>#### The project is licensed under GNU 2.0 license.

# **For developers**

>To connect your project's dependency to **Atherys** you need to write this code in your **```build.gradle```**

>```Java
>repositories {
>   //from Modrinth:
>   maven {
>        name = "Modrinth"
>        url = "https://api.modrinth.com/maven"
>    }
>   //from CurseForge:
>   maven { url "https://cursemaven.com"}
>}
>
>dependencies {
>   //from Modrinth:
>   implementation "maven.modrinth:atherys:${atherys_version}"
>   //from CurseForge:
>   implementation "curse.maven:atherys-1288584:${atherys_file_id}"
>}
>```