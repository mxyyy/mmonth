package entity;

import java.util.List;


public class CategoryTwo {
    private String cid;
    private String name;
    private String pcid;
    private List<CategoryThree> list;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public List<CategoryThree> getList() {
        return list;
    }

    public void setList(List<CategoryThree> list) {
        this.list = list;
    }
}
