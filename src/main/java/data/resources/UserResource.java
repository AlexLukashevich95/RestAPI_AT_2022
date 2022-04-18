package data.resources;

import data.DataUser;
import data.Support;

import java.util.List;

public class UserResource extends Resource {
    private List<DataUser> data;


    public UserResource(Integer page, Integer per_page, Integer total, Integer total_pages, List<DataUser> data, Support support) {
        super(page,per_page,total,total_pages,support);
        this.data = data;
    }

    public UserResource() {
        super();
    }

    public List<DataUser> getData() {
        return data;
    }

    public void setData(List<DataUser> data) {
        this.data = data;
    }
}
