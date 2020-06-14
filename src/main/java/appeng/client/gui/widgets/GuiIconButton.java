/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.client.gui.widgets;


import appeng.api.config.*;
import appeng.core.localization.ButtonToolTips;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public abstract class GuiIconButton extends Button implements ITooltip
{
	public static final ResourceLocation TEXTURE_STATES = new ResourceLocation("appliedenergistics2", "textures/guis/states.png");

	private boolean halfSize = false;

	public GuiIconButton(final int x, final int y, IPressable onPress )
	{
		super( x, y, 16, 16, "", onPress );
	}

	public void setVisibility( final boolean vis )
	{
		this.visible = vis;
		this.active = vis;
	}

	@Override
	public void renderButton(final int mouseX, final int mouseY, float partial) {

		Minecraft minecraft = Minecraft.getInstance();

		if( this.visible )
		{
			final int iconIndex = this.getIconIndex();

			TextureManager textureManager = minecraft.getTextureManager();
			textureManager.bindTexture(TEXTURE_STATES);
			RenderSystem.disableDepthTest();
			RenderSystem.enableBlend(); // FIXME: This should be the _default_ state, but some vanilla widget disables it :|
			if( this.halfSize )
			{
				this.width = 8;
				this.height = 8;

				RenderSystem.pushMatrix();
				RenderSystem.translatef( this.x, this.y, 0.0F );
				RenderSystem.scalef( 0.5f, 0.5f, 0.5f );

				if( this.active )
				{
					RenderSystem.color4f( 1.0f, 1.0f, 1.0f, 1.0f );
				}
				else
				{
					RenderSystem.color4f( 0.5f, 0.5f, 0.5f, 1.0f );
				}

				final int uv_y = iconIndex / 16;
				final int uv_x = iconIndex - uv_y * 16;

				GuiUtils.drawTexturedModalRect( 0, 0, 256 - 16, 256 - 16, 16, 16, 0 );
				GuiUtils.drawTexturedModalRect( 0, 0, uv_x * 16, uv_y * 16, 16, 16, 0 );
				RenderSystem.popMatrix();
			}
			else
			{
				if( this.active )
				{
					RenderSystem.color4f( 1.0f, 1.0f, 1.0f, 1.0f );
				}
				else
				{
					RenderSystem.color4f( 0.5f, 0.5f, 0.5f, 1.0f );
				}

				final int uv_y = iconIndex / 16;
				final int uv_x = iconIndex - uv_y * 16;

				GuiUtils.drawTexturedModalRect( this.x, this.y, 256 - 16, 256 - 16, 16, 16, 0 );
				GuiUtils.drawTexturedModalRect( this.x, this.y, uv_x * 16, uv_y * 16, 16, 16, 0 );
			}
			RenderSystem.enableDepthTest();
		}
		RenderSystem.color4f( 1.0f, 1.0f, 1.0f, 1.0f );
	}

	protected abstract int getIconIndex();

	@Override
	public int xPos()
	{
		return this.x;
	}

	@Override
	public int yPos()
	{
		return this.y;
	}

	@Override
	public int getWidth()
	{
		return this.halfSize ? 8 : 16;
	}

	@Override
	public int getHeight()
	{
		return this.halfSize ? 8 : 16;
	}

	@Override
	public boolean isVisible()
	{
		return this.visible;
	}

	public boolean isHalfSize()
	{
		return this.halfSize;
	}

	public void setHalfSize( final boolean halfSize )
	{
		this.halfSize = halfSize;
	}

}
