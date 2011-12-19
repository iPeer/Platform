package iPeer.Platform.Graphics;

import java.awt.Color;
import java.awt.color.ColorSpace;

public class Colour extends Color {
	
	private static final long serialVersionUID = -4227268747401926577L;
	
	public static Color WHITE = new Color(255, 255, 255);
	public static Color BLACK = new Color(0, 0, 0);
	public static Color PINK = Color.pink;

	
	public Color getColour(int r, int g, int b) {
		return new Color(r, g, b);
	}

	public Colour(int rgb) {
		super(rgb);
	}

	public Colour(int rgba, boolean hasalpha) {
		super(rgba, hasalpha);
	}

	public Colour(int r, int g, int b) {
		super(r, g, b);
	}

	public Colour(float r, float g, float b) {
		super(r, g, b);
	}

	public Colour(ColorSpace cspace, float[] components, float alpha) {
		super(cspace, components, alpha);
	}

	public Colour(int arg0, int arg1, int arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public Colour(float r, float g, float b, float a) {
		super(r, g, b, a);
	}

}
