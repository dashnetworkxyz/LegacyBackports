package xyz.dashnetwork.legacybackports.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.legacybackports.BackportConfig;

import java.lang.reflect.Field;

@Mixin(GuiScreen.class)
public class GuiScreenMixin {

    @Unique
    private static final Minecraft legacyBackports$minecraft = Minecraft.getMinecraft();

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    public void onSendChatMessage(String msg, boolean addToChat, CallbackInfo callback) {
        if (BackportConfig.tfsChatLimit)
            callback.cancel();
        else
            return;

        if (addToChat)
            legacyBackports$minecraft.ingameGUI.getChatGUI().addToSentMessages(msg);

        if (ClientCommandHandler.instance.executeCommand(legacyBackports$minecraft.thePlayer, msg) != 0)
            return; // Forge command.

        if (msg.length() > 256)
            msg = msg.substring(0, 256);

        C01PacketChatMessage packet = new C01PacketChatMessage();
        Field field = ReflectionHelper.findField(C01PacketChatMessage.class, "message", "field_149440_a");
        field.setAccessible(true);

        try {
            field.set(packet, msg);
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }

        legacyBackports$minecraft.thePlayer.sendQueue.addToSendQueue(packet);
    }

}
