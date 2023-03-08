package org.bukkit.craftbukkit.v1_19_R2.block;

import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import org.bukkit.World;
import org.bukkit.block.HangingSign;

public class CraftHangingSign extends CraftSign<HangingSignBlockEntity> implements HangingSign {

    public CraftHangingSign(World world, HangingSignBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}
