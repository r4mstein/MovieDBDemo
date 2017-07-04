
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("avatar")
    private Avatar mAvatar;
    @SerializedName("id")
    private Long mId;
    @SerializedName("include_adult")
    private Boolean mIncludeAdult;
    @SerializedName("iso_3166_1")
    private String mIso31661;
    @SerializedName("iso_639_1")
    private String mIso6391;
    @SerializedName("name")
    private String mName;
    @SerializedName("username")
    private String mUsername;


    public class Avatar {

        @SerializedName("gravatar")
        private Gravatar mGravatar;

        public Gravatar getGravatar() {
            return mGravatar;
        }

        public class Gravatar {

            @SerializedName("hash")
            private String mHash;

            public String getHash() {
                return mHash;
            }

        }
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

}
