package com.awkwardstudios.noname.custom;

import java.io.PrintStream;
import java.util.Locale;

public class stuff {
	public static PrintStream printf(Locale l, Object format, Object... args){return System.out.format(l, format.toString(), args);}
	public static PrintStream printf(Object format, Object... args){return System.out.format(format.toString(), args);}
}
