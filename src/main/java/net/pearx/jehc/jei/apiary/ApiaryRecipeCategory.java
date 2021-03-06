/*
 * This file is part of Just Enough HarvestCraft.
 *
 * Just Enough HarvestCraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Just Enough HarvestCraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Just Enough HarvestCraft.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.pearx.jehc.jei.apiary;

import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.gui.ContainerApiary;
import com.pam.harvestcraft.gui.GuiApiary;
import com.pam.harvestcraft.item.ItemRegistry;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.jehc.jei.JehcRecipeCategory;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class ApiaryRecipeCategory extends JehcRecipeCategory<ApiaryRecipeWrapper> {
    public ApiaryRecipeCategory(IGuiHelper help) {
        super("jehc.apiary", new ItemStack(BlockRegistry.apiaryItemBlock), help.drawableBuilder(new ResourceLocation("harvestcraft", "textures/gui/apiary.png"), 3, 8, 170, 66).build());
    }

    @Override
    public void setupRecipes(IModRegistry registry) {
        registry.addRecipeClickArea(GuiApiary.class, 8, 55, 50, 12, getUid());
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerApiary.class, getUid(), 18, 1, 19, 36);
        ItemStack bee = new ItemStack(ItemRegistry.queenbeeItem, 1);
        registry.addRecipes(Arrays.asList(
                new ApiaryRecipeWrapper(bee, new ItemStack(ItemRegistry.waxcombItem), 50),
                new ApiaryRecipeWrapper(bee, new ItemStack(ItemRegistry.honeycombItem), 45),
                new ApiaryRecipeWrapper(bee, new ItemStack(ItemRegistry.grubItem), 5)
        ), getUid());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ApiaryRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 22, 26);
        recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        recipeLayout.getItemStacks().init(1, false, 58, 8);
        recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }
}
