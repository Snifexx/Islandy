package me.snifex.islandy

import de.leonhard.storage.Json
import de.leonhard.storage.internal.FlatFile
import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

val LocationNull: Location = Location(Bukkit.getWorld("world"), .0, .0, .0)

object Database {
    val Config: Json
        get() = _config
    private lateinit var _config: Json

    fun init() {
        _config = Json("config", Main.instance.dataFolder.path)
        _config.setDefault(LocationNull.string(), ItemStack(Material.DIAMOND).serialize())
    }

    fun write(loc: Location, item: ItemStack) {
        _config.set(loc.string(), item.serialize())
    }

    fun read(loc: Location) = _config.getByteArray(loc.string())
}

fun FlatFile.getByteArray(key: String): ByteArray = getOrDefault(key, ByteArray(0))

fun Location.string(): String = "${this.world?.uid}</|\\>${this.x}</|\\>${this.y}</|\\>${this.z}</|\\>${this.pitch}</|\\>${this.yaw}"
fun Location.serialize(): ByteArray = this.string().encodeToByteArray()
fun ItemStack.serialize(): ByteArray {
    val nbt = NBTItem(this).toString()
    return "${this.type}</|\\>${this.amount}</|\\>${if (hasItemMeta()) itemMeta.toString() else "null"}</|\\>$nbt".encodeToByteArray()
}
