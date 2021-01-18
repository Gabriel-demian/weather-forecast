package com.meli.weatherforecast.geometry;

import java.awt.geom.Point2D;

public class Calculator {

	public static Double perimeter(Point2D point1, Point2D point2, Point2D point3) {

		Double distance1to2 = point1.distance(point2);
		Double distance2to3 = point2.distance(point3);
		Double distance3to1 = point3.distance(point1);

		return distance1to2 + distance2to3 + distance3to1;
	}

	public static Double areaByHeron(Point2D point1, Point2D point2, Point2D point3) {

		Double distance1to2 = point1.distance(point2);
		Double distance2to3 = point2.distance(point3);
		Double distance3to1 = point3.distance(point1);

		Double perimeter = distance1to2 + distance2to3 + distance3to1;

		Double semiPerimeter = perimeter / 2;

		Double area = Math.sqrt(semiPerimeter * (semiPerimeter - distance1to2) * (semiPerimeter - distance2to3)
				* (semiPerimeter - distance3to1));

		return area.isNaN() ? 0 : area;
	}
	
	public static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) < 0.0001;
    }

}
