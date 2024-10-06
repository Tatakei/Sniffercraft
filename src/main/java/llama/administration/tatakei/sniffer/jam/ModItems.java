package llama.administration.tatakei.sniffer.jam;

import llama.administration.tatakei.sniffer.jam.tools.SnifferMaterial;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static void initialize() {
        // Item Group Maker
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.SNIFFER_AXE);
            itemGroup.add(ModItems.SNIFFER_SWORD);
            itemGroup.add(ModItems.SNIFFER_PICKAXE);
            itemGroup.add(ModItems.SNIFFER_SHOVEL);
            itemGroup.add(ModItems.SNIFFER_HOE);

            itemGroup.add(ModItems.SNIFFER_SEEDS);
        });

        // Register the group.
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        // Makes the item compostable
        CompostingChanceRegistry.INSTANCE.add(ModItems.SNIFFER_SEEDS, 1.0f);
    }

    // public static Item register(Item item, String id) {
    //        // Create the identifier for the item.
    //        Identifier itemID = Identifier.of(Sniffercraft.MOD_ID, id);
    //
    //        // Return the registered item!
    //        return Registry.register(Registries.ITEM, itemID, item);
    //    }

    public static Item register(Item item, String id) {Identifier itemID = Identifier.of(Sniffercraft.MOD_ID, id); return Registry.register(Registries.ITEM, itemID, item);}

    // Food Component Creator
    public static final FoodComponent SNIFFER_SEEDS_FOOD_COMPONENT = new FoodComponent.Builder()
            .nutrition(10)
            .build();

    // public static final FoodComponent SNIFFER_SEEDS_FOOD_COMPONENT = new FoodComponent.Builder().nutrition(10).build();

    // Register the food component
    public static final Item SNIFFER_SEEDS = register(
            new Item(new Item.Settings().food(SNIFFER_SEEDS_FOOD_COMPONENT).maxCount(64)),
            "sniffer_seeds"
    );

    public static final Item SNIFFER_PICKAXE = registerItem("sniffer_pickaxe",
            new PickaxeItem(SnifferMaterial.SNIFFER, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(SnifferMaterial.SNIFFER, 2, -2.4F))));

    public static final Item SNIFFER_AXE = registerItem("sniffer_axe",
            new AxeItem(SnifferMaterial.SNIFFER, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(SnifferMaterial.SNIFFER, 4, -1.9F))));

    public static final Item SNIFFER_SHOVEL = registerItem("sniffer_shovel",
            new ShovelItem(SnifferMaterial.SNIFFER, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(SnifferMaterial.SNIFFER, 3, -2.0F))));

    public static final Item SNIFFER_HOE = registerItem("sniffer_hoe",
            new HoeItem(SnifferMaterial.SNIFFER, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(SnifferMaterial.SNIFFER, 1, -0.0F))));

    public static final Item SNIFFER_SWORD = registerItem("sniffer_sword",
            new SwordItem(SnifferMaterial.SNIFFER, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(SnifferMaterial.SNIFFER, 5, -1.0F))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.tryParse(Sniffercraft.MOD_ID + ":" + name), item);
    }
    // Registers the Custom Item Group
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Sniffercraft.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SNIFFER_SEEDS))
            .displayName(Text.translatable("itemGroup.sniffercraft"))
            .build();
}