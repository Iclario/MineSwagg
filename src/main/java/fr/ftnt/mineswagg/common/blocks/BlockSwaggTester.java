package fr.ftnt.mineswagg.common.blocks;

import fr.ftnt.mineswagg.common.MineSwagg;
import fr.ftnt.mineswagg.common.MineSwaggExtendedEntityPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockSwaggTester extends Block
{
    public static int swaggAmount, swaggLevel, maxSwagg;

    public BlockSwaggTester()
    {
        super(Material.iron);
        this.setCreativeTab(MineSwagg.customTab);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypePiston);
        this.setBlockName("swaggTester");
        this.setBlockTextureName(MineSwagg.NAME + ":swaggium_block");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ)
    {

        MineSwaggExtendedEntityPlayer props = MineSwaggExtendedEntityPlayer.get(player);

        if(side == 1)
        {
            if(player.isSneaking())
            {
                if(!world.isRemote)
                {
                    props.addSwaggLevel(1, true);
                }
            }
            else
            {
                player.addChatMessage(new ChatComponentText((world.isRemote ? "Client: " : "Server: ") + "Amount: " + props.getSwaggAmount() + " / Level: " + props.getSwaggLevel() + (props.isNegativeSwagg() ? " / Negative swagg" : " / Positive swagg")));
            }

        }

        if(side == 0)
        {
            if(player.isSneaking())
            {
                if(!world.isRemote)
                {
                    props.consumeSwaggLevel(10, true);
                }
            }
            else
            {
                if(!world.isRemote)
                {
                    props.consumeSwaggLevel(1, true);
                }
            }
        }

        // System.out.println((!world.isRemote ? "Server: " : "Client: ") + "Swagg: " + props.getSwaggAmount());
        // System.out.println((!world.isRemote ? "Server: " : "Client: ") + "Swagg total: " + (props.getSwaggAmount() + props.getSwaggLevel() * props.getMaxSwagg()));
        // System.out.println((!world.isRemote ? "Server: " : "Client: ") + "Swagg level: " + props.getSwaggLevel() + "\n");

        // System.out.println("Side: " + side);

        return true;
    }
}