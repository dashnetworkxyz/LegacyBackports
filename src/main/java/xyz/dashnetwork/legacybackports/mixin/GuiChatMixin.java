package xyz.dashnetwork.legacybackports.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.legacybackports.BackportConfig;

import java.util.Arrays;

@Mixin(GuiChat.class)
public class GuiChatMixin {

    @Shadow
    protected GuiTextField inputField;

    @Inject(method = "initGui", at = @At(value = "TAIL"))
    private void onInitGui(CallbackInfo callback) {
        if (!BackportConfig.tfsChatLimit)
            return;

        ServerData server = Minecraft.getMinecraft().getCurrentServerData();

        if (server != null) {
            final String ip = server.serverIP;
            String[] servers = BackportConfig.tfsChatServers.split(";");

            if (Arrays.stream(servers).noneMatch(
                    each -> ip.equalsIgnoreCase(each) || ip.toLowerCase().endsWith("." + each.toLowerCase())
            ))
                return;
        }

        inputField.setMaxStringLength(256);
    }

}
