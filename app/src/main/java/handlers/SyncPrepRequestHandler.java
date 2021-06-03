package handlers;

import java.util.ArrayList;
import java.util.List;

public class SyncPrepRequestHandler {

    private  static boolean qualityMap;
    private static  boolean constrast;
    private static  boolean colormap;
    private static  boolean gaborfilter;
    private static  boolean binarization;
    private static  boolean skeleton;

    private static List<Boolean> checkBoxes = new ArrayList<>();

    public static synchronized List<Boolean> getCheckBoxes() {
        return checkBoxes;
    }

    public static synchronized void addCheckBoxes() {
        checkBoxes.add(0,qualityMap);
        checkBoxes.add(1,constrast);
        checkBoxes.add(2,colormap);
        checkBoxes.add(3,gaborfilter);
        checkBoxes.add(4,binarization);
        checkBoxes.add(5,skeleton);


    }

    public static synchronized void clearCheckboxes(){
        checkBoxes.clear();

        setSkeleton(false);
        setQualityMap(false);
        setGaborfilter(false);
        setConstrast(false);
        setColormap(false);
        setBinarization(false);
    }

    public static synchronized boolean isQualityMap() {
        return qualityMap;
    }

    public static synchronized void setQualityMap(boolean qualityMap) {
        SyncPrepRequestHandler.qualityMap = qualityMap;
    }

    public static synchronized boolean isConstrast() {
        return constrast;
    }

    public static synchronized void setConstrast(boolean constrast) {
        SyncPrepRequestHandler.constrast = constrast;
    }

    public static synchronized boolean isColormap() {
        return colormap;
    }

    public static synchronized void setColormap(boolean colormap) {
        SyncPrepRequestHandler.colormap = colormap;
    }

    public static synchronized boolean isGaborfilter() {
        return gaborfilter;
    }

    public static synchronized void setGaborfilter(boolean gaborfilter) {
        SyncPrepRequestHandler.gaborfilter = gaborfilter;
    }

    public static synchronized boolean isBinarization() {
        return binarization;
    }

    public static synchronized void setBinarization(boolean binarization) {
        SyncPrepRequestHandler.binarization = binarization;
    }

    public static synchronized boolean isSkeleton() {
        return skeleton;
    }

    public static synchronized void setSkeleton(boolean skeleton) {
        SyncPrepRequestHandler.skeleton = skeleton;
    }
}
