package TrueCommand.wrapper;

import com.comphenix.protocol.ProtocolManager;
import TrueCommand.PacketCMD;
import TrueCommand.PacketChat;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import TrueCommand.utils.CommandUtils;
import TrueCommand.Main;

public class Packet {
	public void hack(Main plugin, CommandUtils commandUtils) {
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		manager.addPacketListener(
				new PacketCMD(plugin, new PacketType[] { PacketType.Play.Client.CHAT_COMMAND }, commandUtils));
		manager.addPacketListener(
				new PacketChat(plugin, new PacketType[] { PacketType.Play.Client.CHAT }, commandUtils));
	}
}