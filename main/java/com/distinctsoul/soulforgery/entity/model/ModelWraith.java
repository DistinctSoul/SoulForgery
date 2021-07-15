package com.distinctsoul.soulforgery.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelZombie - Either Mojang or a mod author
 * Created using Tabula 7.1.0
 */
public class ModelWraith extends ModelBase {
    public ModelRenderer right_arm;
    public ModelRenderer head;
    public ModelRenderer torso;
    public ModelRenderer left_arm;

    public ModelWraith() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.right_arm = new ModelRenderer(this, 40, 16);
        this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(right_arm, -1.5707963267948966F, 0.0F, 0.0F);
        this.left_arm = new ModelRenderer(this, 40, 16);
        this.left_arm.mirror = true;
        this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(left_arm, -1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.torso = new ModelRenderer(this, 16, 16);
        this.torso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.right_arm.render(f5);
        this.left_arm.render(f5);
        this.head.render(f5);
        this.torso.render(f5);
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    		float headPitch, float scaleFactor, Entity entityIn) {
    	
    	this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    	this.head.rotateAngleX = headPitch * 0.017453292F;
    }
}
