
/***************************************************************************
 *
 * Clase que almacena todas las fonts consideradas 'multiplataforma'.
 *
 * <p><b>Descripcion:</b> 
 * Esta clase almacena una lista con las fonts compatibles, estas fonts son:
 * Arial
 * Arial Narrow
 * Book Antiqua 
 * Century Gothic
 * Courier New 
 * Dialog
 * DialogInput 
 * Lucida Bright 
 * Lucida Sans (En Unix hay otra ademas != (LucidaSans sin blanco).
 * Lucida Sans Typewriter ("")
 * Monospaced
 * SansSerif
 * Serif
 * Times New Roman
 * 
 * Todas admiten cursiva y negrita.
 * <p><b>Problemas:</b>
 *
 * @see FontChooser 
 * 
 * 
 * @author  ONIS
 ****************************************************************************/
package test.fontchooser;

import java.awt.Font;

public class FontList {
	public static final String COMPATFONTS[] = {"Arial","Arial Narrow","Book Antiqua","Century Gothic","Courier New","Dialog","DialogInput","Lucida Bright","Lucida Sans","Lucida Sans Typewriter","Monospaced","SansSerif","Serif","Times New Roman"};
	
	/**
	@roseuid 3CA83DBE0370
	*/
	public static Font getFont(int i) {
		if(i>=COMPATFONTS.length) return null;
		else
			return new Font(COMPATFONTS[i],Font.PLAIN,10);
	}
	
	/**
	@roseuid 3CA83DBE0372
	*/
	public static Font getFont(String fname) {
		return new Font(fname,Font.PLAIN,10);
	}
	
	/**
	@roseuid 3CA83DBE037B
	*/
	public static String getFontName(int i) {
		if(i>=COMPATFONTS.length) return null;
		else
			return COMPATFONTS[i];
	}
	
	/**
	@roseuid 3CA83DBE037D
	*/
	public static boolean isCompatible(String fname) {
		for(int i=0; i<COMPATFONTS.length;i++) {
			if(COMPATFONTS[i].equals(fname)) return true;
		}
		return false;
	}
	
	/**
	@roseuid 3CA83DBE037F
	*/
	public static boolean isCompatible(Font f) {
		String fname=f.getFamily();
		return isCompatible(fname);
	}
	
	/**
	@roseuid 3CA83DBE038B
	*/
	public static int getFontNumber(String fname) {
		for(int i=0; i<COMPATFONTS.length;i++) {
			if(COMPATFONTS[i].equals(fname)) return i;
		}
		return -1;
	}
	
	/**
	@roseuid 3CA83DBE038D
	*/
	public static int getFontNumber(Font f) {
		String fname=f.getFamily();
		return getFontNumber(fname);
	}
}
