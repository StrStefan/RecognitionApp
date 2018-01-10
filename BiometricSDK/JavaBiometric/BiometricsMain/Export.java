package BiometricsMain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import BiometricsMain.CEntityForm.BJPanel;
import BiometricsU.CFingerPrint;
import BiometricsU.CFingerPrintGraphics;

public class Export {
	
	private CFingerPrint m_finger1 = new CFingerPrint();
	private CFingerPrint m_finger2 = new CFingerPrint();
	private BufferedImage m_bimage1 = new BufferedImage(m_finger1.FP_IMAGE_WIDTH ,m_finger1.FP_IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB );
	private BufferedImage m_bimage2 = new BufferedImage(m_finger2.FP_IMAGE_WIDTH ,m_finger2.FP_IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB );
	private double finger1[] = new double[m_finger1.FP_TEMPLATE_MAX_SIZE];
	private double finger2[] = new double[m_finger2.FP_TEMPLATE_MAX_SIZE];	
	
	
	public Export(){
		
		try {
			//m_bimage1 = ImageIO.read(new ByteArrayInputStream(pic1));
			m_bimage1=ImageIO.read(new File(new java.io.File("").getAbsolutePath()+"\\pics\\pics1.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    m_finger1.setFingerPrintImage(m_bimage1) ;
	    finger1=m_finger1.getFingerPrintTemplate();
	    
		try {
			m_bimage2=ImageIO.read(new File(new java.io.File("").getAbsolutePath()+"\\pics\\pics2.bmp")) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    m_finger2.setFingerPrintImage(m_bimage2) ;
	    finger2=m_finger2.getFingerPrintTemplate();
	    
	}
	
	public Export(byte[] fingerPrintToMatch){
		
		
        String link=("C:\\Users\\Stefan\\Desktop\\Licenta\\Recognition\\pics\\fingerP1.bmp");
        
		try {
			// convert byte array to BufferedImage
			InputStream in = new ByteArrayInputStream(fingerPrintToMatch);
			m_bimage1 = ImageIO.read(in);
			ImageIO.write(m_bimage1, "bmp", new File(link));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	    m_finger1.setFingerPrintImage(m_bimage1) ;
	    finger1=m_finger1.getFingerPrintTemplate();
	    
	}
	
	public int Compare() {
		return m_finger1.Match(finger1 , finger2,65,false);
	}
	
	public String CompareType1N() {
		
	    File dir=new File("E:\\FingerPrints");
	    File[] dirListing = dir.listFiles();
	    String output="Empty";
		
	    if (dirListing != null) {
	        for (File fingerPrintAsFile : dirListing) {
	          
				try {
					m_bimage2=ImageIO.read(fingerPrintAsFile) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    m_finger2.setFingerPrintImage(m_bimage2) ;
			    finger2=m_finger2.getFingerPrintTemplate();
			    
			    int percentData=m_finger1.Match(finger1 , finger2,65,false);
			    System.out.println(fingerPrintAsFile.getName()+" has:"+percentData+"%");
	        	if(percentData>= 50) {
	        		output=fingerPrintAsFile.getName();
	        		break;
	        	}
	        }
	      }
		return output;
	}
}
