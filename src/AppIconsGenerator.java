import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

/*
 * @author Fadi Hatem
 *
 */
public class AppIconsGenerator {

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			System.out.println(
					"Usage:  AppIconsGenerator [path] [fileName] [android|iosScreenshots|iosLaunchImages|windows] ");
		}

		final String path = args[0];
		final String fileName = args[1];
		final String option = args.length == 3 ? args[2] : null;

		try {
			if (option != null && option.equalsIgnoreCase("android")) {
				createAndroidIcons(path, fileName);
			}
			if (option != null && option.equalsIgnoreCase("iosScreenshots")) {
				createIOSScreenshots(path, fileName);
			}
			if (option != null && option.equalsIgnoreCase("iosLaunchImages")) {
				createAppIconsLaunchImages(path, fileName);
			}
			if (option != null && option.equalsIgnoreCase("windows")) {
				createWindowsAppIcons(path, fileName);
			} else {
				createAndroidIcons(path, fileName);
				createIOSScreenshots(path, fileName);
				createAppIconsLaunchImages(path, fileName);
				createWindowsAppIcons(path, fileName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void createAndroidIcons(final String path,
			final String fileName) throws IOException {
		List<Point> launchIcons = Arrays.asList(new Point(1024, 500), new Point(180, 120));
		List<Integer> appIcons = Arrays.asList(48, 72, 96, 144, 512);
		List<String> appIconsFolders = Arrays.asList("drawable-mdpi/", "drawable-hdpi/", "drawable-xhdpi/",
				"drawable-xxhdpi/", ".");
		File androidDir = new File(path + "Android/");
		androidDir.mkdir();

		BufferedImage originalImage = ImageIO.read(new File(path + fileName + ".png"));
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

		for (int i = 0; i < appIcons.size(); i++) {
			File appIconsDir = new File(androidDir.getAbsolutePath() + "/" + appIconsFolders.get(i));
			appIconsDir.mkdir();
			BufferedImage resizeImagePng = resizeImage(originalImage, type, appIcons.get(i), appIcons.get(i));
			if (!appIconsFolders.get(i).equals(".")) {
				ImageIO.write(resizeImagePng, "png",
						new File(androidDir.getAbsolutePath() + "/" + appIconsFolders.get(i) + "ic_launcher.png"));
			} else {
				ImageIO.write(resizeImagePng, "png",
						new File(androidDir.getAbsolutePath() + "/" + "ic_launcher-web.png"));
			}
		}
		;

		for (Point p : launchIcons) {
			BufferedImage resizeImagePng = resizeImage(originalImage, type, p.x, p.y);
			ImageIO.write(resizeImagePng, "png",
					new File(androidDir.getAbsolutePath() + "/" + fileName + "-" + p.x + "-" + p.y + ".png"));
		}
		;
	}

	private static void createWindowsAppIcons(final String path,
			final String fileName) throws IOException {
		List<Point> launchIcons = Arrays.asList(new Point(16, 16), new Point(32, 32), new Point(64, 64),
				new Point(128, 128), new Point(256, 256), new Point(512, 512), new Point(1152, 1920), new Point(58, 58),
				new Point(120, 120), new Point(106, 106), new Point(744, 360), new Point(360, 360), new Point(170, 170),
				new Point(1116, 540), new Point(43, 43), new Point(90, 90), new Point(54, 54), new Point(558, 558),
				new Point(558, 270), new Point(270, 270), new Point(126, 126));
		List<String> fileFormats = Arrays.asList(".jpg", ".jpg", ".jpg", ".jpg", ".jpg", ".jpg", ".png", ".png", ".png",
				".png", ".png",
				".png", ".png", ".png", ".jpg", ".png",
				".png", ".jpg", ".jpg", ".png", ".jpg");
		File winDir = new File(path + "Windows/");
		winDir.mkdir();

		BufferedImage originalImage = ImageIO.read(new File(path + fileName + ".png"));
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

		for (int i = 0; i < launchIcons.size(); i++) {
			Point p = launchIcons.get(i);
			String fileFormat = fileFormats.get(i);
			BufferedImage resizeImagePng = resizeImage(originalImage, type, p.x, p.y);
			ImageIO.write(resizeImagePng, "png",
					new File(winDir.getAbsoluteFile() + "/" + fileName + "-" + p.x + "-" + p.y + fileFormat));
		}
		;
	}

	private static void createIOSScreenshots(final String path,
			final String fileName) throws IOException {
		List<Point> launchIcons = Arrays.asList(new Point(640, 960), new Point(640, 1136), new Point(750, 1334),
				new Point(1242, 2208), new Point(1536, 2048), new Point(960, 640), new Point(1024, 1024),
				new Point(1136, 640), new Point(1334, 750), new Point(2208, 1242), new Point(2048, 1536),
				new Point(2732, 2048));
		File iosDir = new File(path + "IOS/");
		iosDir.mkdir();

		BufferedImage originalImage = ImageIO.read(new File(path + fileName + ".png"));

		// create a blank, RGB, same width and height, and a white background
		BufferedImage newBufferedImage = new BufferedImage(originalImage.getWidth(),
				originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		newBufferedImage.createGraphics().drawImage(originalImage, 0, 0, Color.WHITE, null);

		for (Point p : launchIcons) {
			BufferedImage resizeImagePng = resizeImage(newBufferedImage, BufferedImage.TYPE_INT_RGB, p.x, p.y);
			ImageIO.write(resizeImagePng, "jpg",
					new File(iosDir.getAbsoluteFile() + "/" + fileName + "-" + p.x + "-" + p.y + ".jpg"));
		}
		;
	}

	private static void createAppIconsLaunchImages(final String path,
			final String fileName) throws IOException {
		List<Integer> appIcons = Arrays.asList(20, 29, 40, 50, 57, 58, 60, 72, 76, 80, 87, 100, 114, 120, 144, 152,
				180);
		List<Integer> doubles = Arrays.asList(29, 40, 58, 80, 120);
		List<Point> launchIcons = Arrays.asList(new Point(320, 480), new Point(640, 960), new Point(640, 1136),
				new Point(750, 1334), new Point(768, 1004), new Point(768, 1024), new Point(1024, 748),
				new Point(1024, 768), new Point(1242, 2208), new Point(1536, 2008), new Point(1536, 2048),
				new Point(2048, 1496), new Point(2048, 1536), new Point(2208, 1242));
		List<Point> launchDoubles = Arrays.asList(new Point(640, 960), new Point(640, 1136), new Point(768, 1024),
				new Point(1024, 768), new Point(1536, 2048), new Point(2048, 1536));

		File iosDir = new File(path + "IOS/");
		iosDir.mkdir();
		File appIconsDir = new File(path + "IOS/AppIcon.appiconset/");
		appIconsDir.mkdir();
		File launchIconsDir = new File(path + "IOS/LaunchImage.launchimage/");
		launchIconsDir.mkdir();

		BufferedImage originalImage = ImageIO.read(new File(path + fileName + ".png"));
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

		for (Integer i : appIcons) {
			BufferedImage resizeImagePng = resizeImage(originalImage, type, i, i);
			ImageIO.write(resizeImagePng, "png",
					new File(appIconsDir.getAbsolutePath() + "/" + fileName + "-" + i + ".png"));
			if (doubles.contains(i)) {
				ImageIO.write(resizeImagePng, "png",
						new File(appIconsDir.getAbsolutePath() + "/" + fileName + "-" + i + "-1.png"));
			}
		}
		;
		for (Point p : launchIcons) {
			BufferedImage resizeImagePng = resizeImage(originalImage, type, p.x, p.y);
			ImageIO.write(resizeImagePng, "png",
					new File(launchIconsDir.getAbsolutePath() + "/" + fileName + "-" + p.x + "-" + p.y + ".png"));
			if (launchDoubles.contains(p)) {
				ImageIO.write(resizeImagePng, "png",
						new File(launchIconsDir.getAbsolutePath() + "/" + fileName + "-" + p.x + "-" + p.y + "-1.png"));
			}
		}
		;

		File appIconsJson = new File("./resources/appicons.json");
		File launchIconsJson = new File("./resources/launchicons.json");
		File appIconsContentsJson = new File(path + "IOS/AppIcon.appiconset/Contents.json");
		File launchIconsContentsJson = new File(path + "IOS/LaunchImage.launchimage/Contents.json");
		String appIconString = "AppIcon";
		String launchIconString = "LaunchIcon";

		createContentsJsonFile(fileName, appIconsJson, appIconsContentsJson,
				appIconString);
		createContentsJsonFile(fileName, launchIconsJson, launchIconsContentsJson,
				launchIconString);
	}

	private static void createContentsJsonFile(final String fileName,
			File appIconsJson, File appIconsContentsJson, String stringToReplace)
			throws FileNotFoundException, IOException {
		String fileString = new Scanner(appIconsJson).useDelimiter("\\Z").next();

		fileString = fileString.replaceAll(stringToReplace, fileName);

		FileOutputStream fi = new FileOutputStream(appIconsContentsJson);

		fi.write(fileString.getBytes());
		fi.close();
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}
}
