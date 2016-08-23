/**
 * 
 */
package cn.cetelem.des.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author flaki
 * @date 2016年7月7日
 * @type 注解扫描工具
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public class TaskBeanScanUtil {
	public static Set<Class<?>> getClassesWithAnnotation(String pack,
			Class<? extends Annotation> annotationClass) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		boolean recursive = true;
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findClass(packageName, filePath, recursive, classes,
							annotationClass);
				} else if ("jar".equals(protocol)) {
					JarFile jar;
					try {
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						Enumeration<JarEntry> entries = jar.entries();
						while (entries.hasMoreElements()) {
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							if (name.charAt(0) == '/') {
								name = name.substring(1);
							}
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								if (idx != -1) {
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								if (idx != -1 || recursive) {
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										String className = name.substring(
												packageName.length() + 1,
												name.length() - 6);
										try {
											Class<?> clazz = Class
													.forName(packageName + "."
															+ className);
											if (annotationClass == null
													|| clazz.getAnnotation(annotationClass) != null) {
												classes.add(clazz);
											}
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;

	}

	/**
	 * @param packageName
	 * @param filePath
	 * @param recursive
	 * @param classes
	 * @param annotationClass
	 */
	private static void findClass(String packageName, String packagePath,
			final boolean recursive, Set<Class<?>> classes,
			Class<? extends Annotation> annotationClass) {
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		File[] dirfiles = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return (recursive && file.isDirectory() || (file.getName()
						.endsWith(".class")));
			}
		});
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				findClass(packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes,
						annotationClass);
			} else {
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					Class<?> clazz = Class.forName(packageName + "."
							+ className);
					if (annotationClass == null
							|| clazz.getAnnotation(annotationClass) != null) {
						classes.add(clazz);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(TaskBeanScanUtil.getClassesWithAnnotation("cn.cetelem.des",
				Task.class));
	}
}
