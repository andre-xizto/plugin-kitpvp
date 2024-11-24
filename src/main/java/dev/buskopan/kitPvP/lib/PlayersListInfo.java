package dev.buskopan.kitPvP.lib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import dev.buskopan.kitPvP.Main;
import dev.buskopan.kitPvP.config.ConfigManager;
import dev.buskopan.kitPvP.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayersListInfo {

    private final Main main;
    private final ProtocolManager protocolManager;

    public PlayersListInfo(Main main) {
        this.main = main;
        this.protocolManager = main.getProtocolManager();
    }

    public void listPlayers() {
        protocolManager.addPacketListener(new PacketAdapter(main, ListenerPriority.NORMAL, PacketType.Status.Server.SERVER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                WrappedServerPing ping = event.getPacket().getServerPings().read(0);

                List<String> messages = ConfigManager.getPlayerInfoListMessage();
                List<WrappedGameProfile> profiles = new ArrayList<>();
                for(int i = 0; i<messages.size(); i++) {
                    profiles.add(new WrappedGameProfile(String.valueOf(i).repeat(9), StringUtils.colorize(messages.get(i))));
                }
                ping.setPlayers(profiles);

                event.getPacket().getServerPings().write(0, ping);
            }
        });
    }


}
