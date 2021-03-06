package fr.ftnt.mineswagg.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.ftnt.mineswagg.common.tileentities.TileEntitySwaggiumGenerator;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketSwaggGeneratorRequest implements IMessage
{
    public static int x, y, z, amount;

    public PacketSwaggGeneratorRequest()
    {}

    public PacketSwaggGeneratorRequest(int x, int y, int z, int amount)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeInt(this.amount);
    }

    public static class Handler implements IMessageHandler<PacketSwaggGeneratorRequest, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSwaggGeneratorRequest message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            World world = player.worldObj;
            TileEntity tile = world.getTileEntity(x, y, z);
            int remainingTime = 0;
            int stockedSwagg = 0;
            if(tile instanceof TileEntitySwaggiumGenerator)
            {
                remainingTime = ((TileEntitySwaggiumGenerator)tile).getRemainingTime();
                stockedSwagg = ((TileEntitySwaggiumGenerator)tile).getStockedSwagg();

                if(amount != 0)
                {
                    ((TileEntitySwaggiumGenerator)tile).addStockedSwagg(amount);
                    return null;
                }
            }
            return new PacketSwaggGeneratorAnswer(remainingTime, stockedSwagg);
        }
    }
}
