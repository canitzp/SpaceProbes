package de.canitzp.satellitecom;

import net.minecraft.util.math.BlockPos;

/**
 * @author canitzp
 */
public abstract class PlanetStation implements ISatelliteCommunication{

    @Override
    public boolean canConnect(ISatelliteCommunication communicationPartner){
        return communicationPartner.canConnect(this);
    }

    public abstract BlockPos getStationPosition();

}
