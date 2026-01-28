package io.thatonecoder.totallyfine.mixin.details;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.gui.GameGui;
import net.minecraft.client.render.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameGui.class)
public class GameGuiMixin {
	@Inject(
		method = "renderVignette",
        at = @At("HEAD"),
        cancellable = true
	)
	private void cancelVignette(float brightnessAtEyes, Window window, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.vignette.get()) {
		    ci.cancel();
        }
	}

	@Inject(
		method = "renderXpBar",
        at = @At("HEAD"),
        cancellable = true
	)
	private void hideXpBar(Window window, int x, CallbackInfo ci) {
        if (TotallyFineConfig.instance.hideXpBar.get()) {
		    ci.cancel();
        }
	}
}