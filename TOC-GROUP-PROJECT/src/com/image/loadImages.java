package com.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class loadImages {
	
	public static BufferedImage carBody;
	public static BufferedImage carWheel;
	public static BufferedImage interior;
	public static BufferedImage reverseCam;
	public static BufferedImage seatbelt;
	public static BufferedImage buju;
	public static BufferedImage roddyRich; 
	
	public static BufferedImage line; 
	public static BufferedImage tree1; 
	public static BufferedImage tree2; 
	public static BufferedImage tree3; 
	public static BufferedImage tree4; 
	
	
	
	public static void init() {
		carBody = imageLoader("/../image/Car/Car Body.png");
		carWheel = imageLoader("/../image/Car/Car Wheel.png");
		interior = imageLoader("/../image/interior.jpg"); 
		reverseCam = imageLoader("/../image/reverseCam.jpg");
		seatbelt = imageLoader("/../image/seatbelt.png");
		buju = imageLoader("/../image/Radio/Buju Banton.png");
		roddyRich = imageLoader("/../image/Radio/Roddy Ricch.png");
		line = imageLoader("/../image/assets/line.png");
		tree1 = imageLoader("/../image/assets/tree1.png");
		tree2 = imageLoader("/../image/assets/tree2.png");
		tree3 = imageLoader("/../image/assets/tree3.png");
		tree4 = imageLoader("/../image/assets/tree4.png");
		
	}
	
	public static BufferedImage imageLoader(String path) {
		try {
			return ImageIO.read(loadImages.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static BufferedImage rotateCw(BufferedImage img, int radiant) {
	    AffineTransform transform = new AffineTransform();
	    transform.rotate(radiant, img.getWidth()/2, img.getHeight()/2);
	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	    img = op.filter(img, null);
	    return img;
	}
	
	
}
