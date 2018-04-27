package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends AbstractShape {

    public Triangle() {
    	
        properties = new HashMap<>();
        properties.put("x2", 0.0);
        properties.put("x3", 0.0);
        properties.put("y2", 0.0);
        properties.put("y3", 0.0);
    }

	@Override
	public void draw(Object canvas) {
		
        ((Graphics2D) canvas).setColor(getFillColor());
        ((Graphics2D) canvas).fillPolygon(new int[] {(int) position.getX(),
        			(int) properties.get("x2").intValue(),
        			(int) properties.get("x3").intValue()},
        		new int[] {(int) position.getY(),
    				(int) properties.get("y2").intValue(),
    				(int) properties.get("y3").intValue()},
        		3);

        ((Graphics2D) canvas).setStroke(new BasicStroke(2));
        ((Graphics2D) canvas).setColor(getColor());
        ((Graphics2D) canvas).drawPolygon(new int[] {(int) position.getX(),
    			(int) properties.get("x2").intValue(),
    			(int) properties.get("x3").intValue()},
    		new int[] {(int) position.getY(),
				(int) properties.get("y2").intValue(),
				(int) properties.get("y3").intValue()},
    		3);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape copy = new Triangle();
		copy.setColor(color);
		copy.setFillColor(fillColor);
		copy.setPosition(position);
		Map newProp = new HashMap<>();
		for(Map.Entry s : properties.entrySet()) {
			newProp.put(s.getKey(), s.getValue());
		}
		copy.setProperties(newProp);
		return copy;
	}

	@Override
	public void drawGuide(Object canvas) {
		
        ((Graphics2D) canvas).setColor(new Color(0,0,0,0.0f));
        ((Graphics2D) canvas).fillPolygon(new int[] {(int) position.getX(),
        			(int) properties.get("x2").intValue(),
        			(int) properties.get("x3").intValue()},
        		new int[] {(int) position.getY(),
    				(int) properties.get("y2").intValue(),
    				(int) properties.get("y3").intValue()},
        		3);

        ((Graphics2D) canvas).setStroke(new BasicStroke(2));
        ((Graphics2D) canvas).setColor(Color.LIGHT_GRAY);
        ((Graphics2D) canvas).drawPolygon(new int[] {(int) position.getX(),
    			(int) properties.get("x2").intValue(),
    			(int) properties.get("x3").intValue()},
    		new int[] {(int) position.getY(),
				(int) properties.get("y2").intValue(),
				(int) properties.get("y3").intValue()},
    		3);
	}
	
	float sign (int x1, int y1, int x2, int y2, int x3, int y3)
	{
	    return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
	}

	@Override
	public boolean contains(int x, int y) {
	    boolean b1, b2, b3;

	    b1 = sign(x, y, position.x, position.y, properties.get("x2").intValue(), properties.get("y2").intValue()) < 0.0f;
	    b2 = sign(x, y, properties.get("x2").intValue(), properties.get("y2").intValue(),
	    		properties.get("x3").intValue(), properties.get("y3").intValue()) < 0.0f;
	    b3 = sign(x, y, properties.get("x3").intValue(), properties.get("y3").intValue(), position.x, position.y) < 0.0f;

	    return ((b1 == b2) && (b2 == b3));
	}

	@Override
	public void calculateDimensions(Point startPoint, Point endPoint) {
		
	}
}
