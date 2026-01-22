package io.thatonecoder.totallyfine.mixin.details;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.world.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    // Credits to NotFine, for this!
    @ModifyArg(
        method = "setupFog",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V",
            ordinal = 14,
            remap = false
        )
    )
    private float totallyFine$nearFogDistance(float value) {
        // Extremely high values cause issues, but 15 mebimeters out should be practically infinite
        if (!TotallyFineConfig.instance.fog.get()) return 1024 * 1024 * 15;
        return value;
    }

    @ModifyArg(
        method = "setupFog(IF)V",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V",
            ordinal = 15,
            remap = false
        )
    )
    private float totallyFine$replaceFarFogDistance(float value) {
        if (!TotallyFineConfig.instance.fog.get()) return 1024 * 1024 * 16;
        return value;
    }

    /*@Inject(
        method = "renderFog",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderFog(int mode, float tickDelta, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.fog.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "setupFog",
        at = @At("HEAD"),
        cancellable = true
    )
    private void setupFog(float tickDelta, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.fog.get()) {
            ci.cancel();
        }
    }*/

    @Inject(
        method = "renderClouds",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderClouds(WorldRenderer worldRenderer, float tickDelta, int anaglyphRenderPass, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.clouds.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "renderSnowAndRain",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderSnowAndRain(float tickDelta, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.snowAndRain.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "renderItemInHand",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renderItemInHand(float tickDelta, int anaglyphRenderPass, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.renderItemInHand.get()) {
            ci.cancel();
        }
    }
}


