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

package net.pearx.jehc.jei.sbm;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.jehc.jei.JehcRecipeCategory;

@SideOnly(Side.CLIENT)
public abstract class SBMRecipeCategory extends JehcRecipeCategory<IRecipeWrapper> {
    private Class<? extends GuiContainer> guiClass;

    public SBMRecipeCategory(String uid, ItemStack catalyst, String png, IGuiHelper helper, Class<? extends GuiContainer> guiClass) {
        super(uid, catalyst, helper.drawableBuilder(new ResourceLocation("harvestcraft", "textures/gui/" + png + ".png"), 32, 0, 112, 76).build());
        this.guiClass = guiClass;
    }

    @Override
    public void setupRecipes(IModRegistry registry) {
        registry.addRecipeClickArea(guiClass, 99, 15, 36, 18, getUid());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, false, 40, 25);
        recipeLayout.getItemStacks().set(0, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        recipeLayout.getItemStacks().init(1, true, 80, 47);
        recipeLayout.getItemStacks().set(1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft minecraft) {
        String s = I18n.format("jehc.price");
        int width = minecraft.fontRenderer.getStringWidth(s);
        minecraft.fontRenderer.drawString(s, 67 + ((37 - width) / 2), 25 + ((18 - minecraft.fontRenderer.FONT_HEIGHT) / 2), 0);
    }
}
