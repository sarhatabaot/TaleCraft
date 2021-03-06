package talecraft.client.gui.components;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import talecraft.client.gui.GuiColors;
import talecraft.util.ArrayListHelper;
@OnlyIn(Dist.CLIENT)
public class GuiLabel extends net.minecraft.client.gui.GuiLabel {

	public GuiLabel(FontRenderer font, List<String> text, int x, int y, int color) {
		super(text, color, font); 
		this.x = x;
		this.y = y;
		this.visible = true;
	}
	public GuiLabel(FontRenderer font, List<String> text, int x, int y) {
		this(font, text, x, y, GuiColors.WHITE);
	}
	public GuiLabel(FontRenderer font, String string, int x, int y) {
		this(font, ArrayListHelper.createArrayListString(string), x, y);
	}
}
