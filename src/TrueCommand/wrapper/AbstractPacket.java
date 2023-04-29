package TrueCommand.wrapper;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.entity.Player;
import com.google.common.base.Objects;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public abstract class AbstractPacket {
	protected PacketContainer handle;

	protected AbstractPacket(PacketContainer handle, PacketType type) {
		if (handle == null) {
			throw new IllegalArgumentException("Packet handle cannot be NULL.");
		}
		if (!Objects.equal(handle.getType(), type)) {
			throw new IllegalArgumentException(handle.getHandle() + " is not a packet of type " + type);
		}
		this.handle = handle;
	}

	public PacketContainer getHandle() {
		return this.handle;
	}

	public void sendPacket(Player receiver) {
		ProtocolLibrary.getProtocolManager().sendServerPacket(receiver, this.getHandle());
	}

	public void broadcastPacket() {
		ProtocolLibrary.getProtocolManager().broadcastServerPacket(this.getHandle());
	}

	public void receivePacket(Player sender) {
		try {
			ProtocolLibrary.getProtocolManager().receiveClientPacket(sender, this.getHandle());
		} catch (Exception e) {
			throw new RuntimeException("Cannot receive packet.", e);
		}
	}
}