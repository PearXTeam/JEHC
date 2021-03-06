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

package net.pearx.jehc.jei.sbm.market;

import com.pam.harvestcraft.tileentities.MarketData;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MarketRecipeWrapper implements IRecipeWrapper {
    private MarketData md;

    public MarketRecipeWrapper(MarketData data) {
        md = data;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ItemStack pr = md.getCurrency().copy();
        pr.setCount(md.getPrice());
        ingredients.setInput(VanillaTypes.ITEM, pr);
        ingredients.setOutput(VanillaTypes.ITEM, md.getItem());
    }
}
