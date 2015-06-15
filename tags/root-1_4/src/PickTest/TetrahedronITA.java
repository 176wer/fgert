/*
 * $RCSfile$
 *
 * Copyright (c) 2004 Sun Microsystems, Inc. All rights reserved.
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
 * $Revision: 3 $
 * $Date: 2004-06-09 11:28:13 +0800 (周三, 2004-06-09) $
 * $State$
 */

import javax.media.j3d.*;
import javax.vecmath.*;

class TetrahedronITA extends IndexedTriangleArray {

  TetrahedronITA() {
    super(4, GeometryArray.COORDINATES | GeometryArray.COLOR_3, 12);  
    
    Point3f verts[] = new Point3f[4];
    Color3f colors[] = new Color3f[4];
    
    verts[0] = new Point3f(1.0f,1.0f,1.0f);
    verts[1] = new Point3f(1.0f,-1.0f,-1.0f);
    verts[2] = new Point3f(-1.0f,-1.0f,1.0f);
    verts[3] = new Point3f(-1.0f,1.0f,-1.0f);

    colors[0] = new Color3f(1.0f, 0.0f, 0.0f);
    colors[1] = new Color3f(0.0f, 1.0f, 0.0f);
    colors[2] = new Color3f(0.0f, 0.0f, 1.0f);
    colors[3] = new Color3f(1.0f, 1.0f, 0.0f);
    
    int pntsIndex[] = new int[12];
    int clrsIndex[] = new int[12];

    pntsIndex[0] = 2;
    clrsIndex[0] = 0;
    pntsIndex[1] = 1;
    clrsIndex[1] = 0;
    pntsIndex[2] = 0;
    clrsIndex[2] = 0;
    
    pntsIndex[3] = 3;
    clrsIndex[3] = 1;
    pntsIndex[4] = 2;
    clrsIndex[4] = 1;
    pntsIndex[5] = 0;
    clrsIndex[5] = 1;

    pntsIndex[6] = 1;
    clrsIndex[6] = 2;    
    pntsIndex[7] = 2;
    clrsIndex[7] = 2;
    pntsIndex[8] = 3;
    clrsIndex[8] = 2;

    pntsIndex[9] = 1;
    clrsIndex[9] = 3;
    pntsIndex[10] = 3;
    clrsIndex[10] = 3;
    pntsIndex[11] = 0;
    clrsIndex[11] = 3;
    
    setCoordinates(0, verts);
    setCoordinateIndices(0, pntsIndex);
    setColors(0, colors);
    setColorIndices(0, clrsIndex);
  }
}
