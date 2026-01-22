package io.thatonecoder.totallyfine.mixin.glint;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.render.entity.layer.AbstractArmorLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArmorLayer.class)
public class AbstractArmorLayerMixin {

    @Inject(
        method = "renderGlint",
        at = @At("HEAD"),
        cancellable = true
    )
    private void disableGlint(CallbackInfo ci) {
        if (!TotallyFineConfig.instance.glint.get()) {
            ci.cancel();
        }
    }
}