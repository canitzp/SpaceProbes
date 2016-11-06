package de.canitzp.satellitecom;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

/**
 * @author canitzp
 */
public class SimpleAbstractSatellite implements ISatellite {

    public String name;
    public BlockPos position;

    public SimpleAbstractSatellite(){}

    public SimpleAbstractSatellite(String name, BlockPos correspondingPosition){
        this.name = name;
        this.position = correspondingPosition;
    }

    @Override
    public String getSatelliteName() {
        return name;
    }

    @Override
    public BlockPos getCorrespondingPosition() {
        return position;
    }

    @Override
    public void setCorrespondingPosition(BlockPos pos) {
        this.position = pos;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

}
