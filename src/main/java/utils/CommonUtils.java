package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtils {
	public static String generateBrandNewEmail() {
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "") + "@email.com";
	}
	
	public static boolean compareTwoScreenshots1(String actualImagePath, String expectedImagePath) throws IOException {
		BufferedImage actualImg = ImageIO.read(new File(actualImagePath));
		BufferedImage expectedImg = ImageIO.read(new File(expectedImagePath));

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedImg, actualImg);

		return imgDifference.hasDiff();
	}
	
	public static int compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
		BufferedImage actualImg = ImageIO.read(new File(actualImagePath));
		BufferedImage expectedImg = ImageIO.read(new File(expectedImagePath));

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedImg, actualImg);

		return imgDifference.getDiffSize();
	}
}
