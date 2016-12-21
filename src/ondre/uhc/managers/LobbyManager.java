package ondre.uhc.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LobbyManager {
	
	public static File file = new File("plugins/" + "uhc", "lobby.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

	public void setLocation(String name, Location location) {
		config.set(name + ".World", location.getWorld().getName());
		config.set(name + ".X", location.getX());
		config.set(name + ".Y", location.getY());
		config.set(name + ".Z", location.getZ());
		config.set(name + ".Yaw", location.getYaw());
		config.set(name + ".Pitch", location.getPitch());
		saveConfig();
	}

	public Location getLocation(String name) {
		Location loc;

		try {

			World w = Bukkit.getWorld(config.getString(name + ".World"));
			double x = config.getDouble(name + ".X");
			double y = config.getDouble(name + ".Y");
			double z = config.getDouble(name + ".Z");

			loc = new Location(w, x, y, z);
			loc.setYaw(config.getInt(name + ".Yaw"));
			loc.setPitch(config.getInt(name + ".Pitch"));

		} catch (Exception ex) {
			loc = new Location(Bukkit.getWorlds().get(0), 0, 100, 0);

		}

		return loc;

	}

	public static void saveConfig() {

		try {
			config.save(file);
		} catch (IOException e) {

		}
	}
}