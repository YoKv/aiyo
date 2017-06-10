package space.aiyo.steam.entity.match;

/**
 * 玩家控制的额外的单位信息,如德鲁伊小熊
 * Created by Yo on 2017/6/10.
 */
public class AdditionalUnitsEntity {
    /**
     * The name of the unit
     */
    private String unitname;
    /**
     * ID of the top-left inventory item.
     */
    private int item_0;
    /**
     * ID of the top-center inventory item.
     */
    private int item_1;
    /**
     * ID of the top-right inventory item.
     */
    private int item_2;
    /**
     * ID of the bottom-left inventory item.
     */
    private int item_3;
    /**
     * ID of the bottom-center inventory item.
     */
    private int item_4;
    /**
     * ID of the bottom-right inventory item.
     */
    private int item_5;

    public AdditionalUnitsEntity() {
    }

    @Override
    public String toString() {
        return "AdditionalUnitsEntity{" +
                "unitname='" + unitname + '\'' +
                ", item_0=" + item_0 +
                ", item_1=" + item_1 +
                ", item_2=" + item_2 +
                ", item_3=" + item_3 +
                ", item_4=" + item_4 +
                ", item_5=" + item_5 +
                '}';
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public int getItem_0() {
        return item_0;
    }

    public void setItem_0(int item_0) {
        this.item_0 = item_0;
    }

    public int getItem_1() {
        return item_1;
    }

    public void setItem_1(int item_1) {
        this.item_1 = item_1;
    }

    public int getItem_2() {
        return item_2;
    }

    public void setItem_2(int item_2) {
        this.item_2 = item_2;
    }

    public int getItem_3() {
        return item_3;
    }

    public void setItem_3(int item_3) {
        this.item_3 = item_3;
    }

    public int getItem_4() {
        return item_4;
    }

    public void setItem_4(int item_4) {
        this.item_4 = item_4;
    }

    public int getItem_5() {
        return item_5;
    }

    public void setItem_5(int item_5) {
        this.item_5 = item_5;
    }
}
