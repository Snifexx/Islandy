package me.snifex.islandy

import me.snifex.snifexcore.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: Main
    }

    override fun onEnable() {
        registerCommand("test", TestComand, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}

object TestComand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        sender.sendMessage("${Material.values().size}")
        return true
    }
}