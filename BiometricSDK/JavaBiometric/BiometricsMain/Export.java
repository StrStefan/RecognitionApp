package BiometricsMain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	
	public int CompareType1N() {
		
	    File dir=new File("C:\\Users\\Stefan\\Desktop\\ServerAppPrints");
	    File[] dirListing = dir.listFiles();
	    String output="Empty";
	    int result=0;
		
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
	        	if(percentData>= 30) {
	        		output=fingerPrintAsFile.getName();
	        		String s = ""+output.charAt(0);
	        		result=Integer.parseInt(s);
	        		break;
	        	}
	        }
	      }
		return result;
	}
	
    private int comparingFH = 0;
    private int comparingSH = 0;
	
    int comparingFirstHalf(){
    	
	    String output="0";
	    File dir=new File("E:\\FingerPrints");	    
	    File[] dirListing = dir.listFiles();
	    
	    if (dirListing != null) {
	    	
		    int end = dirListing.length/2;
		    File[] firstHalfList = Arrays.copyOfRange(dirListing, 0, end);
		    
	        for (File fingerPrintAsFile : firstHalfList) {
	          
				try {
					m_bimage2=ImageIO.read(fingerPrintAsFile) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    m_finger2.setFingerPrintImage(m_bimage2) ;
			    finger2=m_finger2.getFingerPrintTemplate();
			    
			    int percentData=m_finger1.Match(finger1 , finger2,50,false);
			    System.out.println("1 "+fingerPrintAsFile.getName()+" has: "+percentData+"%");
	        	if(percentData>= 50) {
	        		output=fingerPrintAsFile.getName();
	        		String s = ""+output.charAt(0);
	        		comparingFH=Integer.parseInt(s);  		
	        		break;
	        	}
	        }
	      }
        return comparingFH;
    }

    int comparingSecondHalf(){
		
    	String output="0";
	    File dir=new File("E:\\FingerPrints");
	    File[] dirListing = dir.listFiles();
	    
	    if (dirListing != null) {
	    	
		    int start = dirListing.length/2;
		    File[] secondHalfList = Arrays.copyOfRange(dirListing, start, dirListing.length-1);
		    
	        for (File fingerPrintAsFile : secondHalfList) {
	          
				try {
					m_bimage2=ImageIO.read(fingerPrintAsFile) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    m_finger2.setFingerPrintImage(m_bimage2) ;
			    finger2=m_finger2.getFingerPrintTemplate();
			    
			    int percentData=m_finger1.Match(finger1 , finger2,50,false);
			    System.out.println("2 "+fingerPrintAsFile.getName()+" has: "+percentData+"%");
	        	if(percentData>= 50) {
	        		output=fingerPrintAsFile.getName();
	        		String s = ""+output.charAt(0);
	        		comparingSH=Integer.parseInt(s);
	        		break;
	        	}
	        }
	      }
        return comparingSH;
    }

    public int execute(){
        
    	int result=0;
    	int result1=0;
    	int result2=0;
    	ExecutorService executorService = Executors.newFixedThreadPool(2);

        // method reference introduced in Java 8
        
        Future<Integer> future1 = executorService.submit(this::comparingFirstHalf);
        Future<Integer> future2 = executorService.submit(this::comparingSecondHalf);
        
        try {
			result1 = future1.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
        try {
			result2 = future2.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if( result1 != 0 )
        	result=result1;
        else if( result2 != 0)
        	result=result2;
        
        // close executorService
        executorService.shutdown();
        
        return result;
    }
}
