package de.canitzp.satellitecom;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author canitzp
 */
public interface ISatelliteMessage{

    SatelliteMessageTypes getType();

    NBTTagCompound data();

}
