
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListsModel {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public class Result {

        @SerializedName("description")
        private String mDescription;
        @SerializedName("favorite_count")
        private Long mFavoriteCount;
        @SerializedName("id")
        private Long mId;
        @SerializedName("iso_639_1")
        private String mIso6391;
        @SerializedName("item_count")
        private Long mItemCount;
        @SerializedName("list_type")
        private String mListType;
        @SerializedName("name")
        private String mName;
        @SerializedName("poster_path")
        private Object mPosterPath;

        public Long getId() {
            return mId;
        }

        public Long getItemCount() {
            return mItemCount;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

    }
}
