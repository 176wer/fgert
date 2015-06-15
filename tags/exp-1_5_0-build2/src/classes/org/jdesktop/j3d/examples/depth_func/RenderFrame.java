/*
 * $RCSfile$
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. All rights reserved.
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
 * $Revision: 87 $
 * $Date: 2006-02-09 06:48:24 +0800 (周四, 2006-02-09) $
 * $State$
 */

package org.jdesktop.j3d.examples.depth_func;

import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*;


public class RenderFrame extends javax.swing.JFrame {
    
    DepthFuncTest dpt;
    
    SimpleUniverse su;
    
    RenderingAttributes staticWFBoxRA;
    RenderingAttributes staticBoxRA;

		RenderingAttributes rotatingBoxRA;
    
    /** Creates new form RenderFrame */
    public RenderFrame( DepthFuncTest _dpt) {
        dpt = _dpt;
        initComponents();
        initUniverse();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D frame");
        setBounds(400, 0, 640, 480);
    }
    // </editor-fold>//GEN-END:initComponents
    
    void initUniverse() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        
        Canvas3D c = new Canvas3D(config);
        add("Center", c);
        su = new SimpleUniverse(c);
        su.addBranchGraph( createScene() );
        c.getView().setMinimumFrameCycleTime( 10 );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    BranchGroup createScene() {
        BoundingSphere bounds = new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 100.0 );
        
        BranchGroup globalBG = new BranchGroup();
        BranchGroup rotObjectBG = new BranchGroup();
        OrderedGroup staticObjectOG = new OrderedGroup();
        BranchGroup lampsBG = new BranchGroup();
        OrderedGroup oGroup = new OrderedGroup();
        TransformGroup staticBoxRotTG = new TransformGroup();
        staticBoxRotTG.addChild( staticObjectOG );
        TransformGroup objectsTGRot = new TransformGroup();
        TransformGroup objectsTGTrans = new TransformGroup();
        Transform3D objectsTGTransT3d = new Transform3D();
        objectsTGTransT3d.setTranslation( new Vector3f( 0.0f, 0.0f, -10.0f ) );
        objectsTGTrans.setTransform( objectsTGTransT3d );
        objectsTGRot.addChild( oGroup );
        objectsTGTrans.addChild( objectsTGRot );
        lampsBG.addChild( objectsTGTrans );
        
        //adding a sphere as backgroung so there is something else than flat black, and cut cube removal as an other implication. (seeing through)
        Appearance globalSphereAppearance = new Appearance();
        PolygonAttributes globalSpherePA = new PolygonAttributes();
        globalSpherePA.setCullFace( globalSpherePA.CULL_FRONT );// so that interior of the sphere is visible.
        Material globalSphereMaterial = new Material();
        globalSphereMaterial.setEmissiveColor( .25f ,.3f ,.35f );
        globalSphereAppearance.setMaterial( globalSphereMaterial );
        globalSphereAppearance.setPolygonAttributes( globalSpherePA );
        Sphere globalSphere = new Sphere( 6.0f );
        globalSphere.setAppearance( globalSphereAppearance );
        globalSphere.setBounds( bounds );
        oGroup.addChild( globalSphere );
        
        globalBG.addChild( lampsBG );
        
        // adding lamps.
        PointLight frontLamp =  new PointLight( new Color3f( 1.0f, 1.0f, 1.0f ), new Point3f( 20, 20, 20 ), new Point3f( 0.0f, .0f, 0.f )  );
        lampsBG.addChild( frontLamp );
        frontLamp.setBounds( bounds );
        frontLamp.setInfluencingBounds( bounds );
        PointLight backLamp =  new PointLight( new Color3f( 1.0f, .0f, .0f ), new Point3f( -20, -20, -20 ), new Point3f( 0.0f, .0f, 0.f )  );
        lampsBG.addChild( backLamp );
        backLamp.setBounds( bounds );
        backLamp.setInfluencingBounds( bounds );
        
        //adding shapes.
         {
        //adding rotating and scaling cube
        //doing the rotation
        TransformGroup rotBoxTGRot = new TransformGroup();
        rotBoxTGRot.setCapability( rotBoxTGRot.ALLOW_TRANSFORM_WRITE );
        RotationInterpolator rotBoxRotInt = new RotationInterpolator( new Alpha( -1, 20000 ) , rotBoxTGRot );
        rotBoxRotInt.setSchedulingBounds( bounds );
        rotBoxRotInt.setBounds( bounds );
        
        //doing the scaling
        Transform3D scaleBoxt3d = new Transform3D();
        TransformGroup rotBoxTGScale = new TransformGroup();
        rotBoxTGScale.setCapability( rotBoxTGScale.ALLOW_TRANSFORM_WRITE );
        ScaleInterpolator rotBoxScaleInt = new ScaleInterpolator( new Alpha( -1, Alpha.INCREASING_ENABLE|Alpha.DECREASING_ENABLE, 0, 0, 3000, 1500, 0, 3000, 1500, 0 ) , rotBoxTGScale, new Transform3D(), 0.7f, 1.6f );
        rotBoxScaleInt.setSchedulingBounds( bounds );
        rotBoxScaleInt.setBounds( bounds );
        
        Appearance rotBoxApp = new Appearance();
        Material rotBoxMat = new Material();
        rotBoxMat.setDiffuseColor( .4f, .4f, .4f );
        rotBoxApp.setMaterial( rotBoxMat );
        Box rotBox = new Box( 1.1f, 1.1f, 1.1f, rotBoxApp );
        rotBoxTGScale.addChild( rotBox );
        rotBoxTGRot.addChild( rotBoxTGScale );
        TransformGroup rotBoxTG = new TransformGroup();
        rotBoxTG.addChild( rotBoxTGRot );
        rotObjectBG.addChild( rotBoxTG );
        rotObjectBG.addChild( rotBoxScaleInt );
        rotObjectBG.addChild( rotBoxRotInt );
        rotBox.setBounds( bounds );

        rotatingBoxRA = new RenderingAttributes();
        rotatingBoxRA.setRasterOpEnable( true );
        rotatingBoxRA.setCapability( staticBoxRA.ALLOW_RASTER_OP_WRITE );
//        rotatingBoxRA.setRasterOp( rotatingBoxRA.ROP_XOR );
        rotBoxApp.setRenderingAttributes( rotatingBoxRA );

				
		rotBox.setAppearance( rotBoxApp );
        }

