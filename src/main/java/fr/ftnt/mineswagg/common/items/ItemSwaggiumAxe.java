package fr.ftnt.mineswagg.common.items;

import fr.ftnt.mineswagg.common.MineSwagg;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemSwaggiumAxe extends ItemAxe
{
    public ItemSwaggiumAxe()
    {
        super(MineSwagg.toolSwaggium);
        setUnlocalizedName("axeSwaggium");
        setTextureName(MineSwagg.NAME + ":swaggium_axe");
        setCreativeTab(MineSwagg.customTab);
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        if(repair.getItem() == MineSwagg.itemSwaggiumIngot)
        {
            return true;
        }
        return false;
    }
}