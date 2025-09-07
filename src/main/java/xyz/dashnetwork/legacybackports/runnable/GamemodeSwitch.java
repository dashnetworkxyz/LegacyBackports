package xyz.dashnetwork.legacybackports.runnable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public final class GamemodeSwitch implements Runnable {

    @Override
    public void run() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        if (player != null) {
            if (player.isSpectator())
                player.sendChatMessage("/gamemode creative");
            else
                player.sendChatMessage("/gamemode spectator");
        }
    }

}
