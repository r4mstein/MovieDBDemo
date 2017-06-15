
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

    public void setPage(Long page) {
        mPage = page;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
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

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public Long getFavoriteCount() {
            return mFavoriteCount;
        }

        public void setFavoriteCount(Long favoriteCount) {
            mFavoriteCount = favoriteCount;
        }

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
            mId = id;
        }

        public String getIso6391() {
            return mIso6391;
        }

        public void setIso6391(String iso6391) {
            mIso6391 = iso6391;
        }

        public Long getItemCount() {
            return mItemCount;
        }

        public void setItemCount(Long itemCount) {
            mItemCount = itemCount;
        }

        public String getListType() {
            return mListType;
        }

        public void setListType(String listType) {
            mListType = listType;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public Object getPosterPath() {
            return mPosterPath;
        }

        public void setPosterPath(Object posterPath) {
            mPosterPath = posterPath;
        }

    }
}
