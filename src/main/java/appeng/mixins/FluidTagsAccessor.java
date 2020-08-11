package appeng.mixins;

import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FluidTags.class)
public interface FluidTagsAccessor {

    @Invoker("register")
    static Tag.Identified<Fluid> register(String id) {
        throw new AssertionError();
    }

}
