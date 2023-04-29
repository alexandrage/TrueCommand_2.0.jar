package TrueCommand;

import TrueCommand.utils.CommandUtils;
import TrueCommand.wrapper.Packet;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		new Packet().hack(this, new CommandUtils());
	}
}