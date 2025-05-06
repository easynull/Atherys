package com.easynull.rebyssal.core.world;

import com.easynull.rebyssal.core.Register;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.JunglePyramidStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

import static com.easynull.rebyssal.Rebyssal.ID;

public final class DimensionsManager {
    public static ArrayList<Data> dims = new ArrayList<>(), strs = new ArrayList<>();
    public static RegistryKey<World> abyssalWasteland, dreadlands, omothol, darkRealm;
    public static StructureFeature<?, ?> etherCastle;

    public static void setupDIM() {
    }

    public static void setupStr(){
        etherCastle = registerStr("ether_castle", ()-> new JunglePyramidStructure(NoFeatureConfig.field_236558_a_), new Track(null, (short) 1200, (short) 1600));
    }

    public static RegistryKey<World> registerDIM(String name, Block frame, Block portal, int color, Track track){
        RegistryKey<World> w = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(ID, name));
        Data data = new Data(name, frame, portal, color, w, track);
        dims.add(data);
        return w;
    }

    public static StructureFeature<?, ?> registerStr(String name, Supplier<Structure<NoFeatureConfig>> structure, Track track){
        RegistryObject<Structure<NoFeatureConfig>> s = Register.STRUCTURES.register(name, structure);
        StructureFeature<?, ?> sf = s.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
        Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(ID, name), sf);
        Data data = new Data(name, s.get(), track);
        strs.add(data);
        return sf;
    }

    public static String getID(String name) {
        return RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(ID, name)).getLocation().getPath();
    }

    public static Data getData(String name){
        for(Data d : dims){
            if (d.name.contains(name)) return d;
        }
        return null;
    }

    public static class Data {
        public final String name;
        public final Block frame, portal;
        public final int color;
        public final RegistryKey<?> reg;
        public final Track track;
        public Structure<?> structure;

        public Data(String name, Structure<?> structure, Track track){
            this(name, null, null, 0, null, track);
            this.structure = structure;
        }

        public Data(String name, Block frame, Block portal, int color, RegistryKey<?> reg, Track track){
            this.name = name;
            this.frame = frame;
            this.portal = portal;
            this.color = color;
            this.reg = reg;
            this.track = track;
        }
    }
    public record Track(SoundEvent sound, short minDelay, short maxDelay){}
}
