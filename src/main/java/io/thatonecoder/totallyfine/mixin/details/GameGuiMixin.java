package io.thatonecoder.totallyfine.mixin.details;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.gui.GameGui;
import net.minecraft.client.render.Window;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(GameGui.class)
public class GameGuiMixin {

	@Inject(
		method = "renderVignette"
	)
	private void cancelVignette(float brightnessAtEyes, Window window, CallbackInfo ci) {
        if (TotallyFineConfig.instance.vignette.get()) {
		    ci.cancel();
        }
	}
}