package doridlens.analyse.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MaoMorn
 * Date: 2018/9/20
 * Time: 20:28
 * Description: 应用实体信息,包括应用的基本属性信息
 */
public class PaprikaApp extends Entity {

    // Neo4J attributes names
    public static final String N4J_APP_KEY = "app_key";
    public static final String N4J_NAME = "name";
    public static final String N4J_CATEGORY = "category";
    public static final String N4J_PACKAGE = "package";
    public static final String N4J_DEVELOPER = "developer";
    public static final String N4J_RATING = "rating";
    public static final String N4J_NB_DOWN = "nb_download";
    public static final String N4J_DATE_DOWN = "date_download";
    public static final String N4J_VERSION_CODE = "version_code";
    public static final String N4J_VERSION_NAME = "version_name";
    public static final String N4J_SDK = "sdk";
    public static final String N4J_TARGET_SDK = "target_sdk";
    public static final String N4J_DATE_ANALYSIS = "date_analysis";
    public static final String N4J_SIZE = "size";
    public static final String N4J_PRICE = "price";

    public static final int NO_SDK = -1;

    private double rating;
    private String date;
    private String pack; //Package
    private int size;
    private String developer;
    private String category;
    private String price;
    private String key;
    private int nbDownload;
    private String versionCode;
    private String versionName;
    private int sdkVersion;
    private int targetSdkVersion;
    private List<PaprikaClass> paprikaClasses;
    private List<PaprikaExternalClass> paprikaExternalClasses;

    protected PaprikaApp(String name, String key, String pack, String date, int size, String developer,
                         String category, String price, double rating, int nbDownload, String versionCode,
                         String versionName, int sdkVersion, int targetSdkVersion) {
        this.name = name;
        this.key = key;
        this.pack = pack;
        this.date = date;
        this.size = size;
        this.developer = developer;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.nbDownload = nbDownload;
        this.paprikaClasses = new ArrayList<>();
        this.paprikaExternalClasses = new ArrayList<>();
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.sdkVersion = sdkVersion;
        this.targetSdkVersion = targetSdkVersion;
    }


    public List<PaprikaExternalClass> getPaprikaExternalClasses() {
        return paprikaExternalClasses;
    }

    public List<PaprikaClass> getPaprikaClasses() {
        return paprikaClasses;
    }

    public void addPaprikaExternalClass(PaprikaExternalClass paprikaExternalClass) {
        paprikaExternalClasses.add(paprikaExternalClass);
    }

    public void addPaprikaClass(PaprikaClass paprikaClass) {
        paprikaClasses.add(paprikaClass);
    }

    public double getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public String getPackage() {
        return pack;
    }

    public int getSize() {
        return size;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getKey() {
        return key;
    }

    public int getNbDownload() {
        return nbDownload;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getSdkVersion() {
        return sdkVersion;
    }

    public int getTargetSdkVersion() {
        return targetSdkVersion;
    }
}