package io.thatonecoder.totallyfine.mixin.glint;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.render.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Inject(
        method = "renderEnchantmentGlint",
        at = @At("HEAD"),
        cancellable = true
    )
    private void disableGlint(CallbackInfo ci) {
        if (!TotallyFineConfig.instance.glint.get()) {
            ci.cancel();
        }
    }
}