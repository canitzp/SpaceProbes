package de.canitzp.spaceprobes.items;

import de.canitzp.ctpcore.base.ItemBase;
import de.canitzp.satellitecom.SatelliteWorldSave;
import de.canitzp.satellitecom.SimpleAbstractSatellite;
import de.canitzp.spaceprobes.SpaceProbes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

import static de.canitzp.spaceprobes.items.ProbeParts.*;

/**
 * @author canitzp
 */
public class ItemSpaceProbe extends ItemBase{

    public ItemSpaceProbe(ProbeTypes probeType){
        super(new ResourceLocation(SpaceProbes.MODID, probeType.name().toLowerCase()));
        this.setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        super.addInformation(stack, player, tooltip, advanced);
        Hull hull = getProbeHull(stack);
        if(hull != null){
            tooltip.add("The " + hull.toString() + " Hull is installed.");
        } else {
            tooltip.add("No Hull installed.");
        }
        Antenna antenna = getProbeAntenna(stack);
        if(antenna != null){
            tooltip.add("The " + antenna.toString() + " Antenna is installed.");
        } else {
            tooltip.add("No Antenna installed.");
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
        setProbeHull(itemStackIn, Hull.SIMPLE);
        setProbeAntenna(itemStackIn, Antenna.FOUR_ADVANCED);
        SatelliteWorldSave.addSatelliteToOrbit(worldIn, new SimpleAbstractSatellite("TestProbe", playerIn.getPosition()));
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    /*
    Get the parts from the probe
     */
    public Hull getProbeHull(ItemStack probe){
       return getPart(Hull.class, HULL, probe);
    }

    public Antenna getProbeAntenna(ItemStack probe){
        return getPart(Antenna.class, ANTENNA, probe);
    }

    private  <T extends Enum> T getPart(Class<T> num, String type, ItemStack probe){
        int i = getIDFromProbe(type, probe);
        if(i >= 0){
            return num.getEnumConstants()[i];
        }
        return null;
    }


    /*
    Set the parts for the probe
     */
    public void setProbeHull(ItemStack probe, Hull hull){
        setProbePart(HULL, hull.ordinal(), probe);
    }

    public void setProbeAntenna(ItemStack probe, Antenna antenna){
        setProbePart(ANTENNA, antenna.ordinal(), probe);
    }



    private int getIDFromProbe(String type, ItemStack probe){
        if(probe.hasTagCompound()){
            NBTTagCompound nbt = probe.getTagCompound();
            if(nbt.hasKey("Parts")){
                NBTTagCompound parts = nbt.getCompoundTag("Parts");
                if(parts.hasKey(type)){
                    return parts.getInteger(type);
                }
            }
        }
        return -1;
    }

    private void setProbePart(String type, int partID, ItemStack probe){
        if(probe.hasTagCompound()){
            NBTTagCompound nbt = probe.getTagCompound();
            NBTTagCompound parts;
            if(nbt.hasKey("Parts")){
                parts = nbt.getCompoundTag("Parts");
            } else {
                parts = new NBTTagCompound();
            }
            parts.setInteger(type, partID);
            nbt.setTag("Parts", parts);
        } else {
            probe.setTagCompound(new NBTTagCompound());
            setProbePart(type, partID, probe);
        }
    }


    public enum ProbeTypes{
        SPUTNIK
    }

}
