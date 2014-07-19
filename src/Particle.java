/*
 *          DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2014 Mytchel Hammond
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this file, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * -----------------------------------------------------------------
 *
 *
 * A particles, it fades as it moves.
 * Looks pretty, does very little else.
 * Oh, I forgot, it does something very important.
 * It adds lag. Lots AND LOTS OF LAG.
 * It's pretty though.
 */

import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;

public class Particle extends Part  {
   
    short a, r, g, b;

    public Particle(GameControler co,
		    short alpha, short red, short green, short blue,
		    float x, float y,
		    float d,
		    float maxSpeed, float O) {
        super(co, new Color(red, green, blue, alpha));
        this.x = x;
        this.y = y;
        this.w = 5;
        this.h = 5;
        this.a = alpha;
        this.r = red;
        this.g = green;
        this.b = blue;

        // Work out a good speed based on how far away the bullet impact was.
        float speed = -(maxSpeed / 900) * (d - 30) * (d + 30);

        // Decide on a good error range for the direction.
        float Oerror = (2 * (float) Math.PI / 1600) * d * d;
        
        // Randomize the direction;
        O = O + (rand().nextFloat() * Oerror) - (Oerror / 2);

        this.xv = (float) Math.cos(O) * speed;
        this.yv = (float) Math.sin(O) * speed; 
    }

    public void paint(Graphics2D gr) {
        if (a < 0) return;
        gr.setColor(color);
        gr.fillRect((int)x, (int)y, (int)w, (int)h);
    }

    public void update() {
        x += xv;
        y += yv;
        a -= 5;
        color = new Color(r, g, b, a);
    }

    public boolean shouldRemove() {
        return (a < 10);
    }
}
