import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Star {
    public static int MAX_BRIGHTNESS = 200;

    GameControler controler;
    Color color;
    short x, y;
    short a, r, g, b;
    boolean brighter;

    public Star(GameControler co) {
	controler = co;
	setPlace();
        a = (short)controler.getRandom().nextInt(MAX_BRIGHTNESS);
    }

    public void paint(Graphics gr) {
	if (brighter) a++;
	else a--;
	if (a > MAX_BRIGHTNESS) brighter = false;
	if (a < 0) setPlace();
	color = new Color(r, g, b, a);
        gr.setColor(color);
        gr.fillRect(x, y, 5, 5);
    }

    public void setPlace() {
        x = (short)controler.getRandom().nextInt(controler.getWidth());
        y = (short)controler.getRandom().nextInt(controler.getHeight());
	a = 0;
        r = (short)controler.getRandom().nextInt(255);
        g = 255;
	if (r < 50) b = 255;
        else b = (short)controler.getRandom().nextInt(255);
	brighter = true;
        color = new Color(r, g, b, a);
    }
}
