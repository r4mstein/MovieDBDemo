
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

    public void setPage(Long page) {
        mPage = page;
    }

    public List<PopularPeopleItem> getItems() {
        return mItems;
    }

    public void setItems(List<PopularPeopleItem> items) {
        mItems = items;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
