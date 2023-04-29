package TrueCommand;

import TrueCommand.wrapper.WrapperPlayClientChatCommand;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.PacketType;
import org.bukkit.plugin.Plugin;
import TrueCommand.utils.CommandUtils;
import com.comphenix.protocol.events.PacketAdapter;

public class PacketCMD extends PacketAdapter {
	private CommandUtils utils;

	public PacketCMD(Plugin plugin, PacketType[] type, CommandUtils commandUtils) {
		super(plugin, type);
		this.utils = commandUtils;
	}

	@Override
	public void onPacketReceiving(PacketEvent e) {
		WrapperPlayClientChatCommand packet = new WrapperPlayClientChatCommand(e.getPacket());
		String msg = packet.getMessage();
		if (msg.length() > 1) {
			String cmd = msg.split(" ")[0];
			if (this.utils.get(cmd) != null) {
				packet.setMessage(msg);
				return;
			}
			if (this.utils.has(String.valueOf(cmd.toCharArray()[0]))) {
				String rcmd = this.utils.repl(cmd);
				if (this.utils.get(rcmd) != null) {
					packet.setMessage(rcmd);
				}
			}
		}
	}
}