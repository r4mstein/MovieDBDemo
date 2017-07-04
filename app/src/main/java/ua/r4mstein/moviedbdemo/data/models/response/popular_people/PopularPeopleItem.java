
package ua.r4mstein.moviedbdemo.data.models.response.popular_people;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularPeopleItem {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("id")
    private Long mId;
    @SerializedName("known_for")
    private List<KnownFor> mKnownFor;
    @SerializedName("name")
    private String mName;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("profile_path")
    private String mProfilePath;

    public Long getId() {
        return mId;
    }

    public List<KnownFor> getKnownFor() {
        return mKnownFor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

}
