package xyz.dashnetwork.legacybackports.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.legacybackports.BackportConfig;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "damageItem", at = @At("HEAD"), cancellable = true)
    public void onDamageItem(int amount, EntityLivingBase entityIn, CallbackInfo ci) {
        if (Minecraft.getMinecraft().getCurrentServerData() != null && BackportConfig.disableItemBreaks)
            ci.cancel();
    }

}
