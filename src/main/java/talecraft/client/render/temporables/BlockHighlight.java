package talecraft.client.render.temporables;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.BlockPos;
import talecraft.client.ClientResources;
import talecraft.client.render.ITemporaryRenderable;
import talecraft.client.render.renderers.BoxRenderer;

public class BlockHighlight implements ITemporaryRenderable {
	private BlockPos position;
	private long dietime;

	public BlockHighlight(BlockPos pos, double duration) {
		position = pos;
		dietime = System.currentTimeMillis() + (long)(duration * 1000d);
	}

	@Override
	public void render(Minecraft mc, 
			Tessellator tessellator, BufferBuilder vertexbuffer,
			double partialTicks) {

		float minX = position.getX()+1;
		float minY = position.getY()+1;
		float minZ = position.getZ()+1;
		float maxX = position.getX();
		float maxY = position.getY();
		float maxZ = position.getZ();

		int color = 0xFF7F00;
		float r = ((color >> 16) & 0xFF) / 256f;
		float g = ((color >> 8) & 0xFF) / 256f;
		float b = (color & 0xFF) / 256f;
		float a = .75f;

		GlStateManager.disableDepthTest();
		GlStateManager.enableBlend();
		GlStateManager.enableCull();
		GlStateManager.color4f(1f, 1f, 1f, 0.5f);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		Minecraft.getInstance().getTextureManager().bindTexture(ClientResources.texColorWhite);
		BoxRenderer.renderBox(tessellator, vertexbuffer, minX, minY, minZ, maxX, maxY, maxZ, r, g, b, a);
		GlStateManager.enableDepthTest();
	}

	@Override
	public boolean canRemove() {
		return System.currentTimeMillis() > dietime;
	}

}
