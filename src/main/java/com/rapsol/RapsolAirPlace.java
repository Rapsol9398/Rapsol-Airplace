package com.rapsol;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

public class RapsolAirPlace implements ClientModInitializer {
	private static KeyBinding key;
	private static boolean isKeyPressed = false;
	@Override
	public void onInitializeClient() {

		/*dont steal the code or youre gay!*/
		/*"only because the mod isnt allowed doesnt mean you shouldnt use it" - Muni´s__ Grandma*/

		key = new KeyBinding(
				"Air-Place",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_4,
				"Rapsol Air-Place"
		);
		KeyBindingHelper.registerKeyBinding(key);
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (client.player != null && client.world != null) {
				if (key.isPressed()) {
					if (!isKeyPressed) {
						isKeyPressed = true;

						if (client.crosshairTarget != null && client.crosshairTarget.getType() == HitResult.Type.BLOCK) {
							BlockHitResult blockHitResult = (BlockHitResult) client.crosshairTarget;

							assert client.interactionManager != null;
							client.interactionManager.interactBlock(client.player, Hand.MAIN_HAND, blockHitResult);
							client.player.swingHand(Hand.MAIN_HAND);
						}
					}
				} else {
					isKeyPressed = false;
				}
			}
		});
	}
}