        //adding static back face wireframe cube
        {
        Box staticWFBoxBack = new Box( );
        Appearance staticWFBoxApp = new Appearance();
        Material staticWFBoxMat = new Material();
        staticWFBoxMat.setDiffuseColor( 0.f, 0.f, 0.f );
        staticWFBoxMat.setEmissiveColor( 0.f, .4f, 0.f );
        staticWFBoxApp.setMaterial( staticWFBoxMat );
        PolygonAttributes staticWFBoxPABack = new PolygonAttributes( PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_FRONT, 0.0f );
        staticWFBoxApp.setPolygonAttributes( staticWFBoxPABack );
        staticWFBoxRA = new RenderingAttributes();
        staticWFBoxRA.setCapability( staticWFBoxRA.ALLOW_DEPTH_TEST_FUNCTION_WRITE );
        staticWFBoxRA.setCapability( staticWFBoxRA.ALLOW_DEPTH_ENABLE_WRITE );
        staticWFBoxRA.setDepthTestFunction( staticWFBoxRA.GREATER );
        staticWFBoxRA.setDepthBufferWriteEnable( false );
        staticWFBoxApp.setRenderingAttributes( staticWFBoxRA );
        staticWFBoxBack.setAppearance( staticWFBoxApp );
        staticWFBoxBack.setBounds( bounds );
        staticObjectOG.addChild( staticWFBoxBack );
        }

         //adding static front face wireframe cube
        {
        Box staticWFBox = new Box( );
        Appearance staticWFBoxApp = new Appearance();
        Material staticWFBoxMat = new Material();
        staticWFBoxMat.setDiffuseColor( 0.f, 0.f, 0.f );
        staticWFBoxMat.setEmissiveColor( 0.f, 1.f, 0.f );
        staticWFBoxApp.setMaterial( staticWFBoxMat );
        PolygonAttributes staticWFBoxPA = new PolygonAttributes( PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_BACK, 0.0f );
        staticWFBoxApp.setPolygonAttributes( staticWFBoxPA );
        staticWFBoxApp.setRenderingAttributes( staticWFBoxRA );
        staticWFBox.setAppearance( staticWFBoxApp );
        staticWFBox.setBounds( bounds );
        staticObjectOG.addChild( staticWFBox );
        }

       
        {// rotating the static cubes
        Transform3D boxt3d = new Transform3D();
        Transform3D tempt3d = new Transform3D();
         boxt3d.rotZ( Math.PI/4.0f );
        tempt3d.rotX( Math.PI/4.0f );
        boxt3d.mul( tempt3d );
        tempt3d.rotY( Math.PI/4.0f );
        boxt3d.mul( tempt3d );
        staticBoxRotTG.setTransform( boxt3d );
        }

        // adding static flat cube
        {
        Box staticBox = new Box( );
        staticBox.setBounds( bounds );
        Appearance boxApp = new Appearance();
        Material boxMat = new Material();
        boxMat.setDiffuseColor( .7f, .7f, .7f );
        boxApp.setMaterial( boxMat );
        staticBoxRA = new RenderingAttributes();
        staticBoxRA.setCapability( staticBoxRA.ALLOW_DEPTH_TEST_FUNCTION_WRITE );
        staticBoxRA.setCapability( staticBoxRA.ALLOW_DEPTH_ENABLE_WRITE );
        staticBoxRA.setDepthTestFunction( staticBoxRA.LESS );
        staticBoxRA.setDepthBufferWriteEnable( false );
        boxApp.setRenderingAttributes( staticBoxRA );
        staticBox.setAppearance( boxApp );
        staticObjectOG.addChild( staticBox );
       }
        oGroup.addChild( rotObjectBG );
        oGroup.addChild( staticBoxRotTG );
        
        //adding the mouse rotate behavior to the group of cubes.
        MouseRotate behavior = new MouseRotate();
        behavior.setTransformGroup( objectsTGRot );
        objectsTGRot.addChild( behavior );
        objectsTGRot.setCapability( objectsTGRot.ALLOW_TRANSFORM_READ );
        objectsTGRot.setCapability( objectsTGRot.ALLOW_TRANSFORM_WRITE );
        behavior.setSchedulingBounds(bounds);
        return globalBG;
    }
    
    public void setStaticWFObjectTestFunc( int func )
    {
        staticWFBoxRA.setDepthTestFunction( func );
    }
    
    public void setStaticObjectTestFunc( int func )
    {
        staticBoxRA.setDepthTestFunction( func );
    }
 
    public void setStaticWFObjectDBWriteStatus( boolean status )
    {
        staticWFBoxRA.setDepthBufferWriteEnable( status );
    }
    
    public void setStaticObjectDBWriteStatus( boolean status )
    {
        staticBoxRA.setDepthBufferWriteEnable( status );
    }
    
    public void setRotatingObjectROPMode( int mode )
    {
        rotatingBoxRA.setRasterOp( mode );
    }
		
}
