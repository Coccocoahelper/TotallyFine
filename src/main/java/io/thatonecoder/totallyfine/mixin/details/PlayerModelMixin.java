package io.thatonecoder.totallyfine.mixin.details;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.render.model.entity.PlayerModel;
import net.minecraft.client.render.model.entity.HumanoidModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public class PlayerModelMixin extends HumanoidModel {

    @Inject(
        method = "renderCape",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderCape(float tickDelta, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.capes.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "renderEars",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderEars(float tickDelta, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.ears.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "renderLeftArm",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderLeftArm(CallbackInfo ci) {
        if (!TotallyFineConfig.instance.leftArm.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "renderRightArm",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderRightArm(CallbackInfo ci) {
        if (!TotallyFineConfig.instance.rightArm.get()) {
            ci.cancel();
        }
    }
}