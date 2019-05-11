import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageCompiler {
	public static BufferedImage getTheImage() {
		return new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
	}
	
	public static int makeImages(BufferedImage workingImage, File destination, File[][] allLayers, int startIndex, int currentNumber) throws IOException {
		File[] currentLayer = allLayers[startIndex];

		for (File f : currentLayer) {
			if (f.isFile()) {
				BufferedImage myImage = ImageIO.read(f);
				BufferedImage myWork = getTheImage();
				Graphics2D myGraphics = myWork.createGraphics();
				myGraphics.drawImage(workingImage, 0, 0, 128, 128, null);
				myGraphics.drawImage(myImage, 0, 0, 128, 128, null);
				myGraphics.dispose();
				
				if (startIndex == allLayers.length - 1) {
					File finalDestination = destination.toPath().resolve(String.valueOf(currentNumber++) + ".png").toFile();
					ImageIO.write(myWork, "png", finalDestination);
				} else {
					currentNumber = makeImages(myWork, destination, allLayers, startIndex + 1, currentNumber);				
				}
			}
		}
		
		return currentNumber;
	}
	
	public static void main(String[] args) {
		String location = args[0];
		
		File base = null;
		File output = null;
		File folder = new File(location);
		List<File> partFolders = new LinkedList<>();

		for(File file: folder.listFiles()) {
		    if (file.isDirectory()) {
		    	 if (file.getName().equalsIgnoreCase("base")) {
		    		base = file;
		    	} else if (file.getName().equalsIgnoreCase("output")) {
		    		output = file;
		    	} else {
		    		partFolders.add(file);
		    	}
		    }
		}
		
		List<List<File>> allParts = new LinkedList<>();
		for (File file : partFolders) {
			LinkedList<File> files = new LinkedList<>();
			allParts.add(files);
			
			for(File imageFile : file.listFiles()) {
			    if (imageFile.isFile()) {
			    	files.add(imageFile);
			    }
			}
		}
				
		File[][] allFiles = new File[allParts.size()][];
		int i = 0;
		
		for (List<File> list : allParts) {
			allFiles[i] = new File[list.size()];
			
			int j = 0;
			for (File f : list) {
				allFiles[i][j++] = f;
			}
			
			i++;
		}		
		
		try {
			for (File f : base.listFiles()) {
				BufferedImage baseImage = ImageIO.read(f);
				long startTime = System.nanoTime();
				int result = makeImages(baseImage, output, allFiles, 0, 0);	
				long endTime = System.nanoTime();
				System.out.println("Created Images: " + result);
				System.out.println("Elapsted Time: " + ((endTime - startTime) / 1000.0 / 1000.0 / 1000.0) + " seconds");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
