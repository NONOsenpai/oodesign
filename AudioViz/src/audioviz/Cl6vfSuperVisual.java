/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import static java.lang.Integer.min;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author air
 */
public class Cl6vfSuperVisual implements Visualizer {
    
    private final String name = "Cl6vf Super Visual";
    private Integer numBands;
    private AnchorPane vizPane;
    
    private final Double bandHeightPercentage = 1.3;
//    private final Double minEllipseRadius = 10.0;  // 10.0
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    private final Double startHue = 30.0;
    private Rectangle[] recs;
    
    private Rectangle[] recstay;
    
    Bloom bloom = new Bloom();
    
    public Cl6vfSuperVisual(){
    
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();
        
        this.numBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        recs = new Rectangle[numBands];
        recstay = new Rectangle[numBands];
        
        for (int i=0;i<numBands;i++){
            Rectangle rec = new Rectangle();
            rec.setX(bandWidth / 2 + bandWidth * i);
            rec.setY(height / 2);
            rec.setWidth(bandWidth / 1.5);
            rec.setHeight(8);
            rec.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            rec.setArcWidth(10);
            rec.setArcHeight(10);
            vizPane.getChildren().add(rec);
            recs[i] = rec;
        }
        for (int i=0;i<numBands;i++){
            Rectangle recnew = new Rectangle();
            recnew.setX(bandWidth / 2 + bandWidth * i);
            recnew.setY(height / 2 + 1);
            recnew.setWidth(bandWidth / 1.5);
            recnew.setHeight(8);
            recnew.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            recnew.setArcWidth(10);
            recnew.setArcHeight(10);
            vizPane.getChildren().add(recnew);
            recstay[i] = recnew;
        }
    }
    @Override
    public void end() {
     if (recs != null) {
             for (Rectangle rec : recs) {
                 vizPane.getChildren().remove(rec);
             }
            recs = null;
        }    
    }
    
    
    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
    if (recs == null) {return;}
        Integer num = min(recs.length, magnitudes.length);
        for (int i = 0; i < num; i++) {
            recs[i].setHeight( ((60.0 + magnitudes[i])/60.0) * halfBandHeight + 8);
            recs[i].setFill(Color.hsb(startHue - (magnitudes[i]/2.5), 1.0, 1.0, 1.0));
            recs[i].setEffect(bloom);
//            recs[i].setSmooth(true);
//            recs[i].setOpacity(i*0.5);

            recstay[i].setY(((110.0 + magnitudes[i])/60.0) * halfBandHeight + 10);
            recstay[i].setEffect(bloom);
            recstay[i].setOpacity(0.5*(magnitudes[i]/60) + 1.1);
//            recstay[i].setRotate(50);
            
//            recs[i].setArcWidth(magnitudes[i] * 14);
//            recs[i].setArcHeight(magnitudes[i] * 14);
        }
    }    
}

