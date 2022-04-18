package data.resources;

import data.Color;
import data.Support;

import java.util.List;

public class ColorResource extends Resource {

    private List<Color> data;


    public ColorResource(Integer page, Integer per_page, Integer total, Integer total_pages, List<Color> data, Support support) {
        super(page,per_page,total,total_pages,support);
        this.data = data;
    }

    public ColorResource() {
        super();
    }

    public List<Color> getData() {
        return data;
    }

    public void setData(List<Color> data) {
        this.data = data;
    }
}
