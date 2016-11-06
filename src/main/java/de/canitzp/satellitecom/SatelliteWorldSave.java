package de.canitzp.satellitecom;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

/**
 * @author canitzp
 */
@Mod.EventBusSubscriber
@SuppressWarnings("ConstantConditions")
public class SatelliteWorldSave extends WorldSavedData{

    public static final String SAVE_NAME = "satellite_communication";

    private static boolean isDirty;

    public final List<ISatellite> satellitesInOrbit = new ArrayList<>();

    public SatelliteWorldSave(String s){
        super(s);
    }

    public SatelliteWorldSave(){
        super(SAVE_NAME);
    }

    @Override
    public void readFromNBT(NBTTagCompound satNBT){
        satellitesInOrbit.clear();
        Set<String> keys = satNBT.getKeySet();
        for(String key : keys){
            NBTBase nbt = satNBT.getTag(key);
            if(nbt != null && nbt instanceof NBTTagCompound){
                NBTTagCompound satDataNBT = (NBTTagCompound) nbt;
                try {
                    Class<? extends ISatellite> satClass = (Class<? extends ISatellite>) Class.forName(satDataNBT.getString("SatClass"));
                    ISatellite satellite = satClass.newInstance();
                    satellite.setName(key);
                    satellite.setCorrespondingPosition(BlockPos.fromLong(satDataNBT.getLong("OrbitPosition")));
                    satellite.deserializeNBT(satDataNBT.getCompoundTag("SatData"));
                    satellitesInOrbit.add(satellite);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound satNBT) {
        for (ISatellite satellite : satellitesInOrbit) {
            NBTTagCompound satDataNBT = new NBTTagCompound();
            satDataNBT.setString("SatClass", satellite.getClass().getName());
            satDataNBT.setLong("OrbitPosition", satellite.getCorrespondingPosition().toLong());
            satDataNBT.setTag("SatData", satellite.serializeNBT());
            satNBT.setTag(satellite.getSatelliteName(), satDataNBT);
        }
        return satNBT;
    }

    @Override
    public String toString() {
        return "SatelliteWorldSave={" + satellitesInOrbit + "}";
    }

    public static void addSatelliteToOrbit(World world, ISatellite satellite){
        if(isPlanetaryDimension(world)){
            SatelliteWorldSave data = getOrLoadData(world);
            if(data != null){
                data.satellitesInOrbit.add(satellite);
                setDirty();
            }
        }
    }

    public static boolean removeSatelliteFromOrbit(World world, ISatellite satellite){
        if(isPlanetaryDimension(world)){
            SatelliteWorldSave data = getOrLoadData(world);
            if(data != null){
                ISatellite removeCandidate = null;
                for(ISatellite satellite1 : data.satellitesInOrbit){
                    if(satellite1.getCorrespondingPosition().equals(satellite.getCorrespondingPosition())){
                        removeCandidate = satellite1;
                        break;
                    }
                }
                if(removeCandidate != null){
                    data.satellitesInOrbit.remove(removeCandidate);
                    setDirty();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPlanetaryDimension(World world){
        return world != null && !world.isRemote && !world.provider.getHasNoSky() && world.provider.canRespawnHere();
    }

    @SubscribeEvent
    public static void loadWorld(WorldEvent.Load event){
        System.out.println(getOrLoadData(event.getWorld()));
    }

    @SubscribeEvent
    public static void saveWorld(WorldEvent.Save event){
        System.out.println(getOrLoadData(event.getWorld()));
    }

    public static SatelliteWorldSave getOrLoadData(World world){
        if(world != null && !world.isRemote){
            MapStorage storage = world.getPerWorldStorage();
            if(storage != null){
                WorldSavedData worldSave = storage.getOrLoadData(SatelliteWorldSave.class, SAVE_NAME);
                if(worldSave instanceof SatelliteWorldSave){
                    worldSave.setDirty(isDirty);
                    return (SatelliteWorldSave) worldSave;
                } else {
                    SatelliteWorldSave satelliteWorldSave = new SatelliteWorldSave();
                    satelliteWorldSave.setDirty(isDirty);
                    storage.setData(SAVE_NAME, satelliteWorldSave);
                    return satelliteWorldSave;
                }
            }
        }
        return null;
    }

    public static void setDirty(){
        isDirty = true;
    }

}
