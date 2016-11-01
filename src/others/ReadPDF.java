package others;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;


public class ReadPDF {
	public static String readPDF(String fileName){
		String st = null;
		try{
			PDDocument document = null; 
			document = PDDocument.load(new File(fileName));
			document.getClass();
			if( !document.isEncrypted() ){
			    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			    stripper.setSortByPosition( true );
			    PDFTextStripper Tstripper = new PDFTextStripper();
			    st = Tstripper.getText(document);
			}
			}catch(Exception e){
			    e.printStackTrace();
			}
		return st;
	}
}
