package com.github.vscyther.config

import org.bukkit.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ConfigFrame(private val plugin: JavaPlugin) {

    fun load(vararg objects: Any) {
        for (any in objects) {
            val clazz: Class<*> = any.javaClass

            val configPath = clazz.getAnnotation(ConfigPath::class.java) ?: return
            val path = configPath.value

            val file = File(plugin.dataFolder, path)
            if (!file.exists()) plugin.saveResource(path, false)

            val config = YamlConfiguration.loadConfiguration(file)

            for (field in clazz.declaredFields) {
                val type = field.type

                val configKey = field.getAnnotation(ConfigKey::class.java)
                val section = configKey.value

                field.isAccessible = true

                var value = config[section]

                when {
                    type.equals(String::class.java) -> value = translate(value as String)
                    type.equals(List::class.java) -> value = config.getStringList(section).map { translate(it) }
                }

                field.set(any, value)
            }
        }
    }

    private fun translate(text: String): String {
        return ChatColor.translateAlternateColorCodes('&', text)
    }

}

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigKey(val value: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigPath(val value: String)