package techguns.items.additionalslots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import techguns.Techguns;
import techguns.api.tginventory.TGSlotType;
import techguns.capabilities.TGExtendedPlayer;
import techguns.util.MathUtil;

import static techguns.TGConfig.jetpackMotionFactor;

public class ItemGlider extends ItemTGSpecialSlot {

	public ItemGlider(String unlocalizedName, int camoCount, int dur) {
		super(unlocalizedName, TGSlotType.BACKSLOT, camoCount, dur);
	}

	@Override
	public void onPlayerTick(ItemStack item, PlayerTickEvent event) {
		glide(event.player);
	}

	public static void glide(EntityPlayer player) {
		EntityPlayer ply = player;

		if (!ply.onGround && ply.isSneaking()) {
			if (ply.motionY < -0.1f) {

				TGExtendedPlayer extendedPlayer = TGExtendedPlayer.get(ply);
				extendedPlayer.isGliding = true;

				/**
				 * totally stolen from modular powersuits glider code
				 */
				Vec3d lookVec = player.getLookVec();
				MathUtil.Vec2 playerMoveVec = new MathUtil.Vec2(lookVec.x, lookVec.z);

				if (player.motionY < -0.1f) {
					double motion = Math.min(0.08, -0.1 - player.motionY);
					player.motionY += motion;
					if (player.dimension == DimensionType.THE_END.getId()) {
						player.motionX += playerMoveVec.x * motion * jetpackMotionFactor * 0.2f;
						player.motionZ += playerMoveVec.y * motion * jetpackMotionFactor * 0.2f;
					} else if (player.dimension == DimensionType.NETHER.getId()) {
						player.motionX += playerMoveVec.x * motion * jetpackMotionFactor * 0.5f;
						player.motionZ += playerMoveVec.y * motion * jetpackMotionFactor * 0.5f;
					} else {
						player.motionX += playerMoveVec.x * motion * jetpackMotionFactor;
						player.motionZ += playerMoveVec.y * motion * jetpackMotionFactor;
					}
					player.jumpMovementFactor += 0.03f;
				}
			}
			player.fallDistance = 0;

		}
	}
	
}
