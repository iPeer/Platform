package iPeer.Platform.Engine;

import java.io.File;
import java.net.URL;

public class Debug {

	public static void p(String s) {
		System.out.println(s);
	}
	public static void p(boolean b) {
		System.out.println(b);
	}
	public static void p(int i) {
		System.out.println(i);
	}
	public static void p(float f) {
		System.out.println(f);
	}
	public static void p(File f) {
		System.out.println(f);		
	}
	public static void p(URL u) {
		System.out.println(u);	
	}
	
	
}
