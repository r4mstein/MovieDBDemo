
package ua.r4mstein.moviedbdemo.data.models.response.popular_people;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularPeopleModel {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<PopularPeopleItem> mItems;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public List<PopularPeopleItem> getItems() {
        return mItems;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

}
