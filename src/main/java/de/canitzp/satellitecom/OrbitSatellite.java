package de.canitzp.satellitecom;

import net.minecraft.util.math.BlockPos;

/**
 * @author canitzp
 */
public abstract class OrbitSatellite implements ISatelliteCommunication, ISatellite{

    @Override
    public final BlockPos getCorrespondingPosition() {
        return correspondingPosition();
    }

    public abstract BlockPos correspondingPosition();

}
