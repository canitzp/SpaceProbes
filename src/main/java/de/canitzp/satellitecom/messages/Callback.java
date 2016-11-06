package de.canitzp.satellitecom.messages;

import de.canitzp.satellitecom.ISatelliteMessage;
import de.canitzp.satellitecom.SatelliteMessageTypes;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author canitzp
 */
public class Callback implements ISatelliteMessage{

    @Override
    public SatelliteMessageTypes getType(){
        return SatelliteMessageTypes.RECEIVED;
    }

    @Override
    public NBTTagCompound data(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("MainData", "MessageReceived");
        return nbt;
    }

    public static boolean isCallback(ISatelliteMessage message){
        return message != null && SatelliteMessageTypes.RECEIVED.equals(message.getType()) && message instanceof Callback && "MessageReceived".equals(message.data().getString("MainData"));
    }

}
