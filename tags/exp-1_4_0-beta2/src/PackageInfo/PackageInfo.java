/*
 * $RCSfile$
 *
 * Copyright (c) 2005 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 *
 * $Revision: 30 $
 * $Date: 2005-02-19 00:34:20 +0800 (周六, 2005-02-19) $
 * $State$
 */

public class PackageInfo {
    public PackageInfo() {
	ClassLoader classLoader = getClass().getClassLoader();

	pkgInfo(classLoader, "javax.vecmath", "Point3d");
	pkgInfo(classLoader, "javax.media.j3d", "SceneGraphObject");
	pkgInfo(classLoader, "com.sun.j3d.utils.universe", "SimpleUniverse");
	//pkgInfo(classLoader, "com.sun.j3d.loaders.vrml97", "VrmlLoader");
    }

    static void pkgInfo(ClassLoader classLoader,
			String pkgName,
			String className) {

	try {
	    classLoader.loadClass(pkgName + "." + className);

	    Package p = Package.getPackage(pkgName);
	    if (p == null) {
		System.out.println("WARNING: Package.getPackage(" +
				   pkgName +
				   ") is null");
	    }
	    else {
		System.out.println(p);
		System.out.println("Specification Title = " +
				   p.getSpecificationTitle());
		System.out.println("Specification Vendor = " +
				   p.getSpecificationVendor());
		System.out.println("Specification Version = " +
				   p.getSpecificationVersion());

		System.out.println("Implementation Vendor = " +
				   p.getImplementationVendor());
		System.out.println("Implementation Version = " +
				   p.getImplementationVersion());
	    }
	}
	catch (ClassNotFoundException e) {
	    System.out.println("Unable to load " + pkgName);
	}

	System.out.println();
    }

    public static void main(String[] args) {
	new PackageInfo();
    }
}