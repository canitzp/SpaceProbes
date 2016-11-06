package de.canitzp.satellitecom;

import net.minecraft.util.math.BlockPos;

/**
 * @author canitzp
 */
public interface ISatelliteCommunication{

    boolean canConnect(ISatelliteCommunication communicationPartner);

    void connected(ISatelliteCommunication communicationPartner);

    void sendData(ISatelliteCommunication communicationPartner, ISatelliteMessage message);

    int communicationRange();

    BlockPos position();

}
