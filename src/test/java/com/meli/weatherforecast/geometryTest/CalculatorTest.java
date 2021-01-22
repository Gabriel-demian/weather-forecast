package com.meli.weatherforecast.geometryTest;

import java.awt.geom.Point2D;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.weatherforecast.geometry.Calculator;

@SpringBootTest
public class CalculatorTest {

	@Test
	public void trianglePerimeter() {
		double trianglePerimeter = Calculator.trianglePerimeter(new Point2D.Double(1, 3), new Point2D.Double(3, 5),
				new Point2D.Double(5, 2));
		Assert.assertTrue(Calculator.doubleEquals(10.557, trianglePerimeter));
	}

	@Test
	public void trianglePerimeterMustFail() {
		double trianglePerimeter = Calculator.trianglePerimeter(new Point2D.Double(0, 0), new Point2D.Double(1, 1),
				new Point2D.Double(7, 8));
		Assert.assertFalse(Calculator.doubleEquals(3.1, trianglePerimeter));
	}

	@Test
	public void triangleArea() {
		Double area = Calculator.areaByHeron(new Point2D.Double(0, 0), new Point2D.Double(1, 1),
				new Point2D.Double(5, 3));
		Assert.assertTrue(Calculator.doubleEquals(0.9999, area));
	}

	@Test
	public void doubleEquals() {
		Assert.assertTrue(Calculator.doubleEquals(0, 0));
		Assert.assertTrue(Calculator.doubleEquals(1, 1.0));
		Assert.assertTrue(Calculator.doubleEquals(0.00,0.0));
        Assert.assertTrue(Calculator.doubleEquals(4.0000004,4));
	}

}
