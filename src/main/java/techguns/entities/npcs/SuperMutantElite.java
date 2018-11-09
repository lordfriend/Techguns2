package techguns.entities.npcs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import techguns.TGuns;
import techguns.damagesystem.TGDamageSource;

import java.util.Random;

public class SuperMutantElite extends SuperMutantBasic {

	public SuperMutantElite(World world) {
		super(world);
		setTGArmorStats(15.0f, 1f);
	}
	
	public int gettype() {
		return 2;
	};

	public double getModelHeightOffset(){
		return 0.95d;
	}
	
	public float getModelScale() {
		return 1.65f;
	}
	
	@Override
	protected float getMutantWidth() {
		return 1.4f;
	}

	@Override
	public float getWeaponPosX() {
		return 0.23f;
	}

	@Override
	public float getWeaponPosZ() {
		return -0.39f;
	}

	
	@Override
	public float getTotalArmorAgainstType(TGDamageSource dmgsrc) {
		switch(dmgsrc.damageType){
		case EXPLOSION:
		case LIGHTNING:
		case ENERGY:
		case FIRE:
		case ICE:
			return 14.0f;
		case PHYSICAL:
		case PROJECTILE:
			return 10.0f;
		case POISON:
		case RADIATION:
			return 16.0f;
		case UNRESISTABLE:
	default:
		return 0.0f;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(11);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2.5D);
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		// Weapons
		Random r = new Random();
		Item weapon = null;
		switch (r.nextInt(5)) {
			case 0:
				weapon = TGuns.grimreaper;
				break;
			case 1:
				weapon = TGuns.minigun;
				break;
			case 2:
				weapon = TGuns.scar;
				break;
			default:
				weapon = TGuns.as50;
				break;
			/*default:
				weapon = Items.IRON_SHOVEL;
				break;*/
		}
		if (weapon != null) this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapon));
	}

}
