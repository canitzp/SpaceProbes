package de.canitzp.satellitecom;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author canitzp
 */
public interface ISatellite extends INBTSerializable<NBTTagCompound>{

    String getSatelliteName();

    BlockPos getCorrespondingPosition();

    void setCorrespondingPosition(BlockPos pos);

    void setName(String name);

}
