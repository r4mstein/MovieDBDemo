
package ua.r4mstein.moviedbdemo.data.models.response.movie_details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailsModel {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("belongs_to_collection")
    private Object mBelongsToCollection;
    @SerializedName("budget")
    private Long mBudget;
    @SerializedName("genres")
    private List<Genre> mGenres;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> mProductionCountries;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("revenue")
    private Long mRevenue;
    @SerializedName("runtime")
    private Long mRuntime;
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> mSpokenLanguages;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("tagline")
    private String mTagline;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public Long getBudget() {
        return mBudget;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public Long getId() {
        return mId;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return mProductionCountries;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public Long getRevenue() {
        return mRevenue;
    }

    public Long getRuntime() {
        return mRuntime;
    }

    public String getTagline() {
        return mTagline;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }
}
