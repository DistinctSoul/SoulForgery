package com.distinctsoul.soulforgery.entity;

import com.distinctsoul.soulforgery.util.handlers.LootTableHandler;
import com.distinctsoul.soulforgery.util.handlers.SFSoundHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityWraith extends EntityMob {
	public float destPos;
	
	
	public EntityWraith(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.95F);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }
	
	@Override
	public void onLivingUpdate() {
		this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
        
        if (this.world.isDaytime() && !this.world.isRemote && this.shouldBurnInDay()) {
        	float f = this.getBrightness();
        	
        	if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ))) {
        		
        		this.setFire(8);
        	}
        }
        
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
        
        super.onLivingUpdate();
	}
	
	public static void registerFixesWraith(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityWraith.class);
    }
	
	@Override
	public float getEyeHeight() {
		return 1.74F;
	}
	
	protected boolean shouldBurnInDay() {
        return true;
    }
	
	public void fall(float distance, float damageMultiplier) {
		
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entity = source.getImmediateSource();
		
		if (entity instanceof EntityArrow) {
			return false;
		}
		
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableHandler.WRAITH;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SFSoundHandler.ENTITY_WRAITH_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SFSoundHandler.ENTITY_WRAITH_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SFSoundHandler.ENTITY_WRAITH_DEATH;
	}
}
