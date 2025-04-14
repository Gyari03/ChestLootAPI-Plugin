import os

plugins_path = input("Enter full path to your Paper server's plugins folder:").strip()

plugins_path = plugins_path.replace('\\', '/')

build_gradle_content = """
plugins {
    id 'java'
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = 'com.github.gyari03.LootRandomizer'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = "https://repo.papermc.io/repository/maven-public/"
    }
    maven {
        name = "sonatype"
        url = "https://oss.sonatype.org/content/groups/public/"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation 'com.google.code.gson:gson:2.8.8' // Gson
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21")
    }

    jar{
        def destination = file('__PLUGIN_PATH__')
        destination.mkdirs();
        destinationDirectory = destination //destination
        from sourceSets.main.output  // Include all compiled classes
    }
}

def targetJavaVersion = 21
java {
    def javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

tasks.withType(JavaCompile).configureEach {
    options.encoding = 'UTF-8'

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release.set(targetJavaVersion)
    }
}

processResources {
    def props = [version: version]
    inputs.properties props
    filteringCharset 'UTF-8'
    filesMatching('plugin.yml') {
        expand props
    }
}

"""

build_gradle_content = build_gradle_content.replace('__PLUGIN_PATH__', plugins_path)

with open("build.gradle", "w") as f:
    f.write(build_gradle_content)

print("build.gradle created")